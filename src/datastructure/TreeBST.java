package datastructure;

/**
 * Project Name: AlgoDsProject
 * Created By: Jin LI
 * Email:jin.li@vub.be  homtingli@gmail.com
 * By IntelliJ IDEA 2016.3
 * Date: 04/January/2017 Time: 11:18
 *
 * @param <T> the type parameter
 */
public class TreeBST<T extends Comparable<T>> {

    /**
     * The type Tree node.
     */
    public class TreeNode implements Comparable<TreeNode> {
        private T value;
        private TreeNode leftNode;
        private TreeNode rightNode;
        private TreeNode parentNode;

        /**
         * Instantiates a new Tree node.
         *
         * @param value the value
         */
        public TreeNode(T value) {
            this(value,null,null,null);
        }

        /**
         * Instantiates a new Tree node.
         *
         * @param value  the value
         * @param left   the left
         * @param right  the right
         * @param parent the parent
         */
        public TreeNode(T value, TreeNode left, TreeNode right, TreeNode parent) {
            this.value = value;
            this.leftNode = left;
            this.rightNode = right;
            this.parentNode = parent;
        }

        /**
         * Gets left tree.
         *
         * @return the left tree
         */
        public TreeNode getLeftTree() {
            return this.leftNode;
        }

        /**
         * Gets right tree.
         *
         * @return the right tree
         */
        public TreeNode getRightTree() {
            return this.rightNode;
        }

        /**
         * Gets parent.
         *
         * @return the parent
         */
        public TreeNode getParent() {
            return this.parentNode;
        }

        /**
         * Gets value.
         *
         * @return the value
         */
        public T getValue() {
            return this.value;
        }

        @Override
        public int compareTo(TreeNode o) {
            return this.value.compareTo(o.value);
        }
    }

    private TreeNode root;

    /**
     * Find t.
     *
     * @param element the element
     * @return the t
     */
    public T find(T element) {
        return find(element, root);
    }

    private T find(T element, TreeNode current) {
        if (current==null) {
            return null;
        } else if (element.compareTo(current.value)==0) {
            return current.value;
        } else if (element.compareTo(current.value) >0) {
            return find(element,current.getRightTree());
        }else {
            return find(element,current.getLeftTree());
        }
    }

    /**
     * Insert.
     *
     * @param o the o
     */
    public void insert(T o) {
        insertAtNode(o,root,null);
    }
    /**
     * Insert Node. If the node exists, update it.
     *
     * @param o the o
     * @param current the current node
     * @param parent the parent's node
     */
    private void insertAtNode(T o, TreeNode current, TreeNode parent) {
        if (current==null) {
            TreeNode node = new TreeNode(o);
            if (parent !=null) {
                if (o.compareTo(parent.value) < 0) {
                    parent.leftNode = node;
                    node.parentNode = parent;
                } else if (o.compareTo(parent.value) > 0) {
                    parent.rightNode = node;
                    node.parentNode = parent;
                }
            } else {
                root = node;
            }
        } else if (o.compareTo(current.value) < 0) {
            insertAtNode(o,current.getLeftTree(),current);
        } else if (o.compareTo(current.value) > 0) {
            insertAtNode(o,current.getRightTree(),current);
        } else {
            current.value = o;
        }
    }

    /**
     * Find the min node in the tree.
     *
     * @param current the current node.
     */
    private TreeNode minNode(TreeNode current) {
        if (current==null) {
            return null;
        } else if (current.getLeftTree()==null) {
            return current;
        } else {
            return minNode(current.getLeftTree());
        }
    }

    /**
     * Swap two nodes.
     *
     * @param oldNode the old node
     * @param newNode the new node
     */
    private void transplant(TreeNode oldNode, TreeNode newNode) {
        if (oldNode.parentNode==null) {
            root = newNode;
        } else if(oldNode.parentNode.getLeftTree() == oldNode) {
            oldNode.parentNode.leftNode = newNode;
        } else {
            oldNode.parentNode.rightNode = newNode;
        }
        if (newNode!=null) {
            newNode.parentNode = oldNode.parentNode;
        }
    }

    /**
     * Remove.
     *
     * @param element the element
     */
    public void remove(T element) {
        TreeNode n = root;
        removeNode(element,n);
    }

    /**
     * Insert.
     *
     * @param element the element
     * @param current the current node
     */
    private void removeNode(T element, TreeNode current) {
        if (current==null) {
            return;
        } else if (element.compareTo(current.value)==0) {
            if (current.leftNode==null) {
                transplant(current,current.rightNode);
            }
            else if (current.rightNode==null) {
                transplant(current,current.leftNode);
            }
            else {
                TreeNode y = minNode(current.rightNode);
                if (y.parentNode!=current) {
 //                   y.parentNode.leftNode = null;  ////
                    transplant(y,y.rightNode);
                    y.rightNode = current.rightNode;
                    y.rightNode.parentNode = y;
                    //y.parentNode.leftNode = null;
                }
//                y.parentNode.leftNode = null;
                transplant(current,y);
                y.leftNode = current.leftNode;
                y.leftNode.parentNode = y;
            }
        } else if (element.compareTo(current.value)<0) {
            removeNode(element,current.getLeftTree());
        } else {
            removeNode(element,current.getRightTree());
        }
    }

