package com.github.ddnosh.arabbit.module.glide

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.Transformation
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.github.ddnosh.arabbit.module.glide.support.CircleBorderTransformation
import com.github.ddnosh.arabbit.module.glide.support.ImageListener
import com.github.ddnosh.arabbit.module.glide.support.LoadOption
import com.github.ddnosh.arabbit.util.FileUtil
import jp.wasabeef.glide.transformations.BlurTransformation
import jp.wasabeef.glide.transformations.GrayscaleTransformation
import jp.wasabeef.glide.transformations.RoundedCornersTransformation
import java.io.File
import java.util.ArrayList
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
object GlideManager {

    private var cacheThreadPool: ExecutorService? = null

    fun loadNet(url: String, imageView: ImageView) {
        load(Glide.with(imageView.context).load(url), null).into(imageView)
    }

    fun loadNet(url: String, imageView: ImageView, loadOption: LoadOption) {
        load(Glide.with(imageView.context).load(url), loadOption).into(imageView)
    }

    fun loadRes(resId: Int, imageView: ImageView) {
        load(Glide.with(imageView.context).load(resId), null).into(imageView)
    }

    fun loadRes(resId: Int, imageView: ImageView, loadOption: LoadOption) {
        load(Glide.with(imageView.context).load(resId), loadOption).into(imageView)
    }

    fun loadAsset(assetName: String, imageView: ImageView) {
        load(Glide.with(imageView.context).load("file:///android_asset/$assetName"), null).into(imageView)
    }

    fun loadAsset(assetName: String, imageView: ImageView, loadOption: LoadOption) {
        load(Glide.with(imageView.context).load("file:///android_asset/$assetName"), loadOption).into(imageView)
    }

    fun loadFile(file: File, imageView: ImageView) {
        load(Glide.with(imageView.context).load(file), null).into(imageView)
    }

    fun loadFile(file: File, imageView: ImageView, loadOption: LoadOption) {
        load(Glide.with(imageView.context).load(file), loadOption).into(imageView)
    }

    fun preLoad(mContext: Context, url: String) {
        Glide.with(mContext).load(url).preload()
    }

    fun getBitmap(context: Context, url: String, imageListener: ImageListener<Bitmap>?) {
        Glide.with(context).asBitmap().load(url).listener(object : RequestListener<Bitmap> {
            override fun onLoadFailed(e: GlideException?, model: Any, target: Target<Bitmap>, isFirstResource: Boolean): Boolean {
                e?.let { imageListener?.onFail(it) }
                return false
            }

            override fun onResourceReady(resource: Bitmap, model: Any, target: Target<Bitmap>, dataSource: DataSource, isFirstResource: Boolean): Boolean {
                imageListener?.onSuccess(resource)
                return false
            }
        }).submit(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
    }

    fun downLoadImage(context: Context, url: String, targetFile: File, imageListener: ImageListener<File>?) {
        if (cacheThreadPool == null) {
            cacheThreadPool = Executors.newCachedThreadPool()
        }

        cacheThreadPool!!.execute {
            try {
                val sourceFile = Glide.with(context).asFile().load(url).submit().get()
                if (FileUtil.copyFile(sourceFile, targetFile) && imageListener != null) {
                    imageListener.onSuccess(targetFile) // 回调在后台线程
                }
            } catch (exception: Exception) {
                imageListener?.onFail(exception)
            }
        }
    }

    fun clearMemoryCache(mContext: Context) {
        // Glide要求清除内存缓存需在主线程执行
        if (Looper.myLooper() == Looper.getMainLooper()) {
            Glide.get(mContext).clearMemory()
        } else {
            Handler(Looper.getMainLooper()).post { Glide.get(mContext).clearMemory() }
        }
    }

    fun clearDiskCache(mContext: Context) {
        // Glide要求清除内存缓存需在后台程执行
        if (Looper.myLooper() == Looper.getMainLooper()) {
            Thread(Runnable { Glide.get(mContext).clearDiskCache() }).start()
        } else {
            Glide.get(mContext).clearDiskCache()
        }
    }

    private fun load(requestBuilder: RequestBuilder<Drawable>, loadOption: LoadOption?): RequestBuilder<Drawable> {

        val requestOptions = RequestOptions()

        // 使用全局的配置进行设置
        if (loadOption == null) {
        } else {
            if (loadOption.isShowTransition) {
                requestBuilder.transition(DrawableTransitionOptions.withCrossFade(600))
            }

            if (loadOption.mLoadingResId > 0) {
                requestOptions.placeholder(loadOption.mLoadingResId)
            }

            if (loadOption.mErrorResId > 0) {
                requestOptions.error(loadOption.mErrorResId)
            }

            requestOptions.skipMemoryCache(!loadOption.isUseMemoryCache)
            if (loadOption.isUseDiskCache) {
                requestOptions.diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            } else {
                requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE)
            }

            var circleTransformation: CircleBorderTransformation? = null
            var roundedCornersTransformation: RoundedCornersTransformation? = null
            var blurTransformation: BlurTransformation? = null
            var grayscaleTransformation: GrayscaleTransformation? = null

            if (loadOption.isCircle) {
                //                circleTransformation = new CropCircleTransformation();
                val borderWidth = loadOption.mBorderWidth
                val borderColor = loadOption.mBorderColor
                if (borderWidth > 0 && borderColor != 0) {
                    circleTransformation = CircleBorderTransformation(borderWidth, borderColor)
                } else {
                    circleTransformation = CircleBorderTransformation()
                }
            } else if (loadOption.mRoundRadius > 0) {
                roundedCornersTransformation = RoundedCornersTransformation(loadOption.mRoundRadius, 0)
            }

            if (loadOption.mBlurRadius > 0) {
                blurTransformation = BlurTransformation(loadOption.mBlurRadius)
            }

            if (loadOption.isGray) {
                grayscaleTransformation = GrayscaleTransformation()
            }

            val multiTransformation = getMultiTransformation(circleTransformation, roundedCornersTransformation, blurTransformation, grayscaleTransformation)
            if (multiTransformation != null) {
                requestOptions.transform(multiTransformation)
            }
        } // 使用临时的配置进行设置
        return requestBuilder.apply(requestOptions)
    }

    private fun getMultiTransformation(vararg transformations: Transformation<Bitmap>?): MultiTransformation<Bitmap>? {
        val list = ArrayList<Transformation<Bitmap>?>()

        for (i in transformations.indices) {
            if (transformations[i] != null) {
                list.add(transformations[i])
            }
        }

        return list?.let {
            MultiTransformation(list)
        }
    }
}
