package matthew.pecompass.components.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by Sihua on 2016/11/25.
 */
public class Courses {

    public static ArrayList<UnratedCourse> unratedCourses;
    public static Map<String,List<HighestRatedCourse>> highestRatedCourses;
    public static Map<String,Course> allCourses;
    public static int shareTimes=3;
    //currentUnratedCrn
    public static int currUnratedCrn;
    public static int currHighestRatingGroupIndex;
    public static int currHighestRatingChildIndex;
    public static double currKarma;
    //Unrated classes
    public static int[] courseCrn={306,332,355,360,370,394,400,405};
    public static String[] courseNames={"Game Mechanics","Operating Systems Concepts 1","Theory " +
            "and " +
            "Appl of Databases","Machines and Algorithms","Software Engineering 1","Simulation " +
            "Principles","Research","Project Design and Implement"};
    public static double[] scores={2.4,2.1,2.0,1.7,2.3,2.2,2.3,1.9};

    //highest rating classes
    //All:
    public static int[] allHCourses={868,842,839,371,862,871};
    public static String[] allHInstructors={"Julita I Vassileva","Ralph A Deters","Alexey F " +
            "Shevyakov","Ebrahim Samei","Daniel Chen","David R Sumner"};
    public static float[] allHRatings={92,90,89,89,88,88};
    //Computer Science:
    public static int[] comHCourses={868,842,840,435};
    public static String[] comHInstructors={"Julita I Vassileva","Ralph A Deters","James A " +
            "Carter","Nadeem Jamali"};
    public static float[] comHRatings={92,90,86,82};
    //Math:
    public static int[] mHCourses={839,371};
    public static String[] mHInstructors={"Alexey F " +
            "Shevyakov","Ebrahim Samei"};
    public static float[] mHRatings={89,89};

    //Machanical Engineering:
    public static int[] maHCourses={862,871,450,327};
    public static String[] maHInstructors={"Daniel Chen","David R Sumner","Walerian " +
            "Szyszkowski","David A Torvi"};
    public static float[] maHRatings={88,88,82,80};

    //department
    public static String[] departments={"All","Computer Science","Math","Mechanical Engineering"};

    //rating theme
    public static String[] partitions=new String[]{"LEARNING","ENTHUSIASM","ORGANIZATION","OVERALL",
            "SUPPORT ENVIRONMENT"};
    public static String[] allCourseStrings={"Game Mechanics","Operating Systems Concepts 1","Theory " +
            "and " +
            "Appl of Databases","Machines and Algorithms","Software Engineering 1","Simulation " +
            "Principles","Research","Project Design and Implement","Social Computing","Mobile and" +
            "Cloud Computing","Accessible Computing","Fdtns Concurrent Programming","Methods of " +
            "Applied Math I","Metric Spaces and Cont Funct","Linear Control Systems 1",
            "Experimental Fluid Mechanics","Finite Element Analysis","Heat Transfer"};
    public static List<String> allCourseNames= Arrays.asList(allCourseStrings);
    public static String[] allCourseInstructors={"Jason T Bowey","Dwight J Makaroff","James A " +
            "Carter","Mark M Keil","Chris J Dutchyn","Nathaniel D Osgood","Nadeem Jamali","Nadeem" +
            " Jamali","Julita I Vassileva","Ralph A Deters","James A " +
            "Carter","Nadeem Jamali","Alexey F " +
            "Shevyakov","Ebrahim Samei","Daniel Chen","David R Sumner","Walerian " +
            "Szyszkowski","David A Torvi"};
    public static int[] allCourseCrns={306,322,355,360,370,394,400,405,868,842,840,435,839,371,862,871,450,327};
    public static int[] allCourseRates={67,72,73,71,72,71,62,68,92,90,86,82,89,89,88,88,82,80};

}
