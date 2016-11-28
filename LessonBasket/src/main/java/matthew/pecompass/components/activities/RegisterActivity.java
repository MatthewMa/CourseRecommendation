package matthew.pecompass.components.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import matthew.pecompass.R;

/**
 * Created by Sihua on 2016/11/24.
 */
public class RegisterActivity extends BaseActivity {
    private SharedPreferences sp;
    private Button btn_regback;
    private Button btn_continue;
    private LinearLayout ll_register;
    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        btn_regback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                finish();
            }
        });
        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //to register to database:

                finish();
            }
        });
    }

    @Override
    protected void initView() {
        btn_regback= (Button) findViewById(R.id.btn_regback);
        btn_continue= (Button) findViewById(R.id.btn_continue);
        ll_register= (LinearLayout) findViewById(R.id.ll_register);
        sp=getSharedPreferences("config",MODE_PRIVATE);
        int id=sp.getInt("theme_id",R.id.theme_animal);
        switch (id){
            case R.id.theme_animal:
                ll_register.setBackgroundResource(R.drawable.theme_animal);
                break;
            case R.id.theme_autumn:
                ll_register.setBackgroundResource(R.drawable.theme_autumn);
                break;
            case R.id.theme_car:
                ll_register.setBackgroundResource(R.drawable.theme_car);
                break;
        }
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_register;
    }
}
