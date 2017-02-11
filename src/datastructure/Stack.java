package datastructure;

/**
 * Project Name: DSAlgoStack
 * Created By: Jin LI
 * Email:jin.li@vub.ac.be  homtingli@gmail.com
 * By IntelliJ IDEA 15
 * Date: 24/October/2016 Time: 16:34
 */
public class Stack<T extends Comparable> implements Comparable{
    /**
     * The Data.
     */
    private Vector<T> vector;

    /**
     * Instantiates a new Stack.
     */
    public Stack() {
        vector = new Vector<>();
    }

    /**
     * Push.
     *
     * @param o the o
     */
    public void push(T o) {
        vector.addLast(o);
    }

    /**
     * Pop object.
     *
     * @return the object
     */
    public T pop() {
        T temp = null;
        if (vector.size()>0) {
            temp = vector.getLast();
            vector.removeLast();
        }
        return temp;
    }

    /**
     * Top object.
     *
     * @return the object
     */
    public T top() {
        if (!empty())
            return vector.getLast();
        else
            return null;
    }

    /**
     * Size int.
     *
     * @return the int
     */
    public int size() {
        return vector.size();
    }

    /**
     * Empty boolean.
     *
     * @return the boolean
     */
    public boolean empty() {
        return vector.isEmpty();
    }

    @Override
    public String toString() {
        return "Stack: {" +
                "Top is: " + top() +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}

