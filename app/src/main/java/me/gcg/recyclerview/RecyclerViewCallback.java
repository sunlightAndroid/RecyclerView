package me.gcg.recyclerview;

/**
 * Created by gechuanguang on 2016/12/28.
 * 邮箱：1944633835@qq.com
 */

public interface RecyclerViewCallback<T> {

    /**
     * recyclerView点击事件回调
     *
     * @param position  位置
     * @param dataModel 对应的实体类
     * @param flag      布局的类型
     */
    void OnItemClickListener(int position, T dataModel, int flag);

    /**
     * recyclerView 长按事件回调
     *
     * @param position  位置
     * @param dataModel 对应的实体类
     * @param flag      布局的类型
     */
    void OnItemLongClickListener(int position, T dataModel, int flag);
}
