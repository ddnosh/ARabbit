package com.androidwind.androidquick.ui.adapter

import android.content.Context
import android.view.ViewGroup

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
abstract class MultiItemCommonAdapter<T>(
    context: Context, datas: MutableList<T>,
    protected var mMultiItemTypeSupport: MultiItemTypeSupport<T>
) : CommonAdapter<T>(context, -1, datas) {

    override fun getItemViewType(position: Int): Int {
        return mMultiItemTypeSupport.getItemViewType(position, mDatas[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommonViewHolder {
        val layoutId = mMultiItemTypeSupport.getLayoutId(viewType)
        return CommonViewHolder.get(mContext, parent, layoutId)
    }
}