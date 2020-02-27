package com.bksx.mobile.myapplication;

import android.annotation.SuppressLint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;


import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;


/**
 * Created by Administrator on 2017/1/5.
 */

public class MyDialogFragment extends DialogFragment {

    private  int flag;
    private TabLayout tab;
    private NoScrollViewPager viewPager;
    private ImageView imageView;

    private MyAdapter adapter;

    public static final String[] tabTitle = new String[]{"请选择","",""};

    private List<Fragment> fragmentList = new ArrayList<>();

    private ProvinceFragment provinceFragment;
    private CityFragment cityFragment;
    private CountyFragment countyFragment;

    private OnPermanentAddress onPermanentAddressListener;

    private boolean needBeijing;
    @SuppressLint({"NewApi", "ValidFragment"})
    public MyDialogFragment(boolean needBeijing){
        this.needBeijing = needBeijing;
    }
    @SuppressLint({"NewApi", "ValidFragment"})
    public MyDialogFragment(boolean needBeijing,int flag){
        this.needBeijing = needBeijing;
        this.flag=flag;
    }

    public MyDialogFragment(){
       super();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.address_pop,container);
        //初始化Fragment
        initFragment();

        initView(view);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);


        return view;

    }

    private void initView(View view) {
        tab = view.findViewById(R.id.tab);
        viewPager =  view.findViewById(R.id.viewpager_address);

        viewPager.setScroll(false);

        //默认只会加载一个界面
        viewPager.setOffscreenPageLimit(3);

        adapter = new MyAdapter(getChildFragmentManager(),fragmentList);
        viewPager.setAdapter(adapter);

        //viewPager和TabLayout做关联
        tab.setupWithViewPager(viewPager);

        //设置可以滑动
        tab.setTabMode(TabLayout.MODE_SCROLLABLE);



        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initFragment() {
        provinceFragment = new ProvinceFragment(needBeijing);
        cityFragment = new CityFragment();
        cityFragment.setLevel(flag);
        countyFragment = new CountyFragment();
//        if(flag==2){
//            fragmentList.add(provinceFragment);
//            fragmentList.add(cityFragment);
//        }else{
            fragmentList.add(provinceFragment);
            fragmentList.add(cityFragment);
            fragmentList.add(countyFragment);
//        }

        provinceFragment.setTabLayoutTitle(new ProvinceFragment.TabLayoutTitle() {
            @Override
            public void setTitle(String itemTitle) {
                tabTitle[0] = itemTitle;
                tabTitle[1] = "请选择";
                tabTitle[2] = "";
                adapter.notifyDataSetChanged();
                viewPager.setCurrentItem(1);

                Log.i("TAG", "===setTitle: " + itemTitle);
            }
        });

        provinceFragment.setCityAddressList(new ProvinceFragment.CityData() {
            @Override
            public void setCityData(List<String> cityList, LinkedHashMap<String, String> cityHashMap) {
                cityFragment.map = cityHashMap;
                cityFragment.adapter.isClear(true,cityList);
                cityList.clear();
            }
        });

        cityFragment.setTabLayoutTitle(new CityFragment.TabLayoutTitle() {
            @Override
            public void setTitle(String itemTitle, int position) {
                tabTitle[1] = itemTitle;
                tabTitle[2] = "请选择";
                adapter.notifyDataSetChanged();
                viewPager.setCurrentItem(2);
            }
        });

        cityFragment.setCityAddressList(new CityFragment.TownData() {
            @Override
            public void setTownData(List<String> cityList, LinkedHashMap<String, String> map) {
                if (cityList.size() == 0){
                    getAddressCodeId();
                }else {
                    countyFragment.map = map;
                    Log.i("TAG", "setTownData: " + cityList.size());
                    countyFragment.adapter.isClear(true,cityList);
                    cityList.clear();
                    if (flag == 2){
                        getAddressCodeId();
                    }
                }

            }
        });

        countyFragment.setTabLayoutTitle(new CountyFragment.TabLayoutTitle() {
            @Override
            public void setTitle(String itemTitle, String codeId) {
                tabTitle[2] = itemTitle;
                provinceFragment.provinceAddressList.clear();
                adapter.notifyDataSetChanged();
                onPermanentAddressListener.setPermanentAddress(tabTitle,codeId);
                getDialog().cancel();
                tabTitle[0] = "请选择";
                tabTitle[1] = "";
                tabTitle[2] = "";
            }
        });
    }

    private void getAddressCodeId() {
        if (cityFragment.map != null && cityFragment.map.size() != 0){
            for (String key : cityFragment.map.keySet()) {
                if (cityFragment.map.get(key).toString().trim().equals(tabTitle[1])){
                    Log.i("TAG", "====submitNetData: " + key.toString());
                    onPermanentAddressListener.setPermanentAddress(tabTitle,key);
                }
            }
        }
        getDialog().cancel();
        tabTitle[0] = "请选择";
        tabTitle[1] = "";
        tabTitle[2] = "";
    }

    @Override
    public void onStart() {
        super.onStart();
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics( dm );
        getDialog().getWindow().setLayout( dm.widthPixels,dm.heightPixels);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable( 0xff000000) );
        WindowManager.LayoutParams wlp = getDialog().getWindow().getAttributes();
        wlp.gravity = Gravity.CENTER;
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        wlp.height = 600;
        getDialog().getWindow().setAttributes(wlp);
    }

    public interface OnPermanentAddress{
        void setPermanentAddress(String[] address, String codeId);
    }

    public void setOnPernamentAddress(OnPermanentAddress onPermanentAddressListener){
        this.onPermanentAddressListener = onPermanentAddressListener;
    }
}
