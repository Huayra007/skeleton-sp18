public class LinkedListDeque<T> implements Deque<T>{
    private Node sentinel;
    private int size;

    public class Node{
        public T item;
        public Node next;
        public Node prev;

        public Node(){}
        public Node(T n){
            item = n;
            next = prev = null;
        }
    }

    /** Constructor */
    public LinkedListDeque(){
        sentinel = new Node();
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    /** Constructor */
    public LinkedListDeque(T item){
        Node cur = new Node(item);
        sentinel.next = cur;
        sentinel.prev = cur;
        cur.prev = sentinel;
        cur.next = sentinel;

    }

    @Override
    /** Add a member in front */
    public void addFirst(T item){
        size++;
        Node cur = new Node(item);
        cur.next = sentinel.next;
        sentinel.next.prev = cur;
        sentinel.next = cur;
        cur.prev = sentinel;
    }

    @Override
    /** Add a member in back */
    public void addLast(T item){
        size++;
        Node cur = new Node(item);
        cur.prev = sentinel.prev;
        sentinel.prev.next = cur;
        cur.next = sentinel;
        sentinel.prev = cur;
    }

    @Override
    /** Return whether the list is empty */
    public boolean isEmpty(){
        return size == 0;
    }

    @Override
    /** Return the size of list */
    public int size(){
        return size;
    }

    @Override
    /** Print all members */
    public void printDeque(){
        Node cur = sentinel.next;
        while(cur != sentinel) {
            System.out.print(cur.item + " ");
            cur = cur.next;
        }
        System.out.println();
    }

    @Override
    /** Remove the first member */
    public T removeFirst(){
        Node cur = sentinel.next;
        sentinel.next = cur.next;
        cur.next.prev = sentinel;
        size--;
        return cur.item;
    }

    @Override
    /** Remove the last member */
    public T removeLast(){
        Node cur = sentinel.prev;
        sentinel.prev = cur.prev;
        cur.prev.next = sentinel;
        size--;
        return cur.item;
    }

    @Override
    /** Return the value of certain index */
    public T getItem(int index){
        Node cur = sentinel.next;
        for(int i = 0; i < index; i++)
            cur = cur.next;
        return cur.item;
    }

}
