package com.hjc.module_other.ui.video.cache

import android.content.Context
import com.google.android.exoplayer2.upstream.*
import com.google.android.exoplayer2.upstream.cache.CacheDataSink
import com.google.android.exoplayer2.upstream.cache.CacheDataSource
import com.google.android.exoplayer2.util.Util
import com.hjc.module_other.R

/**
 * @Author: HJC
 * @Date: 2021/11/22 15:23
 * @Description: 缓存DataSourceFactory
 */
class CacheDataSourceFactory(private val context: Context, private val maxCacheSize: Long, private val maxFileSize: Long) :
    DataSource.Factory {

    private val defaultDatasourceFactory: DefaultDataSourceFactory

    init {
        val userAgent = Util.getUserAgent(context, context.getString(R.string.app_name))
        val bandwidthMeter = DefaultBandwidthMeter.Builder(context).build()
        defaultDatasourceFactory =
            DefaultDataSourceFactory(context, bandwidthMeter, DefaultHttpDataSourceFactory(userAgent, bandwidthMeter))
    }

    override fun createDataSource(): DataSource {
        val simpleCache = VideoCacheManager.getInstance(context, maxCacheSize)
        return CacheDataSource(
            simpleCache!!, defaultDatasourceFactory.createDataSource(), FileDataSource(), CacheDataSink(simpleCache, maxFileSize),
            CacheDataSource.FLAG_BLOCK_ON_CACHE or CacheDataSource.FLAG_IGNORE_CACHE_ON_ERROR, null
        )
    }

}