package datastructure;

/**
 * Project Name: AlgoDsProject
 * Created By: Jin LI
 * Email:jin.li@vub.ac.be  homtingli@gmail.com
 * By IntelliJ IDEA 15
 * Date: 14/October/2016 Time: 14:33
 * <p>
 * The type Linked list.
 * A linked list.
 *
 * @param <T> the type parameter
 */
public class LinkedList<T extends Comparable<T>> implements Comparable<LinkedList> {
    @Override
    public int compareTo(LinkedList o) {
        return 0;
    }

    /**
     * The type List element.
     */
    private class ListElement {
        /**
         * The element.
         */
        private T el1;         // store data
        /**
         * The pointer to next element.
         */
        private ListElement el2;    // el2 is to point next Element

        /**
         * Instantiates a new List element.
         *
         * @param el          the el
         * @param nextElement the next element
         */
        public ListElement(T el, ListElement nextElement) {
            el1 = el;
            el2 = nextElement;
        }

        /**
         * Instantiates a new List element.
         *
         * @param el the el
         */
        public ListElement(T el) {
            this(el, null);
        }

        /**
         * Get the first object. (the data we store)
         *
         * @return the object
         */
        public T first() {
            return el1;
        }

        /**
         * point to next list element.
         *
         * @return the next element
         */
        public ListElement rest() {
            return el2;
        }

        /**
         * Sets data.
         *
         * @param value the value
         */
        public void setFirst(T value) {
            el1 = value;
        }

        /**
         * Sets next element.
         *
         * @param value the value
         */
        public void setRest(ListElement value) {
            el2 = value;
        }
    }

    /**
     * The Head. We get the head, so we can get the whole list
     */
    private ListElement head;

    /**
     * Add element in a sorted order.
     *
     * @param p the p
     */
    public void addSorted(T p) {
        //try {
        if (head == null) {
            head = new ListElement(p);
        } else if (head.first().compareTo(p) > 0) {
            head = new ListElement(p, head);
        } else {
            ListElement d = head;
            while (d.rest() != null && d.rest().first().compareTo(p) < 0) {
                d = d.rest();
            }
            ListElement next = d.rest();
            d.setRest(new ListElement(p, next));
        }
//        } catch (ClassCastException e) {
//            System.out.println("addSorted error: CastException");
//        }
    }

    /**
     * Instantiates a new Linked list.
     */
    public LinkedList() {
        head = null;
    }

    /**
     * Add an element from first position.
     *
     * @param o the o
     */
    public void addFirst(T o) {
        head = new ListElement(o, head);
    }


    /**
     * Add element from last position.
     *
     * @param o the o
     */
    public void addLast(T o) {
        if (head==null) {
            head = new ListElement(o);
            return;
        }
        ListElement temp = head;
        while(temp.rest()!=null) {
            temp = temp.rest();
        }
        ListElement t = new ListElement(o);
        temp.setRest(t);
    }

    /**
     * Gets data from the element.
     *
     * @return the first
     */
    public T getFirst() {
        if (head==null) {
            return null;
        }
        return head.first();
    }

    /**
     * In order to access the n-th element in the list,
     * we cannot do anything else than starting from the head and
     * traveling n times to the next element in the list
     *
     * @param n the n
     * @return the object
     */
    public T get(int n) {
        if (head ==null || n<0 || n>=size()) {
            return null;
        }
        ListElement d = head;
        while (n > 0 && d.rest()!=null) {
            d = d.rest();
            n--;
        }
        return d.first();
    }

    /**
     * Print the linked list.
     */
    public void print() {
        System.out.print("(");
        ListElement d = head;
        if (d==null) {
            System.out.println(")");
            return;
        }
        while(d.rest() != null) {
            System.out.print(d.first().toString()+" -> ");
            d = d.rest();
        }
        System.out.print(d.first());
        System.out.println(")");
    }

    /**
     * Size int. To calculate
     *
     * @return the int
     */
    public int size() {
        ListElement d = head;
        int i = 0;
        while(d!=null) {
            d = d.rest();
            i++;
        }
        return i;
    }

    /**
     * Is list empty boolean.
     *
     * @return the boolean
     */
    public boolean isEmpty() {
        return size()==0;
    }

    /**
     * To string string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        String s = "LinkedList: (";
        ListElement d = head;
        while (d != null) {
            s += d.first().toString();
            s += " -> ";
            d = d.rest();
        }
        s += ")";
        return s;
    }

    /**
     * Set n-th element a new data.
     *
     * @param n   the
     * @param obj the obj
     */
    public void set(int n, T obj) {
        ListElement temp = head;
        for(int i=0;i<n && temp!=null;i++){
            temp = temp.rest();
        }
        if (temp==null) {
            return;
        }
        temp.setFirst(obj);
    }

    /**
     * Gets last element's data.
     *
     * @return the last
     */
    public T getLast() {
        if (head==null) {
            return null;
        }
        ListElement temp = head;
        while (temp.rest()!=null) {
            temp = temp.rest();
        }
        return temp.first();
    }

    /**
     * Search boolean.
     *
     * @param obj the obj
     * @return the boolean
     */
    public Boolean find(T obj) {
        return this.contains(obj)!=null;
    }

