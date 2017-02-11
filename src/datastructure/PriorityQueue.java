package datastructure;

/**
 * Project Name: AlgoDsProject
 * Created By: Jin LI
 * Email:jin.li@vub.ac.be  homtingli@gmail.com
 * By IntelliJ IDEA 15
 * Date: 05/November/2016 Time: 12:18
 */
public class PriorityQueue<K,V extends Comparable<V>> implements Comparable {

    /**
     * The type Pair.
     */
    private class Pair implements Comparable<Pair> {
        /**
         * The Element.
         */
        private K element;
        /**
         * The Priority.
         */
        private V priority;

        /**
         * Instantiates a new Pair.
         *
         * @param element  the element
         * @param priority the priority
         */
        Pair(K element, V priority) {
            this.element = element;
            this.priority = priority;
        }

        /**
         * Compare two pairs.
         *
         * @param p the p
         * @return the int
         */
        @Override
        public int compareTo(Pair p) {
            return (priority).compareTo(p.priority);
        }
    }

    /**
     * The List.
     */
    private LinkedList<Pair> list;

    /**
     * Instantiates a new Priority queue.
     */
    public PriorityQueue() {
        list = new LinkedList<>();
    }

    /**
     * Push.
     *
     * @param o        the o
     * @param priority the priority
     */
    public void push(K o, V priority) {
        Pair p = new Pair(o,priority);
        list.addSorted(p);
    }

    /**
     * Pop object.
     *
     * @return the object
     */
    public K pop()
    {
        if (isEmpty()) {
            return null;
        }
        Pair p = list.getFirst();
        list.removeFirst();
        return p.element;
    }

    /**
     * Get object.
     *
     * @param i the
     * @return the object
     */
    public K get(int i) {
        if (list.get(i) ==null) {
            return null;
        }

        Pair p = list.get(i);
        return p.element;
    }

    /**
     * Gets priority.
     *
     * @param i the
     * @return the priority
     */
    public V getPriority(int i) {
        if (list.get(i)==null) {
            return null;
        }
        Pair p = list.get(i);
        return p.priority;
    }

    /**
     * Top object.
     *
     * @return the object
     */
    public K top() {
        if (isEmpty()) {
            return null;
        }
        Pair p = list.getFirst();
        return p.element;
    }

    /**
     * Top priority object.
     *
     * @return the object
     */
    public V topPriority(){
        if (isEmpty()) {
            return null;
        }
        Pair p = list.getFirst();
        return p.priority;
    }

    /**
     * Size int.
     *
     * @return the int
     */
    public int size () {
        return list.size();
    }

    /**
     * Empty boolean.
     *
     * @return the boolean
     */
    public boolean isEmpty() {
        return size()==0;
    }

    @Override
    public String toString() {
        String s = "PriorityQueue: {";
        if (!list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                Pair pair = list.get(i);
                s += "(Key: " + pair.element + ", Value: " + pair.priority + ")";
                s += ", ";
            }
        }
        s += "}";
        return s;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}

