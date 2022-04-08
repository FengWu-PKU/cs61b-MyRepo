public class ArrayDeque<T> implements Deque<T>{
    private T[] items;
    private int size;  // The actual size of the real items.
    private int arrayLen; // The length of the array.
    private static final int grow_factor=2;
    private static final double shrink_threshold=1/4;
    private static final double shrink_factor=1/2;
    /** Treat the arrayDeque as circle. */
    private int frontIdx;// The index of the circle's front.
    private  int lastIdx;// The index of the circle's last.
    /** Constructor */
    public ArrayDeque() {
        items=(T[]) new Object[8];
        arrayLen=8;
        size=0;
        frontIdx=0;
        lastIdx=0;
    }
    /** Add one to the index. */
    private int addOne(int index) {
        return (index+1)%arrayLen;
    }
    /** Minus one to the index. */
    private int minusOne(int index) {
        if(index==0) {
            return arrayLen-1;
        }
        return index-1;
    }
    /** Create an new array and Copy. */
    private void create_and_copy(int new_arrayLen) {
        int new_frontIdx=new_arrayLen-(arrayLen-frontIdx);
        int new_lastIdx=lastIdx;
        T[] newItems=(T[]) new Object[new_arrayLen];
        System.arraycopy(items,0,newItems,0,lastIdx+1);
        System.arraycopy(items,frontIdx,newItems,new_frontIdx,(arrayLen-frontIdx));
        items=newItems;
        arrayLen=new_arrayLen;
        frontIdx=new_frontIdx;
        lastIdx=new_lastIdx;
    }
    /** Grow by mul grow_factor when the current ArrayDeque is too small to hold all the items. */
    private void grow() {
        int new_arrayLen=arrayLen*grow_factor;
        create_and_copy(new_arrayLen);
    }
    /** Once the size(actual) is smaller than threshold(1/3) items.length, the ArrayDeque(items) shrinks shrink_factor(1/2) . */
    private void shrink() {
        int new_arrayLen=(int) (arrayLen*shrink_factor);
        create_and_copy(new_arrayLen);
    }

    /** Returns true if the current array is full. */
    public boolean isFull() {
        return size==arrayLen;
    }
    /** Returns true if the current array is too large unnecessarily. */
    private boolean isTooLarge() {
        int threshold_size=(int) (arrayLen*shrink_threshold);
        return size<=threshold_size;
    }
    /** Adds one item to the first. */
    @Override
    public void addFirst(T item) {
        if(isFull()) {
            grow();
        }
        if(!isEmpty())  // When the deque is empty, just put the first item in the frontIdx place.
            frontIdx=minusOne(frontIdx);
        items[frontIdx]=item;
        size++;
    }
    /** Adds one item to the last. */
    @Override
    public void addLast(T item) {
        if(isFull()) {
            grow();
        }
        if(!isEmpty())
            lastIdx=addOne(lastIdx);
        items[lastIdx]=item;
        size++;
    }
    /** Returns true if deque is empty. */
    @Override
    public boolean isEmpty() {
        return size==0;
    }
    /** Returns the number of items in the deque. */
    @Override
    public int size() {
        return size;
    }
    /** Prints the items in the deque from first to last, separated by a space. */
    @Override
    public void printDeque() {
        int tmp_frontIdx=frontIdx;
        for(int i=0;i<size-1;i++) {
            System.out.print(items[tmp_frontIdx]);
            System.out.print(' ');
            tmp_frontIdx=addOne(tmp_frontIdx);
        }
        System.out.print(items[tmp_frontIdx]); // There's no space after the last items.
    }
    /** Removes and returns the item at the front of the deque. If no such item exists, returns null. */
    @Override
    public T removeFirst() {
        if(isEmpty()) {
            return null;
        }
        if(isTooLarge())
            shrink();
        T rm=items[frontIdx];
        items[frontIdx]=null;
        frontIdx=addOne(frontIdx);
        size--;
        return rm;
    }
    /** Removes and returns the item at the back of the deque. If no such item exists, returns null. */
    @Override
    public T removeLast() {
        if(isEmpty()) {
            return null;
        }
        if(isTooLarge())
            shrink();
        T rm=items[lastIdx];
        items[lastIdx]=null;
        lastIdx=minusOne(lastIdx);
        size--;
        return rm;
    }
    /** Gets the item at the given index (user's perspective), where 0 is the front, 1 is the next item, and so forth. If no such item exists, returns null. Must not alter the deque! */
    @Override
    public T get(int index) {
        if(index<0 || index>size-1) {// No such item exits.
            return null;
        }
        int trueIdx=frontIdx;
        for(int i=0;i<index;i++) {
            trueIdx=addOne(trueIdx);
        }
        return items[trueIdx];
    }

    public static void main(String[] args) {
        ArrayDeque<Integer> myDeque=new ArrayDeque();
        System.out.print(myDeque.isEmpty());
        myDeque.addFirst(1);
        myDeque.addFirst(2);
        myDeque.addLast(3);
        for(int i=0;i<7;i++) {
            myDeque.addFirst(i+4);
        }
        System.out.println(myDeque.removeFirst());
        System.out.println(myDeque.size());
        myDeque.addLast(4);
        myDeque.removeFirst();
        myDeque.removeFirst();
    }
}
