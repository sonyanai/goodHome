package jp.techacademy.son.goodhome;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by taiso on 2018/04/17.
 */


class ViewHolder {
    TextView companyTextView;
    TextView blackNameTextView;
    TextView caseTextView;
    TextView dateTextView;
}

public class BusinessDataArrayListAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private int layoutId;
    private ArrayList<BusinessData> businessDataArrayList = new ArrayList<BusinessData>();

    public BusinessDataArrayListAdapter(Context context, int layoutId) {
        super();
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.layoutId = layoutId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        String mCompanyName = businessDataArrayList.get(position).getCompanyName();
        String mBlackName = businessDataArrayList.get(position).getBlackName();
        String mCase = businessDataArrayList.get(position).getCase();
        String mDate = businessDataArrayList.get(position).getDate();


        ViewHolder holder;
        if (convertView == null) {
            // main.xml の <GridView .../> に grid_items.xml を inflate して convertView とする
            convertView = inflater.inflate(layoutId, parent, false);
            // FolderViewHolder を生成
            holder = new ViewHolder();
            holder.companyTextView = (TextView) convertView.findViewById(R.id.companyTextView);
            holder.blackNameTextView = (TextView) convertView.findViewById(R.id.blackNameTextView);
            holder.caseTextView = (TextView) convertView.findViewById(R.id.caseTextView);
            holder.dateTextView = (TextView) convertView.findViewById(R.id.dateTextView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        if (mCompanyName != null) {
            holder.companyTextView.setText(mCompanyName);
        }
        if (mBlackName != null) {
            holder.blackNameTextView.setText(mBlackName);
        }
        if (mCase != null) {
            holder.caseTextView.setText(mCase);
        }
        if (mDate != null) {
            holder.dateTextView.setText(mDate);
        }


        return convertView;
    }

    @Override
    public int getCount() {
        // List<String> imgList の全要素数を返す
        return businessDataArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        //return null;
        return businessDataArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
        //return articleDataArrayList.get(position).getId();
    }

    public void setArticleDataArrayList(ArrayList<BusinessData> articleDataArrayList) {
        this.businessDataArrayList = articleDataArrayList;
    }
}



