package matthew.pecompass.components.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import matthew.pecompass.R;
import matthew.pecompass.components.Model.Karma;
import matthew.pecompass.components.Model.User;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment {

    private ListView lv_topkarma;

    public AccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_account, container, false);
        lv_topkarma= (ListView) v.findViewById(R.id.lv_topkarma);
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
        return v;
    }

}
