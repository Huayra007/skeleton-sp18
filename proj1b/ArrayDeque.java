public class ArrayDeque<T> implements Deque<T>{
    private int size;
    private T[] items;
    private int front;

    public ArrayDeque(){
        front = 0;
        size = 0;
        items = (T []) new Object[100];
    }

    @Override
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

    @Override
    public void addLast(T t){
        if(size == items.length){
            T[] a = (T[]) new Object[size * 2];
            System.arraycopy(items,0,a,0,size);
            items = a;
        }
        items[size + front] = t;
        size++;
    }

    @Override
    public T removeFirst(){
        if (size == 0)
            return null;

        int ind = getFirstIndex();
        T t = items[ind];
        front++;
        size--;
        return t;
    }

    @Override
    public T removeLast(){
        if (size == 0)
            return null;
        T t = items[size + front - 1];
        size--;
        return t;
    }

    @Override
    public boolean isEmpty(){
        return size == 0;
    }

    @Override
    public T getItem(int i){
        int ind = getFirstIndex();
        return items[(ind + i) % items.length];
    }

    @Override
    public int size(){
        return size;
    }

    @Override
    public void printDeque(){
        for (int i = 0; i < size; i++){
            int ind = (getFirstIndex() + i) % items.length;
            System.out.print(items[ind] + " ");
        }
        System.out.println();
    }

    public int getFirstIndex(){
        return front < 0 ? front + items.length : front;
    }

}