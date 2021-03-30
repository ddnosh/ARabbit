package com.github.ddnosh.arabbit.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup

import com.github.ddnosh.arabbit.util.LogUtil

import androidx.recyclerview.widget.RecyclerView

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
abstract class CommonAdapter<T>(protected var mContext: Context, protected var mLayoutId: Int, protected var mDatas: MutableList<T>) : RecyclerView.Adapter<CommonViewHolder>() {
    private val TAG = "CommonAdapter"
    protected var mInflater: LayoutInflater = LayoutInflater.from(mContext)

    /**
     * data的更新方法
     *
     * @param data
     */
    fun update(data: MutableList<T>?) {
        if (data != null) {
            mDatas = data
            this.notifyDataSetChanged()
        }
    }

    /**
     * data的增加方法
     *
     * @param data
     */
    fun add(data: List<T>?) {
        if (data != null) {
            mDatas.addAll(data)
            this.notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommonViewHolder {
        return CommonViewHolder.get(mContext, parent, mLayoutId)
    }

    override fun onBindViewHolder(holder: CommonViewHolder, position: Int) {
        convert(holder, mDatas[position])
    }

    abstract fun convert(holder: CommonViewHolder, t: T)

    override fun getItemCount(): Int {
        LogUtil.i(TAG, "getItemCount() = " + mDatas.size)
        return mDatas.size
    }
}
