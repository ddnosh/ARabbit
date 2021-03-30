package com.github.ddnosh.arabbit.module.glide.support

import com.bumptech.glide.load.Options
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.ModelLoader
import com.bumptech.glide.load.model.ModelLoaderFactory
import com.bumptech.glide.load.model.MultiModelLoaderFactory

import java.io.InputStream

import okhttp3.Call
import okhttp3.OkHttpClient

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
class OkHttpUrlLoader(private val client: Call.Factory) : ModelLoader<GlideUrl, InputStream> {

    override fun handles(url: GlideUrl): Boolean {
        return true
    }

    override fun buildLoadData(model: GlideUrl, width: Int, height: Int, options: Options): ModelLoader.LoadData<InputStream>? {
        return ModelLoader.LoadData(model, OkHttpStreamFetcher(client, model))
    }

    /**
     * The default factory for [OkHttpUrlLoader]s.
     */
    class Factory
    /**
     * Constructor for a new Factory that runs requests using given client.
     *
     * @param client this is typically an instance of `OkHttpClient`.
     */
    @JvmOverloads constructor(private val client: Call.Factory = getInternalClient()) : ModelLoaderFactory<GlideUrl, InputStream> {

        override fun build(multiFactory: MultiModelLoaderFactory): ModelLoader<GlideUrl, InputStream> {
            return OkHttpUrlLoader(client)
        }

        override fun teardown() {
            // Do nothing, this instance doesn't own the client.
        }

        companion object {
            @Volatile
            private lateinit var internalClient: Call.Factory

            private fun getInternalClient(): Call.Factory {
                return internalClient
            }
        }
    }
    /**
     * Constructor for a new Factory that runs requests using a static singleton client.
     */
}