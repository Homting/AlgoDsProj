package mynetwork;

import datastructure.*;
import util.UserConst;
import util.Wall;

/**
 * Project Name: AlgoDsProject
 * Created By: Jin LI
 * Email:jin.li@vub.ac.be  homtingli@gmail.com
 * By IntelliJ IDEA 15
 * Date: 07/November/2016 Time: 14:31
 */
public class Network implements NetworkInterface {

    /**
     * The Graph to store all relationship.
     */
    private Graph<String> graph;

    /**
     * The Graph to store only users.
     */
    private Graph<String> gWithoutCorp;

    /**
     * The User list.
     */
    private LinkedList<Profile> userList;

    /**
     * The Corp list.
     */
    private LinkedList<Profile> corpList;
    //private LinkedList<String> corpNameList;

    /**
     * Instantiates a new Network.
     */
    public Network() {
        userList = new LinkedList<>();
        corpList = new LinkedList<>();
        //corpNameList = new LinkedList<>();
        graph = new Graph<>();
        gWithoutCorp = new Graph<>();
    }

    /**
     * Create user profile.
     *
     * @param name the name
     * @param age  the age
     */
    public void createUserProfile(String name, int age) {
        Profile userProfile = new Profile(name,age);
        if (!userList.find(userProfile)) {
            userList.addFirst(userProfile);
            graph.addNode(name);
            gWithoutCorp.addNode(name);
        }
    }

    /**
     * Create corporate profile.
     *
     * @param name the name
     */
    public void createCorporateProfile(String name) {
        Profile corpProfile = new Profile(name);
        if (!corpList.find(corpProfile)) {
            //corpNameList.addLast(name);
            corpList.addFirst(corpProfile);
            graph.addNode(name);
        }
    }

    /**
     * Print user wall. 4 messages and 1 ad.
     *
     * @param username the username
     */
    public void printWall(String username) {
        // find the user position in the userlist.
        int index = findUserIndex(username);
        if (index == UserConst.USER_NOT_FOUND) {
            System.out.println(username+": no this user");
            return;
        }
        // Get the user and user age limit.
        Profile user = userList.get(index);
        int userAge = user.getAge();

        System.out.print("For " + username+":\n");
        Wall<Message,Integer> pq = findAllAds(corpList,username,userAge);
        int i = user.countWallSize();
        int j = pq.size();
        // k for counter
        int k = 0;
        if (i==0) {
            System.out.println("\t No Post");
        }
        while (i > 0) {
            i--;
            String temp = user.getPost(i);
            if (temp.equals(DsConst.EMPTY_STRING)) {
                break;
            }
            System.out.println(temp);
            k++;
            if ((k==4) && (j>0)) {
                j--;k=0;
                String ad = pq.getTimestamp(j) + " "+ pq.getMessage(j).toString();
                System.out.println(ad);
            }
        }
    }

    /**
     * Post user message.
     *
     * @param username  the username
     * @param message   the message
     * @param privacy   the privacy
     * @param ageLimit  the age limit
     * @param timestamp the timestamp
     */
    public void postMessage(String username, String message, int privacy, int ageLimit, int timestamp) {
        int count = userList.size();
        for (int i=0; i<count; i++) {
            Profile temp = userList.get(i);
            String thisUserName = temp.getName();
            if (privacy==UserConst.PRIVACY_PUBLIC) {
                temp.postMessage(username, message, privacy, ageLimit, timestamp);
            } else if (distanceExcludeCorporate(thisUserName,username)==privacy || thisUserName.equals(username)) {
                temp.postMessage(username, message, privacy, ageLimit, timestamp);
            }
        }
    }

    /**
     * Post company ad.
     *
     * @param corpname  the corpname
     * @param message   the message
     * @param ageLimit  the age limit
     * @param paid      the paid
     * @param timestamp the timestamp
     */
    public void postAd(String corpname, String message, int ageLimit, boolean paid, int timestamp) {
        int index = findCorpIndex(corpname);
        if (index!=UserConst.CORP_NOT_FOUND) {
            corpList.get(index).postAd(corpname, message, ageLimit, paid, timestamp);
        }
    }