    /**
     * Find position int.
     *
     * @param obj the obj
     * @return the int
     */
    public int findPosition(T obj) {
        ListElement p = head;
        int i = 0;
        while (p!=null) {
            if (p.first().compareTo(obj)==0) {
                return i;
            }
            i++;
            p = p.rest();
        }
        return DsConst.NOT_FOUND;
    }

    /**
     * Binary search int.
     *
     * @param obj the obj
     * @return the int
     */
    public int binarySearch(T obj) {
        int start = 0;
        int end = size()-1;
        while (start<end) {
            int middle = (start+end+1)/2;
            if(obj.compareTo(this.get(middle))<0) {
                start = middle+1;
            } else if (obj.compareTo(this.get(middle))<0) {
                end = middle-1;
            }
            else {
                return middle;
            }
        }
        return DsConst.NOT_FOUND;
    }

    /**
     * Contains boolean.
     *
     * @param obj the obj
     * @return the boolean
     */
    public T contains(T obj) {
        ListElement temp = head;
        while (temp !=null) {
            if (temp.first().compareTo(obj)==0) {
                return temp.first();
            }
            temp = temp.rest();
        }
        return null;
    }

    /**
     * Remove first.
     */
    public void removeFirst() {
        if (head==null || head.rest()==null) {
            head = null;
            return;
        }
        head = head.rest();
    }

    /**
     * Remove n-th element in the list.
     *
     * @param n the n
     * @return the t
     */
    public T removeAny(int n) {
        if (n<0 || n>=size()) {
            return null;
        }

        T o = get(n);
        if (n==0) {
            removeFirst();
            return o;
        }
        int i;
        ListElement p = head;
        for (i=0;i<n-1;i++) {
            p = p.rest();
        }
        p.setRest(p.rest().rest());

        return o;
    }

    /**
     * Remove one object in the list.
     *
     * @param o the o
     */
    public void removeObject(T o) {
        if (isEmpty()) {
            return;
        }
        ListElement p = head;
        if (p.first().compareTo(o)==0) {
            this.removeFirst();
        } else {
            ListElement q = head;
            p = head.rest();
            while (p!=null) {
                if (p.first().compareTo(o)==0) {
                    q.setRest(p.rest());
                    return;
                }
                q = p;
                p = p.rest();
            }
        }
    }

    /**
     * Remove last.
     */
    public void removeLast() {
        if (head ==null || head.rest()==null) {
            head = null;
            return;
        }

        ListElement temp = head;
        while(temp.rest().rest()!=null) {
            temp = temp.rest();
        }
        temp.setRest(null);
    }

    /**
     * Reverse the link list.
     */
    public void reverse() {
        if (head==null || head.rest()==null) {
            return;
        }
        ListElement p = head.rest();
        // !! now head is the last one, it must setRest(null).
        head.setRest(null);

        while (p !=null) {
            ListElement r = p.rest();
            p.setRest(head);
            head = p;
            p = r;
        }
    }

    /**
     * Doubled linked list.
     *
     * @return the linked list
     */
    public LinkedList<T> doubled() {
        LinkedList<T> result = new LinkedList<T>();
        ListElement temp = head;
        while (temp!=null) {
            result.addLast(temp.first());
            result.addLast(temp.first());
            temp = temp.rest();
        }
        return result;
    }

    /**
     * Interleave linked list.
     *
     * @param list the list
     * @return the linked list
     */
    public LinkedList<T> interleave(LinkedList<T> list) {
        ListElement r1 = this.head;
        ListElement r2 = list.head;
        LinkedList<T> result = new LinkedList<>();
        while (r1!=null && r2!=null) {
            result.addLast(r1.first());
            result.addLast(r2.first());
            r1 = r1.rest();
            r2 = r2.rest();
        }
        while (r1!=null) {
            result.addLast(r1.first());
            r1 = r1.rest();
        }
        while (r2!=null) {
            result.addLast(r2.first());
            r2 = r2.rest();
        }
        return result;
    }

    /**
     * Fropple. to exchange the two elements.
     */
    public void fropple() {
        if (head==null || head.rest()==null) {
            return;
        }
        ListElement t1 = head;
        ListElement t2 = t1.rest();
        head = t2;
        ListElement t3;

        while (t2 !=null) {
            t3 = t2.rest();
            t1.setRest(t2.rest());
            t2.setRest(t1);

            if (t3 == null) {
                break;
            }if (t3.rest() != null) {
                t1.setRest(t3.rest());
                t1 = t3;
                t2 = t1.rest();
            }
            else {
                t1 = t3;
                t2 = t1.rest();
            }
        }
    }

    /**
     * Append one list behind the other list
     *
     * @param list2 the list 2
     */
    public void append(LinkedList<T> list2) {
        if (list2.size()==0) {
            return;
        }

        ListElement temp = list2.head;

        while(temp!=null) {
            this.addLast(temp.first());
            temp = temp.rest();
        }
    }

    /**
     * Odd even list.
     */
    public void OddEven() {
        if (head==null || head.rest()==null) {
            return;
        }
        ListElement odd = head;
        ListElement even = odd.rest();
        ListElement evenhead = even;

        while(true) {
            ListElement tempOdd = even.rest();
            if(tempOdd==null)
                break;
            odd.setRest(tempOdd);
            ListElement tempEven = tempOdd.rest();
            if (tempEven==null)
                break;
            even.setRest(tempEven);
            odd = tempOdd;
            even = tempEven;
        }
        odd.setRest(evenhead);
    }
}