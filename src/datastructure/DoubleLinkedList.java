package datastructure;

/**
 * Project Name: AlgoDsProject
 * Created By: Jin LI
 * Email:jin.li@vub.ac.be  homtingli@gmail.com
 * By IntelliJ IDEA 15
 * Date: 06/November/2016 Time: 20:35
 *
 * @param <T> the type parameter
 */
public class DoubleLinkedList<T extends Comparable> implements Comparable<DoubleLinkedList>{

    /**
     * The type Double linked list element.
     */
    private class DoubleLinkedListElement implements Comparable<DoubleLinkedListElement>{
        /**
         * The Value.
         */
        private T value;
        /**
         * The Next element.
         */
        private DoubleLinkedListElement nextElement;
        /**
         * The Previous element.
         */
        private DoubleLinkedListElement previousElement;

        /**
         * Instantiates a new Double linked list element.
         *
         * @param v        the v
         * @param next     the next
         * @param previous the previous
         */
        DoubleLinkedListElement(T v,
            DoubleLinkedListElement next, DoubleLinkedListElement previous) {
            this.value = v;
            nextElement = next;
            previousElement = previous;
            if(nextElement != null) {
                nextElement.previousElement = this;
            }
            if(previousElement != null) {
                previousElement.nextElement = this;
            }
        }

        /**
         * Instantiates a new Double linked list element.
         *
         * @param v the v
         */
        public DoubleLinkedListElement(T v) {
            this(v,null,null);
        }

        /**
         * Previous double linked list element.
         *
         * @return the double linked list element
         */
        private DoubleLinkedListElement previous() {
            return previousElement;
        }

        /**
         * Gets value.
         *
         * @return the value
         */
        private T getValue() {
            return this.value;
        }

        /**
         * Sets value.
         *
         * @param value the value
         */
        private void setValue(T value) {
            this.value = value;
        }

        /**
         * Next double linked list element.
         *
         * @return the double linked list element
         */
        private DoubleLinkedListElement next() {
            return nextElement;
        }

        /**
         * Sets next.
         *
         * @param value the value
         */
        private void setNext(DoubleLinkedListElement value) {
            nextElement = value;
        }

        /**
         * Sets previous.
         *
         * @param value the value
         */
        private void setPrevious(DoubleLinkedListElement value) {
            previousElement = value;
        }

        @Override
        public int compareTo(DoubleLinkedListElement o) {
            return this.getValue().compareTo(o.getValue());
        }
    }

    /**
     * The Count.
     */
    private int count;
    /**
     * The Head.
     */
    private DoubleLinkedListElement head;
    /**
     * The Tail.
     */
    private DoubleLinkedListElement tail;

    /**
     * Instantiates a new Double linked list.
     */
    public DoubleLinkedList() {
        head = null;
        tail = null;
        count = 0;
    }

    /**
     * Gets first.
     *
     * @return the first
     */
    public T getFirst() {
        if(head==null) {
            return null;
        }
        return head.getValue();
    }

    /**
     * Gets last.
     *
     * @return the last
     */
    public T getLast() {
        if (tail==null) {
            return null;
        }
        return tail.getValue();
    }

    /**
     * Get n-th element value.
     *
     * @param i the
     * @return the t
     */
    public T get(int i) {
        if (i<0 || i>=size()) {
            return null;
        }
        DoubleLinkedListElement temp = findElement(i);
        return temp==null?null:temp.getValue();
//        if (temp==null) {
//            return null;
//        } else {
//            return temp.getValue();
//        }
    }

    /**
     * Find element double linked list element.
     *
     * @param index the index
     * @return the double linked list element
     */
    private DoubleLinkedListElement findElement(int index) {
        if (index<0 || index >= size()) {
            return null;
        }
        DoubleLinkedListElement temp;
        if(index>size()/2) {
            temp = tail;
            while (index<size()-1) {
                temp = temp.previous();
                index++;
            }
        } else {
            temp = head;
            while (index>0) {
                temp = temp.next();
                index--;
            }
        }
        return temp;
    }

