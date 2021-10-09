package com.github.ddnosh.arabbit.ui.adapter

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
interface MultiItemTypeSupport<T> {

    fun getLayoutId(itemType: Int): Int

    fun getItemViewType(position: Int, t: T): Int
}
