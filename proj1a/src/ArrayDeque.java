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
        front = (front - 1) % items.length;
        size++;
        items[front] = t;
    }

    public void addLast(T t){
        if(size == items.length){
            T[] a = (T[]) new Object[size * 2];
            System.arraycopy(items,0,a,0,size);
            items = a;
        }
        size++;
        items[size] = t;
    }

    public T removeFirst(){
        T t = items[front];
        front = (front + 1) % items.length;
        size--;
        return t;
    }

    public T removeLast(){
        T t = items[size-1];
        size--;
        return t;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public T getItem(int i){
        int index = (front + i) % items.length;
        return items[index];
    }

}