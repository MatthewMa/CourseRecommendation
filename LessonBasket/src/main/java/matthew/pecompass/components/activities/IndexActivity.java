package matthew.pecompass.components.activities;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import matthew.pecompass.R;
import matthew.pecompass.components.Model.Courses;
import matthew.pecompass.components.Model.HighestRatedCourse;
import matthew.pecompass.components.Model.Karma;
import matthew.pecompass.components.Model.Notification;
import matthew.pecompass.components.Model.UnratedCourse;
import matthew.pecompass.components.Model.User;
import matthew.pecompass.components.fragments.AccountFragment;
import matthew.pecompass.components.fragments.RatingFragment;
import matthew.pecompass.components.fragments.SearchFragment;
import matthew.pecompass.components.fragments.ShareFragment;
import matthew.pecompass.components.views.NoCollisionViewPager;

public class IndexActivity extends BaseActivity {

    public NoCollisionViewPager vp_pagers;
    private RadioGroup rg_group;
    private RadioButton rb_rate;
    private RadioButton rb_account;
    private RadioButton rb_search;
    private RadioButton rb_share;
    private TextSwitcher ts_notification;
    private CheckBox do_not_show;
    private List<Fragment> fragments;
    private Button btn_start;
    private int currIndex=0;
    private PopupWindow popupWindow;
    private ImageButton ib_notification;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    @Override
    protected void initData() {

    }


    @Override
    protected void initListener() {

    }

    @Override
    protected void initView() {
        vp_pagers= (NoCollisionViewPager) findViewById(R.id.vp_pagers);
        rg_group= (RadioGroup) findViewById(R.id.rg_group);
        rb_rate= (RadioButton) findViewById(R.id.rb_rate);
        rb_account= (RadioButton) findViewById(R.id.rb_account);
        rb_search= (RadioButton) findViewById(R.id.rb_search);
        rb_share= (RadioButton) findViewById(R.id.rb_share);
        ib_notification= (ImageButton) findViewById(R.id.ib_notification);
        ib_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopUp();
            }
        });
        rg_group.check(R.id.rb_rate);
        fragments=new ArrayList<Fragment>();
        fragments.add(new RatingFragment());
        fragments.add(new AccountFragment());
        fragments.add(new SearchFragment());
        fragments.add(new ShareFragment());
        rg_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                rg_group.check(checkedId);
                int checked=0;
                switch (checkedId){
                    case R.id.rb_rate:
                        checked=0;
                        break;
                    case R.id.rb_account:
                        checked=1;
                        break;
                    case R.id.rb_search:
                        checked=2;
                        break;
                    case R.id.rb_share:
                        checked=3;
                        break;
                }
                vp_pagers.setCurrentItem(checked);
            }
        });
        FragmentPagerAdapter adapter=new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        };
        vp_pagers.setAdapter(adapter);
        vp_pagers.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int checkedId=R.id.rb_rate;
                switch (position){
                    case 0:
                        checkedId=R.id.rb_rate;
                        break;
                    case 1:
                        checkedId=R.id.rb_account;
                        break;
                    case 2:
                        checkedId=R.id.rb_search;
                        break;
                    case 3:
                        checkedId=R.id.rb_share;
                        break;
                }
                rg_group.check(checkedId);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        ib_notification.post(new Runnable() {
            @Override
            public void run() {
                sp=getSharedPreferences("config",MODE_PRIVATE);
                if(sp.getBoolean("show_popoup", true)) {
                    showPopUp();
                }
            }
        });
    }

    private void showPopUp() {
        View v= LayoutInflater.from(this).inflate(R.layout.popup_notification,null);
        ts_notification= (TextSwitcher) v.findViewById(R.id.ts_notification);
        do_not_show= (CheckBox) v.findViewById(R.id.do_not_show);
        btn_start= (Button) v.findViewById(R.id.btn_start);
        updateBtnStart();
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currIndex++;
                updateBtnStart();
                if(currIndex<Notification.notifications.size()){
                    //next page
                    ts_notification.setText(Notification.notifications.get(currIndex));
                }else{
                    //dismiss
                    popupWindow.dismiss();
                    currIndex=0;
                }
            }
        });
        do_not_show.setChecked(!sp.getBoolean("show_popoup", true));
        do_not_show.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                editor=sp.edit();
                editor.putBoolean("show_popup",!isChecked);
            }
        });
        ts_notification.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView textView = new TextView(IndexActivity.this);
                textView.setTextSize(22);
                textView.setTextColor(Color.BLACK);
                return textView;
            }
        });
        // 设置转换时的淡入和淡出动画效果（可选）
        Animation in = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
        Animation out = AnimationUtils.loadAnimation(this, android.R.anim.fade_out);
        ts_notification.setInAnimation(in);
        ts_notification.setOutAnimation(out);
        ts_notification.setText(Notification.notifications.get(currIndex));
        popupWindow = new PopupWindow(v,600,400);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
        popupWindow.setOutsideTouchable(false);
        // 设置PopupWindow以外部分的背景颜色  有一种变暗的效果
        final WindowManager.LayoutParams wlBackground = getWindow().getAttributes();
        wlBackground.alpha = 0.5f;      // 0.0 完全不透明,1.0完全透明
        getWindow().setAttributes(wlBackground);
        // 当PopupWindow消失时,恢复其为原来的颜色
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                wlBackground.alpha = 1.0f;
                getWindow().setAttributes(wlBackground);
            }
        });
        //设置PopupWindow进入和退出动画
        popupWindow.setAnimationStyle(R.style.anim_popup_centerbar);
        // 设置PopupWindow显示在中间
        popupWindow.showAtLocation(ib_notification, Gravity.CENTER,0,0);
    }

    private void updateBtnStart() {
        if(currIndex<Notification.notifications.size()){
            btn_start.setText("NEXT");
        }else{
            btn_start.setText("GET STARTED");
        }
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_index;
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
