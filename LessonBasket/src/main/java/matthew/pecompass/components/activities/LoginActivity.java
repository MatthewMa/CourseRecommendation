package matthew.pecompass.components.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import matthew.pecompass.R;
import matthew.pecompass.components.Constants.Constants;
import matthew.pecompass.components.utils.Utils;

public class LoginActivity extends BaseActivity {

    private Button btn_login;
    private Button btn_cancel;
    private Button btn_reset;
    private CheckBox auto_save_password;
    private Button regist_btn;
    private EditText login_accounts;
    private EditText login_password;
    private RelativeLayout loginpage;
    private String email;
    private String password;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private View popView;
    //popView
    private EditText et_email;
    private Button btn_send;
    private Button btn_resetcancel;
    private RadioGroup rg_theme;
    private LinearLayout more_menu;
    private RelativeLayout more;
    private TextView more_text;
    private ImageView more_image;
    @Override
    protected void initData() {
        auto_save_password.setChecked(sp.getBoolean("checked",true));
        //remember me?
        if(auto_save_password.isChecked()){
            login_accounts.setText(sp.getString("email",""));
            login_password.setText(sp.getString("password",""));
        }
        int col=sp.getInt("theme_id",R.id.theme_animal);
        switch (col){
            case R.id.theme_animal:
                loginpage.setBackgroundResource(R.drawable.theme_animal);
                break;
            case R.id.theme_autumn:
                loginpage.setBackgroundResource(R.drawable.theme_autumn);
                break;
            case R.id.theme_car:
                loginpage.setBackgroundResource(R.drawable.theme_car);
                break;
        }
        rg_theme.check(col);
    }

    @Override
    protected void initListener() {
        rg_theme.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.theme_animal:
                        loginpage.setBackgroundResource(R.drawable.theme_animal);
                        break;
                    case R.id.theme_autumn:
                        loginpage.setBackgroundResource(R.drawable.theme_autumn);
                        break;
                    case R.id.theme_car:
                        loginpage.setBackgroundResource(R.drawable.theme_car);
                        break;
                }
                //save to sp
                editor.putInt("theme_id",checkedId);
                editor.commit();
            }
        });
        //menu click
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(more_menu.getVisibility()==View.VISIBLE){
                    //hide
                    more_menu.setVisibility(View.GONE);
                    more_text.setText("More Themes");
                    more_image.setImageResource(R.drawable.login_more_up);
                }else{
                    more_menu.setVisibility(View.VISIBLE);
                    more_text.setText("Hide Themes");
                    more_image.setImageResource(R.drawable.login_more);
                }
            }
        });
        //button listener
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email=login_accounts.getText().toString();
                password=login_password.getText().toString();
                if(TextUtils.isEmpty(email)||TextUtils.isEmpty(password)){
                    Utils.ToastPositiveDialog(LoginActivity.this,"Login Error","Username cannot " +
                            "be empty!",R.drawable.icon_warning, Constants.EMPTY_FLAG);
                }else {
                    String regx="^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w{2,3}){1,3})$";
                    if(!email.matches(regx)){
                        Utils.ToastPositiveDialog(LoginActivity.this,"Login Error","Email format " +
                                "is not correct!",R.drawable.icon_warning, Constants.EMPTY_FLAG);
                    }else{
                        boolean check=auto_save_password.isChecked();
                        if(check){
                            editor.putString("email",email);
                            editor.putString("password",password);
                            editor.putBoolean("checked",check);
                        }else{
                            editor.putBoolean("checked",check);
                        }
                        editor.commit();
                        Constants.USER_EMAIL=email;
                        Constants.USER_PASSWORD=password;
                        //start a new activity
                        Intent intent=new Intent(LoginActivity.this,SplashActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });

        regist_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });

        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view=View.inflate(LoginActivity.this,R.layout
                        .dialog_resetpassword,null);
                et_email= (EditText) view.findViewById(R.id.et_emailaddress);
                btn_send= (Button) view.findViewById(R.id.btn_send);
                btn_resetcancel= (Button) view.findViewById(R.id.btn_cancel);
                final PopupWindow window=new PopupWindow(view,800,500);
                window.showAtLocation(btn_reset, Gravity.CENTER,0,-100);
                window.setAnimationStyle(R.style.popwin_anim_style);
                btn_send.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //to do:send an email

                    }
                });
                btn_resetcancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        window.dismiss();
                    }
                });


            }
        });
    }

    @Override
    protected void initView() {
        btn_login= (Button) findViewById(R.id.login_btn);
        btn_cancel= (Button) findViewById(R.id.btn_back);
        btn_reset= (Button) findViewById(R.id.btn_reset);
        loginpage= (RelativeLayout) findViewById(R.id.loginpage);
        auto_save_password= (CheckBox) findViewById(R.id.auto_save_password);
        regist_btn= (Button) findViewById(R.id.regist_btn);
        login_accounts= (EditText) findViewById(R.id.login_accounts);
        login_password= (EditText) findViewById(R.id.login_password);
        rg_theme= (RadioGroup) findViewById(R.id.rg_theme);
        more_menu= (LinearLayout) findViewById(R.id.moremenu);
        more= (RelativeLayout) findViewById(R.id.more);
        more_text= (TextView) findViewById(R.id.more_text);
        more_image= (ImageView) findViewById(R.id.more_image);
        sp=getSharedPreferences("config",MODE_PRIVATE);
        editor=sp.edit();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_login;
    }
}
