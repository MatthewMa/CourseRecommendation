package matthew.pecompass.components.fragments;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.zip.Inflater;

import matthew.pecompass.R;
import matthew.pecompass.components.Model.Course;
import matthew.pecompass.components.Model.Courses;
import matthew.pecompass.components.activities.IndexActivity;
import matthew.pecompass.components.utils.Utils;

import static android.R.attr.name;
import static matthew.pecompass.components.Model.Courses.allCourses;

public class ShareFragment extends Fragment {

    private static final int REQUEST_PHONE_CALL = 0;
    private GridView gv_shared;
    private LinearLayout tl_info;
    private AutoCompleteTextView atv_coursename;
    private EditText et_description;
    private String[] sharedMethods={"Send Message","Phone Call","Send Email","Facebook",
            "Youtube","Twitter"};
    private int[] sharedIcons={R.drawable.share_message,R.drawable.share_call,R.drawable
            .share_mail,R.drawable.share_facebook,R.drawable.share_youtube,R.drawable.share_twitter};

    //Table Row
    private TextView tv_coursename;
    private TextView tv_coursecrn;
    private TextView tv_instructorname;
    private TextView tv_coursedays;
    private ProgressBar pb_courseavail;
    private TextView tv_courseavail;
    private String coursename;
    private String coursemsg;


    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_share, container, false);
        gv_shared= (GridView) v.findViewById(R.id.gv_shared);
        tl_info= (LinearLayout) v.findViewById(R.id.tl_info);
        et_description= (EditText) v.findViewById(R.id.et_description);
        atv_coursename= (AutoCompleteTextView) v.findViewById(R.id.atv_coursename);
        //TableLayout
        tv_coursename= (TextView) v.findViewById(R.id.tv_coursename);
        tv_coursecrn= (TextView) v.findViewById(R.id.tv_coursecrn);
        tv_instructorname= (TextView) v.findViewById(R.id.tv_instructorname);
        tv_coursedays= (TextView) v.findViewById(R.id.tv_coursedays);
        pb_courseavail= (ProgressBar) v.findViewById(R.id.pb_courseavail);
        tv_courseavail= (TextView) v.findViewById(R.id.tv_courseavail);
        atv_coursename.requestFocus();
        atv_coursename.requestFocusFromTouch();
        atv_coursename.setAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout
                .simple_list_item_1, Courses.allCourseNames));
        atv_coursename.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if((!hasFocus)&&(((IndexActivity)ShareFragment.this.getActivity()).vp_pagers
                        .getCurrentItem()==3)){
                    String name=atv_coursename.getText().toString();
                    if(!allCourses.containsKey(name)){
                        //no such course
                        Utils.ToastPositiveDialog(ShareFragment.this.getActivity(),"No Such " +
                                "Course","No such course,please input another course!",R.drawable
                                .icon_warning,0);
                    }else {
                        Animation animation=new AlphaAnimation(0.0f,1.0f);
                        animation.setFillAfter(true);
                        animation.setDuration(600);
                        tl_info.setAnimation(animation);
                        tl_info.setVisibility(View.VISIBLE);
                        tl_info.startAnimation(animation);
                        Course info=Courses.allCourses.get(name);
                        tv_coursename.setText(name);
                        tv_coursecrn.setText(info.crn+"");
                        tv_instructorname.setText(info.instructor.toString());
                        tv_coursedays.setText(info.days.toString());
                        pb_courseavail.setProgress(info.avail);
                        tv_courseavail.setText(info.avail+"/"+info.capacity);
                    }

                }
            }
        });
        gv_shared.setAdapter(new BaseAdapter() {

            private ImageView iv_gifticon;
            private TextView tv_explain;
            @Override
            public int getCount() {
                return sharedMethods.length;
            }

            @Override
            public Object getItem(int position) {
                return sharedMethods[position];
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View v=View.inflate(ShareFragment.this.getActivity(),R.layout.gridview_share,null);
                iv_gifticon= (ImageView) v.findViewById(R.id.iv_gifticon);
                tv_explain= (TextView) v.findViewById(R.id.tv_explain);
                iv_gifticon.setImageResource(sharedIcons[position]);
                tv_explain.setText(sharedMethods[position]+"");
                return v;
            }
        });
        //set item click for shared method
        gv_shared.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                coursename = atv_coursename.getText().toString();
                coursemsg = et_description.getText().toString();
                switch (position){
                    case 0:
                        //sendmessage
                        sendMessage();
                        break;
                    case 1:
                        //phonecall:
                        callPhone();
                        break;
                    case 2:
                        //sendemail
                        sendEmail();
                        break;
                    case 3:
                        //facebook:
                        shareFaceBook();
                        break;
                    case 4:
                        //youtube:
                        shareYouTube();
                        break;
                    case 5:
                        //twitter
                        shareTwitter();
                        break;
                }
            }
        });

        return v;
    }

    private void shareTwitter() {

    }

    private void shareYouTube() {

    }

    private void shareFaceBook() {
    }

    private void sendEmail() {
    }

    private void callPhone() {
        final AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setTitle("Please input phone number:");
        View v= View.inflate(getActivity(),R.layout.alertdialog_phoenumber,null);
        EditText et_number= (EditText) v.findViewById(R.id.et_phonenumber);
        final String number=et_number.getText().toString();
        builder.setView(v);
        requestPermissions(new String[]{Manifest.permission.CALL_PHONE},REQUEST_PHONE_CALL);
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if(getActivity().checkSelfPermission(Manifest.permission.CALL_PHONE)==
                            PackageManager.PERMISSION_GRANTED) {
                        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + number));
                        startActivity(intent);
                    }
                }else{
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + number));
                    startActivity(intent);
                }
            }
        }).setNegativeButton("Cancel",null);
        builder.create().show();
    }

    private void sendMessage() {
        Uri smsToUri = Uri.parse("smsto:");
        Intent intent = new Intent(Intent.ACTION_SENDTO, smsToUri);
        intent.putExtra("sms_body", "Hi,I want to recommend a course:"+coursename+" for you,if " +
                "you " +
                "choose, both of us can get free karma!\n"+coursemsg);
        startActivity(intent);
    }

    @Override
    public void onPause() {
        atv_coursename.setText("");
        super.onPause();
    }
}
