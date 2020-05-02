import java.util.Set;
public class BSTMap <K extends Comparable<K>, V > implements Map61B<K, V>  {

    private class Node {
        private K key;
        private V value;
        private Node left;
        private Node right;

        public Node(K k, V v) {
            this.key = k;
            this.value = v;
            this.left = null;
            this.right = null;
        }

    }

    private Node root;
    private int size;

    public BSTMap() {
        this.root = null;
        this.size = 0;

    }

    /** Removes all of the mappings from this map. */
   @Override
    public void clear() {
        this.root = null;
        this.size = 0;
    }

    /* Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        return get(root, key);
    }

    private V get(Node n, K key) {
        if (n == null) {
            return null;
        }
         // compare keys
         int integer = key.compareTo(n.key);
         //if k==0, we found the key we're looking for, so we just return the value
         if (integer == 0)   {
             return n.value;
         } else if (integer < 0 ) {
             return get(n.left, key);
         } else {
             return get(n.right, key);
         }
    }


    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return this.size;
    }

    /* Associates the specified value with the specified key in this map. */
    public void put(K key, V value) {
        put(root, key, value);
        }

    private Node put(Node n, K key, V value) {
        if (root == null) {
            this.root = new Node(key, value);
            this.size ++;
        } else {
            if (n == null) {
                n = new Node(key, value);
                this.size++;
            }

            int integer = key.compareTo(n.key);
            if (integer == 0) {
                n.value = value;
            } else if (integer > 0) {
                n.right = put(n.right, key, value);
            } else {
                n.left = put(n.right, key, value);
            }
        }

        return n;


    }

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    /* Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException. */
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
}

    public void printInOrder() {
        if (root == null) {
            System.out.println("None");
        } else {
            printInOrder(root);
        }
    }

    private void printInOrder(Node n) {
        if (n.left == null && n.right == null) {
            System.out.println(n.key);
        } else if (n.left != null && n.right == null) {
            printInOrder(n.left);
            System.out.println(n.key);
        } else if (n.right !=null && n.left == null) {
            printInOrder(n.right);
            System.out.println(n.key);
        } else {
            printInOrder(n.left);
            System.out.println(n.key);
            printInOrder(n.right);
        }
        }


}
