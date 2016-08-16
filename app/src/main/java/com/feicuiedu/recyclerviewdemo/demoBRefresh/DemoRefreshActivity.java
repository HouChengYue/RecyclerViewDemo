package com.feicuiedu.recyclerviewdemo.demoBRefresh;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.feicuiedu.recyclerviewdemo.R;
import com.feicuiedu.recyclerviewdemo.SimpleAdapter;
import com.mugen.Mugen;
import com.mugen.MugenCallbacks;

import butterknife.Bind;
import butterknife.ButterKnife;

/** 带下拉刷新及上拉加载功能的RecyclerView的实现*/
public class DemoRefreshActivity extends AppCompatActivity implements
        SwipeRefreshLayout.OnRefreshListener,MugenCallbacks{

    @Bind(R.id.recyclerView)RecyclerView recyclerView;
    @Bind(R.id.refreshLayout) SwipeRefreshLayout refreshLayout;
    @Bind(R.id.progressBar) ProgressBar progressBar;

    private SimpleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demb_refresh);
        ButterKnife.bind(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SimpleAdapter();
        recyclerView.setAdapter(adapter);

        // 配置下拉刷新
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setColorSchemeColors(R.color.colorPrimary);
        // 配置上拉加载(用来监听指定list是否已滑动到底部)
        Mugen.with(recyclerView, this).start();
    }

    // 当开始下拉操作时，将来触发此方法,用来做下拉的刷新工作
    @Override public void onRefresh() {
        // 模型操作
        new Thread(new Runnable() {
            @Override public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {}

                adapter.clear();
                for (int i = 0; i < 30; i++) {
                    adapter.addItems("我是刷新到的 " + i + " 条数据");
                }
                runOnUiThread(new Runnable() {
                    @Override public void run() {
                        // 结果“下拉”
                        refreshLayout.setRefreshing(false);
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        }).start();
    }

    // 当快滑动到底部时，将来触发此方法(会受isLoading和hasLoadedAllItems的影响)
    @Override public void onLoadMore() {
        progressBar.setVisibility(View.VISIBLE);
        new Thread(new Runnable() {
            @Override public void run() {
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                }
                for (int i = 0; i < 30; i++) {
                    adapter.addItems("我是新加载的 " + i + " 条数据");
                }
                runOnUiThread(new Runnable() {
                    @Override public void run() {
                        progressBar.setVisibility(View.GONE);
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        }).start();
    }

    @Override public boolean isLoading() {
        return progressBar.getVisibility() == View.VISIBLE;
    }

    @Override public boolean hasLoadedAllItems() {
        return false;
    }
}
