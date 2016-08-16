package com.feicuiedu.recyclerviewdemo.demoAbase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.feicuiedu.recyclerviewdemo.R;
import com.feicuiedu.recyclerviewdemo.SimpleAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DemoListActivity extends AppCompatActivity {

    @Bind(R.id.recyclerView) RecyclerView recyclerView;
    private SimpleAdapter simpleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_list);
        ButterKnife.bind(this);
        // setLayout
        // recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        // setAdapter
        simpleAdapter = new SimpleAdapter();
        recyclerView.setAdapter(simpleAdapter);

        for (int i = 0; i < 100; i++) {
            simpleAdapter.addItems("我是第 " + i + " 条数据");
        }
        simpleAdapter.notifyDataSetChanged();
    }
}
