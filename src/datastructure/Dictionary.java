package datastructure;
/**
 * Project Name: AlgoDsProject
 * Created By: Jin LI
 * Email:jin.li@vub.ac.be  homtingli@gmail.com
 * By IntelliJ IDEA 15
 * Date: 06/November/2016 Time: 16:58
 *
 * @param <K> the type parameter key
 * @param <V> the type parameter value
 */
public class Dictionary<K extends Comparable<K>,V> {
    /**
     * The type Dictionary pair.
     * Pair includes key and value.
     */
    private class DictionaryPair implements Comparable<DictionaryPair>{
        /**
         * The Key.
         */
        private K key;
        /**
         * The Value.
         */
        private V value;

        /**
         * Instantiates a new Dictionary pair.
         *
         * @param someKey   the key
         * @param someValue the value
         */
        DictionaryPair (K someKey , V someValue) {
            this.key = someKey;
            this.value = someValue;
        }

        /**
         * Gets key.
         *
         * @return the key
         */
        K getKey() {
            return this.key;
        }

        /**
         * Gets value.
         *
         * @return the value
         */
        V getValue() {
            return this.value;
        }

        /**
         * Sets key.
         * Actually, we don't use this function.
         *
         * @param newKey the new key
         */
        public void setKey(K newKey) {
            this.key = newKey;
        }

        /**
         * Sets new value via key.
         *
         * @param newValue the new value
         */
        public void setValue(V newValue) {
            this.value = newValue;
        }

        /**
         * Compare two keys same or not.
         *
         * @param o the o
         * @return the int
         */
        @Override
        public int compareTo(DictionaryPair o) {
            return this.key.compareTo(o.key);
        }
    }

    /**
     * The List to store Dictionary pair.
     */
    private DoubleLinkedList<DictionaryPair> list;
    /**
     * The Count of the pair in the dictionay.
     */
    private int count;

    /**
     * Instantiates a new Dictionary.
     * We use list to store Dictionary pair.
     */
    public Dictionary () {
        list = new DoubleLinkedList<>();
        this.count = 0;
    }

    /**
     * Add key and value to the dictionary pair.
     * It the key exits, update the value.
     *
     * @param key   the key
     * @param value the value
     */
    public void add(K key,V value) {
        DictionaryPair pair = new DictionaryPair(key,value);
        int index = findPosition(key);

        if (index!=DsConst.NOT_FOUND) {
            list.set(index,pair);
        } else {
            list.addLast(pair);
            this.count++;
        }
    }

    /**
     * Find key position.
     *
     * @param key the key
     * @return the pair's index in the list.
     */
    public int findPosition(K key) {
        for (int i=0;i<this.count;i++) {
            DictionaryPair pair = list.get(i);
            if (pair.getKey().equals(key)) {
                return i;
            }
        }
        return DsConst.NOT_FOUND;
    }

    /**
     * Sets value.
     * Add key and value to the dictionary pair.
     * It the key exits, update the value.
     *
     * @param key   the key
     * @param value the value
     */
    public void setValue(K key, V value) {
        this.add(key, value);
    }

    /**
     * Gets value.
     * Input a key, and return the corresponding value.
     * If the key doesn't exist, return null
     *
     * @param key the key
     * @return the value
     */
    public V getValue(K key) {
        int i = findPosition(key);
        if (i==DsConst.NOT_FOUND) {
            return null;
        }
        DictionaryPair p = list.get(i);
        return p.getValue();
    }

    /**
     * Keys vector.
     * Add all key.
     *
     * @return the vector of Keys.
     */
    public Vector keys() {
        Vector<K> temp = new Vector<>(3);
        for (int i=0;i<this.count;i++) {
            DictionaryPair p = list.get(i);//(DictionaryPair) data.get(i);
            temp.addLast(p.getKey());
        }
        return temp;
    }

    /**
     * To string string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        String s = "Dictionary: ";
        for (int i=0;i<count;i++) {
            DictionaryPair p = list.get(i);
            s += "(" + p.getKey() + ", " + p.getValue() + ")";
            if (i!=count-1)
                s +=", ";
        }
        return s;
    }
}