    /**
     * Print stack.
     * In order traversal
     *
     * @param action the action
     */
    public void printStack(TreeAction<T> action) {
        //in_order_traversal
        Stack<TreeNode> n = new Stack<>();
        if (root==null) {
            return;
        }
        n.push(root);
        while (!n.empty()) {
            TreeNode temp = n.pop();
            action.run(temp);
            // right tree first
            if (temp.getRightTree()!=null) {
                n.push(temp.getRightTree());
            }
            // then left tree
            if (temp.getLeftTree()!=null) {
                n.push(temp.getLeftTree());
            }
        }
    }

    /**
     * Print queue.
     * Layer Traver
     *
     * @param action the action
     */
    public void printQueue(TreeAction<T> action) {
        //Layer_Traver
        Queue<TreeNode> n = new Queue<>();
        if (root==null) {
            return;
        }
        n.push(root);

        while (!n.empty()) {
            TreeNode temp = n.pop();
            action.run(temp);

            if (temp.getLeftTree()!=null) {
                n.push(temp.getLeftTree());
            }
            if (temp.getRightTree()!=null) {
                n.push(temp.getRightTree());
            }
        }
    }

    /**
     * Swap tree.
     */
    public void swapTree() {
        root = swap(root);
    }

    /**
     * Swap left and right of the tree.
     *
     * @param root the current node
     */
    private TreeNode swap(TreeNode root) {
        if (root==null) {
            return null;
        }
        //TreeNode newLeft = swap(root.getRightTree());
        //TreeNode newRight = swap(root.getLeftTree());

        //root.leftNode = newLeft;
        //root.rightNode = newRight;
        root.leftNode = swap(root.getRightTree());
        root.rightNode = swap(root.getLeftTree());

        return root;
    }

    /**
     * Depth of the tree.
     *
     * @return the int
     */
    public int depthTree() {
        return depth(root);
    }

    /**
     * Depth of the tree.
     *
     * @param root the current node.
     * @return the int
     */
    private int depth(TreeNode root) {
        if (root==null) {
            return 0;
        }

        int left = depth(root.getLeftTree());
        int right = depth(root.getRightTree());

        return left>right?left+1:right+1;
    }

    /**
     * Find the Big element.
     *
     * @return the t
     */
    public T bigElement() {
        return bigElementTree(root).getValue();
    }

    /**
     * Find the Big element.
     *
     * @param root the current node.
     * @return the t
     */
    private TreeNode bigElementTree(TreeNode root) {
        if (root==null) {
            return null;
        } else if (root.getRightTree()==null) {
            return root;
        } else {
            return bigElementTree(root.rightNode);
        }
    }

    /**
     * Gets all tree node.
     * In in-order way.
     *
     * @return linked list node
     */
    public LinkedList<T> getAllTreeNode() {
        LinkedList<T> l = new LinkedList<>();
        Stack<TreeNode> n = new Stack<>();
        if (root==null) {
            return null;
        }
        n.push(root);
        while (!n.empty()) {
            TreeNode temp = n.pop();
            l.addLast(temp.getValue());
            if (temp.getRightTree()!=null) {
                n.push(temp.getRightTree());
            }

            if (temp.getLeftTree()!=null) {
                n.push(temp.getLeftTree());
            }
        }
        return l;
    }

    public void traverseInOrder(TreeAction<T> action) {
        traverseNode(root,action);
    }

    private void traverseNode(TreeNode root, TreeAction<T> action) {
        if (root!=null) {
            if (root.getLeftTree() !=null) {
                traverseNode(root.getLeftTree(),action);
            }
            action.run(root);
            if (root.getRightTree()!=null) {
                traverseNode(root.getRightTree(),action);
            }
        }
    }

    public boolean treeEquals(TreeBST<T> t){
        return treeEqualshelp(root,t.root);

    }

    private boolean treeEqualshelp(TreeNode t, TreeNode n) {
        if (t==null && n==null)
            return true;
        else if (t==null && n!=null || t!=null && n==null) {
            return false;
        }
        if (t.compareTo(n)!=0) {
            return false;
        }
        return treeEqualshelp(t.getLeftTree(),n.getLeftTree()) &&
            treeEqualshelp(t.getRightTree(),n.getRightTree());

    }

    public int minDepth() {
        return minDepthhelp(root);
    }

    private int minDepthhelp(TreeNode root) {
        if (root==null) {
            return 0;
        }
        if (root.getLeftTree()!=null && root.getRightTree()==null) {
            return minDepthhelp(root.getLeftTree()) + 1;
        }
        if (root.getLeftTree()==null && root.getRightTree()!=null) {
            return minDepthhelp(root.getRightTree()) + 1;
        }
        int left = minDepthhelp(root.getLeftTree());
        int right = minDepthhelp(root.getRightTree());
        return left>right? right+1:left+1;
    }
}
