package com.alex.githubusers.util

import android.content.Context
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions

@GlideModule
class GlideUtil : AppGlideModule() {

    private val imageCacheSize = 16 * 1024 * 1024
    private val imageFolder = "/images"

    override fun applyOptions(context: Context, builder: GlideBuilder) {
        builder.setDefaultRequestOptions(
            RequestOptions()
                .format(DecodeFormat.PREFER_ARGB_8888)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
        )
        val factory = InternalCacheDiskCacheFactory(context, imageFolder, imageCacheSize.toLong())
        builder.setDiskCache(factory)
    }

    override fun isManifestParsingEnabled(): Boolean {
        return false
    }
}