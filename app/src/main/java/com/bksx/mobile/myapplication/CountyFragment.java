package com.bksx.mobile.myapplication;

import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/12/28.
 */

public class CountyFragment extends Fragment {
    private ListView mListView;
    public MyListViewAdapter adapter;
    public TabLayoutTitle lisenter;
    public List<String> townAddressList = new ArrayList<>();
    public LinkedHashMap<String, String> map = new LinkedHashMap<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_province,container,false);

        initView(view);

        return view;
    }

    private void initView(View view) {
        mListView = view.findViewById(R.id.listView_provinceAddress);

        adapter = new MyListViewAdapter(getContext(),townAddressList);

        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("TAG", "===onItemClick: " + map.toString());
                //获取前居住地址对应的代码值
                for (String key : map.keySet()) {
                    if (map.get(key).toString().trim().equals(townAddressList.get(position))){
                        lisenter.setTitle(townAddressList.get(position),key.toString().trim());
                    }
                }
            }
        });

    }

    public interface TabLayoutTitle{
        void setTitle(String itemTitle, String codeId);
    }

    public void setTabLayoutTitle(TabLayoutTitle lisenter){
        this.lisenter = lisenter;
    }

}
