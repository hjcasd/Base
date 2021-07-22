package com.hjc.module_other.model

import com.hjc.library_common.model.CommonModel
import com.hjc.module_other.http.OtherService
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class AudioModel : CommonModel() {

    private val mApi = getApiService(OtherService::class.java)

    suspend fun uploadVoiceFile(file: File): Any? {
        val requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file)
        val multipartBody = MultipartBody.Part.createFormData("file", file.name, requestBody)
        return mApi.uploadVoiceFile(multipartBody)
    }
}