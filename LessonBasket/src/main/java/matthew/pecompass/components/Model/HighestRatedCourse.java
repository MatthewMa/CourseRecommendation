package matthew.pecompass.components.Model;

/**
 * Created by Sihua on 2016/11/24.
 */
public class HighestRatedCourse {
    private int crn;
    private String instructorname;
    private float score;


    public int getCrn() {
        return crn;
    }

    public void setCrn(int crn) {
        this.crn = crn;
    }

    public String getInstructorname() {
        return instructorname;
    }

    public void setInstructorname(String instructorname) {
        this.instructorname = instructorname;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public HighestRatedCourse(int crn, String instructorname, float score) {
        this.crn = crn;
        this.instructorname = instructorname;
        this.score = score;
    }
}
