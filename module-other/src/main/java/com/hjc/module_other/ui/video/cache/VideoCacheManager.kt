package com.hjc.module_other.ui.video.cache

import android.content.Context
import com.google.android.exoplayer2.database.ExoDatabaseProvider
import com.google.android.exoplayer2.upstream.cache.LeastRecentlyUsedCacheEvictor
import com.google.android.exoplayer2.upstream.cache.SimpleCache
import java.io.File

/**
 * @Author: HJC
 * @Date: 2021/11/22 15:27
 * @Description: 缓存管理
 */
object VideoCacheManager {

    private var sDownloadCache: SimpleCache? = null

    fun getInstance(context: Context, maxCacheSize: Long): SimpleCache? {
        if (sDownloadCache == null) {
            sDownloadCache =
                SimpleCache(File(context.cacheDir, "exoCache"), LeastRecentlyUsedCacheEvictor(maxCacheSize), ExoDatabaseProvider(context))
        }
        return sDownloadCache
    }
}