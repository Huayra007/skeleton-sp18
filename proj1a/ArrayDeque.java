public class ArrayDeque<T>{
    private int size;
    private T[] items;
    private int front;

    public ArrayDeque(){
        front = 0;
        size = 0;
        items = (T []) new Object[100];
    }

    public void addFirst(T t){
        if (size == items.length){
            T[] a = (T[]) new Object[size * 2];
            System.arraycopy(items,0,a,0,size);
            items = a;
        }
        if (size > 0)
            front--;

        int ind = front < 0 ? front + items.length : front;
        items[ind] = t;
        size++;
    }

    public void addLast(T t){
        if(size == items.length){
            T[] a = (T[]) new Object[size * 2];
            System.arraycopy(items,0,a,0,size);
            items = a;
        }
        items[size + front] = t;
        size++;
    }

    public T removeFirst(){
        if (size == 0)
            return null;

        int ind = getFirstIndex();
        T t = items[ind];
        front++;
        size--;
        return t;
    }

    public T removeLast(){
        if (size == 0)
            return null;
        T t = items[size + front - 1];
        size--;
        return t;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public T getItem(int i){
        int ind = getFirstIndex();
        return items[(ind + i) % items.length];
    }

    public int size(){
        return size;
    }

    public int getFirstIndex(){
        return front < 0 ? front + items.length : front;
    }

}