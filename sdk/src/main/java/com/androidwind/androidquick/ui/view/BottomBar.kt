package com.androidwind.androidquick.ui.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Parcel
import android.os.Parcelable
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.Interpolator
import android.widget.LinearLayout

import java.util.ArrayList

import androidx.core.view.ViewCompat


/**
 * Created by YoKeyword on 16/6/3.
 */
class BottomBar @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : LinearLayout(context, attrs, defStyleAttr) {

    private val TRANSLATE_DURATION_MILLIS = 200

    private val mInterpolator = AccelerateDecelerateInterpolator()
    var isVisible = true
        private set

    private val mTabs = ArrayList<BottomBarTab>()

    private var mTabLayout: LinearLayout? = null

    private var mTabParams: LinearLayout.LayoutParams? = null
    var currentItemPosition = 0
        private set
    private var mListener: OnTabSelectedListener? = null

    init {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        orientation = LinearLayout.VERTICAL

        //        ImageView shadowView = new ImageView(context);
        //        shadowView.setBackgroundResource(R.drawable.actionbar_shadow_up);
        //        addView(shadowView, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        mTabLayout = LinearLayout(context)
        mTabLayout!!.setBackgroundColor(Color.WHITE)
        mTabLayout!!.orientation = LinearLayout.HORIZONTAL
        addView(mTabLayout, LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))

        mTabParams = LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT)
        mTabParams!!.weight = 1f
    }

    fun addItem(tab: BottomBarTab): BottomBar {
        tab.setOnClickListener(OnClickListener {
            if (mListener == null) return@OnClickListener

            val pos = tab.tabPosition
            if (currentItemPosition == pos) {
                mListener!!.onTabReselected(pos)
            } else {
                mListener!!.onTabSelected(pos, currentItemPosition)
                tab.isSelected = true
                mListener!!.onTabUnselected(currentItemPosition)
                mTabs[currentItemPosition].isSelected = false
                currentItemPosition = pos
            }
        })
        tab.tabPosition = mTabLayout!!.childCount
        tab.layoutParams = mTabParams
        mTabLayout!!.addView(tab)
        mTabs.add(tab)
        return this
    }

    fun setOnTabSelectedListener(onTabSelectedListener: OnTabSelectedListener) {
        mListener = onTabSelectedListener
    }

    fun setCurrentItem(position: Int) {
        mTabLayout!!.post { mTabLayout!!.getChildAt(position).performClick() }
    }

    /**
     * 获取 Tab
     */
    fun getItem(index: Int): BottomBarTab? {
        return if (mTabs.size < index) null else mTabs[index]
    }

    interface OnTabSelectedListener {
        fun onTabSelected(position: Int, prePosition: Int)

        fun onTabUnselected(position: Int)

        fun onTabReselected(position: Int)
    }

    override fun onSaveInstanceState(): Parcelable? {
        val superState = super.onSaveInstanceState()
        return superState?.let { SavedState(it, currentItemPosition) }
    }

    override fun onRestoreInstanceState(state: Parcelable) {
        val ss = state as SavedState
        super.onRestoreInstanceState(ss.superState)

        if (currentItemPosition != ss.position) {
            mTabLayout!!.getChildAt(currentItemPosition).isSelected = false
            mTabLayout!!.getChildAt(ss.position).isSelected = true
        }
        currentItemPosition = ss.position
    }

    @SuppressLint("ParcelCreator")
    internal class SavedState : View.BaseSavedState {
        internal var position: Int = 0

        constructor(source: Parcel) : super(source) {
            position = source.readInt()
        }

        constructor(superState: Parcelable, position: Int) : super(superState) {
            this.position = position
        }

        override fun writeToParcel(out: Parcel, flags: Int) {
            super.writeToParcel(out, flags)
            out.writeInt(position)
        }

        companion object {

            val CREATOR: Parcelable.Creator<SavedState> = object : Parcelable.Creator<SavedState> {
                override fun createFromParcel(`in`: Parcel): SavedState {
                    return SavedState(`in`)
                }

                override fun newArray(size: Int): Array<SavedState?> {
                    return arrayOfNulls(size)
                }
            }
        }
    }

    @JvmOverloads
    fun hide(anim: Boolean = true) {
        toggle(false, anim, false)
    }

    @JvmOverloads
    fun show(anim: Boolean = true) {
        toggle(true, anim, false)
    }

    private fun toggle(visible: Boolean, animate: Boolean, force: Boolean) {
        if (isVisible != visible || force) {
            isVisible = visible
            val height = height
            if (height == 0 && !force) {
                val vto = viewTreeObserver
                if (vto.isAlive) {
                    // view树完成测量并且分配空间而绘制过程还没有开始的时候播放动画。
                    vto.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
                        override fun onPreDraw(): Boolean {
                            val currentVto = viewTreeObserver
                            if (currentVto.isAlive) {
                                currentVto.removeOnPreDrawListener(this)
                            }
                            toggle(visible, animate, true)
                            return true
                        }
                    })
                    return
                }
            }
            val translationY = if (visible) 0 else height
            if (animate) {
                animate().setInterpolator(mInterpolator)
                    .setDuration(TRANSLATE_DURATION_MILLIS.toLong())
                    .translationY(translationY.toFloat())
            } else {
                ViewCompat.setTranslationY(this, translationY.toFloat())
            }
        }
    }
}
