package matthew.pecompass.components.activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import matthew.pecompass.R;
import matthew.pecompass.components.Constants.Constants;
import matthew.pecompass.components.utils.Utils;


public class RecommendActivity extends Activity {

    private VideoView vv_intro;
    private Button btn_back;
    private Button btn_select;
    private LinearLayout ll_videoview;
    private MediaController controller;

    //Table Row
    private TextView tv_coursename;
    private TextView tv_coursecrn;
    private TextView tv_instructorname;
    private TextView tv_coursedays;
    private ProgressBar pb_courseavail;
    private TextView tv_courseavail;
    private String coursename;
    private String coursemsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend);
        vv_intro= (VideoView) findViewById(R.id.vv_intro);
        btn_back= (Button) findViewById(R.id.btn_back);
        btn_select= (Button) findViewById(R.id.btn_select);
        ll_videoview= (LinearLayout) findViewById(R.id.ll_videoview);
        //TableLayout
        tv_coursename= (TextView) findViewById(R.id.tv_coursename);
        tv_coursecrn= (TextView) findViewById(R.id.tv_coursecrn);
        tv_instructorname= (TextView) findViewById(R.id.tv_instructorname);
        tv_coursedays= (TextView) findViewById(R.id.tv_coursedays);
        pb_courseavail= (ProgressBar) findViewById(R.id.pb_courseavail);
        tv_courseavail= (TextView) findViewById(R.id.tv_courseavail);

        String uri = "android.resource://" + getPackageName() + "/" + R.raw.social_computing;
        controller = new MediaController(this);
        controller.setAnchorView(ll_videoview);
        vv_intro.setMediaController(controller);
        vv_intro.setVideoURI(Uri.parse(uri));
        vv_intro.start();
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RecommendActivity.this,IndexActivity.class));
                finish();
            }
        });
        btn_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo: jump to select courses
                Utils.ToastPositiveAndNegativeDialog(RecommendActivity.this,"Register Class",
                        "Now let us enter register screen!",R.drawable.rating_congra, Constants
                                .FINISH_RECOMMEND);
            }
        });
    }

    @Override
    public void onBackPressed() {

    }
}
