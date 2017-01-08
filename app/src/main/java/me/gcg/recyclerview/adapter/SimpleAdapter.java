package me.gcg.recyclerview.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import me.gcg.recyclerview.R;
import me.gcg.recyclerview.bean.DataModel;
import me.gcg.recyclerview.viewHolder.ItemHolder;

/**
 * Created by gechuanguang on 2016/12/28.
 * 邮箱：1944633835@qq.com
 */

public class SimpleAdapter extends RecyclerViewAdapter<DataModel> {

    public static final int TYPE_FOOTER = 1003;

    public static  final  int STATE_LOADINGMORE=100120;
    public static  final  int STATE_NOMOREDATA=100121;

    private int Loadingstate=STATE_LOADINGMORE;

    public SimpleAdapter(Context context) {
        super(context);
        Loadingmore(true);
    }

    @Override
    public int getItemViewType(int position) {
        super.getItemViewType(position);
        if (position == mData.size()) {
            //说明加载到最底部了  -->显示底部布局
            return TYPE_FOOTER;
        }
        //不是底部，那么必然是内容区域布局了
        DataModel dataModel = mData.get(position);
        int dataType = dataModel.getDataType();
        if (dataType == DataModel.TYPE_ONE) {
            return DataModel.TYPE_ONE;
        }
        return DataModel.TYPE_TWO;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        ItemHolder itemHolder = null;
        switch (viewType) {
            case DataModel.TYPE_ONE:
                view = mInflater.inflate(R.layout.item_typeone, parent, false);
                itemHolder = new ItemHolder(view,DataModel.TYPE_ONE);
                break;
            case DataModel.TYPE_TWO:
                view = mInflater.inflate(R.layout.item_typetwo, parent, false);
                itemHolder = new ItemHolder(view,DataModel.TYPE_TWO);
                break;
            case TYPE_FOOTER:
                view = mInflater.inflate(R.layout.footer, parent, false);
                itemHolder = new ItemHolder(view,TYPE_FOOTER);
                break;
        }
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        //首先要判断是不是底部布局
        final int itemViewType = getItemViewType(position);
        if (itemViewType == TYPE_FOOTER) {
            String tips = "";
            if (Loadingstate==STATE_LOADINGMORE) {
                tips = "加载更多...";
            } else if(Loadingstate==STATE_NOMOREDATA){
                tips = "没有更多数据！！！";
            }
            ((ItemHolder) holder).showTips.setText(tips);
            return;
        }

        //内容区域布局
        final DataModel dataMode = mData.get(position);

        if (itemViewType == DataModel.TYPE_ONE) {
            ((ItemHolder) holder).title.setText(dataMode.getTitle() );
            ((ItemHolder) holder).description.setText("typeOne:\n" + dataMode.getDescreption());
            ((ItemHolder) holder).mImageView.setBackgroundColor(Color.parseColor("#f3f33f"));
        } else {
            ((ItemHolder) holder).title.setText(dataMode.getTitle() );
            ((ItemHolder) holder).description.setText("typetwo:\n" + dataMode.getDescreption());
            ((ItemHolder) holder).mImageView.setBackgroundColor(Color.parseColor("#ff22ff"));
            ((ItemHolder) holder).mImageView2.setBackgroundColor(Color.parseColor("#ff22ff"));
        }
        //点击事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getRecyclerViewCallback() != null) {
                    getRecyclerViewCallback().OnItemClickListener(position, dataMode, itemViewType);
                }
            }
        });
        //长按事件
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (getRecyclerViewCallback() != null) {
                    getRecyclerViewCallback().OnItemLongClickListener(position, dataMode, itemViewType);
                }
                return true;
            }
        });
    }

    /**
     *  上拉加载开关
     * @param isLoadingmore  true：上拉加载， false：关闭加载(并且隐藏了底部布局)
     */
    public void Loadingmore(boolean isLoadingmore) {
        if(isLoadingmore){
            setLoadingMore(isLoadingmore);
            Loadingstate=STATE_LOADINGMORE;
            this.notifyDataSetChanged();
        }

    }

    /**
     * 没有更多数据加载
     */
    public void LoadIngNoMoreData(){

        Loadingmore(true);
        this.Loadingstate=STATE_NOMOREDATA;
        this.notifyDataSetChanged();
    }
}
