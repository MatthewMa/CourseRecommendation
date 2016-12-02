package matthew.pecompass.components.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import matthew.pecompass.R;
import matthew.pecompass.components.Model.Courses;
import matthew.pecompass.components.Model.SortModel;
import matthew.pecompass.components.adapters.SortAdapter;
import matthew.pecompass.components.views.ClearEditText;
import matthew.pecompass.components.views.SideBar;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {
    private ListView sortListView;
    private SideBar sideBar;
    private TextView dialog;
    private SortAdapter adapter;
    private ClearEditText mClearEditText;

    //scroll identification
    private int lastFirstVisibleItem = -1;
    private List<String> SourceDateList;

    private LinearLayout titleLayout;
    private TextView title;
    private View v;

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_search, container, false);
        initViews();
        return v;
    }

    private void initViews() {
        titleLayout = (LinearLayout) v.findViewById(R.id.title_layout);
        title = (TextView) v.findViewById(R.id.title);


        sideBar = (SideBar) v.findViewById(R.id.sidrbar);
        dialog = (TextView) v.findViewById(R.id.dialog);
        sideBar.setTextView(dialog);

        //设置右侧触摸监听
        sideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {

            @Override
            public void onTouchingLetterChanged(String s) {
                //该字母首次出现的位置
                int position = adapter.getPositionForSection(s.charAt(0));
                if (position != -1) {
                    sortListView.setSelection(position);
                }

            }
        });

        sortListView = (ListView) v.findViewById(R.id.country_lvcountry);
        sortListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //这里要利用adapter.getItem(position)来获取当前position所对应的对象
                Toast.makeText(SearchFragment.this.getActivity(), (adapter.getItem(position))
                        + "=" + position, Toast.LENGTH_SHORT).show();
            }
        });

        SourceDateList=new ArrayList<>();
        SourceDateList=Courses.allCourseNames;
        // 根据a-z进行排序源数据
        Collections.sort(SourceDateList);
        adapter = new SortAdapter(getActivity(), SourceDateList);
        sortListView.setAdapter(adapter);

        sortListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
                                 int totalItemCount) {

//                LogUtils.i(+visibleItemCount+"=当前对呀的Item是="+firstVisibleItem);

                //字母连续断层使不能置顶，例如  D （空） F使D到F阶段不存在置顶
                int section;
                try{
                    section = adapter.getSectionForPosition(firstVisibleItem);
                }catch (Exception e){
                    return ;
                }
                int nextSecPosition = adapter.getPositionForSection(section + 1);
                //解决断层置顶
                for (int i = 1; i < 30; i++) {
                    //26个英文字母充分循环
                    if (nextSecPosition == -1) {
                        //继续累加
                        int data = section + 1 + i;
                        nextSecPosition = adapter.getPositionForSection(data);
                    } else {
                        break;
                    }
                }


                if (firstVisibleItem != lastFirstVisibleItem) {
                    ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) titleLayout.getLayoutParams();
                    params.topMargin = 0;
                    titleLayout.setLayoutParams(params);
                    title.setText(String.valueOf((char) section));

                }
                if (nextSecPosition == firstVisibleItem + 1) {
                    View childView = view.getChildAt(0);
                    if (childView != null) {
                        int titleHeight = titleLayout.getHeight();
                        int bottom = childView.getBottom();
                        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) titleLayout
                                .getLayoutParams();
                        if (bottom < titleHeight) {
                            float pushedDistance = bottom - titleHeight;
                            params.topMargin = (int) pushedDistance;
                            titleLayout.setLayoutParams(params);
                        } else {
                            if (params.topMargin != 0) {
                                params.topMargin = 0;
                                titleLayout.setLayoutParams(params);
                            }
                        }
                    }
                }
                lastFirstVisibleItem = firstVisibleItem;
            }
        });


        mClearEditText = (ClearEditText) v.findViewById(R.id.filter_edit);

        //根据输入框输入值的改变来过滤搜索
        mClearEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //当输入框里面的值为空，更新为原来的列表，否则为过滤数据列表
                filterData(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void filterData(String filterStr) {
        List<String> filterDateList = new ArrayList<String>();

        if (TextUtils.isEmpty(filterStr)) {
            filterDateList = SourceDateList;
            titleLayout.setVisibility(View.VISIBLE);
            title.setText("A");
        } else {
            titleLayout.setVisibility(View.GONE);
            filterDateList.clear();
            for (String sortModel : SourceDateList) {
                String name = sortModel;
                if (name.toLowerCase().indexOf(filterStr.toString().toLowerCase()) != -1 ) {
                    filterDateList.add(sortModel);
                }
            }
        }

        Collections.sort(filterDateList);
        adapter.updateListView(filterDateList);
    }


}
