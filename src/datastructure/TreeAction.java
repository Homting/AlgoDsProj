package datastructure;

/**
 * Project Name: ALDSBasic
 * Created By: Jin LI
 * Email:jin.li@vub.ac.be  homtingli@gmail.com
 * By IntelliJ IDEA 15
 * Date: 06/November/2016 Time: 22:18
 *
 * @param <T> the type parameter
 */
public abstract class TreeAction<T extends Comparable<T>>{
    /**
     * Run.
     *
     * @param n the n
     */
    public abstract void run(TreeBST<T>.TreeNode n);
}
