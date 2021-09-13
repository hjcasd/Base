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
            .registerTypeAdapter(Int::class.java, IntegerDefaultAdapter())
            .registerTypeAdapter(Int::class.javaPrimitiveType, IntegerDefaultAdapter())
            .registerTypeAdapter(Double::class.java, DoubleDefaultAdapter())
            .registerTypeAdapter(Double::class.javaPrimitiveType, DoubleDefaultAdapter())
            .registerTypeAdapter(Long::class.java, LongDefaultAdapter())
            .registerTypeAdapter(Long::class.javaPrimitiveType, LongDefaultAdapter())
            .registerTypeAdapter(Float::class.java, FloatDefaultAdapter())
            .registerTypeAdapter(Float::class.javaPrimitiveType, FloatDefaultAdapter())
            .registerTypeAdapter(Boolean::class.java, BooleanDefaultAdapter())
            .registerTypeAdapter(Boolean::class.javaPrimitiveType, BooleanDefaultAdapter())
            .registerTypeAdapter(String::class.java, StringNullAdapter())
            .create()
    }

    private class BooleanDefaultAdapter : JsonSerializer<Boolean>, JsonDeserializer<Boolean> {
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

        override fun serialize(src: Boolean?, typeOfSrc: Type, context: JsonSerializationContext): JsonElement {
            return JsonPrimitive(src!!)
        }
    }

    private class FloatDefaultAdapter : JsonSerializer<Float>, JsonDeserializer<Float> {
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
            return JsonPrimitive(src!!)
        }
    }

    private class DoubleDefaultAdapter : JsonSerializer<Double>, JsonDeserializer<Double> {
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
            return JsonPrimitive(src!!)
        }
    }

    private class IntegerDefaultAdapter : JsonSerializer<Int>, JsonDeserializer<Int> {
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
            return JsonPrimitive(src!!)
        }
    }

    private class LongDefaultAdapter : JsonSerializer<Long>, JsonDeserializer<Long> {
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
            return JsonPrimitive(src!!)
        }
    }

    private class StringNullAdapter : TypeAdapter<String>() {
        @Throws(IOException::class)
        override fun read(reader: JsonReader): String {
            if (reader.peek() == JsonToken.NULL) {
                reader.nextNull()
                //原先是返回Null，这里改为返回空字符串
                return ""
            }

            val jsonStr = reader.nextString()
            return if ("null" == jsonStr) {
                ""
            } else {
                jsonStr
            }
        }

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
