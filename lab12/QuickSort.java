import edu.princeton.cs.algs4.Queue;

public class QuickSort {
    /**
     * Returns a new queue that contains the given queues catenated together.
     *
     * The items in q2 will be catenated after all of the items in q1.
     */
    private static <Item extends Comparable> Queue<Item> catenate(Queue<Item> q1, Queue<Item> q2) {
        Queue<Item> catenated = new Queue<Item>();
        for (Item item : q1) {
            catenated.enqueue(item);
        }
        for (Item item: q2) {
            catenated.enqueue(item);
        }
        return catenated;
    }

    /** Returns a random item from the given queue. */
    private static <Item extends Comparable> Item getRandomItem(Queue<Item> items) {
        int pivotIndex = (int) (Math.random() * items.size());
        Item pivot = null;
        // Walk through the queue to find the item at the given index.
        for (Item item : items) {
            if (pivotIndex == 0) {
                pivot = item;
                break;
            }
            pivotIndex--;
        }
        return pivot;
    }

    /**
     * Partitions the given unsorted queue by pivoting on the given item.
     *
     * @param unsorted  A Queue of unsorted items
     * @param pivot     The item to pivot on
     * @param less      An empty Queue. When the function completes, this queue will contain
     *                  all of the items in unsorted that are less than the given pivot.
     * @param equal     An empty Queue. When the function completes, this queue will contain
     *                  all of the items in unsorted that are equal to the given pivot.
     * @param greater   An empty Queue. When the function completes, this queue will contain
     *                  all of the items in unsorted that are greater than the given pivot.
     */
    private static <Item extends Comparable> void partition(
            Queue<Item> unsorted, Item pivot,
            Queue<Item> less, Queue<Item> equal, Queue<Item> greater) {
        // Your code here!
        for(Item i:unsorted) {
            if(i.compareTo(pivot)<0) less.enqueue(i);
            else if(i.compareTo(pivot)==0) equal.enqueue(i);
            else greater.enqueue(i);
        }
        return;
    }

    /** Returns a Queue that contains the given items sorted from least to greatest. */
    public static <Item extends Comparable> Queue<Item> quickSort(
            Queue<Item> items) {
        // Your code here!
        if(items==null) return null;
        if(items.isEmpty()) return items;
        if(items.size()==1) return items;
        Item pivot=getRandomItem(items);
        Queue<Item> less=new Queue<Item>();
        Queue<Item> equal=new Queue<Item>();
        Queue<Item> greater=new Queue<Item>();
        partition(items,pivot,less,equal,greater);
        less=quickSort(less);
        greater=quickSort(greater);
        return catenate(less,catenate(equal,greater));
    }

    public static void main(String[] args) {
        Queue<Integer> intQue=new Queue<Integer>();
        intQue.enqueue(3);
        intQue.enqueue(9);
        intQue.enqueue(5);
        intQue.enqueue(4);
        Queue<Integer> intQue_sorted=QuickSort.quickSort(intQue);
        System.out.print("Origin que: ");
        MergeSort.pirntQue(intQue);
        System.out.print('\n');
        System.out.print("Sorted que: ");
        MergeSort.pirntQue(intQue_sorted);
        System.out.print('\n');

        Queue<String> q = new Queue<String>();
        q.enqueue("Cat");
        q.enqueue("Pig");
        q.enqueue("Ant");
        q.enqueue("Hello");
        q.enqueue("Orange");
        Queue<String> sortedQueue = QuickSort.quickSort(q);
        System.out.println(q);
        System.out.println(sortedQueue);
    }
}
