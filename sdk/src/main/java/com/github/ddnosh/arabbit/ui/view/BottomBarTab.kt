package com.github.ddnosh.arabbit.ui.view

import android.content.Context
import android.graphics.Color
import android.text.TextUtils
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.github.ddnosh.arabbit.R

/**
 * Created by YoKeyword on 16/6/3.
 */
class BottomBarTab(context: Context, attrs: AttributeSet?, defStyleAttr: Int, icon: Int, title: CharSequence) : FrameLayout(context, attrs, defStyleAttr) {
    private var mIcon: ImageView? = null
    private var mTvTitle: TextView? = null
    private var mContext: Context? = null
    var tabPosition = -1
        set(position) {
            field = position
            if (position == 0) {
                isSelected = true
            }
        }

    private var mTvUnreadCount: TextView? = null

    /**
     * 获取当前未读数量
     */
    /**
     * 设置未读数量
     */
    var unreadCount: Int
        get() {
            var count = 0
            if (TextUtils.isEmpty(mTvUnreadCount!!.text)) {
                return count
            }
            if (mTvUnreadCount!!.text.toString() == "99+") {
                return 99
            }
            try {
                count = Integer.valueOf(mTvUnreadCount!!.text.toString())
            } catch (ignored: Exception) {
            }

            return count
        }
        set(num) = if (num <= 0) {
            mTvUnreadCount!!.text = 0.toString()
            mTvUnreadCount!!.visibility = View.GONE
        } else {
            mTvUnreadCount!!.visibility = View.VISIBLE
            if (num > 99) {
                mTvUnreadCount!!.text = "99+"
            } else {
                mTvUnreadCount!!.text = num.toString()
            }
        }

    constructor(context: Context, @DrawableRes icon: Int, title: CharSequence) : this(context, null, icon, title) {}

    constructor(context: Context, attrs: AttributeSet?, icon: Int, title: CharSequence) : this(context, attrs, 0, icon, title) {}

    init {
        init(context, icon, title)
    }

    private fun init(context: Context, icon: Int, title: CharSequence) {
        mContext = context
        val typedArray = context.obtainStyledAttributes(intArrayOf(R.attr.selectableItemBackgroundBorderless))
        val drawable = typedArray.getDrawable(0)
        setBackgroundDrawable(drawable)
        typedArray.recycle()

        val lLContainer = LinearLayout(context)
        lLContainer.orientation = LinearLayout.VERTICAL
        lLContainer.gravity = Gravity.CENTER
        val paramsContainer = FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        paramsContainer.gravity = Gravity.CENTER
        lLContainer.layoutParams = paramsContainer

        mIcon = ImageView(context)
        val size = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 27f, resources.displayMetrics).toInt()
        val params = LinearLayout.LayoutParams(size, size)
        mIcon!!.setImageResource(icon)
        mIcon!!.layoutParams = params
        mIcon!!.setColorFilter(ContextCompat.getColor(context, R.color.tab_unselect))
        lLContainer.addView(mIcon)

        mTvTitle = TextView(context)
        mTvTitle!!.text = title
        val paramsTv = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        paramsTv.topMargin = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2f, resources.displayMetrics).toInt()
        mTvTitle!!.textSize = 10f
        mTvTitle!!.setTextColor(ContextCompat.getColor(context, R.color.tab_unselect))
        mTvTitle!!.layoutParams = paramsTv
        lLContainer.addView(mTvTitle)

        addView(lLContainer)

        val min = dip2px(context, 20f)
        val padding = dip2px(context, 5f)
        mTvUnreadCount = TextView(context)
        mTvUnreadCount!!.setBackgroundResource(R.drawable.bg_msg_bubble)
        mTvUnreadCount!!.minWidth = min
        mTvUnreadCount!!.setTextColor(Color.WHITE)
        mTvUnreadCount!!.setPadding(padding, 0, padding, 0)
        mTvUnreadCount!!.gravity = Gravity.CENTER
        val tvUnReadParams = FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, min)
        tvUnReadParams.gravity = Gravity.CENTER
        tvUnReadParams.leftMargin = dip2px(context, 17f)
        tvUnReadParams.bottomMargin = dip2px(context, 14f)
        mTvUnreadCount!!.layoutParams = tvUnReadParams
        mTvUnreadCount!!.visibility = View.GONE

        addView(mTvUnreadCount)
    }

    override fun setSelected(selected: Boolean) {
        super.setSelected(selected)
        if (selected) {
            mIcon!!.setColorFilter(ContextCompat.getColor(mContext!!, R.color.colorPrimary))
            mTvTitle!!.setTextColor(ContextCompat.getColor(mContext!!, R.color.colorPrimary))
        } else {
            mIcon!!.setColorFilter(ContextCompat.getColor(mContext!!, R.color.tab_unselect))
            mTvTitle!!.setTextColor(ContextCompat.getColor(mContext!!, R.color.tab_unselect))
        }
    }

    private fun dip2px(context: Context, dp: Float): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.resources.displayMetrics).toInt()
    }
}
