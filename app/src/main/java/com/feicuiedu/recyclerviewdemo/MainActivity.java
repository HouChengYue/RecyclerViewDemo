package com.feicuiedu.recyclerviewdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.feicuiedu.recyclerviewdemo.demoAbase.DemoListActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

// 5.0后,RecyclerView, V7包中
// ListView,GridView .....

// 1. RecyclerView 封装了 ViewHolder来重用View
// 2. RecyclerView来实现list,grid,staggered
// 3. 动画
// 4. 指定位置更新
public class MainActivity extends AppCompatActivity {

    @Bind(R.id.listView)ListView listView;

    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        String[] datas = {
          "基本运用demo"
        };
        adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                datas
        );
        listView.setAdapter(adapter);
    }

    @OnItemClick(R.id.listView)
    void onItemClick(int position){
        Intent intent = new Intent();
        switch (position){
            case 0:
                intent.setClass(this, DemoListActivity.class);
                startActivity(intent);
                break;
        }
    }


}