    /**
     * Set n-th value.
     *
     * @param i   the
     * @param obj the obj
     */
    public void set(int i,T obj) {
        if (i<0 || i>=size()) {
            return;
        }
        if (findElement(i)!=null) {
            findElement(i).setValue(obj);
        }
    }

    /**
     * Get double linked list Size.
     *
     * @return the int
     */
    public int size() {
        return count;
    }

    /**
     * Is empty boolean.
     *
     * @return the boolean
     */
    public boolean isEmpty() {
        return size()==0;
    }

    /**
     * Add element in the first position.
     *
     * @param value the value
     */
    public void addFirst(T value) {
        head = new DoubleLinkedListElement(value,head,null);
        if(tail == null) {
            tail = head;
        }
        count++;
    }

    /**
     * Add element in the last position.
     *
     * @param value the value
     */
    public void addLast(T value) {
        tail = new DoubleLinkedListElement(value,null,tail);
        if(head == null) {
            head = tail;
        }
        count++;
    }

    /**
     * Remove first ele in the list.
     */
    public void removeFirst() {
        if (size()==0) {
            return;
        }
        head = head.next();
        if (head==null) {
            tail = null;
        } else {
            head.setPrevious(null);
        }
        count--;
    }

    /**
     * Remove last one.
     */
    public void removeLast() {
        if (size()==0) {
            return;
        }
        tail = tail.previous();
        if (tail==null) {
            head = null;
        } else {
            tail.setNext(null);
        }
        count--;
    }

    /**
     * Remove n-th in the list.
     *
     * @param index the index
     */
    public void removeAny(int index) {
        if (index>=size() || index<0) {
            return;
        }
        if (index==0) {
            removeFirst();
        }
        else if (index==size()-1) {
            removeLast();
        }
        DoubleLinkedListElement d = head;
        while (index>1) {
            d = d.next();
            index--;
        }
        //DoubleLinkedListElement d2 = d.next().next();
        //d.setNext(d2);
        //d2.setPrevious(d);
        d.setNext(d.next().next());
        d.next().setPrevious(d);
        count--;
    }

    /**
     * Remove object.
     *
     * @param obj the obj
     */
    public void removeObject(T obj) {
        if (isEmpty()) {
            return;
        }
        DoubleLinkedListElement element = head;
        if (element.getValue().compareTo(obj)==0) {
            removeFirst();
            return;
        }
        while (element!=null) {
            if (element.getValue().compareTo(obj)==0) {
                element.previous().setNext(element.next());
                element.next().setPrevious(element.previous());
            }
            element = element.next();
        }
    }

    /**
     * Print all elements.
     */
    public void print() {
        DoubleLinkedListElement d = head;
        System.out.print("(");
        while(d != null) {
            System.out.print(d.getValue());
            System.out.print(d==tail?"":"<==>");
            d = d.next();
        }
        System.out.println(")");
    }

    /**
     * Print reverse.
     */
    public void printReverse() {
        DoubleLinkedListElement d = tail;
        System.out.print("(");
        while(d != null) {
            System.out.print(d.getValue());
            System.out.print(d==head?"":"<==>");
            d = d.previous();
        }
        System.out.println(")");
    }

    /**
     * Reverse the list.
     *
     * We don't use this one. We use reverse.
     */
    public void reverse2no() {
        if (size()<=1) {
            return;
        }
        DoubleLinkedListElement p = head.next();
        head.setNext(null);//

        while (p !=null) {
            DoubleLinkedListElement cur = p.next();
            p.setNext(head);
            head = p;
            p = cur;
        }

        p = tail.previous();
        tail.setPrevious(null);
        while (p !=null) {
            DoubleLinkedListElement cur = p.previous();
            p.setPrevious(tail);
            tail = p;
            p = cur;
        }
    }

