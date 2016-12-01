package matthew.pecompass.components.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import matthew.pecompass.R;
import matthew.pecompass.components.Model.Karma;
import matthew.pecompass.components.utils.Utils;

public class RewardActivity extends BaseActivity {

    private TextView tv_title;
    private GridView gv_gifts;
    private String[] giftNames;
    private int[] giftImgs;
    private int[] karmaCosts;
    private Button btn_back;
    @Override
    protected void initData() {
        giftNames=new String[]{"$10 Gift Card(10 karma)","$20 Tuition Deposit(18 karma)","32GB " +
                "USB(26 karma)","BlueTooth HeadSet(33 karma)","$50 Gift Card(40 Karma)","$100 " +
                "Tuition Deposit(75 Karma)"};
        karmaCosts=new int[]{10,18,26,33,40,75};
        giftImgs=new int[]{R.drawable.gift_card_10,R.drawable.tuition_deposit_20,R.drawable.usb_32,R
                .drawable
                .bluetooth_hreadset,R.drawable.gift_card_50,R.drawable.tuition_deposit_20};

    }

    @Override
    protected void initListener() {
        gv_gifts.setAdapter(new BaseAdapter() {
            private ImageView iv_gifticon;
            private TextView tv_explain;
            @Override
            public int getCount() {
                return giftNames.length;
            }

            @Override
            public Object getItem(int position) {
                return giftNames[position];
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View v=View.inflate(RewardActivity.this,R.layout.griditem_gift,null);
                iv_gifticon= (ImageView) v.findViewById(R.id.iv_gifticon);
                tv_explain= (TextView) v.findViewById(R.id.tv_explain);
                iv_gifticon.setImageResource(giftImgs[position]);
                tv_explain.setText(giftNames[position].toString());
                return v;
            }
        });
        gv_gifts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(Karma.currKarma<karmaCosts[position]){
                    Utils.ToastPositiveDialog(RewardActivity.this,"Not enough karma","You have" +
                            " not enough karma to buy this product!Please do tasks and get " +
                            "more karma!",R.drawable.icon_warning,0);
                }else{
                    Karma.currKarma-=karmaCosts[position];
                    Utils.ToastPositiveDialog(RewardActivity.this,"Buy successfully",
                            "Congratulations,you have bought it,please check your email!",R
                                    .drawable.rating_congra,0);
                    tv_title.setText("Now you have "+ Karma.currKarma+" Karma,enjoy shopping!");
                }
            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RewardActivity.this,IndexActivity.class));
                finish();
            }
        });
    }

    @Override
    protected void initView() {
        tv_title= (TextView) findViewById(R.id.tv_title);
        gv_gifts= (GridView) findViewById(R.id.gv_gifts);
        btn_back= (Button) findViewById(R.id.btn_back);
        tv_title.setText("Now you have "+ Karma.currKarma+" Karma,enjoy shopping!");

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_reward;
    }

    @Override
    public void onBackPressed() {

    }
}
