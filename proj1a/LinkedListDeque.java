public class LinkedListDeque<T> {
    /** Invariants :
     * sentinel.next=first node
     * sentinel.prev=last node
     */
    /** Nested class Node. */
    public class Node {
        public T item;
        public Node next;
        public Node prev;
        public Node(T i, Node nnext, Node pprev) {
            item=i;
            next=nnext;
            prev=pprev;
        }
        /** Constructor for the sentinel. Can't put something arbitrary as the item because the T. */
        public Node(Node nnext, Node pprev) {
            next=nnext;
            prev=pprev;
        }
    }

    public Node sentinel;
    public int size=0;

    public LinkedListDeque() {
        sentinel=new Node(null,null);
        sentinel.next=sentinel;
        sentinel.prev=sentinel;
    }

    public void addFirst(T item) {
        Node newNode=new Node(item, sentinel.next,sentinel);
        sentinel.next.prev=newNode;
        sentinel.next=newNode;
        size+=1;
    }

    public void addLast(T item) {
        Node newNode=new Node(item,sentinel,sentinel.prev);
        sentinel.prev.next=newNode;
        sentinel.prev=newNode;
        size+=1;
    }

    public boolean isEmpty() {
        if(sentinel.next==sentinel) {
            return true;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Node p=sentinel.next;
        for(;p.next!=sentinel;p=p.next) {
            System.out.print(p.item);
            System.out.print(' ');
        }
    }

    public T removeFirst() {
        if (size==0) {
            return null;
        }
        T rmitem=sentinel.next.item;
        sentinel.next.next.prev=sentinel;
        sentinel.next=sentinel.next.next;
        size-=1;
        return rmitem;
    }

    public T removeLast() {
        if(size==0) {
            return null;
        }
        T rmitem=sentinel.prev.item;
        sentinel.prev.prev.next=sentinel;
        sentinel.prev=sentinel.prev.prev;
        size-=1;
        return rmitem;
    }

    public T get(int index) {
        if(index>=size) {
            return null;
        }
        Node p=sentinel;
        for(int i=0;i<=index;++i) {
            p=p.next;
        }
        return p.item;
    }

    public T GetRecursiveHelper(int count,Node p) {
        if(count==0) {
            return p.item;
        }
        return GetRecursiveHelper(count-1,p.next);
    }
    public T GetRecursive(int index) {
        if(index>=size) {
            return null;
        }
        return GetRecursiveHelper(index,sentinel);
    }

    public static void main(String[] args) {
        LinkedListDeque<Integer> l=new LinkedListDeque();
        l.addFirst(6);
        l.addFirst(7);
        l.addLast(8);
    }

}
