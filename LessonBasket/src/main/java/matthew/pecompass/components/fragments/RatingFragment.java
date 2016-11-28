package matthew.pecompass.components.fragments;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import matthew.pecompass.R;
import matthew.pecompass.components.Constants.Constants;
import matthew.pecompass.components.Model.Courses;
import matthew.pecompass.components.Model.HighestRatedCourse;
import matthew.pecompass.components.Model.UnratedCourse;
import matthew.pecompass.components.activities.RecommendActivity;
import matthew.pecompass.components.utils.Utils;

/**
 * A simple {@link Fragment} subclass.
 */
public class RatingFragment extends Fragment {


    private ListView lv_ratecourses;
    private ExpandableListView lv_highestrating;
    private List<String> departments;
    private Spinner spinner_condition;
    private String[] sortedCondition={"By Week","By Month","By Year"};
    private int removeUnratedId;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_rating, container, false);
        lv_ratecourses= (ListView) v.findViewById(R.id.lv_ratecourses);
        lv_highestrating= (ExpandableListView) v.findViewById(R.id.lv_highestrating);
        spinner_condition= (Spinner) v.findViewById(R.id.spinner_condition);
        departments=new ArrayList<>();
        departments.add("All");
        departments.add("Computer Science");
        departments.add("Mechanical Engineer");
        departments.add("Math And Statistics");
        removeUnratedId=getActivity().getIntent().getIntExtra("finishUnratedCrn",-1);
        if(removeUnratedId!=-1){
            Courses.unratedCourses.remove(removeUnratedId);
            Utils.ToastPositiveDialog(getActivity(),"Finish rating","Congratulations,you have " +
                    "finished the rating and get 2 karma, please check your account!",R.drawable
                    .rating_congra,0);
            //add rated courses:

            getActivity().getIntent().putExtra("finishUnratedCrn",-1);
        }
        spinner_condition.setAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout
                .simple_spinner_item,sortedCondition));

        //expandable listview
        lv_highestrating.setAdapter(new BaseExpandableListAdapter() {
            @Override
            public int getGroupCount() {
                return Courses.departments.length;
            }

            @Override
            public int getChildrenCount(int groupPosition) {
                return Courses.highestRatedCourses.get(Courses.departments[groupPosition]).size();
            }

            @Override
            public Object getGroup(int groupPosition) {
                return Courses.departments[groupPosition];
            }

            @Override
            public Object getChild(int groupPosition, int childPosition) {
                return Courses.highestRatedCourses.get(Courses.departments[groupPosition]).get
                        (childPosition);
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
                TextView tv=new TextView(RatingFragment.this.getActivity());
                tv.setTextSize(22);
                tv.setTextColor(Color.WHITE);
                tv.setBackgroundResource(android.R.color.holo_blue_bright);
                tv.setText(getGroup(groupPosition).toString());
                tv.setPadding(20,5,5,5);
                return tv;
            }

            @Override
            public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
                View view=View.inflate(RatingFragment.this.getActivity(),R.layout
                        .listitem_highestratings,
                        null);
                TextView tv_crn= (TextView) view.findViewById(R.id.tv_crn);
                TextView tv_instructor= (TextView) view.findViewById(R.id.tv_instructorname);
                TextView tv_ratings= (TextView) view.findViewById(R.id.tv_ratings);
                ProgressBar pb_ratings= (ProgressBar) view.findViewById(R.id.tv_progress);
                HighestRatedCourse child = (HighestRatedCourse) getChild(groupPosition, childPosition);
                tv_crn.setText(child.getCrn()+"");
                tv_instructor.setText(child.getInstructorname());
                tv_ratings.setText(child.getScore()+"");
                pb_ratings.setProgress(Math.round(child.getScore()));
                return view;
            }

            @Override
            public boolean isChildSelectable(int groupPosition, int childPosition) {
                return true;
            }
        });
        lv_ratecourses.setAdapter(new BaseAdapter() {

            @Override
            public int getCount() {
                return Courses.unratedCourses.size();
            }

            @Override
            public Object getItem(int position) {
                return Courses.unratedCourses.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                UnratedViewHolder holder=null;
                if(convertView==null){
                    View v=View.inflate(RatingFragment.this.getActivity(),R.layout
                            .listitem_unratedcourse,null);
                    holder=new UnratedViewHolder();
                    holder.tv_crn= (TextView) v.findViewById(R.id.tv_crn);
                    holder.tv_coursename= (TextView) v.findViewById(R.id.tv_coursename);
                    holder.tv_score= (TextView) v.findViewById(R.id.tv_score);
                    holder.ll_unratedlistitem= (LinearLayout) v.findViewById(R.id.ll_unratedlistitem);
                    convertView=v;
                    convertView.setTag(holder);
                }
                holder= (UnratedViewHolder) convertView.getTag();
                UnratedCourse currentCourse= (UnratedCourse) getItem(position);
                holder.tv_crn.setText(currentCourse.getCrn()+"");
                holder.tv_coursename.setText(currentCourse.getCoursename());
                holder.tv_score.setText(currentCourse.getScore()+"");
                if(position%2==1){
                    holder.ll_unratedlistitem.setBackgroundResource(android.R.color.holo_blue_bright);
                }
                return convertView;
            }
        });
        spinner_condition.setSelection(0);
        //itemlistener
        setItemListener();
        return v;

    }

    private void setItemListener() {
        lv_ratecourses.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Courses.currUnratedCrn=position;
                //do survey:
                Utils.ToastPositiveAndNegativeDialog(RatingFragment.this.getActivity(),"Begin " +
                        "Rating","Now let us do a rating survey and you can get 2 karma when you " +
                        "finish it.Thanks for your cooperation.",R.drawable.help, Constants.JUMP_TO_RATING);
            }
        });
        lv_highestrating.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Courses.currHighestRatingGroupIndex=groupPosition;
                Courses.currHighestRatingChildIndex=childPosition;
                Intent intent=new Intent(RatingFragment.this.getActivity(),RecommendActivity.class);
                intent.putExtra("currHighestRatingGroupIndex",Courses.currHighestRatingGroupIndex);
                intent.putExtra("currHighestRatingChildIndex",Courses.currHighestRatingChildIndex);
                RatingFragment.this.getActivity().startActivity(intent);
                return false;
            }
        });
    }

    class UnratedViewHolder{
        public TextView tv_crn;
        public TextView tv_coursename;
        public TextView tv_score;
        private LinearLayout ll_unratedlistitem;
    }

}
