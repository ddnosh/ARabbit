package com.github.ddnosh.arabbit.ui.view

import android.app.Activity
import android.content.Context
import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.text.TextUtils
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView

import com.github.ddnosh.arabbit.R

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
class CommonToolBar(private val mContext: Context, attrs: AttributeSet) : RelativeLayout(mContext, attrs), View.OnClickListener {
    private var mTvTitle: TextView? = null
    private var mTvBack: TextView? = null
    private var mImgBack: ImageView? = null
    private var mImgLeft: ImageView? = null
    private var mImgRight: ImageView? = null
    private var mTvRight: TextView? = null
    private var mImgTitle: ImageView? = null
    private var mLeftView: View? = null
    private var mRightView: View? = null
    private var mOnToolbarClickListener: OnToolBarClickListener? = null
    private var mOnBackClickListener: OnBackClickListener? = null

    init {
        init(attrs)
    }

    private fun init(attrs: AttributeSet) {
        if (isInEditMode) {
            return
        }

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CommonToolBar)
        val titleText = typedArray.getString(R.styleable.CommonToolBar_title_text)
        val leftImgDrawable = typedArray.getDrawable(R.styleable.CommonToolBar_left_img_src)
        val rightImgDrawable = typedArray.getDrawable(R.styleable.CommonToolBar_right_img_src)
        val rightText = typedArray.getString(R.styleable.CommonToolBar_right_text)
        setBackgroundColor(typedArray.getColor(R.styleable.CommonToolBar_background, context.resources.getColor(R.color.blue_sky)))
        typedArray.recycle()
        LayoutInflater.from(context).inflate(R.layout.common_toolbar, this)
        //左边图片
        mImgLeft = findViewById<View>(R.id.img_left) as ImageView
        //左边返回箭头
        mImgBack = findViewById<View>(R.id.img_back) as ImageView
        //左边文字
        mTvBack = findViewById<View>(R.id.tv_back) as TextView
        //中间文字
        mTvTitle = findViewById<View>(R.id.tv_title) as TextView
        //中间图片
        mImgTitle = findViewById<View>(R.id.img_title) as ImageView
        //右边文字
        mTvRight = findViewById<View>(R.id.tv_right) as TextView
        //右边图片
        mImgRight = findViewById<View>(R.id.img_right) as ImageView
        mLeftView = findViewById(R.id.rl_left)
        mRightView = findViewById(R.id.rl_right)
        mImgLeft!!.setOnClickListener(this)
        mImgRight!!.setOnClickListener(this)
        mImgBack!!.setOnClickListener(this)
        mTvBack!!.setOnClickListener(this)
        mTvRight!!.setOnClickListener(this)

        if (!TextUtils.isEmpty(titleText)) {
            titleText?.let { setTitle(it) }
        } else {
            mTvTitle!!.visibility = View.GONE
            mImgTitle!!.visibility = View.VISIBLE
        }

        if (!TextUtils.isEmpty(rightText)) {
            mTvRight!!.visibility = View.VISIBLE
            mTvRight!!.text = rightText
        } else {
            mTvRight!!.visibility = View.GONE
        }

        if (leftImgDrawable != null) {
            mTvBack!!.visibility = View.GONE
            mImgBack!!.visibility = View.GONE
            mImgLeft!!.visibility = View.VISIBLE
            mImgLeft!!.setImageDrawable(leftImgDrawable)
        }

        if (rightImgDrawable != null) {
            mImgRight!!.setImageDrawable(rightImgDrawable)
        } else {
            mImgRight!!.visibility = View.GONE
        }

        //        if (mImgRight.getVisibility() == GONE && mTvRight.getVisibility() == GONE) {
        //            mRightView.setVisibility(GONE);
        //        }else {
        mLeftView!!.viewTreeObserver.addOnGlobalLayoutListener() //                boolean isFirst = true;
        {
            val width = mLeftView!!.width
            val width2 = mRightView!!.width
            if (width > width2) {
                val param = mRightView!!.layoutParams
                param.width = width
                mRightView!!.layoutParams = param
            } else {
                val param = mLeftView!!.layoutParams
                param.width = width2
                mLeftView!!.layoutParams = param
            }
        }
        //        }
    }

    fun setTitle(title: String) {
        mTvTitle!!.text = title
        mTvTitle!!.visibility = View.VISIBLE
        mImgTitle!!.visibility = View.GONE
    }

    override fun onClick(v: View) {
        val i = v.id
        if (i == R.id.img_back || i == R.id.tv_back) {
            if (mOnBackClickListener != null) {
                mOnBackClickListener!!.onBackClick()
            } else {
                if (mContext is Activity) {
                    mContext.finish()
                }
            }
        } else if (i == R.id.img_left) {
            if (mOnToolbarClickListener != null) {
                mOnToolbarClickListener!!.onLeftClick()
            }
        } else if (i == R.id.img_right) {
            if (mOnToolbarClickListener != null) {
                mOnToolbarClickListener!!.onRightClick()
            }
        } else if (i == R.id.tv_right) {
            if (mOnToolbarClickListener != null) {
                mOnToolbarClickListener!!.onRightClick()
            }
        }
    }

    fun setOnTopBarClickListener(onToolBarClickListener: OnToolBarClickListener) {
        this.mOnToolbarClickListener = onToolBarClickListener
    }

    fun setOnBackClickListener(onBackClickListener: OnBackClickListener) {
        this.mOnBackClickListener = onBackClickListener
    }

    interface OnToolBarClickListener {
        fun onLeftClick()

        fun onRightClick()
    }

    interface OnBackClickListener {
        fun onBackClick()
    }
}
