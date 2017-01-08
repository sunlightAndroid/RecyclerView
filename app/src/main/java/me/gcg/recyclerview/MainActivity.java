package me.gcg.recyclerview;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import me.gcg.recyclerview.bean.DataModel;
import me.gcg.recyclerview.itemDivider.DividerGridItemDecoration;
import me.gcg.recyclerview.adapter.SimpleAdapter;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView mRecyclerView;
    private SimpleAdapter mAdapter;
    private Context mContext = this;
    private List<DataModel> mList = new ArrayList<>();
    private GridLayoutManager mLayoutManager;
    private SwipeRefreshLayout mSwipeRefreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        //设置刷新回调
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimaryDark,
                R.color.colorAccent, R.color.colorPrimary, R.color.colorAccent);

        mRecyclerView.addItemDecoration(new DividerGridItemDecoration(mContext));
        mAdapter = new SimpleAdapter(mContext);
        mLayoutManager = new GridLayoutManager(mContext, 2);
        //动态控制显示列数
        mLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int itemViewType = mAdapter.getItemViewType(position);
                if (itemViewType == DataModel.TYPE_ONE) {
                    return 1;
                } else {
                    return 2;
                }
            }
        });
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        initData();
        initListener();
        mRecyclerView.addOnScrollListener(mOnScrollListener);


//        mSwipeRefreshLayout.setEnabled(false);

    }

    RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener() {
        int lastVisibleItemPosition = 0;

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItemPosition + 1 == mAdapter.getItemCount()) {
                /**--此处要做的是从后台请求数据，判断是否有更多数据要加载--**/
                boolean hasmoreData = true;//实际情况——>根据后台返回的json的某个字段判断
                if (hasmoreData) {
                    //开启下拉加载
                    mAdapter.Loadingmore(true);
                    //此处实际开发中，应该是从后台请求下一页的数据
                    //模仿 添加一些测试数据

                    List<DataModel> moreData = new ArrayList<>();
                    for (int i = 0; i < 5; i++) {
                        moreData.add(new DataModel("下拉刷新:" + i, "des" + i, DataModel.TYPE_TWO));
                    }
                    mAdapter.addData(moreData);
                    return;
                }

                //没有更多数据---> 此处根据需求来做，当没有更多数据的时候是隐藏底部布局，还是显示底部布局，并且提示没更多数据
                mAdapter.LoadIngNoMoreData();
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            lastVisibleItemPosition = mLayoutManager.findLastVisibleItemPosition();
        }
    };

    private void initListener() {
        //点击事件
        mAdapter.setRecyclerViewCallback(new RecyclerViewCallback() {
            @Override
            public void OnItemClickListener(int position, Object dataModel, int flag) {
                Toast.makeText(mContext, "点击了：" + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void OnItemLongClickListener(int position, Object dataModel, int flag) {
                Toast.makeText(mContext, "长按了" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeLayout);
        mRecyclerView = (RecyclerView) findViewById(R.id.rl);
    }

    private void initData() {
        //模拟一些list数据
        for (int i = 0; i < 15; i++) {
            DataModel model;
            double random = Math.random();
            if (random < 0.5) {
                model = new DataModel("title" + i, "description" + i, DataModel.TYPE_ONE);
            } else {
                model = new DataModel("title" + i, "description" + i, DataModel.TYPE_TWO);
            }
            mList.add(model);
        }
        mAdapter.setData(mList);
    }


    //下拉刷新
    @Override
    public void onRefresh() {
        //重新刷新 加载  (既然是刷新，就应该把之前的数据集都清除掉，再重新请求)
        mAdapter.clearData();

        //伪造一些数据，实际情况应该在此处从后台请求最新的20条数据（条数按实际情况确定）
        List<DataModel> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(new DataModel("title:刷新的数据" + i, "des:刷新的数据" + i, DataModel.TYPE_TWO));
        }
        mAdapter.addData(list);
        mSwipeRefreshLayout.setRefreshing(false);
    }
}
