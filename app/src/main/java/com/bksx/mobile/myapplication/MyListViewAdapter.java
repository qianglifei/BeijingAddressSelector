package com.bksx.mobile.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/28.
 */

public class MyListViewAdapter extends BaseAdapter {
    private static boolean isClick = false;   //选中项
    private Context mConext;
    private LayoutInflater mInflater;
    private List<String> mList = new ArrayList<>();
    private int position ;
    private boolean isVisiable = false;
    public MyListViewAdapter(Context context, List<String> mListView){
        mConext = context;
        this.mList = mListView;
        mInflater = (LayoutInflater) mConext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        MyViewHolder mHolder = null;
        if (convertView == null){
            convertView = mInflater.inflate(R.layout.item_listview_province,viewGroup,false);
            mHolder = new MyViewHolder(convertView);
            convertView.setTag(mHolder);
        }else {
            mHolder = (MyViewHolder) convertView.getTag();
        }
        mHolder.textViewAddress.setText(mList.get(i).toString());

        return convertView;
    }



    class MyViewHolder{

        private TextView textViewAddress;
        private ImageView imageViewCheck;

        public MyViewHolder(View view){
            textViewAddress = (TextView) view.findViewById(R.id.textView_address);
            imageViewCheck = (ImageView) view.findViewById(R.id.imageView_check);
        }
    }

    public void isClear(boolean isClear, List<String> list){
        if (isClear){
            mList.clear();
        }
        mList.addAll(list);
        notifyDataSetChanged();
    }

}