    /**
     * Rate. 1 star means 3600s.
     *
     * @param username      the username
     * @param corporateName the corporate name
     * @param stars         the stars
     */
    @Override
    public void rate(String username, String corporateName, int stars) {
        int index = findCorpIndex(corporateName);
        if (index!=UserConst.CORP_NOT_FOUND) {
            corpList.get(index).setRateInfo(username, stars);
            graph.addEdge(username, corporateName,UserConst.CORP_LINK);
            graph.addEdge(corporateName, username,UserConst.CORP_LINK);
        }
        //corpList.get(0).printTree();
    }

    /**
     * Connect two users.
     *
     * @param username1 the username 1
     * @param username2 the username 2
     */
    @Override
    public void connect(String username1, String username2) {
        graph.addEdge(username1,username2,UserConst.FRIEND_LINK);
        graph.addEdge(username2,username1,UserConst.FRIEND_LINK);

        gWithoutCorp.addEdge(username1,username2,UserConst.FRIEND_LINK);
        gWithoutCorp.addEdge(username2,username1,UserConst.FRIEND_LINK);
    }

    /**
     * Print user's friend list.
     *
     * @param username the username
     */
    @Override
    public void printFriendList(String username) {
        //Vector vector = graph.getAdjacentNode(username,corpNameList);
        Vector vector = gWithoutCorp.getAdjacentNode(username);
        if (vector==null) {
            System.out.println("No This User: "+username);
        } else {
            for (int i=0; i < vector.size(); i++) {
                System.out.print(i == vector.size() - 1 ? vector.get(i) : vector.get(i) + ", ");
            }
        }
    }

    /**
     * Find user index in the user list.
     * If not exist, return -1
     *
     * @param username the username
     * @return the int
     */
    private int findUserIndex(String username) {
        int i = 0;
        while (i<userList.size()) {
            if ( userList.get(i).getName().equals(username)) {
                break;
            }
            i++;
        }
        if (i>=userList.size()) {
            return UserConst.USER_NOT_FOUND;
        }
        return i;
    }

    /**
     * Find company index in the corplist.
     *
     * @param corpname the company name
     * @return the int
     */
    private int findCorpIndex(String corpname) {
        int i = 0;
        while (i<corpList.size()) {
            if (corpList.get(i).getName().equals(corpname)) {
                break;
            }
            i++;
        }
        if (i>=corpList.size()) {
            return UserConst.CORP_NOT_FOUND;
        }
        return i;
    }

    /**
     * Find all ads wall. And arrange them into a new wall with new priority.
     *
     * @param corplist the corplist
     * @param username the username
     * @param userAge  the user age
     * @return the wall
     */
    private Wall<Message,Integer> findAllAds(LinkedList<Profile> corplist, String username, int userAge) {
        Wall<Message,Integer> pq = new Wall<>();
        int i = 0;
        while (i < corplist.size()) {
            Profile tempCorp = corplist.get(i);
            int star = tempCorp.getRateInfo(username);
            boolean flag = tempCorp.isPaidUser(username);

            // Get a company's all ads
            Wall<Message, Integer> temp = tempCorp.getMessages();
            int j = 0;
            while (j < temp.size()) {
                if (temp.getMessage(j).getAgeLimit() <= userAge) {
                    if (temp.getMessage(j).isPaid()) {
                        //Paid advertisements are equivalent to a 0 star rating.
                        pq.push(temp.getMessage(j),temp.getTimestamp(j));
                    } else if(!temp.getMessage(j).isPaid() && flag){
                        pq.push(temp.getMessage(j),temp.getTimestamp(j) + star*UserConst.STAR_RATE);
                    }
                }
                j++;
            }
            i++;
        }
        return pq;
    }


