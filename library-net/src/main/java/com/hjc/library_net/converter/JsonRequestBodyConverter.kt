package com.hjc.library_net.converter

import android.text.TextUtils
import com.google.gson.Gson
import com.google.gson.TypeAdapter
import okhttp3.MediaType
import okhttp3.RequestBody
import okio.Buffer
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Converter
import java.io.IOException
import java.io.OutputStreamWriter
import java.io.Writer
import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*

/**
 * @Author: HJC
 * @Date: 2019/1/7 11:43
 * @Description: Gson对请求体进行处理
 */
class JsonRequestBodyConverter<T> constructor(
    private val mGson: Gson,
    private val mAdapter: TypeAdapter<T>
) : Converter<T, RequestBody> {

    companion object {
        private val MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8")
        private val UTF_8 = StandardCharsets.UTF_8
    }

    @Throws(IOException::class)
    override fun convert(value: T): RequestBody {
        val buffer = Buffer()
        val writer: Writer = OutputStreamWriter(buffer.outputStream(), UTF_8)
        val jsonWriter = mGson.newJsonWriter(writer)
        mAdapter.write(jsonWriter, value)
        jsonWriter.flush()
        val json = buffer.readString(UTF_8)
        return RequestBody.create(MEDIA_TYPE, generateSignedJson(json))
    }

    /**
     * 生成带sign字段的新json
     *
     * @param json 原始json
     * @return 新json
     */
    private fun generateSignedJson(json: String): String {
        try {
            val list: MutableList<String> = ArrayList()
            val jsonObject = JSONObject(json)

            //遍历key和value
            val keys = jsonObject.keys()
            while (keys.hasNext()) {
                val paramKey = keys.next()
                val paramValue = jsonObject[paramKey]
                val str = "$paramKey=$paramValue"
                list.add(str)
            }

            // 以&拼接集合中的字符串
            val sb = StringBuilder()
            if (list.size > 0) {
                for (i in list.indices) {
                    if (i < list.size - 1) {
                        sb.append(list[i]).append("&")
                    } else {
                        sb.append(list[i])
                    }
                }
            }
            // 加密字符串
            val sign = md5(sb.toString())
            jsonObject.put("sign", sign)
            return jsonObject.toString()
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return ""
    }

    /**
     * MD5加密
     *
     * @param str 要加密的字符串
     * @return 加密后的字符串
     */
    private fun md5(str: String): String {
        if (TextUtils.isEmpty(str)) {
            return ""
        }
        try {
            val digest = MessageDigest.getInstance("MD5")
            val bytes = digest.digest(str.toByteArray())
            val result = StringBuilder()
            for (b in bytes) {
                var temp = Integer.toHexString(b.toInt() and 0xff)
                if (temp.length == 1) {
                    temp = "0$temp"
                }
                result.append(temp)
            }
            return result.toString()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
        return ""
    }


}