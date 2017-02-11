package mynetwork;

/**
 * Project Name: AlgoDsProject
 * Created By: Jin LI
 * Email:jin.li@vub.ac.be  homtingli@gmail.com
 * By IntelliJ IDEA 15
 * Date: 21/November/2016 Time: 13:26
 */
public interface NetworkInterface {
    /**
     * Create user profile.
     *
     * @param name the name
     * @param age  the age
     */
    void createUserProfile(String name, int age);

    /**
     * Create corporate profile.
     *
     * @param name the name
     */
    void createCorporateProfile(String name);

    /**
     * Print user's wall.
     *
     * @param username the username
     */
    void printWall(String username);

    /**
     * Post message.
     *
     * @param username  the username
     * @param message   the message
     * @param privacy   the privacy
     * @param ageLimit  the age limit
     * @param timestamp the timestamp
     */
    void postMessage(String username, String message,
                     int privacy, int ageLimit, int timestamp);

    /**
     * Post ad.
     *
     * @param username  the username
     * @param message   the message
     * @param ageLimit  the age limit
     * @param paid      the paid
     * @param timestamp the timestamp
     */
    void postAd(String username, String message, int ageLimit, boolean paid, int timestamp);

    /**
     * Rate, connect user and company.
     *
     * @param username      the username
     * @param corporateName the corporate name
     * @param stars         the stars
     */
    void rate(String username, String corporateName, int stars);

    /**
     * Connect two users.
     *
     * @param username1 the username 1
     * @param username2 the username 2
     */
    void connect(String username1, String username2);

    /**
     * Print friend list.
     *
     * @param username the username
     */
    void printFriendList(String username);

    /**
     * Get the distance between to users.
     *
     * @param username1 the username 1
     * @param username2 the username 2
     * @return the int
     */
    int distance(String username1, String username2);

    /**
     * Print the path from one user to another.
     *
     * @param username1 the username 1
     * @param username2 the username 2
     */
    void printPath(String username1, String username2);

    /**
     * Get the distance between to users excluding corporate nodes.
     *
     * @param username1 the username 1
     * @param username2 the username 2
     * @return the int
     */
    int distanceExcludeCorporate(String username1, String username2);

    /**
     * Print the path from one user to another excluding corporate nodes.
     *
     * @param username1 the username 1
     * @param username2 the username 2
     */
    void printPathExcludeCorporate(String username1, String username2);

}
