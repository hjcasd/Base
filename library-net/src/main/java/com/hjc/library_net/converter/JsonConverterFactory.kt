package com.hjc.library_net.converter

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

/**
 * @Author: HJC
 * @Date: 2019/1/7 11:43
 * @Description: Gson数据转换
 */
class JsonConverterFactory private constructor(gson: Gson) : Converter.Factory() {

    private val mGson: Gson = gson

    companion object {
        fun create(): JsonConverterFactory {
            return JsonConverterFactory(Gson())
        }
    }

    /**
     * 请求Converter
     */
    override fun requestBodyConverter(
        type: Type,
        parameterAnnotations: Array<Annotation>,
        methodAnnotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<*, RequestBody> {
        val adapter = mGson.getAdapter(TypeToken.get(type))
        return JsonRequestBodyConverter(mGson, adapter)
    }

    /**
     * 响应Converter
     */
    override fun responseBodyConverter(
        type: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *> {
        return JsonResponseBodyConverter<Any>(mGson, type)
    }

}