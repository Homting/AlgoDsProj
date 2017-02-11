package mynetwork;

import util.UserConst.Type;

/**
 * Project Name: AlgoDsProject
 * Created By: Jin LI
 * Email:jin.li@vub.ac.be  homtingli@gmail.com
 * By IntelliJ IDEA 2016.3
 * Date: 09/December/2016 Time: 10:12
 */
public class Message {
    /**
     * The Name.
     */
    private String name;
    /**
     * The Content.
     */
    private String content;
    /**
     * The Age limit.
     */
    private int ageLimit;
    /**
     * The Timestamp.
     */
    private int timestamp;
    /**
     * The Paid.
     */
    private boolean paid;
    /**
     * The Privacy.
     */
    private int privacy;
    /**
     * The Type.
     */
    private Type type;


    /**
     * Instantiates a new advertisement Message.
     *
     * @param name      the name
     * @param adMessage the ad message
     * @param paid      the paid
     * @param ageLimit  the age limit
     * @param timestamp the timestamp
     */
    public Message(String name, String adMessage, boolean paid, int ageLimit, int timestamp) {
        this.name = name;
        this.content = adMessage;
        this.paid = paid;
        this.ageLimit = ageLimit;
        this.timestamp = timestamp;
        this.type = Type.Ad;
    }

    /**
     * Instantiates a new Message.
     *
     * @param author    the author
     * @param content   the content
     * @param privacy   the privacy
     * @param ageLimit  the age limit
     * @param timestamp the timestamp
     */
    public Message(String author,String content,int privacy,int ageLimit,int timestamp) {
        this.name = author;
        this.content = content;
        this.privacy = privacy;
        this.ageLimit = ageLimit;
        this.timestamp = timestamp;
        this.type = Type.Mess;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets content.
     *
     * @return the content
     */
    public String getContent() {
        return this.content;
    }

    /**
     * Gets age limit.
     *
     * @return the age limit
     */
    public int getAgeLimit() {
        return this.ageLimit;
    }

    /**
     * Gets timestamp.
     *
     * @return the timestamp
     */
    public int getTimestamp() {
        return this.timestamp;
    }

    /**
     * Is paid boolean.
     *
     * @return the boolean
     */
    public boolean isPaid() {
        return getType().equals(Type.Ad) && this.paid;
    }

    /**
     * Gets privacy.
     *
     * @return the privacy
     */
    public int getPrivacy() {
        if (getType().equals(Type.Mess)) {
            return privacy;
        }
        return -1;
    }

    /**
     * Gets type {COMPANY or USER}.
     *
     * @return the type
     */
    public Type getType() {
        return this.type;
    }

    /**
     * To string string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        String s = "";
        if (getType().equals(Type.Ad)) {
            s = "Ad Message";
        }
        return s + "{ Post Time: "+ getTimestamp() +
                ", Name='" + getName() + '\'' +
                ", Content='" + getContent() + '\'' +
                '}'+"\n";
    }
}
