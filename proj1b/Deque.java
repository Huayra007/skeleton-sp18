public interface Deque<T> {
    public void addFirst(T t);
    public void addLast(T t);
    public T removeFirst();
    public T removeLast();
    public boolean isEmpty();
    public int size();
    public T getItem(int i);
    public void printDeque();
}
