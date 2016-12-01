package matthew.pecompass.components.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.security.PolicySpi;

import matthew.pecompass.R;
import matthew.pecompass.components.Model.Karma;
import matthew.pecompass.components.Model.KarmaTask;
import matthew.pecompass.components.Model.User;
import matthew.pecompass.components.activities.RewardActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment {

    private ListView lv_topkarma;

    private ExpandableListView el_tasks;
    private int[] images={R.drawable.friend_recommendation,R.drawable.system_recommendation};
    private TextView tv_currkarma;
    private Button btn_setting;
    private Button btn_reward;
    public AccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_account, container, false);
        lv_topkarma= (ListView) v.findViewById(R.id.lv_topkarma);
        el_tasks= (ExpandableListView) v.findViewById(R.id.el_tasks);
        tv_currkarma= (TextView) v.findViewById(R.id.tv_currkarma);
        btn_setting= (Button) v.findViewById(R.id.btn_setting);
        btn_reward= (Button) v.findViewById(R.id.btn_reward);
        tv_currkarma.setText("Right now you have "+Karma.currKarma+" karma!");
        lv_topkarma.setAdapter(new BaseAdapter() {
            private ImageView iv_rank;
            private TextView tv_name;
            private TextView tv_karma;
            @Override
            public int getCount() {
                return Karma.highestKarmaUsers.size();
            }

            @Override
            public Object getItem(int position) {
                return Karma.highestKarmaUsers.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View v=View.inflate(AccountFragment.this.getActivity(),R.layout
                        .listitem_karmauser,null);
                iv_rank= (ImageView) v.findViewById(R.id.iv_rank);
                tv_name= (TextView) v.findViewById(R.id.tv_name);
                tv_karma= (TextView) v.findViewById(R.id.tv_karma);
                User currentUser=(User)getItem(position);
                switch (position){
                    case 0:
                        iv_rank.setImageResource(R.drawable.number_1);
                        break;
                    case 1:
                        iv_rank.setImageResource(R.drawable.number_2);
                        break;
                    case 2:
                        iv_rank.setImageResource(R.drawable.number_3);
                        break;
                }
                tv_name.setText(currentUser.getUserName().toString());
                tv_karma.setText(currentUser.getKarma()+"");
                return v;
            }
        });
        el_tasks.setAdapter(new BaseExpandableListAdapter() {
            @Override
            public int getGroupCount() {
                return Karma.taskNames.size();
            }

            @Override
            public int getChildrenCount(int groupPosition) {
                return Karma.karmaTasks.get(Karma.taskNames.get(groupPosition)).size();
            }

            @Override
            public Object getGroup(int groupPosition) {
                return Karma.taskNames.get(groupPosition);
            }

            @Override
            public Object getChild(int groupPosition, int childPosition) {
                return Karma.karmaTasks.get(Karma.taskNames.get(groupPosition)).get(childPosition);
            }

            @Override
            public long getGroupId(int groupPosition) {
                return groupPosition;
            }

            @Override
            public long getChildId(int groupPosition, int childPosition) {
                return childPosition;
            }

            @Override
            public boolean hasStableIds() {
                return true;
            }

            @Override
            public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
                View v=View.inflate(AccountFragment.this.getActivity(),R.layout
                        .groupview_notification,
                        null);
                ImageView iv_gicon= (ImageView) v.findViewById(R.id.iv_gicon);
                TextView tv_gtitle= (TextView) v.findViewById(R.id.tv_gtitle);
                iv_gicon.setImageResource(images[groupPosition]);
                tv_gtitle.setText(Karma.taskNames.get(groupPosition).toString());
                return v;
            }

            @Override
            public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
                KarmaTask task=(KarmaTask) getChild(groupPosition,childPosition);
                View v=View.inflate(AccountFragment.this.getActivity(),R.layout
                        .childview_notification,
                        null);
                TextView tv_taskname= (TextView) v.findViewById(R.id.tv_taskname);
                TextView tv_reward= (TextView) v.findViewById(R.id.tv_taskreward);
                tv_taskname.setText(task.taskName.toString());
                tv_reward.setText(task.karmaRewards+"");
                return v;
            }

            @Override
            public boolean isChildSelectable(int groupPosition, int childPosition) {
                return true;
            }
        });
        btn_reward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AccountFragment.this.getActivity().startActivity(new Intent(AccountFragment.this
                        .getActivity(),
                        RewardActivity
                        .class));
                AccountFragment.this.getActivity().finish();

            }
        });
        return v;
    }

}
