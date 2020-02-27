package com.bksx.mobile.myapplication;

import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by Administrator on 2016/12/28.
 */

public class CityFragment extends Fragment {
    private ListView mListView;
    public MyListViewAdapter adapter;
    public List<String> cityDataList = new ArrayList<>();

    private List<String> townDataList = new ArrayList<>();
    public LinkedHashMap<String, String> map = new LinkedHashMap<>();

    private ProvinceFragment provinceFragment;
    private TabLayoutTitle lisenter;
    private TownData townDataListener;

    private DzxzHelper dzxzHelper;
    private int level;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_province,container,false);

        initView(view);

        return view;
    }

    private void initView(View view) {
        provinceFragment = new ProvinceFragment(true);
        mListView = (ListView) view.findViewById(R.id.listView_provinceAddress);

        adapter = new MyListViewAdapter(getContext(),cityDataList);

        mListView.setAdapter(adapter);

        dzxzHelper = new DzxzHelper(getContext(),true);

        //ListView条目点击监听器
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String strItemContent = cityDataList.get(position);
                if (strItemContent.equals("")){
                    Toast.makeText(getContext(),"你当前选择的城市无效", Toast.LENGTH_SHORT).show();
                }else {

                    lisenter.setTitle(strItemContent,position);

                    Log.i("TAG", "===strItemContent" + strItemContent);
                    // 获取选择的城市的id
                    String cd_id_c = dzxzHelper.getCodeId(map,strItemContent);

                 //   if(level!=2){
                        // 初始化县级对象
                        dzxzHelper.initTownView(cd_id_c);

                        // 获取县城的数据集合
                        initTownData();
                        Log.i("TAG", "===onItemClick1: " + cd_id_c);
                        Log.i("TAG", "===onItemClick2: " + map.toString());

                        for (int i = 0; i < townDataList.size() ; i++) {
                            Log.i(TAG, "====县城的数据集合: " + townDataList.get(i));
                        }
                        townDataListener.setTownData(townDataList,dzxzHelper.map_t);
                //    }
                }
            }
        });
    }

    public void setLevel(int i) {
        this.level=i;
    }


    public interface TabLayoutTitle{
        void setTitle(String itemTitle, int position);
    }

    public void setTabLayoutTitle(TabLayoutTitle lisenter){
        this.lisenter = lisenter;
    }

    private void initTownData() {
        for (int i = 0; i < dzxzHelper.map_t.size() ; i++) {
            townDataList.add(dzxzHelper.values_t[i]);
        }
    }

    public interface TownData{
        void setTownData(List<String> cityList, LinkedHashMap<String, String> map);
    }

    public void setCityAddressList(TownData townDataListener){
        this.townDataListener = townDataListener;
    }

}
