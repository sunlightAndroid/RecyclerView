package me.gcg.recyclerview.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.gcg.recyclerview.RecyclerViewCallback;

/**
 * Created by imSunLight on 2016/12/26.
 * 邮箱:1944633835@qq.com
 */
public abstract class RecyclerViewAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected Context mContext;
    protected LayoutInflater mInflater;
    private int preSize = 0;
    //判断需不需要上拉加载更多
    private boolean isLoadingMore = false;

    //点击事件回调监听
    private RecyclerViewCallback<T> mRecyclerViewCallback;


    protected List<T> mData = new ArrayList<>();

    public RecyclerViewAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }


    @Override
    public int getItemCount() {

        //根据是否下拉加载更多来决定数据的大小
        if (isLoadingMore()) {
            return mData.size() + 1;
        }
        return mData.size();
    }

    public RecyclerViewCallback<T> getRecyclerViewCallback() {
        return mRecyclerViewCallback;
    }

    public void setRecyclerViewCallback(RecyclerViewCallback recyclerViewCallback) {
        this.mRecyclerViewCallback = recyclerViewCallback;
    }

    public boolean isLoadingMore() {
        return isLoadingMore;
    }


    public void setLoadingMore(boolean loadingMore) {
        isLoadingMore = loadingMore;
    }

    /**
     * 设置数据
     *
     * @param list
     */
    public void setData(List<T> list) {
        mData.addAll(list);
        preSize = mData.size();
    }

    /**
     * 设置数据源
     *
     * @param data
     */
    public void setData(T[] data) {
        if (data != null && data.length > 0) {
            setData(Arrays.asList(data));
        }
    }

    /**
     * 添加数据
     *
     * @param data
     */
    public void addData(List<T> data) {
        int preSize = this.mData.size();
        if (data != null && data.size() > 0) {
            if (this.mData == null) {
                this.mData = new ArrayList<T>();
            }
            this.mData.addAll(data);
            notifyItemRangeInserted(preSize, this.mData.size());
        }
        notifyDataSetChanged();
    }

    /**
     * 添加数据
     *
     * @param data
     */
    public void addData(T[] data) {
        addData(Arrays.asList(data));
    }

    /**
     * 删除元素
     *
     * @param element
     */
    public void removeElement(T element) {
        if (mData.contains(element)) {
            int position = mData.indexOf(element);
            mData.remove(element);
            notifyItemRemoved(position);
            notifyItemChanged(position);
        }
    }

    /**
     * 删除元素
     *
     * @param position
     */
    public void removeElement(int position) {
        if (mData != null && mData.size() > position) {
            mData.remove(position);
            notifyItemRemoved(position);
            notifyItemChanged(position);
        }
    }

    /**
     * 删除元素
     *
     * @param elements
     */
    public void removeElements(List<T> elements) {
        if (mData != null && elements != null && elements.size() > 0
                && mData.size() >= elements.size()) {

            for (T element : elements) {
                if (mData.contains(element)) {
                    mData.remove(element);
                }
            }

            notifyDataSetChanged();
        }
    }

    /**
     * 删除元素
     *
     * @param elements
     */
    public void removeElements(T[] elements) {
        if (elements != null && elements.length > 0) {
            removeElements(Arrays.asList(elements));
        }
    }

    /**
     * 更新元素
     *
     * @param element
     * @param position
     */
    public void updateElement(T element, int position) {
        if (position >= 0 && mData.size() > position) {
            mData.remove(position);
            mData.add(position, element);
            notifyItemChanged(position);
        }
    }

    /**
     * 添加元素
     *
     * @param element
     */
    public void addElement(T element) {
        if (element != null) {
            if (this.mData == null) {
                this.mData = new ArrayList<T>();
            }
            mData.add(element);
            notifyItemInserted(this.mData.size());
        }
    }

    public void addElement(int position, T element) {
        if (element != null) {
            if (this.mData == null) {
                this.mData = new ArrayList<T>();
            }
            mData.add(position, element);
            notifyItemInserted(position);
        }
    }

    /**
     * 清除数据源
     */
    public void clearData() {
        if (this.mData != null) {
            this.mData.clear();
            notifyDataSetChanged();
        }
    }


    /**
     * 设置控件可见
     *
     * @param view
     */
    protected void setVisible(View view) {
        view.setVisibility(View.VISIBLE);
    }

    /**
     * 设置控件不可见
     *
     * @param view
     */
    protected void setGone(View view) {
        view.setVisibility(View.GONE);
    }

    /**
     * 设置控件不可见
     *
     * @param view
     */
    protected void setInvisible(View view) {
        view.setVisibility(View.INVISIBLE);
    }

    /**
     * 设置控件可用
     *
     * @param view
     */
    protected void setEnable(View view) {
        view.setEnabled(true);
    }

    /**
     * 设置控件不可用
     *
     * @param view
     */
    protected void setDisable(View view) {
        view.setEnabled(false);
    }

    /**
     * 获取图片资源
     *
     * @param resId
     * @return
     */
    protected Drawable getDrawable(int resId) {
        return mContext.getResources().getDrawable(resId);
    }

    /**
     * 获取字符串资源
     *
     * @param resId
     * @return
     */
    protected String getString(int resId) {
        return mContext.getResources().getString(resId);
    }

    /**
     * 获取颜色资源
     *
     * @param resId
     * @return
     */
    protected int getColor(int resId) {
        return mContext.getResources().getColor(resId);
    }

    /**
     * 获取数据源
     *
     * @return
     */
    public List<T> getDataSource() {
        return mData;
    }

}
