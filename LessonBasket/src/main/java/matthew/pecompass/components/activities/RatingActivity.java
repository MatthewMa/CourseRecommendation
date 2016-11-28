package matthew.pecompass.components.activities;

import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import matthew.pecompass.R;
import matthew.pecompass.components.Constants.Constants;
import matthew.pecompass.components.Model.Courses;
import matthew.pecompass.components.fragments.BaseQuestionFragment;
import matthew.pecompass.components.fragments.QuestionFiveFragment;
import matthew.pecompass.components.fragments.QuestionFourFragment;
import matthew.pecompass.components.fragments.QuestionOneFragment;
import matthew.pecompass.components.fragments.QuestionThreeFragment;
import matthew.pecompass.components.fragments.QuestionTwoFragment;
import matthew.pecompass.components.utils.Utils;

/**
 * assume 5 questions
 */
public class RatingActivity extends BaseActivity {

    private TextView navigationTxt;
    private Chronometer chronometer;
    private FrameLayout fragmentContainer;
    private TextView tv_partition;
    private Button btn_pre;
    private Button btn_next;
    private int currentScreen=0;
    private int sumScreen=5;
    private List<BaseQuestionFragment> fragments;

    @Override
    protected void initData() {
        fragments.add(new QuestionOneFragment());
        fragments.add(new QuestionTwoFragment());
        fragments.add(new QuestionThreeFragment());
        fragments.add(new QuestionFourFragment());
        fragments.add(new QuestionFiveFragment());
        validateBtns ();
        updateScreen ();
    }

    private void updateScreen() {
        navigationTxt.setText((currentScreen+1)+"/"+sumScreen);
        tv_partition.setText(Courses.partitions[currentScreen]);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.replace(R.id.fragmentContainer, fragments.get(currentScreen));
        transaction.commit();

    }

    private void validateBtns() {
        btn_pre.setVisibility(View.VISIBLE);
        btn_next.setVisibility(View.VISIBLE);
        btn_next.setText("NEXT");
        if (currentScreen == 0) {
            btn_pre.setVisibility(View.INVISIBLE);
        } else if ( currentScreen == sumScreen-1) {
            btn_next.setText("SUBMIT");
        }
    }

    @Override
    protected void initListener() {
        btn_pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseQuestionFragment currentFragment=fragments.get(currentScreen);
                if (currentFragment.canPass()) {
                    if (currentScreen > 0)
                        currentScreen--;
                    validateBtns ();
                    updateScreen ();
                }else{
                    showPassHint();
                }
            }
        });
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseQuestionFragment currentFragment=fragments.get(currentScreen);
                if (currentScreen == sumScreen - 1) {
                    //last page
                    submitAnswers ();
                } else {
                    if (currentFragment.canPass()) {
                        if (currentScreen < sumScreen - 1)
                            currentScreen++;
                        validateBtns ();
                        updateScreen ();
                    }else{
                        showPassHint();
                    }
                }
            }
        });
    }

    private void showPassHint() {
        Utils.ToastPositiveDialog(this,"Empty answer","Please answer all questions!",R.drawable
                .icon_warning,0);
    }

    private void submitAnswers() {
        Utils.ToastPositiveAndNegativeDialog(this,"Submit rating","Are you sure to submit this " +
                "rating?",R.drawable.help,Constants.FINISH_RATING);
    }


    @Override
    protected void initView() {
        chronometer = (Chronometer)findViewById(R.id.chronometer);
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.start();
        fragmentContainer= (FrameLayout) findViewById(R.id.fragmentContainer);
        btn_pre= (Button) findViewById(R.id.btn_pre);
        btn_next= (Button) findViewById(R.id.btn_next);
        navigationTxt= (TextView) findViewById(R.id.navigationTxt);
        tv_partition= (TextView) findViewById(R.id.tv_partition);
        fragments=new ArrayList<>();

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_rating;
    }

    @Override
    public void onBackPressed() {
        Utils.ToastPositiveAndNegativeDialog(this,"Exit","Are you sure to exit,you will not get " +
                "2 karma!",R.drawable.help, Constants.QUIT_RATING);
    }
}
