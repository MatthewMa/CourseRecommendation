package matthew.pecompass.components.Model;

import java.util.List;
import java.util.Map;

/**
 * Created by Sihua on 2016/11/28.
 */

public class Karma {
    //currKarma
    public static int currKarma=20;
    //highest Karma users
    public static List<User> highestKarmaUsers;
    public static List<String> taskNames;
    public static Map<String,List<KarmaTask>> karmaTasks;
    public static String[] names=new String[]{"Katlyn","Zoe","Matthew"};
    public static int[] karmas=new int[]{213,205,203};
    public static String[] friendkarmaTasks={"Your friend Zoe has recommended a course:Human " +
            "Computer" +
            " Interaction 1","Your friend Tom has recommended a course: Design Optimization and " +
            "Optimal Control"};
    public static String[] systemKarmaTasks={"Share one of your favorite courses to your friend through sending " +
            "a message" ,"Please select " +
            "Course: Social Computing,it has only 8 seats available","Sharing a course on " +
            "Facebook"};
    public static int[] friendKarmaRewards={2,2};
    public static int[] systemKarmaRewards={3,4,2};

}
