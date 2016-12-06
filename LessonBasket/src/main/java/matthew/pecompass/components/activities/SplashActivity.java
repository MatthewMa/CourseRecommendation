package matthew.pecompass.components.activities;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import matthew.pecompass.R;
import matthew.pecompass.components.Model.Course;
import matthew.pecompass.components.Model.Courses;
import matthew.pecompass.components.Model.HighestRatedCourse;
import matthew.pecompass.components.Model.Karma;
import matthew.pecompass.components.Model.KarmaTask;
import matthew.pecompass.components.Model.Notification;
import matthew.pecompass.components.Model.UnratedCourse;
import matthew.pecompass.components.Model.User;
import matthew.pecompass.components.utils.Utils;

public class SplashActivity extends AppCompatActivity {

    private RelativeLayout rl_splash;
    private ImageView iv_logo;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==0x123){
                startActivity(new Intent(SplashActivity.this,IndexActivity.class));
                finish();
            }
            if(msg.what==0x122){
                AnimationSet animationSet=new AnimationSet(true);
                animationSet.setDuration(2000);
                animationSet.setFillAfter(true);
                animationSet.setInterpolator(new AccelerateDecelerateInterpolator());
                animationSet.addAnimation(new ScaleAnimation(0.5f,1.0f,0.5f,1.0f));
                iv_logo.startAnimation(animationSet);
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Utils.makeText(this,"Login successfully!");
        rl_splash= (RelativeLayout) findViewById(R.id.rl_splash);
        iv_logo= (ImageView) findViewById(R.id.iv_logo);
        initData();
        AnimationSet set=new AnimationSet(true);
        set.setDuration(3000);
        set.setInterpolator(new LinearInterpolator());
        set.setFillAfter(true);
        set.addAnimation(new AlphaAnimation(0,1));
        rl_splash.startAnimation(set);
        handler.sendEmptyMessageDelayed(0x123,6000);
    }

    private void initData() {
        Courses.highestRatedCourses=new HashMap<String,List<HighestRatedCourse>>();
        makeCourses();
        Karma.highestKarmaUsers=new ArrayList<>();
        makeHighestKarmaUsers();
        Karma.taskNames=new ArrayList<>();
        Karma.karmaTasks=new HashMap<>();
        makeKarmaTasks();
        Courses.allCourses=new HashMap<>();
        makeAllCourses();
        Notification.notifications=new ArrayList<>();
        makeNotifications();
    }

    private void makeNotifications() {
        Notification.notifications.add("You have 5 tasks to finish: 2 from friends and 3 from " +
                "system.");
        Notification.notifications.add("Please rate your unrated courses to get karma.");
        Notification.notifications.add("Now you can get more valuable rewards by using less karma" +
                ".Check your account!");
    }

    private void makeAllCourses() {
        for (int i = 0; i < Courses.allCourseCrns.length; i++) {
            String days="";
            int ran= (int) Math.round(Math.random()*7);
            int avail=0;
            switch (ran){
                case 0:
                case 1:
                    days="Monday";
                    avail=3;
                    break;
                case 2:
                    days="Tuesday";
                    avail=5;
                    break;
                case 3:
                    days="Wednesday";
                    avail=7;
                    break;
                case 4:
                    days="Thursday";
                    avail=10;
                    break;
                case 5:
                    days="Friday";
                    avail=12;
                    break;
                default:
                    days="Saturday";
                    avail=14;
                    break;
            }
            Courses.allCourses.put(Courses.allCourseNames.get(i),new Course(Courses
                    .allCourseCrns[i],Courses.allCourseInstructors[i],3,days,"5:00pm-7:50pm",
                    avail,20,"06 Sep.-08 Dec.","Throv S386",Courses.allCourseRates[i]));
        }
        Courses.allCourses.get("Social Computing").avail=8;
    }

    private void makeKarmaTasks() {
        Karma.taskNames.add("Friend Recommendation");
        Karma.taskNames.add("System Recommendation");
        List<KarmaTask> list=new ArrayList<>();
        for(int i=0;i<Karma.friendkarmaTasks.length;i++){
            KarmaTask task=new KarmaTask(Karma.friendkarmaTasks[i],Karma.friendKarmaRewards[i]);
            list.add(task);
        }
        Karma.karmaTasks.put(Karma.taskNames.get(0),list);
        List<KarmaTask> list1=new ArrayList<>();
        for(int i=0;i<Karma.systemKarmaTasks.length;i++){
            KarmaTask task=new KarmaTask(Karma.systemKarmaTasks[i],Karma.systemKarmaRewards[i]);
            list1.add(task);
        }
        Karma.karmaTasks.put(Karma.taskNames.get(1),list1);
    }

    private void makeCourses() {
        Courses.unratedCourses=new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            UnratedCourse course=new UnratedCourse(Courses.courseCrn[i],Courses.courseNames[i],
                    Courses.scores[i]);
            Courses.unratedCourses.add(course);
        }
        //all
        List<HighestRatedCourse> allHighestRatedCourses=new ArrayList<>();
        for(int i=0;i<Courses.allHCourses.length;i++){
            HighestRatedCourse course=new HighestRatedCourse(Courses.allHCourses[i],Courses
                    .allHInstructors[i],Courses.allHRatings[i]);
            allHighestRatedCourses.add(course);
        }
        Courses.highestRatedCourses.put(Courses.departments[0],allHighestRatedCourses);
        //computer science
        List<HighestRatedCourse> comHighestRatedCourses=new ArrayList<>();
        for(int i=0;i<Courses.comHCourses.length;i++){
            HighestRatedCourse course=new HighestRatedCourse(Courses.comHCourses[i],Courses
                    .comHInstructors[i],Courses.comHRatings[i]);
            comHighestRatedCourses.add(course);
        }
        Courses.highestRatedCourses.put(Courses.departments[1],comHighestRatedCourses);
        //Math
        List<HighestRatedCourse> mathHighestRatedCourses=new ArrayList<>();
        for(int i=0;i<Courses.mHCourses.length;i++){
            HighestRatedCourse course=new HighestRatedCourse(Courses.mHCourses[i],Courses
                    .mHInstructors[i],Courses.mHRatings[i]);
            mathHighestRatedCourses.add(course);
        }
        Courses.highestRatedCourses.put(Courses.departments[2],mathHighestRatedCourses);
        //Machanical Engineering
        List<HighestRatedCourse> maHighestRatedCourses=new ArrayList<>();
        for(int i=0;i<Courses.maHCourses.length;i++){
            HighestRatedCourse course=new HighestRatedCourse(Courses.maHCourses[i],Courses
                    .maHInstructors[i],Courses.maHRatings[i]);
            maHighestRatedCourses.add(course);
        }
        Courses.highestRatedCourses.put(Courses.departments[3],maHighestRatedCourses);
    }

    private void makeHighestKarmaUsers() {

        for (int i = 0; i < 3; i++) {
            Karma.highestKarmaUsers.add(new User(i,Karma.names[i],Karma.karmas[i]));
        }
    }


}
