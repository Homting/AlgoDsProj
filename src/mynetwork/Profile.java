package mynetwork;

import datastructure.DictionaryTree;
import util.UserConst;
import util.UserConst.Type;
import util.Wall;

/**
 * Project Name: AlgoDsProject
 * Created By: Jin LI
 * Email:jin.li@vub.ac.be  homtingli@gmail.com
 * By IntelliJ IDEA 2016.3
 * Date: 08/December/2016 Time: 19:20
 */
public class Profile implements Comparable<Profile>{
    /**
     * The Name: company or user's name.
     */
    private String name;
    /**
     * The Wall to store messages or advertisements.
     */
    private Wall<Message,Integer> wall;

    /**
     * The Rate info. This is for company.
     */
    private DictionaryTree<String,Integer> rateInfo;
    /**
     * The Type. There are two types {Company, User}.
     */
    private Type type;
    /**
     * The Age. This is a user parameter.
     */
    private int age;

    /**
     * Instantiates a new Profile. This is for user profile.
     *
     * @param name the user name
     * @param age  the user age
     */
    public Profile(String name, int age) {
        this.name = name;
        this.age = age;
        this.type = Type.User;
        this.wall = new Wall<>();

        //User doesn't have rate info, but we still need to initial it.
        this.rateInfo = null;
    }

    /**
     * Instantiates a new Profile. This is for company profile.
     *
     * @param name the company name
     */
    public Profile(String name) {
        this.name = name;
        this.type = Type.Company;
        this.wall = new Wall<>();
        this.rateInfo = new DictionaryTree<>();

        //Company doesn't need age, but we need to initial it.
        this.age = -1;
    }

    /**
     * Gets company or user's name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets user messages or company Ads.
     *
     * @return the messages
     */
    public Wall<Message, Integer> getMessages() {
        return this.wall;
    }

    /**
     * Post ad. This function is for company.
     *
     * @param name      the company name
     * @param content   the content
     * @param ageLimit  the age limit
     * @param paid      the paid
     * @param timestamp the timestamp
     */
    public void postAd(String name, String content, int ageLimit, boolean paid, int timestamp) {
        if (this.type.equals(Type.Company)) {
            Message adMessage = new Message(name, content, paid, ageLimit, timestamp);
            wall.push(adMessage, timestamp);
        }
    }

    /**
     * Post message. This is for user to post messages.
     *
     * @param name      the username
     * @param content   the content
     * @param privacy   the privacy
     * @param ageLimit  the age limit
     * @param timestamp the timestamp
     */
    public void postMessage(String name,String content, int privacy, int ageLimit, int timestamp) {
        if (this.type.equals(Type.User) && (ageLimit<=this.age)) {
            Message message = new Message(name,content,privacy,ageLimit,timestamp);
            wall.push(message,timestamp);
        }
    }

    /**
     * Gets timestamp of n-th message.
     *
     * @param i the
     * @return the timestamp
     */
    private int getTimestamp(int i) {
        return wall.getTimestamp(i);
    }

    /**
     * Gets n-th message.
     *
     * @param i the
     * @return the post
     */
    public String getPost(int i) {
        if (this.type.equals(Type.User)) {
            if (i<wall.size() || i>=0) {
                return (wall.getMessage(i)).toString();
            }
        }
        return UserConst.EMPTY_STRING;
    }

    /**
     * Print all message or ads in the wall.
     */
    public void printWall() {
        Wall p = wall;
        int i=0;
        String s = UserConst.EMPTY_STRING;
        if (this.type.equals(Type.User)) {
            s = "User: ";
        } else {
            s = "Corp: ";
        }
        s = s + getName()+":\n";
        if (p.size()==0) {
            s += "{No Post Now.}";
        }
        while (p.getMessage(i)!=null) {
            String temp = (p.getMessage(i)).toString();
            i++;
            s += i+" "+temp+"\n";
        }
        System.out.println(s);

    }

    /**
     * Count number of wall size.
     *
     * @return the int
     */
    public int countWallSize() {
        return wall.size();
    }

    /**
     * Gets rate info. This is for company.
     *
     * @param username the username
     * @return the rate info
     */
    public int getRateInfo(String username) {
        if (this.type.equals(Type.Company)
                && (rateInfo.findValue(username)!=null)) {
            return rateInfo.findValue(username);
        }
        return 0;
    }

    /**
     * Is paid user boolean.
     *
     * @param username the username
     * @return the boolean
     */
    public boolean isPaidUser(String username) {
        return (this.type.equals(Type.Company)
                && (rateInfo.findValue(username)!=null));
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public Type getType() {
        return this.type;
    }

    /**
     * Gets age.
     *
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * Gets ad age limit.
     *
     * @param i the
     * @return the ad age limit
     */
    public int getAdAgeLimit(int i) {
        return wall.getMessage(i).getAgeLimit();
    }

    /**
     * Sets rate info.
     *
     * @param name  the name
     * @param stars the stars
     */
    public void setRateInfo(String name, int stars) {
        if (this.type.equals(Type.Company)) {
            rateInfo.add(name,stars);
        }
    }

    /**
     * To string method.
     *
     * @return the string
     */
    @Override
    public String toString() {
        if (getType().equals(Type.Company)) {
            return  "Corp Profile {" +
                    "name='" + name + '\'' +
                    ", messagesWall: " + wall.size() +
                    '}';
        } else {
            return  "User Profile {" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", messagesWall: " + wall.size() +
                    '}';
        }
    }

    /**
     * Compare two name. If the name are same, return 0.
     *
     * @param o the o
     * @return the int
     */
    @Override
    public int compareTo(Profile o) {
        return this.name.compareTo(o.getName());
    }

    /**
     * Print all rate info.
     */
    public void printAllRateInfo() {
        if (getType()==Type.Company) {
            rateInfo.print();
            System.out.println(rateInfo);
        }
    }
}
