package datastructure;

import java.lang.reflect.Array;

/**
 * Project Name: AlgoDsProject
 * Created By: Jin LI
 * Email:594268218@qq.com  homtingli@gmail.com
 * By IntelliJ IDEA 15
 * Date: 10/October/2016 Time: 13:42
 */
public class Vector<T extends Comparable> implements Comparable {
    /**
     * The Data.
     */
    private Comparable[] data;  // to store the element
    /**
     * The Count.
     */
    private int count;      // to show the number of current array.
    /**
     * The Capacity.
     */
    private int capacity;   // to show the size of the data.


    /**
     * Instantiates a new Vector.
     * Default value is 10.
     */
    public Vector() {
        this(DsConst.VECTOR_DEFAULT);
    }
    /**
     * This is the constructor.
     *
     * @param capacity the size of the array.
     */
    public Vector(int capacity) {
        if (capacity<1) {
            capacity = DsConst.VECTOR_DEFAULT;
        }
        this.data = new Comparable[capacity];
        this.count = 0;
        this.capacity = capacity;
    }

    public Vector(Class<T> type, int capacity) {
        this.data = (T[]) Array.newInstance(type, capacity);
    }

    /**
     * This method calculates the length.
     *
     * @return count. The length of current array.
     */
    public int size() {
        return this.count;
    }

    /**
     * This method is to judge the array is empty or not.
     *
     * @return boolean. boolean
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * This method is judge the array is full or not.
     *
     * @return true or false.
     */
    private boolean isFull() {
        return this.count==this.capacity;
    }

