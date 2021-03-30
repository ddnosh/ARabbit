package com.github.ddnosh.arabbit.module.glide.support

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
class LoadOption(loadingResId: Int, errorResId: Int) {
    var mLoadingResId: Int = loadingResId//加载中状态显示的图片
    var mErrorResId: Int = errorResId//加载失败状态显示的图片
    var isShowTransition: Boolean = false
        private set//是否开启状态切换时的过渡动画
    var isCircle: Boolean = false
        private set//是否加载为圆形图片
    var mBorderWidth: Int = 0//边框粗细，单位dp，仅在圆形模式下有效
    var mBorderColor: Int = 0//边框颜色，仅在圆形模式下有效
    var mRoundRadius: Int = 0//加载为圆角图片的圆角值
    var mBlurRadius: Int = 0//加载为模糊图片的模糊值
    var isGray: Boolean = false
        private set//是否加载为灰白图片
    var isUseMemoryCache: Boolean = false
        private set//是否使用内存缓存
    var isUseDiskCache: Boolean = false
        private set//是否使用磁盘缓存
}
