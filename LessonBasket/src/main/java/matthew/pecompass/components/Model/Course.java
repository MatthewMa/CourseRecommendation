package matthew.pecompass.components.Model;

/**
 * Created by Sihua on 2016/12/1.
 */

public class Course {
    public int crn;
    public String instructor;
    public float credit;
    public String days;
    public String time;
    public int avail;
    public int capacity;
    public String date;
    public String location;
    public int rate;

    public Course(int crn, String instructor, float credit, String days, String time, int avail,
                  int capacity, String date, String location,int rate) {
        this.crn = crn;
        this.instructor = instructor;
        this.credit = credit;
        this.days = days;
        this.time = time;
        this.avail = avail;
        this.capacity = capacity;
        this.date = date;
        this.location = location;
    }
}
