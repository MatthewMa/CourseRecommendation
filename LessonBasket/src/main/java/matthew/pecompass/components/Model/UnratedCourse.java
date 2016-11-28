package matthew.pecompass.components.Model;

/**
 * Created by Sihua on 2016/11/24.
 */
public class UnratedCourse {
    private int crn;
    private String coursename;
    private float score;

    public UnratedCourse(int crn, String coursename, float score) {
        this.crn = crn;
        this.coursename = coursename;
        this.score = score;
    }

    public int getCrn() {
        return crn;
    }

    public void setCrn(int crn) {
        this.crn = crn;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }
}
