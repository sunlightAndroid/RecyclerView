package me.gcg.recyclerview.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import me.gcg.recyclerview.adapter.SimpleAdapter;
import me.gcg.recyclerview.bean.DataModel;

/**
 * Created by gechuanguang on 2016/12/26.
 * 邮箱：1944633835@qq.com
 */

public abstract  class BaseViewHolder extends RecyclerView.ViewHolder {

    public BaseViewHolder(View itemView,int viewType) {
        super(itemView);

        if(viewType== DataModel.TYPE_ONE){
            initTypeOneLayout(itemView);
        }
        if(viewType==DataModel.TYPE_TWO){
            initTypeTwoLayout(itemView);
        }
        if(viewType== SimpleAdapter.TYPE_FOOTER){
            initTypeFooterlayout(itemView);
        }

    }

    abstract void initTypeOneLayout(View itemView);
    abstract void initTypeTwoLayout(View itemView);
    abstract void initTypeFooterlayout(View itemView);

}
