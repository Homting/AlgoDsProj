import mynetwork.Network;
import util.IntTime;
import util.UserConst;

/**
 * Project Name: AlgoDsProject
 * Created By: Jin LI
 * Email:jin.li@vub.ac.be  homtingli@gmail.com
 * By IntelliJ IDEA 15
 * Date: 20/October/2016 Time: 13:59
 */
public class Main {
    public static void main(String[] args) {
        Network n = new Network();
        IntTime t = new IntTime();

        n.createUserProfile("Bob",31);
        n.createUserProfile("Eddy",19);
        n.createUserProfile("Fox",22);
        n.createUserProfile("Cindy",35);
        n.createUserProfile("Amy",20);
        n.createUserProfile("Danny",40);
        n.createUserProfile("Ivan",19);
        n.createUserProfile("Jack",22);
        n.createUserProfile("Gavin",33);
        n.createUserProfile("Heleny",20);
        n.createUserProfile("No",40);
        n.createUserProfile("No",40);


        //System.out.println(n);

        n.createCorporateProfile("Everyday");
        n.createCorporateProfile("Boni");
//        n.createCorporateProfile("Color");

        n.connect("Amy","Cindy");
        n.connect("Amy","Bob");
        n.connect("Bob","Eddy");
        n.connect("Cindy","Danny");
        n.connect("Cindy","Fox");
        n.connect("Danny","Gavin");
        n.connect("Eddy","Gavin");
        n.connect("Fox","Gavin");
        n.connect("Gavin","Jack");
        n.connect("Heleny","Ivan");
        n.connect("Ivan","Jack");



        n.postMessage("Amy", "All can see this, except E & I", UserConst.PRIVACY_PUBLIC, 20, t.getCurrentTime());
        n.postMessage("Amy", "All can see this", UserConst.PRIVACY_PUBLIC, 19, t.getCurrentTime());
        n.postMessage("Amy", "B and C can see this", UserConst.PRIVACY_FRIEND, 20, t.getCurrentTime());

        n.postMessage("Bob", "C and G can see this", UserConst.PRIVACY_F_OF_F, 30, t.getCurrentTime());
        n.postMessage("Cindy", "Only G can see this", UserConst.PRIVACY_F_OF_F, 33, t.getCurrentTime());
        n.postMessage("Cindy", "E and G can see this", UserConst.PRIVACY_F_OF_F, 19, t.getCurrentTime());

        //n.postMessage("System","xxx",UserConst.PRIVACY_PUBLIC,4,t.getCurrentTime());


        for (int i=0;i<19;i++) {
            n.postMessage("Cindy", "for the test", UserConst.PRIVACY_PUBLIC, 33, t.getCurrentTime()+i);
        }


        n.postMessage("Heleny","Ivan can see this",UserConst.PRIVACY_FRIEND,19,t.getCurrentTime());
        n.postMessage("Heleny", "Jack can see this", UserConst.PRIVACY_F_OF_F, 20, t.getCurrentTime());


        n.postAd("Everyday","egg",20, UserConst.UN_PAID, t.getCurrentTime()+100);
        n.postAd("Everyday","Tomato",25, UserConst.PAID, t.getCurrentTime()+200);

        n.postAd("Boni","bread",19,UserConst.PAID, t.getCurrentTime()+50);
        n.postAd("Boni","apple",19,UserConst.UN_PAID, t.getCurrentTime()+150);


        n.rate("Eddy","Boni",UserConst.STAR_FOUR);
        n.rate("Jack","Boni",UserConst.STAR_THREE);
        n.rate("Amy","Boni",UserConst.STAR_ONE);
        n.rate("Amy","Boni",UserConst.STAR_TWO);
        n.rate("Amy","Boni",UserConst.STAR_ONE);
        //n.rate("Cindy","Boni",UserConst.STAR_FOUR);
        n.rate("Heleny","Boni",UserConst.STAR_TWO);
        n.rate("Fox","Everyday",UserConst.STAR_FIVE);
        n.rate("Ivan","Everyday",UserConst.STAR_ZERO);



        n.printWall("Amy");
        System.out.println("**********************");
        n.printWall("Bob");
        System.out.println("**********************");
        n.printWall("Cindy");
        System.out.println("**********************");

        n.printWall("Gavin");

        n.printFriendList("Amy");
        System.out.println();
        System.out.println("**********************");

        n.printFriendList("Cindy");
        System.out.println();
        System.out.println("**********************");

        n.printFriendList("Bob");
        System.out.println();
        System.out.println("**********************");
       // n.printFriendList("Ivan");
        System.out.println("**********************");

        System.out.println("distance: "+n.distance("Amy","Ivan"));
        n.printPath("Amy","Ivan");
        System.out.println("distanceEx: "+n.distanceExcludeCorporate("Amy","Ivan"));
        n.printPathExcludeCorporate("Amy","Ivan");


        n.printAllPath("Amy","Ivan");

        System.out.println();

        n.printAllPath("Bob","Helen");
        n.printFriendList("heh");
        n.printPath("d","x");

        //n.test();
        //n.ddd("Amy","Jack");

        //n.test();
    }

}
