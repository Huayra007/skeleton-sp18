package lab9;

import java.util.*;

/**
 * Implementation of interface Map61B with BST as core data structure.
 *
 * @author Your name here
 */
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class Node {
        /* (K, V) pair stored in this Node. */
        private K key;
        private V value;

        /* Children of this Node. */
        private Node left;
        private Node right;

        private Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    private Node root;  /* Root node of the tree. */
    private int size; /* The number of key-value pairs in the tree */

    /* Creates an empty BSTMap. */
    public BSTMap() {
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /** Returns the value mapped to by KEY in the subtree rooted in P.
     *  or null if this map contains no mapping for the key.
     */
    private V getHelper(K key, Node p) {
        if (key == null)
            throw new IllegalArgumentException("Null key not allowed");
        if (p == null)
            return null;

        Node cur = p;
        int cmp = key.compareTo(cur.key);
        if (cmp == 0) return cur.value;
        else if(cmp < 0) return getHelper(key,cur.left);
        else return getHelper(key,cur.right);
    }

    /** Returns the value to which the specified key is mapped, or null if this
     *  map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        return getHelper(key,root);
    }

    /** Returns a BSTMap rooted in p with (KEY, VALUE) added as a key-value mapping.
      * Or if p is null, it returns a one node BSTMap containing (KEY, VALUE).
     */
    private Node putHelper(K key, V value, Node p) {
        if (p == null) return new Node(key,value);
        int cmp = key.compareTo(p.key);
        if (cmp == 0) p.value = value;
        else if (cmp < 0) p.left = putHelper(key,value,p.left);
        else p.right = putHelper(key,value,p.right);

        return p;
    }

    /** Inserts the key KEY
     *  If it is already present, updates value to be VALUE.
     */
    @Override
    public void put(K key, V value) {

        if (key == null)
            throw new IllegalArgumentException("Null key not allowed");
        if (value == null)
            throw new IllegalArgumentException("Null value not allowed");

        root = putHelper(key,value,root);
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size(root);
    }

    private int size(Node p){
        if (p == null)
            return 0;
        return 1 + size(p.left) + size(p.right);
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        Set<K> set = new HashSet<>();
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            Node cur = queue.poll();
            if (cur == null)
                continue;
            queue.offer(cur.left);
            queue.offer(cur.right);
            set.add(cur.key);
        }
        return set;
    }

    /** Removes KEY from the tree if present
     *  returns VALUE removed,
     *  null on failed removal.
     */
    @Override
    public V remove(K key) {
        if (key == null) throw new IllegalArgumentException("Null key not allowed");
        return remove(key,root).value;
    }

    private Node remove(K key, Node p) {
        if (p == null) return null;

        int cmp = key.compareTo(p.key);
        if (cmp < 0) p.left = remove(key,p.left);
        else if (cmp > 0) p.right = remove(key,p.right);
        else {
            if (p.right == null) return p.left;
            if (p.left == null) return p.right;
            Node t = p;
            p = min(p.right);
            p.right = deleteMin(t.right);
            p.left = t.left;
        }

        return p;
    }

    /** Removes the key-value entry for the specified key only if it is
     *  currently mapped to the specified value.  Returns the VALUE removed,
     *  null on failed removal.
     **/
    @Override
    public V remove(K key, V value) {
        if (get(key) == value)
            return remove(key);
        else return null;
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }

    public Node max(Node p){
        if (p.right == null) return null;
        return max(p.right);
    }

    public Node min(Node p){
        if (p.left == null) return null;
        return min(p.left);
    }

    public Node deleteMin(Node p){
        if (p.left == null) return p.right;
        else
            p.left = deleteMin(p.left);
        return p;
    }

    public Node deleteMax(Node p){
        if (p.right == null) return p.left;
        else
            p.right = deleteMin(p.right);
        return p;
    }
}
