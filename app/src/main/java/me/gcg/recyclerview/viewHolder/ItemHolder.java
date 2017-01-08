package me.gcg.recyclerview.viewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import me.gcg.recyclerview.R;

public class ItemHolder extends BaseViewHolder {

    public TextView title;
    public TextView description;
    public ImageView mImageView;
    public ImageView mImageView2;

    //底部布局
    public  TextView showTips;

    public ItemHolder(View itemView,int viewType) {
        super(itemView,viewType);
    }

    @Override
    void initTypeOneLayout(View itemView) {
        title = (TextView) itemView.findViewById(R.id.title);
        description = (TextView) itemView.findViewById(R.id.des);
        mImageView = (ImageView) itemView.findViewById(R.id.img);
    }

    @Override
    void initTypeTwoLayout(View itemView) {
        title = (TextView) itemView.findViewById(R.id.title);
        description = (TextView) itemView.findViewById(R.id.des);
        mImageView = (ImageView) itemView.findViewById(R.id.img);
        mImageView2 = (ImageView) itemView.findViewById(R.id.img2);
    }

    @Override
    void initTypeFooterlayout(View view) {
        showTips= (TextView) view.findViewById(R.id.tv_showTips);
    }
}