package matthew.pecompass.components.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import matthew.pecompass.R;
import matthew.pecompass.components.Constants.Constants;
import matthew.pecompass.components.Model.Courses;
import matthew.pecompass.components.activities.IndexActivity;
import matthew.pecompass.components.activities.RatingActivity;
import matthew.pecompass.components.fragments.RatingFragment;

/**
 * Created by Sihua on 2016/11/24.
 */
public class Utils {
    public static void findButtonSetOnClickListener(View root, View.OnClickListener listener) {
        if (root instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) root;
            for (int i = 0; i < ((ViewGroup) root).getChildCount(); i++) {
                View child = ((ViewGroup) root).getChildAt(i);
                if (child instanceof Button || child instanceof ImageButton) {
                    child.setOnClickListener(listener);
                } else if (child instanceof ViewGroup) {
                    findButtonSetOnClickListener(child, listener);
                }
            }
        }
    }

    public static void makeText(Activity activity, String content){
        Toast.makeText(activity,content,Toast.LENGTH_LONG).show();
    }

    public static void ToastPositiveDialog(Activity activity, String title, String msg, int icon,
                                           int flag){
        AlertDialog.Builder ab=new AlertDialog.Builder(activity);
        ab.setTitle(title);
        ab.setMessage(msg);
        ab.setIcon(icon);
        ab.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        ab.create().show();
    }

    public static void ToastPositiveAndNegativeDialog(final Activity activity, String title, String msg,
                                                      int icon, final int flag){
        AlertDialog.Builder ab=new AlertDialog.Builder(activity);
        ab.setTitle(title);
        ab.setMessage(msg);
        ab.setIcon(icon);
        ab.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (flag){
                    case Constants.JUMP_TO_RATING:
                        Intent intent=new Intent(activity,RatingActivity.class);
                        intent.putExtra("position",which);
                        activity.startActivity(intent);
                        break;
                    case Constants.QUIT_RATING:
                        activity.finish();
                        break;
                    case Constants.FINISH_RATING:
                        Intent intent1=new Intent(activity, IndexActivity.class);
                        intent1.putExtra("finishUnratedCrn", Courses.currUnratedCrn);
                        activity.startActivity(intent1);
                        activity.finish();
                        break;
                    case Constants.FINISH_RECOMMEND:
                        //todo:select courses
                        Intent intent2=new Intent(activity, IndexActivity.class);
                        activity.startActivity(intent2);
                        activity.finish();
                        break;
                }
            }
        });
        ab.setNegativeButton("Cancel",null);
        ab.create().show();
    }
}