    /**
     * This method gets the specific data.
     *
     * @param index This is the index of the array.
     * @return a data.
     */
    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (index<size() && index>=0) {
            return (T) data[index];
        }
        return null;
    }

    /**
     * Clear the Vector.
     */
    public void clear() {
        this.count = 0;
        this.data = new Comparable[DsConst.VECTOR_DEFAULT];
    }

    /**
     * This method assign a new value to specific location.
     *
     * @param index the label of the array.
     * @param obj   the T
     */
    public void set(int index, T obj) {
        if (index<size() && !isEmpty() && index>=0) {
            data[index] = obj;
        }
    }

    /**
     * This method is to find the object.
     *
     * @param obj Give a object in order to search.
     * @return true or false.
     */
    public T contains(T obj) {
        for(int i=0;i<size();i++) {
            if(get(i).equals(obj)) {  //data[i] == obj)
                return get(i);
            }
        }
        return null;
    }

    /**
     * Find boolean.
     *
     * @param obj the obj
     * @return the boolean
     */
    public boolean find(T obj) {
        return contains(obj)!=null;
    }

    /**
     * Find position int.
     *
     * @param obj the obj
     * @return the int
     */
    public int findPosition(T obj) {
        for (int i=0;i<size();i++) {
            if (this.get(i).equals(obj)) {
                return i;
            }
        }
        return DsConst.NOT_FOUND;
    }

    /**
     * This method is to add a object in the first position.
     *
     * @param item The object that we want to add.
     */
    public void addFirst(T item) {
        if (isFull()) {
            extendCapacity();
        }
        int index = size();             // index from 1 to ...
        for (int i=index;i>0;i--) {
            data[i] = data[i-1];        // Head interpolation
        }
        count++;
        data[0] = item;
    }

    /**
     * This method add a object in the tail.
     *
     * @param obj .
     */
    public void addLast(T obj) {
        if (isFull()) {
            extendCapacity();
        }
        data[count] = obj;
        count++;
    }

    /**
     * Add element by sorted.
     *
     * @param obj the obj
     */
    public void addSort(T obj) {
        if (isFull()) {
            extendCapacity();
        }
        int i;
        for(i=size();i>0;i--) {
            if (data[i-1].compareTo(obj) < 0) {
                data[i] = data[i-1];
            }
            else {
                break;
            }
        }
        data[i] = obj;
        count++;
    }

    /**
     * This method get the first object in the array.
     *
     * @return a data.
     */
    @SuppressWarnings("unchecked")
    public T getFirst() {
        if (!isEmpty()) {
            return (T) data[0];
        }
        else {
            return null;
        }
    }

    /**
     * This method is to get the last object.
     *
     * @return the last data.
     */
    @SuppressWarnings("unchecked")
    public T getLast() {
        int index = size();
        if (!isEmpty()) {
            return (T) data[index - 1];
        }
        return null;
    }

    /**
     * This method is to delete the last data.
     */
    public void removeLast() {
        if (isEmpty()) {          // if the vector is Empty, just return
            return;
        }
        int index = size();
        count--;
        data[index-1] = null;     // to release the room
    }

    /**
     * This method is to delete the first data.
     */
    public void removeFirst()
    {
        if (isEmpty()) {       // if the vector is Empty, just return.
            return;
        }

        int index = size();
        for(int i=0;i<index-1;i++) {
            data[i] = data[i+1];
        }
        count--;
        data[index-1] = null;
    }

    /**
     * Remove any object.
     *
     * @param index the index
     * @return the object
     */
    public T removeAny(int index) {
        if (index>=size() || index <0) {
            return null;
        }
        T o = get(index);
        for (int i=index;i<size()-1;i++) {
            data[i] = data[i+1];
        }
        data[size()-1] = null;
        count--;

        return o;
    }

    /**
     * Remove object.
     *
     * @param e the e
     */
    public void removeObject(T e) {
        if (isEmpty()) {
            return;
        }
        this.removeAny(this.findPosition(e));

    }

    /**
     * This method displays all elements in the vector.
     */
    public void print() {
        System.out.print("[");
        for(int i=0;i<size();i++) {
            System.out.print(this.data[i]);
            System.out.print(i==size()-1?"":",");
        }
        System.out.print("]\n");
    }

    /**
     * This method is to reverse the array in O(1).
     */
    @SuppressWarnings("unchecked")
    public void reverse() {
        if (isEmpty() || size()==1) {
            return;
        }
        int index = this.size()-1;
        for (int i=0;i<=index/2;i++) {
            T object = (T)data[i];
            data[i] = data[index-i];
            data[index-i] = object;
        }
    }

    /**
     * Copy vector.
     *
     * @return the vector
     */
    public Vector copy() {
        Vector<T> v = new Vector<>(capacity);
        for(int i=0;i<size();i++) {
            v.addLast(get(i));
        }
        return v;
    }

    /**
     * This method is a static method.
     * This method mainly duplicates the content of a vector.
     * time complexity: O(n)
     *
     * @return v2 a new Vector
     */
    public Vector doubled() {
        Vector<T> v2 =  new Vector<>(capacity*2);
        for (int i=0;i<this.size();i++) {
            v2.addLast(this.get(i));
            v2.addLast(this.get(i));
        }
        return v2;
    }

    /**
     * This method mainly interleaves a vector to other one.
     * The length of the two Vector are different.
     * time complexity: O(n)
     *
     * @param v2 The Vector you want to duplicate.
     * @return a new Vector
     */
    public Vector interleave(Vector<T> v2) {
        int len1 = this.size();
        int len2 = v2.size();
        Vector<T> result = new Vector<>(len2+len1);

        int len = len1<len2 ? len1:len2;
        int i;
        for(i=0;i<len;i++) {
            result.addLast(this.get(i));
            result.addLast(v2.get(i));
        }
        while (i<len1) {
            result.addLast(this.get(i));
            i++;
        }
        while (i<len2) {
            result.addLast(v2.get(i));
            i++;
        }
        return result;
    }

    /**
     * This method is to double the capacity of our vector.
     * This is done in 3 steps:
     * (1) create a new array,
     * called data2 which can contain “2*capacity” elements.
     * (2) Copy the elements from data into data2.
     * (3) set data to data2.
     */
    private void extendCapacity() {
        Comparable data2[] = new Comparable[this.size()*2];
        System.arraycopy(this.data, 0, data2, 0, this.size());
        this.capacity *= 2;
        this.data = data2;
    }

    /**
     * Add vector.
     *
     * @param v2 the v 2
     */
    public void addVector(Vector<T> v2) {
        if (v2.isEmpty()) {
            return;
        }
        for (int i=0;i<v2.size();i++) {
            this.addLast(v2.get(i));
        }
    }

    /**
     * To string string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        String s = "[";
        if (!isEmpty()) {
            for (int i = 0; i < size(); i++) {
                s += this.data[i];
                s += (i == size() - 1) ? "" : ",";
            }
        }
        s += "]";
        return s;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }

}

