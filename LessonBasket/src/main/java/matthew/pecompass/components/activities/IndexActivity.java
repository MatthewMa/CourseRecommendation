package matthew.pecompass.components.activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import matthew.pecompass.R;
import matthew.pecompass.components.Model.Courses;
import matthew.pecompass.components.Model.HighestRatedCourse;
import matthew.pecompass.components.Model.Karma;
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
    private List<Fragment> fragments;

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
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_index;
    }



}
