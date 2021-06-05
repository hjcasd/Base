package com.hjc.library_net.converter

import com.blankj.utilcode.util.LogUtils
import com.google.gson.Gson
import com.google.gson.JsonParser
import com.hjc.library_net.bean.BaseResponse
import com.hjc.library_net.exception.ServerCode
import okhttp3.ResponseBody
import retrofit2.Converter
import java.io.IOException
import java.lang.reflect.Type

/**
 * @Author: HJC
 * @Date: 2019/1/7 11:45
 * @Description: Gson对响应体进行处理
 */
@Suppress("UNCHECKED_CAST")
class JsonResponseBodyConverter<T> constructor(
    private val mGson: Gson,
    private val mType: Type
) : Converter<ResponseBody, T?> {

    @Throws(IOException::class)
    override fun convert(value: ResponseBody): T? {
        val json = value.string()
        LogUtils.e("response json: $json")

        val jsonObject = JsonParser.parseString(json).asJsonObject
        val jsonPrimitive = jsonObject.getAsJsonPrimitive("code")
        if (jsonPrimitive != null) {
            val code = jsonPrimitive.asInt
            return if (ServerCode.CODE_SUCCESS == code) {
                //返回结果正确,可以转换为对应的bean
                mGson.fromJson(json, mType)
            } else {
                //接口请求失败,则直接转为BaseResponse
                mGson.fromJson(json, BaseResponse::class.java) as T
            }
        }
        return null
    }
}