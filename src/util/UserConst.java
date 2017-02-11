package util;

/**
 * Project Name: AlgoDsProject
 * Created By: Jin LI
 * Email:jin.li@vub.ac.be  homtingli@gmail.com
 * By IntelliJ IDEA 2016.3
 * Date: 29/November/2016 Time: 10:38
 */
public final class UserConst {

    /**
     * Star from 0 to 5, and 1 star means 3600s.
     */
    public static final int STAR_ZERO = 0;
    public static final int STAR_ONE = 1;
    public static final int STAR_TWO = 2;
    public static final int STAR_THREE = 3;
    public static final int STAR_FOUR = 4;
    public static final int STAR_FIVE = 5;
    public static final int STAR_RATE = 3600;

    /**
     * The enum Type. 4 types totally.
     */
    public enum Type {User,Company,Ad, Mess}

    public static final boolean PAID = true;
    public static final boolean UN_PAID = false;

    public static final int USER_NOT_FOUND = -1;
    public static final int CORP_NOT_FOUND = -1;
    public static final String EMPTY_STRING = "";
    public static final int FRIEND_LINK = 1;
    public static final int UNLINK = Integer.MAX_VALUE/2;
    public static final int CORP_LINK = 1;

    public static final int PRIVACY_PUBLIC = 0;
    public static final int PRIVACY_FRIEND = 1;
    public static final int PRIVACY_F_OF_F = 2;
}