    /**
     * List contains obj or not.
     *
     * @param obj the obj
     * @return the boolean
     */
    public T contain(T obj) {
        DoubleLinkedListElement element = head;
        while (element !=null) {
            if (element.getValue().compareTo(obj)==0) {
                return element.getValue();
            }
            element = element.next();
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
        return this.contain(obj)!=null;
    }

    /**
     * Find position int.
     *
     * @param obj the obj
     * @return the int
     */
    public int findPosition(T obj) {
        int i=0;
        DoubleLinkedListElement p = head;
        while (p!=null) {
            if (p.getValue().compareTo(obj)==0) {
                return i;
            }
            p = p.next();
            i++;
        }
        return DsConst.NOT_FOUND;
    }

    /**
     * Append list2 to this list.
     *
     * @param list2 the list 2
     */
    public void append(DoubleLinkedList<T> list2) {
        if (list2.size()==0) {
            return;
        }
        DoubleLinkedListElement h = list2.head;
        while (h!=null) {
            this.addLast(h.getValue());
            h = h.next();
        }
    }

    /**
     * Reserse.
     * you should traverse the entire double linked list only once
     * and you should not create any new DoubleLinkedL is- tElements or a new DoubleLinkedList.
     */
    public void reverse() {
        if (head != null && head.next()!=null) {
            tail = head;
            while (head.next()!=null) {
                head = head.next();
                head.previous().setNext(head.previous().previous());
                head.previous().setPrevious(head);
            }
            head.setNext(head.previous());
            head.setPrevious(null);
        }
    }

    @Override
    public String toString() {
        return "DoubleLinkedList{" +
                "count=" + count +
                ", head=" + head.value +
                ", tail=" + tail.value +
                '}';
    }

    @Override
    public int compareTo(DoubleLinkedList o) {
        return 0;
    }


    public void fropple() {
        DoubleLinkedListElement t1 = head;
        if (t1==null || t1.next()==null) {
            return;
        }
        DoubleLinkedListElement t2 = t1.next();
        DoubleLinkedListElement t3;
        head = t2;
        head.setPrevious(null);

        while (t2!=null) {
            t3 = t2.next();

            t2.setNext(t1);
            t1.setPrevious(t2);

            t1.setNext(t3);
            if (t3!=null) {
                t3.setPrevious(t1);
            } else  {
                break;
            }
            if (t3.next() != null) {
                t1.setNext(t3.next());
                t3.next().setPrevious(t1);
                t1 = t3;
                t2 = t1.next();
            }
            else {
                t1 = t3;
                t2 = t1.next();
            }
        }
        tail = t1;
    }

    public void splits(T o) {
        DoubleLinkedListElement oddHead = null;
        DoubleLinkedListElement oddTail = null;
        DoubleLinkedListElement evenHead = null;
        DoubleLinkedListElement evenTail = null;

        DoubleLinkedListElement test = new DoubleLinkedListElement(o);

        DoubleLinkedListElement current = head;
        while (current!=null) {
            if (current.getValue().compareTo(test.getValue())==0) {
                if (evenHead==null) {
                    evenHead = current;
                    evenTail = current;
                } else {
                    evenTail.setNext(current);
                    current.setPrevious(evenTail);
                    evenTail = evenTail.next();
                }
            } else {
                if (oddHead==null) {
                    oddHead = current;
                    oddTail = current;
                } else {
                    oddTail.setNext(current);
                    current.setPrevious(oddTail);
                    oddTail = oddTail.next();
                }
            }
            current = current.next();
        }
        if (oddTail!=null) {
            oddTail.setNext(null);
        }
        if (evenTail!=null) {
            evenTail.setNext(null);
        }

        current = oddHead;
        while (current!=null) {
            System.out.print(current.getValue());
            current = current.next();
        }
        System.out.print("\n");
        current = evenHead;
        while (current!=null) {
            System.out.print(current.getValue());
            current = current.next();
        }
    }

    public void gnomeSort() {
        if (head==null || head==tail) { return;}
        DoubleLinkedListElement current = head;
        while (current!=tail) {
            DoubleLinkedListElement p = current.previous();
            DoubleLinkedListElement n = current.next();

            if (current.compareTo(n)>0) {
                current.setNext(n.next());
                n.setNext(current);
                current.setPrevious(n);
                n.setPrevious(p);

                if (current.next()==null) {
                    tail = current;
                } else {
                    current.next().setPrevious(current);
                }

                if (p==null) {
                    head = n;
                } else {
                    p.setNext(n);
                    current = p;
                }
            } else {
                current = current.next();
            }
        }
    }

}


