package datastructure;

/**
 * Project Name: DSAlgoQueue
 * Created By: Jin LI
 * Email:jin.li@vub.ac.be  homtingli@gmail.com
 * By IntelliJ IDEA 15
 * Date: 31/October/2016 Time: 13:20
 * This class provide basic Queue operation.
 */
public class Queue<T extends Comparable<T>> implements Comparable {
    private LinkedList<T> list;

    /**
     * Instantiates a new Queue.
     */
    public Queue () {
        list = new LinkedList<>();
    }

    /**
     * Push.
     *
     * @param o the o
     */
    public void push(T o) {
        list.addFirst(o);
    }

    /**
     * Pop object.
     *
     * @return the object
     */
    public T pop() {
        T o = null;
        if (!empty()) {
            o = list.getLast();
            list.removeLast();
        }
        return o;
    }

    /**
     * Top object.
     *
     * @return the object
     */
    public T top() {
        return list.getLast();
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
    public boolean empty() {
        return list.size()==0;
    }

    /**
     * To string string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        return "Queue: {" +
                "Top is " + top() +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}