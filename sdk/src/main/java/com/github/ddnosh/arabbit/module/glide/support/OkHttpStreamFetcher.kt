package com.github.ddnosh.arabbit.module.glide.support

import android.os.Build
import android.util.Log

import com.bumptech.glide.Priority
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.HttpException
import com.bumptech.glide.load.data.DataFetcher
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.util.ContentLengthInputStream
import com.bumptech.glide.util.Synthetic

import java.io.IOException
import java.io.InputStream
import okhttp3.Call
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
class OkHttpStreamFetcher(private val client: Call.Factory, private val url: GlideUrl) : DataFetcher<InputStream>, okhttp3.Callback {
    @Synthetic
    internal var stream: InputStream? = null
    @Synthetic
    internal var responseBody: ResponseBody? = null
    @Volatile
    private var call: Call? = null
    private var callback: DataFetcher.DataCallback<in InputStream>? = null

    override fun loadData(priority: Priority, callback: DataFetcher.DataCallback<in InputStream>) {
        val requestBuilder = Request.Builder().url(url.toStringUrl())
        for ((key, value) in url.headers) {
            requestBuilder.addHeader(key, value)
        }
        val request = requestBuilder.build()
        this.callback = callback

        call = client.newCall(request)
        if (Build.VERSION.SDK_INT != Build.VERSION_CODES.O) {
            call!!.enqueue(this)
        } else {
            try {
                // Calling execute instead of enqueue is a workaround for #2355, where okhttp throws a
                // ClassCastException on O.
                onResponse(call!!, call!!.execute())
            } catch (e: IOException) {
                onFailure(call!!, e)
            } catch (e: ClassCastException) {
                // It's not clear that this catch is necessary, the error may only occur even on O if
                // enqueue is used.
                onFailure(call!!, IOException("Workaround for framework bug on O", e))
            }
        }
    }

    override fun onFailure(call: Call, e: IOException) {
        if (Log.isLoggable(TAG, Log.DEBUG)) {
            Log.d(TAG, "OkHttp failed to obtain result", e)
        }

        callback!!.onLoadFailed(e)
    }

    @Throws(IOException::class)
    override fun onResponse(call: Call, response: Response) {
        responseBody = response.body
        if (response.isSuccessful) {
            val contentLength = responseBody!!.contentLength()
            stream = ContentLengthInputStream.obtain(responseBody!!.byteStream(), contentLength)
            callback!!.onDataReady(stream)
        } else {
            callback!!.onLoadFailed(HttpException(response.message, response.code))
        }
    }

    override fun cleanup() {
        try {
            if (stream != null) {
                stream!!.close()
            }
        } catch (e: IOException) {
            // Ignored
        }

        if (responseBody != null) {
            responseBody!!.close()
        }
        callback = null
    }

    override fun cancel() {
        val local = call
        local?.cancel()
    }

    override fun getDataClass(): Class<InputStream> {
        return InputStream::class.java
    }

    override fun getDataSource(): DataSource {
        return DataSource.REMOTE
    }

    companion object {
        val TAG = "OkHttpFetcher"
    }
}