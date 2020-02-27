package com.bksx.mobile.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static android.content.ContentValues.TAG;


/**
 * Created by Administrator on 2016/12/28.
 */

public class ProvinceFragment extends Fragment {
    private ListView mListView;
    public MyListViewAdapter adapter;

    public List<String> provinceAddressList = new ArrayList<>();
    private List<String> cityAddressList = new ArrayList<>();

    public DzxzHelper dzxzHelper;
    private TextView textViewAddress;


    private TabLayoutTitle lisenter;
    private CityData cityDataListener;


    private boolean needBeijing;
    @SuppressLint({"NewApi", "ValidFragment"})
    public ProvinceFragment(boolean needBeijing){
        super();
        this.needBeijing = needBeijing;
    }

    public ProvinceFragment(){
        super();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_province,container,false);

        initView(view);

        return view;
    }

    private void initView(View view) {
        mListView =  view.findViewById(R.id.listView_provinceAddress);

        //初始化数据
        initProvinceData();

        adapter = new MyListViewAdapter(getContext(),provinceAddressList);

        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                String strProvinceAddress = provinceAddressList.get(position).toString();

                Log.i(TAG, "onItemClick1: " + lisenter);
                lisenter.setTitle(strProvinceAddress);

                //获取选择的cd_id值
                String cd_id_p = dzxzHelper.getCodeId(dzxzHelper.map_p, strProvinceAddress);

                //初始化市级对象
                dzxzHelper.initAgencyView(cd_id_p);

                //得到与省级对应的城市
                initCityData();
                Log.i(TAG, "onItemClick2: " + cityDataListener);

                cityDataListener.setCityData(cityAddressList,dzxzHelper.map_c);

          }
        });

    }

    private void initProvinceData() {
        dzxzHelper = new DzxzHelper(getContext(),needBeijing);
        for (int i = 0; i < dzxzHelper.values_p.length ; i++) {
            provinceAddressList.add(dzxzHelper.values_p[i]);
        }

    }

    private void initCityData() {
        for (int i = 0; i < dzxzHelper.values_c.length ; i++) {
            cityAddressList.add(dzxzHelper.values_c[i]);
        }
    }

    public interface TabLayoutTitle{
        void setTitle(String itemTitle);
    }

    public void setTabLayoutTitle(TabLayoutTitle lisenter){
        this.lisenter = lisenter;
    }

    public interface CityData{
        void setCityData(List<String> cityList, LinkedHashMap<String, String> cityLinkedHashMap);
    }

    public void setCityAddressList(CityData cityDataListener){
        this.cityDataListener = cityDataListener;
    }
}
