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
public class RedBlackTree<T extends Comparable<T>> {
    /**
     * The enum Tree node color.
     */
    private enum TreeNodeColor {
        /**
         * Red tree node color.
         */
        Red, /**
         * Black tree node color.
         */
        Black}
    private class ColorTreeNode implements Comparable<ColorTreeNode> {
        private TreeNodeColor color;
        private T value;
        private ColorTreeNode leftNode;
        private ColorTreeNode rightNode;
        private ColorTreeNode parentNode;

        /**
         * Instantiates a new Color tree node.
         *
         * @param value the value
         * @param color the color
         */
        public ColorTreeNode(T value, TreeNodeColor color) {
            this.value = value;
            this.color = color;
        }

        public String toString() {
            if (value==null) {
                return "nil: "+this.color;
            }
            else {
                return value.toString()+": "+this.color;
            }
        }

        @Override
        public int compareTo(ColorTreeNode o) {
            return value.compareTo(o.value);
        }
    }

    private ColorTreeNode root;
    private ColorTreeNode nilNode;

    /**
     * Instantiates a new Red black tree.
     */
    public RedBlackTree() {
        nilNode = new ColorTreeNode(null, TreeNodeColor.Black);
        root = nilNode;
    }

    /**
     * Left rotate.
     *
     * @param x the current node.
     */
    private void rotateLeft(ColorTreeNode x) {
        System.out.println("left rotate");
        ColorTreeNode y = x.rightNode;
        x.rightNode = y.leftNode;
        if (y.leftNode != nilNode) {
            y.leftNode.parentNode = x;
        }
        y.parentNode = x.parentNode;
        if (x.parentNode==nilNode) {
            root = y;
        } else if (x==x.parentNode.leftNode) {
            x.parentNode.leftNode = y;
        } else {
            x.parentNode.rightNode = y;
        }
        y.leftNode = x;
        x.parentNode = y;
    }

    /**
     * right rotate.
     *
     * @param y the current node.
     */
    private void rotateRight(ColorTreeNode y) {
        System.out.println("right rotate");
        ColorTreeNode x = y.leftNode;
        if (x.rightNode !=nilNode) {
            x.rightNode.parentNode = y;
        }
        x.parentNode = y.parentNode;
        if (y.parentNode==nilNode) {
            root = x;
        } else if (y==y.parentNode.rightNode) {
            y.parentNode.rightNode = x;
        } else {
            y.parentNode.leftNode = x;
        }
        x.rightNode = y;
        y.parentNode = x;
    }

    /**
     * Rb insert.
     *
     * @param element the element
     */
    public void rbInsert(T element) {
        ColorTreeNode z = new ColorTreeNode(element,TreeNodeColor.Red);
        ColorTreeNode y = nilNode;
        ColorTreeNode x = root;

        while (!(x==nilNode)) {
            y = x;
            if (z.compareTo(x)<0) {
                x = x.leftNode;
            } else {
                x = x.rightNode;
            }
        }
        z.parentNode = y;
        if (y==nilNode)
            root = z;
        else if(z.compareTo(y)<0) {
            y.leftNode = z;
        } else {
            y.rightNode = z;
        }
        z.leftNode = nilNode;
        z.rightNode = nilNode;
        fixUpInsert(z);
    }

    /**
     * Update the color of the tree.
     *
     * @param z the current node.
     */
    private void fixUpInsert(ColorTreeNode z) {
        while ((z.parentNode!=null) && (z.parentNode.parentNode!=null)
                && z.parentNode.color==TreeNodeColor.Red) {
            if (z.parentNode==z.parentNode.parentNode.leftNode) {
                ColorTreeNode y = z.parentNode.parentNode.rightNode;
                if (y.color==TreeNodeColor.Red) {
                    z.parentNode.color = TreeNodeColor.Black;
                    y.color = TreeNodeColor.Black;
                    z.parentNode.parentNode.color = TreeNodeColor.Red;
                    z = z.parentNode.parentNode;
                } else {
                    if(z==z.parentNode.rightNode) {
                        z = z.parentNode;
                        rotateLeft(z);
                    }
                    z.parentNode.color = TreeNodeColor.Black;
                    z.parentNode.parentNode.color = TreeNodeColor.Red;
                    rotateRight((z.parentNode.parentNode));
                }
            } else {
                ColorTreeNode y = z.parentNode.parentNode.leftNode;
                if (y.color==TreeNodeColor.Red) {
                    z.parentNode.color = TreeNodeColor.Black;
                    y.color = TreeNodeColor.Black;
                    z.parentNode.parentNode.color = TreeNodeColor.Red;
                    z = z.parentNode.parentNode;
                } else {
                    if (z==z.parentNode.leftNode) {
                        z = z.parentNode;
                        rotateRight(z);
                    }
                    z.parentNode.color = TreeNodeColor.Black;
                    z.parentNode.parentNode.color = TreeNodeColor.Red;
                    rotateLeft(z.parentNode.parentNode);
                }
            }
        }
        if(z==root) {
            root.color = TreeNodeColor.Black;
        }
    }

    /**
     * Rec print.
     */
    public void layerPrint() {
        Queue<ColorTreeNode> t = new Queue<>();
        if (root==null) {
            return;
        }
        t.push(root);
        while (!t.empty()) {
            ColorTreeNode n = t.pop();
            System.out.println(n);
            if (n.leftNode!=nilNode) {
                t.push(n.leftNode);
            }
            if (n.rightNode!=nilNode) {
                t.push(n.rightNode);
            }
        }
    }

    /**
     * Pre order print.
     */
    public void preOrderPrint() {
        prePrint2(root);
    }

    /**
     * Pre order print.
     *
     * @param root the current node.
     */
    private void prePrint2(ColorTreeNode root) {
        if (root==null) {
            return;
        }
        System.out.println(root);
        prePrint2(root.leftNode);
        prePrint2(root.rightNode);
    }
}
