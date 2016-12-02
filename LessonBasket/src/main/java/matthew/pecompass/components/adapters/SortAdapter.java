package matthew.pecompass.components.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SectionIndexer;
import android.widget.TextView;

import java.util.List;

import matthew.pecompass.R;
import matthew.pecompass.components.Model.SortModel;

/**
 * Created by Sihua on 2016/12/1.
 */

public class SortAdapter extends BaseAdapter implements SectionIndexer {
    private List<String> list = null;
    private Context mContext;

    public SortAdapter(Context context, List<String> list ) {
        this.mContext = context;
        this.list = list;
    }

    /**
     * @param list
     */
    public void updateListView(List<String> list){
        this.list = list;
        notifyDataSetChanged();
    }

    public int getCount() {
        return this.list.size();
    }

    public String getItem(int position) {
        return list.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup arg2) {
        ViewHolder viewHolder = null;
        final String mContent = list.get(position);
        if (view == null) {
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.sortlistview_item, null);
            viewHolder.tvTitle = (TextView) view.findViewById(R.id.title);
            viewHolder.tvLetter = (TextView) view.findViewById(R.id.catalog);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        //// 获取首字母的assii值
        int section = getSectionForPosition(position);
        //通过首字母的assii值来判断是否显示字母
        int positionForSelection = getPositionForSection(section);

        viewHolder.tvLetter.setOnClickListener(null);

        if(position == getPositionForSection(section)){
            viewHolder.tvLetter.setVisibility(View.VISIBLE);
            viewHolder.tvLetter.setText(mContent.charAt(0)+"");
        }else{
            viewHolder.tvLetter.setVisibility(View.GONE);
        }

        viewHolder.tvTitle.setText(this.list.get(position).toString());

        return view;

    }



    final static class ViewHolder {
        TextView tvLetter;
        TextView tvTitle;
    }


    public int getSectionForPosition(int position) {
        return list.get(position).charAt(0);
    }

    public int getPositionForSection(int section) {
        for (int i = 0; i < getCount(); i++) {
            String sortStr = list.get(i).charAt(0)+"";
            char firstChar = sortStr.toUpperCase().charAt(0);
            if (firstChar == section) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public Object[] getSections() {
        return null;
    }
}