    /**
     * Two user's distance.
     *
     * @param thisUserName the this user name
     * @param username     the username
     * @return the int
     */
    @Override
    public int distance(String thisUserName, String username) {
        //System.out.println("distance depth: "+graph.getDepth_BFS(thisUserName,username));
        //System.out.println("distance bellmanFord: "+graph.getDistance_BellmanFord(thisUserName,username));
        //System.out.println("distance Dijkstra: "+graph.getDistance_Dijkstra(thisUserName,username));
        return graph.getDepth_BFS(thisUserName,username);
    }

    /**
     * Print path from one user to another user.
     *
     * @param username1 the username 1
     * @param username2 the username 2
     */
    @Override
    public void printPath(String username1, String username2) {
        System.out.print("Dijkstra method: ");
        printStack(graph.getShortPath_Dijkstra(username1, username2));
        System.out.print("BFS method: ");
        printStack(graph.getShortPath_BFS(username1,username2));
        System.out.print("BellmanFord method: ");
        printStack(graph.getShortPath_BellmanFord(username1,username2));
    }

    /**
     * Print all path.
     *
     * @param username1 the username 1
     * @param username2 the username 2
     */
    public void printAllPath(String username1, String username2) {
        System.out.println("From "+username1+" to "+username2+" all path: ");
        //Stack<Vector> stack = graph.findAllPathExtend(username1,username2,corpNameList);
        Stack<Vector> stack = gWithoutCorp.findAllPathExtend(username1,username2);
        if (stack==null) {
            System.out.println("No All Path!");
        } else {
            while (!stack.empty()) {
                Vector temp = stack.pop();
                for (int i = 0; i < temp.size(); i++) {
                    String name = (String) temp.get(i);
                    System.out.print(i == temp.size() - 1 ? name : name + "-->");
                }
                System.out.println();
            }
        }
    }

    /**
     * Distance exclude corporate.
     *
     * @param username1 the username 1
     * @param username2 the username 2
     * @return the int
     */
    @Override
    public int distanceExcludeCorporate(String username1, String username2) {
//        System.out.println("distance Depth: "+gWithoutCorp.getDepth_BFS(username1,username2));
//        System.out.println("distance bellmanFord: "+gWithoutCorp.getDistance_BellmanFord(username1,username2));
//        System.out.println("distance dijkstra: "+gWithoutCorp.getDistance_Dijkstra(username1,username2));
        //return graph.getDepth_BFS(username1,username2);
        return gWithoutCorp.getDepth_BFS(username1,username2);
    }

    /**
     * Print path exclude corporate.
     *
     * @param username1 the username 1
     * @param username2 the username 2
     */
    @Override
    public void printPathExcludeCorporate(String username1, String username2) {
        System.out.print("Dijkstra method: ");
        //printStack(graph.getShortPath_Dijkstra(username1,username2,corpNameList));
        printStack(gWithoutCorp.getShortPath_Dijkstra(username1,username2));
        System.out.print("BFS method: ");
        //printStack(graph.getShortPath_BFS(username1,username2,corpNameList));
        printStack(gWithoutCorp.getShortPath_BFS(username1,username2));
        System.out.print("BellmanFord method: ");
//        printStack(graph.getShortPath_BellmanFord(username1,username2,corpNameList));
        printStack(gWithoutCorp.getShortPath_BellmanFord(username1,username2));
    }

    @Override
    public String toString() {
        return "Network{" +
                "userList=" + userList.size() +
                ", corpList=" + corpList.size() +
                '}';
    }

    /**
     * Print path in a stack.
     *
     * @param s stack
     */
    private void printStack(Stack<String> s) {
        if (s==null) {
            System.out.print("No Path!");
        } else {
            while (!s.empty()) {
                String name = s.pop();
                System.out.print(s.empty() ? name : name + "-->");
            }
        }
        System.out.println();
    }


//    public void test() {
//        System.out.println(graph);
//        graph.print();
//    }
}