package datastructure;

/**
 * Project Name: AlgoDsProject
 * Created By: Jin LI
 * Email:jin.li@vub.ac.be  homtingli@gmail.com
 * By IntelliJ IDEA 2016.3
 * Date: 30/November/2016 Time: 04:06
 *
 * @param <K> the type parameter
 * @param <V> the type parameter
 */
public class DictionaryTree<K extends Comparable,V> {
    private class DictionaryPair implements Comparable<DictionaryPair>{
        private K key;
        private V value;

        /**
         * Instantiates a new Dictionary pair.
         *
         * @param someKey   the some key
         * @param someValue the some value
         */
        public DictionaryPair (K someKey , V someValue) {
            this.key = someKey;
            this.value = someValue;
        }

        /**
         * Gets key.
         *
         * @return the key
         */
        public K getKey() {
            return this.key;
        }

        /**
         * Gets value.
         *
         * @return the value
         */
        public V getValue() {
            return this.value;
        }

        /**
         * Sets key.
         *
         * @param newKey the new key
         */
        public void setKey(K newKey) {
            this.key = newKey;
        }

        /**
         * Sets value.
         *
         * @param newValue the new value
         */
        public void setValue(V newValue) {
            this.value = newValue;
        }

        @Override
        public int compareTo(DictionaryPair o) {
            return key.compareTo(o.getKey());
        }
    }

    private TreeBST<DictionaryPair> dict;

    /**
     * Instantiates a new Dictionary tree.
     */
    public DictionaryTree() {
        dict = new TreeBST<>();
    }

    /**
     * Add.
     *
     * @param key   the key
     * @param value the value
     */
    public void add(K key,V value) {
        DictionaryPair pair = new DictionaryPair(key, value);
        dict.insert(pair);
    }

    /**
     * Remove.
     *
     * @param key the key
     */
    public void remove(K key) {
        dict.remove(new DictionaryPair(key,null));
    }

    /**
     * Print the tree.
     * In order.
     */
    public void print() {
        dict.printStack(new TreeAction<DictionaryPair>() {
            @Override
            public void run(TreeBST<DictionaryPair>.TreeNode n) {
                if (n!=null) {
                    DictionaryPair pair = n.getValue();
                    System.out.print("(" + pair.getKey() + "," + pair.getValue() + ")");
                }
            }
        });
        System.out.println();
    }

    /**
     * Contain2 boolean.
     *
     * @param keys the keys
     * @return the boolean
     */
    public boolean contain22(K keys) {
        final boolean result[] = {false};
        dict.printStack(new TreeAction<DictionaryPair>() {
            @Override
            public void run(TreeBST<DictionaryPair>.TreeNode n) {
                DictionaryPair pair = n.getValue();
                if (keys.equals(pair.getKey())) {
                    result[0] = true;
                    //System.out.print("("+pair.getKey()+ ","+pair.getValue()+")");
                }
            }
        });
        return result[0];
    }

    /**
     * Contain boolean.
     *
     * @param keys the keys
     * @return the boolean
     */
    public boolean contain(K keys) {
        return !(dict.find(new DictionaryPair(keys,null))==null);
    }

    /**
     * Find value v.
     *
     * @param key the key
     * @return the v
     */
    public V findValue(K key) {
        DictionaryPair pair = new DictionaryPair(key,null);
        pair = dict.find(pair);
        if (pair!=null) {
            return pair.getValue();
        }
        return null;
    }

    @Override
    public String toString() {
        String s = "DictionaryTree: {";
        LinkedList<DictionaryPair> l = dict.getAllTreeNode();
        for (int i=0;i<l.size();i++) {
            DictionaryPair pair = l.get(i);
            String temp = "("+pair.getKey()+","+pair.getValue()+")";
            s += temp;
        }
        s += "}\n";
        return s;
    }
}
