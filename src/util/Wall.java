package util;

import datastructure.PriorityQueue;

/**
 * Project Name: AlgoDsProject
 * Created By: Jin LI
 * Email:jin.li@vub.ac.be  homtingli@gmail.com
 * By IntelliJ IDEA 15
 * Date: 25/November/2016 Time: 12:38
 *
 * @param <E> the type parameter
 * @param <P> the type parameter
 */
public class Wall<E,P extends Comparable<P>> {
    /**
     * The Wall. It's a priority queue.
     */
    private PriorityQueue<E,P> wall;

    /**
     * Instantiates a new Wall.
     */
    public Wall() {
        this.wall = new PriorityQueue<>();
    }

    /**
     * Push(element and priority).
     *
     * @param ele the element
     * @param p   the priority
     */
    public void push(E ele, P p) {
        this.wall.push(ele,p);
    }

    /**
     * Pop object in the queue.
     *
     * @return the object
     */
    public E pop() {
        return this.wall.pop();

    }

    /**
     * Gets n-th message from the wall.
     *
     * @param i the
     * @return the message
     */
    public E getMessage(int i) {
        return this.wall.get(i);
    }

    /**
     * Gets n-th timestamp from the wall.
     *
     * @param i the
     * @return the timestamp
     */
    public P getTimestamp(int i) {
        return this.wall.getPriority(i);
    }


    /**
     * Show the top message .
     *
     * @return the top Message.
     */
    public E topMessage() {
        return this.wall.top();
    }

    /**
     * Show the top timestamp.
     *
     * @return the p
     */
    public P topTimestamp(){
        return this.wall.topPriority();
    }


    /**
     * The length of the wall.
     *
     * @return the int
     */
    public int size() {
        return this.wall.size();
    }

    /**
     * Is the wall empty?
     *
     * @return the boolean
     */
    public boolean isEmpty() {
        return this.size()==0;
    }

    @Override
    public String toString() {
        return "Wall{" +
                "wall=" + wall.size() +
                '}';
    }
}
