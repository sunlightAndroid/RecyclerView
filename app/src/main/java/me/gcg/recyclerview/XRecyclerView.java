package me.gcg.recyclerview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import java.util.ArrayList;
import java.util.List;

import me.gcg.recyclerview.adapter.SimpleAdapter;
import me.gcg.recyclerview.bean.DataModel;

/**
 * Created by gechuanguang on 2017/1/5.
 * 邮箱：1944633835@qq.com
 */

public class XRecyclerView<T extends SimpleAdapter>  extends RecyclerView {

    private SimpleAdapter mAdapter;

    public XRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mAdapter=new SimpleAdapter(context);

        this.addOnScrollListener(mOnScrollListener);
    }

    RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener() {
        int lastVisibleItemPosition = 0;

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItemPosition + 1 == mAdapter.getItemCount()) {
                /**--此处要做的是从后台请求数据，判断是否有更多数据要加载--**/
                boolean hasmoreData = false;//实际情况——>根据后台返回的json的某个字段判断
                if (hasmoreData) {
                    //开启下拉加载
                    mAdapter.Loadingmore(true);
                    //此处实际开发中，应该是从后台请求下一页的数据
                    //模仿 添加一些测试数据

                    List<DataModel> moreData = new ArrayList<>();
                    for (int i = 0; i < 5; i++) {
                        moreData.add(new DataModel("下拉加载的数据" + i, "des" + i, DataModel.TYPE_TWO));
                    }
                    mAdapter.addData(moreData);
                    return;
                }

                //没有更多数据---> 此处根据需求来做，当没有更多数据的时候是隐藏底部布局，还是显示底部布局，并且提示没更多数据
                mAdapter.LoadIngNoMoreData();
//                mAdapter.Loadingmore(false);
            }
        }


    };
}
