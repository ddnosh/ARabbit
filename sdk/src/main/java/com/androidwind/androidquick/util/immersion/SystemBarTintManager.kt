package com.androidwind.androidquick.util.immersion

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.ViewConfiguration
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.FrameLayout.LayoutParams
import java.lang.reflect.Method

/**
 * Class to manage status and navigation bar tint effects when using KitKat
 * translucent system UI modes.
 *
 */
class SystemBarTintManager
/**
 * Constructor. Call this in the host activity onCreate method after its
 * content view has been set. You should always create new instances when
 * the host activity is recreated.
 *
 * @param activity The host activity.
 */
@SuppressLint("ResourceType")
@TargetApi(19)
constructor(activity: Activity) {

    /**
     * Get the system bar configuration.
     *
     * @return The system bar configuration for the current device configuration.
     */
    val config: SystemBarConfig
    private var mStatusBarAvailable: Boolean = false
    private var mNavBarAvailable: Boolean = false
    /**
     * Is tinting enabled for the system status bar?
     *
     * @return True if enabled, False otherwise.
     */
    /**
     * Enable tinting of the system status bar.
     *
     *
     * If the platform is running Jelly Bean or earlier, or translucent system
     * UI modes have not been enabled in either the theme or via window flags,
     * then this method does nothing.
     *
     * @param enabled True to enable tinting, false to disable it (default).
     */
    var isStatusBarTintEnabled: Boolean = false
        set(enabled) {
            field = enabled
            if (mStatusBarAvailable) {
                mStatusBarTintView!!.visibility = if (enabled) View.VISIBLE else View.GONE
            }
        }
    /**
     * Is tinting enabled for the system navigation bar?
     *
     * @return True if enabled, False otherwise.
     */
    var isNavBarTintEnabled: Boolean = false
        private set
    private var mStatusBarTintView: View? = null
    private var mNavBarTintView: View? = null

    init {

        val win = activity.window
        val decorViewGroup = win.decorView as ViewGroup

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // check theme attrs
            val attrs = intArrayOf(android.R.attr.windowTranslucentStatus, android.R.attr.windowTranslucentNavigation)
            val a = activity.obtainStyledAttributes(attrs)
            try {
                mStatusBarAvailable = a.getBoolean(0, false)
                mNavBarAvailable = a.getBoolean(1, false)
            } finally {
                a.recycle()
            }

            // check window flags
            val winParams = win.attributes
            var bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
            if (winParams.flags and bits != 0) {
                mStatusBarAvailable = true
            }
            bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION
            if (winParams.flags and bits != 0) {
                mNavBarAvailable = true
            }
        }

        config = SystemBarConfig(activity, mStatusBarAvailable, mNavBarAvailable)
        // device might not have virtual navigation keys
        if (!config.hasNavigtionBar()) {
            mNavBarAvailable = false
        }

        if (mStatusBarAvailable) {
            setupStatusBarView(activity, decorViewGroup)
        }
        if (mNavBarAvailable) {
            setupNavBarView(activity, decorViewGroup)
        }
    }

    /**
     * Enable tinting of the system navigation bar.
     *
     *
     * If the platform does not have soft navigation keys, is running Jelly Bean
     * or earlier, or translucent system UI modes have not been enabled in either
     * the theme or via window flags, then this method does nothing.
     *
     * @param enabled True to enable tinting, false to disable it (default).
     */
    fun setNavigationBarTintEnabled(enabled: Boolean) {
        isNavBarTintEnabled = enabled
        if (mNavBarAvailable) {
            mNavBarTintView!!.visibility = if (enabled) View.VISIBLE else View.GONE
        }
    }

    /**
     * Apply the specified color tint to all system UI bars.
     *
     * @param color The color of the background tint.
     */
    fun setTintColor(color: Int) {
        setStatusBarTintColor(color)
        setNavigationBarTintColor(color)
    }

    /**
     * Apply the specified drawable or color resource to all system UI bars.
     *
     * @param res The identifier of the resource.
     */
    fun setTintResource(res: Int) {
        setStatusBarTintResource(res)
        setNavigationBarTintResource(res)
    }

    /**
     * Apply the specified drawable to all system UI bars.
     *
     * @param drawable The drawable to use as the background, or null to remove it.
     */
    fun setTintDrawable(drawable: Drawable) {
        setStatusBarTintDrawable(drawable)
        setNavigationBarTintDrawable(drawable)
    }

    /**
     * Apply the specified alpha to all system UI bars.
     *
     * @param alpha The alpha to use
     */
    fun setTintAlpha(alpha: Float) {
        setStatusBarAlpha(alpha)
        setNavigationBarAlpha(alpha)
    }

    /**
     * Apply the specified color tint to the system status bar.
     *
     * @param color The color of the background tint.
     */
    fun setStatusBarTintColor(color: Int) {
        if (mStatusBarAvailable) {
            mStatusBarTintView!!.setBackgroundColor(color)
        }
    }

    /**
     * Apply the specified drawable or color resource to the system status bar.
     *
     * @param res The identifier of the resource.
     */
    fun setStatusBarTintResource(res: Int) {
        if (mStatusBarAvailable) {
            mStatusBarTintView!!.setBackgroundResource(res)
        }
    }

    /**
     * Apply the specified drawable to the system status bar.
     *
     * @param drawable The drawable to use as the background, or null to remove it.
     */
    fun setStatusBarTintDrawable(drawable: Drawable) {
        if (mStatusBarAvailable) {
            mStatusBarTintView!!.setBackgroundDrawable(drawable)
        }
    }

    /**
     * Apply the specified alpha to the system status bar.
     *
     * @param alpha The alpha to use
     */
    @TargetApi(11)
    fun setStatusBarAlpha(alpha: Float) {
        if (mStatusBarAvailable && Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            mStatusBarTintView!!.alpha = alpha
        }
    }

    /**
     * Apply the specified color tint to the system navigation bar.
     *
     * @param color The color of the background tint.
     */
    fun setNavigationBarTintColor(color: Int) {
        if (mNavBarAvailable) {
            mNavBarTintView!!.setBackgroundColor(color)
        }
    }

    /**
     * Apply the specified drawable or color resource to the system navigation bar.
     *
     * @param res The identifier of the resource.
     */
    fun setNavigationBarTintResource(res: Int) {
        if (mNavBarAvailable) {
            mNavBarTintView!!.setBackgroundResource(res)
        }
    }

    /**
     * Apply the specified drawable to the system navigation bar.
     *
     * @param drawable The drawable to use as the background, or null to remove it.
     */
    fun setNavigationBarTintDrawable(drawable: Drawable) {
        if (mNavBarAvailable) {
            mNavBarTintView!!.setBackgroundDrawable(drawable)
        }
    }

    /**
     * Apply the specified alpha to the system navigation bar.
     *
     * @param alpha The alpha to use
     */
    @TargetApi(11)
    fun setNavigationBarAlpha(alpha: Float) {
        if (mNavBarAvailable && Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            mNavBarTintView!!.alpha = alpha
        }
    }

    private fun setupStatusBarView(context: Context, decorViewGroup: ViewGroup) {
        mStatusBarTintView = View(context)
        val params = LayoutParams(LayoutParams.MATCH_PARENT, config.statusBarHeight)
        params.gravity = Gravity.TOP
        if (mNavBarAvailable && !config.isNavigationAtBottom) {
            params.rightMargin = config.navigationBarWidth
        }
        mStatusBarTintView!!.layoutParams = params
        mStatusBarTintView!!.setBackgroundColor(DEFAULT_TINT_COLOR)
        mStatusBarTintView!!.visibility = View.GONE
        decorViewGroup.addView(mStatusBarTintView)
    }

    private fun setupNavBarView(context: Context, decorViewGroup: ViewGroup) {
        mNavBarTintView = View(context)
        val params: LayoutParams
        if (config.isNavigationAtBottom) {
            params = LayoutParams(LayoutParams.MATCH_PARENT, config.navigationBarHeight)
            params.gravity = Gravity.BOTTOM
        } else {
            params = LayoutParams(config.navigationBarWidth, LayoutParams.MATCH_PARENT)
            params.gravity = Gravity.RIGHT
        }
        mNavBarTintView!!.layoutParams = params
        mNavBarTintView!!.setBackgroundColor(DEFAULT_TINT_COLOR)
        mNavBarTintView!!.visibility = View.GONE
        decorViewGroup.addView(mNavBarTintView)
    }

    /**
     * Class which describes system bar sizing and other characteristics for the current
     * device configuration.
     */
    class SystemBarConfig(activity: Activity, private val mTranslucentStatusBar: Boolean, private val mTranslucentNavBar: Boolean) {
        /**
         * Get the height of the system status bar.
         *
         * @return The height of the status bar (in pixels).
         */
        val statusBarHeight: Int
        /**
         * Get the height of the action bar.
         *
         * @return The height of the action bar (in pixels).
         */
        val actionBarHeight: Int
        private val mHasNavigationBar: Boolean
        /**
         * Get the height of the system navigation bar.
         *
         * @return The height of the navigation bar (in pixels). If the device does not have
         * soft navigation keys, this will always return 0.
         */
        val navigationBarHeight: Int
        /**
         * Get the width of the system navigation bar when it is placed vertically on the screen.
         *
         * @return The width of the navigation bar (in pixels). If the device does not have
         * soft navigation keys, this will always return 0.
         */
        val navigationBarWidth: Int
        private val mInPortrait: Boolean
        private val mSmallestWidthDp: Float

        /**
         * Should a navigation bar appear at the bottom of the screen in the current
         * device configuration? A navigation bar may appear on the right side of
         * the screen in certain configurations.
         *
         * @return True if navigation should appear at the bottom of the screen, False otherwise.
         */
        val isNavigationAtBottom: Boolean
            get() = mSmallestWidthDp >= 600 || mInPortrait

        /**
         * Get the layout inset for any system UI that appears at the bottom of the screen.
         *
         * @return The layout inset (in pixels).
         */
        val pixelInsetBottom: Int
            get() = if (mTranslucentNavBar && isNavigationAtBottom) {
                navigationBarHeight
            } else {
                0
            }

        /**
         * Get the layout inset for any system UI that appears at the right of the screen.
         *
         * @return The layout inset (in pixels).
         */
        val pixelInsetRight: Int
            get() = if (mTranslucentNavBar && !isNavigationAtBottom) {
                navigationBarWidth
            } else {
                0
            }

        init {
            val res = activity.resources
            mInPortrait = res.configuration.orientation == Configuration.ORIENTATION_PORTRAIT
            mSmallestWidthDp = getSmallestWidthDp(activity)
            statusBarHeight = getInternalDimensionSize(res, STATUS_BAR_HEIGHT_RES_NAME)
            actionBarHeight = getActionBarHeight(activity)
            navigationBarHeight = getNavigationBarHeight(activity)
            navigationBarWidth = getNavigationBarWidth(activity)
            mHasNavigationBar = navigationBarHeight > 0
        }

        @TargetApi(14)
        private fun getActionBarHeight(context: Context): Int {
            var result = 0
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                val tv = TypedValue()
                context.theme.resolveAttribute(android.R.attr.actionBarSize, tv, true)
                result = TypedValue.complexToDimensionPixelSize(tv.data, context.resources.displayMetrics)
            }
            return result
        }

        @TargetApi(14)
        private fun getNavigationBarHeight(context: Context): Int {
            val res = context.resources
            val result = 0
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                if (hasNavBar(context)) {
                    val key: String
                    if (mInPortrait) {
                        key = NAV_BAR_HEIGHT_RES_NAME
                    } else {
                        key = NAV_BAR_HEIGHT_LANDSCAPE_RES_NAME
                    }
                    return getInternalDimensionSize(res, key)
                }
            }
            return result
        }

        @TargetApi(14)
        private fun getNavigationBarWidth(context: Context): Int {
            val res = context.resources
            val result = 0
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                if (hasNavBar(context)) {
                    return getInternalDimensionSize(res, NAV_BAR_WIDTH_RES_NAME)
                }
            }
            return result
        }

        @TargetApi(14)
        private fun hasNavBar(context: Context): Boolean {
            val res = context.resources
            val resourceId = res.getIdentifier(SHOW_NAV_BAR_RES_NAME, "bool", "android")
            if (resourceId != 0) {
                var hasNav = res.getBoolean(resourceId)
                // check override flag (see static block)
                if ("1" == sNavBarOverride) {
                    hasNav = false
                } else if ("0" == sNavBarOverride) {
                    hasNav = true
                }
                return hasNav
            } else { // fallback
                return !ViewConfiguration.get(context).hasPermanentMenuKey()
            }
        }

        private fun getInternalDimensionSize(res: Resources, key: String): Int {
            var result = 0
            val resourceId = res.getIdentifier(key, "dimen", "android")
            if (resourceId > 0) {
                result = res.getDimensionPixelSize(resourceId)
            }
            return result
        }

        @SuppressLint("NewApi")
        private fun getSmallestWidthDp(activity: Activity): Float {
            val metrics = DisplayMetrics()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                activity.windowManager.defaultDisplay.getRealMetrics(metrics)
            } else {
                // TODO this is not correct, but we don't really care pre-kitkat
                activity.windowManager.defaultDisplay.getMetrics(metrics)
            }
            val widthDp = metrics.widthPixels / metrics.density
            val heightDp = metrics.heightPixels / metrics.density
            return Math.min(widthDp, heightDp)
        }

        /**
         * Does this device have a system navigation bar?
         *
         * @return True if this device uses soft key navigation, False otherwise.
         */
        fun hasNavigtionBar(): Boolean {
            return mHasNavigationBar
        }

        /**
         * Get the layout inset for any system UI that appears at the top of the screen.
         *
         * @param withActionBar True to include the height of the action bar, False otherwise.
         * @return The layout inset (in pixels).
         */
        fun getPixelInsetTop(withActionBar: Boolean): Int {
            return (if (mTranslucentStatusBar) statusBarHeight else 0) + if (withActionBar) actionBarHeight else 0
        }

        companion object {

            private val STATUS_BAR_HEIGHT_RES_NAME = "status_bar_height"
            private val NAV_BAR_HEIGHT_RES_NAME = "navigation_bar_height"
            private val NAV_BAR_HEIGHT_LANDSCAPE_RES_NAME = "navigation_bar_height_landscape"
            private val NAV_BAR_WIDTH_RES_NAME = "navigation_bar_width"
            private val SHOW_NAV_BAR_RES_NAME = "config_showNavigationBar"
        }
    }

    companion object {

        /**
         * The default system bar tint color value.
         */
        val DEFAULT_TINT_COLOR = -0x67000000

        private var sNavBarOverride: String? = null

        init {
            // Android allows a system property to override the presence of the navigation bar.
            // Used by the emulator.
            // See https://github.com/android/platform_frameworks_base/blob/master/policy/src/com/android/internal/policy/impl/PhoneWindowManager.java#L1076
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                try {
                    val c = Class.forName("android.os.SystemProperties")
                    val m = c.getDeclaredMethod("get", String::class.java)
                    m.isAccessible = true
                    sNavBarOverride = m.invoke(null, "qemu.hw.mainkeys") as String
                } catch (e: Throwable) {
                    sNavBarOverride = null
                }
            }
        }
    }
}