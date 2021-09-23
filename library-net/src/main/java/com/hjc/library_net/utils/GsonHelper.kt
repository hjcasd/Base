package com.hjc.library_net.utils

import com.google.gson.*
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import java.io.IOException
import java.lang.reflect.Type

/**
 * @Author: HJC
 * @Date: 2021/9/13 14:48
 * @Description: Gson处理类
 */
object GsonHelper {

    /**
     * 增加后台返回""和"null"的处理
     * 1.int=>0
     * 2.double=>0.00
     * 3.long=>0L
     * 4.float=>0f
     * 5.boolean false
     * 6.String ""
     *
     * @return Gson
     */
    fun buildGson(): Gson {
        return GsonBuilder()
            .registerTypeAdapter(Boolean::class.java, BooleanAdapter())
            .registerTypeAdapter(Boolean::class.javaPrimitiveType, BooleanAdapter())
            .registerTypeAdapter(Int::class.java, IntegerAdapter())
            .registerTypeAdapter(Int::class.javaPrimitiveType, IntegerAdapter())
            .registerTypeAdapter(Long::class.java, LongAdapter())
            .registerTypeAdapter(Long::class.javaPrimitiveType, LongAdapter())
            .registerTypeAdapter(Float::class.java, FloatAdapter())
            .registerTypeAdapter(Float::class.javaPrimitiveType, FloatAdapter())
            .registerTypeAdapter(Double::class.java, DoubleAdapter())
            .registerTypeAdapter(Double::class.javaPrimitiveType, DoubleAdapter())
            .registerTypeAdapter(String::class.java, StringAdapter())
            .create()
    }

    /**
     * BooleanAdapter
     */
    private class BooleanAdapter : JsonSerializer<Boolean>, JsonDeserializer<Boolean> {

        @Throws(JsonParseException::class)
        override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Boolean {
            try {
                //定义为Boolean类型,如果后台返回""或者null,则返回false
                if ("" == json.asString || "null" == json.asString) {
                    return false
                }
            } catch (ignore: Exception) {
            }

            try {
                return json.asBoolean
            } catch (e: NumberFormatException) {
                throw JsonSyntaxException(e)
            }

        }

        /**
         * Gson 会在解析指定T类型的数据时触发当前回调方法进行序列化
         *
         * @param src 需要转化为Json数据的类型，对应Boolean
         * @return 返回T指定的类对应的JsonElement
         */
        override fun serialize(src: Boolean?, typeOfSrc: Type, context: JsonSerializationContext): JsonElement {
            return JsonPrimitive(src)
        }

    }

    /**
     * IntegerAdapter
     */
    private class IntegerAdapter : JsonSerializer<Int>, JsonDeserializer<Int> {

        @Throws(JsonParseException::class)
        override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Int {
            try {
                //定义为int类型,如果后台返回""或者null,则返回0
                if ("" == json.asString || "null" == json.asString) {
                    return 0
                }
            } catch (ignore: Exception) {
            }

            try {
                return json.asInt
            } catch (e: NumberFormatException) {
                throw JsonSyntaxException(e)
            }

        }

        override fun serialize(src: Int?, typeOfSrc: Type, context: JsonSerializationContext): JsonElement {
            return JsonPrimitive(src)
        }

    }

    /**
     * LongAdapter
     */
    private class LongAdapter : JsonSerializer<Long>, JsonDeserializer<Long> {

        @Throws(JsonParseException::class)
        override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Long {
            try {
                //定义为long类型,如果后台返回""或者null,则返回0
                if ("" == json.asString || "null" == json.asString) {
                    return 0L
                }
            } catch (ignore: Exception) {
            }

            try {
                return json.asLong
            } catch (e: NumberFormatException) {
                throw JsonSyntaxException(e)
            }

        }

        override fun serialize(src: Long?, typeOfSrc: Type, context: JsonSerializationContext): JsonElement {
            return JsonPrimitive(src)
        }

    }

    /**
     * FloatAdapter
     */
    private class FloatAdapter : JsonSerializer<Float>, JsonDeserializer<Float> {

        @Throws(JsonParseException::class)
        override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Float {
            try {
                //定义为Float类型,如果后台返回""或者null,则返回0f
                if ("" == json.asString || "null" == json.asString) {
                    return 0f
                }
            } catch (ignore: Exception) {
            }

            try {
                return json.asFloat
            } catch (e: NumberFormatException) {
                throw JsonSyntaxException(e)
            }

        }

        override fun serialize(src: Float?, typeOfSrc: Type, context: JsonSerializationContext): JsonElement {
            return JsonPrimitive(src)
        }

    }

    /**
     * DoubleAdapter
     */
    private class DoubleAdapter : JsonSerializer<Double>, JsonDeserializer<Double> {

        @Throws(JsonParseException::class)
        override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Double {
            try {
                //定义为double类型,如果后台返回""或者null,则返回0.00
                if ("" == json.asString || "null" == json.asString) {
                    return 0.00
                }
            } catch (ignore: Exception) {
            }

            try {
                return json.asDouble
            } catch (e: NumberFormatException) {
                throw JsonSyntaxException(e)
            }

        }

        override fun serialize(src: Double?, typeOfSrc: Type, context: JsonSerializationContext): JsonElement {
            return JsonPrimitive(src)
        }

    }

    /**
     * StringAdapter
     */
    private class StringAdapter : TypeAdapter<String>() {

        /**
         * 反序列化(bean转成json字符串)
         */
        @Throws(IOException::class)
        override fun read(reader: JsonReader): String {
            if (reader.peek() == JsonToken.NULL) {
                reader.nextNull()
                //返回空字符串
                return ""
            }

            val jsonStr = reader.nextString()
            return if ("null" == jsonStr) {
                ""
            } else {
                jsonStr
            }
        }

        /**
         * 序列化(json字符串转成bean)
         */
        @Throws(IOException::class)
        override fun write(writer: JsonWriter, value: String?) {
            if (value == null) {
                writer.nullValue()
                return
            }
            writer.value(value)
        }
    }

}
