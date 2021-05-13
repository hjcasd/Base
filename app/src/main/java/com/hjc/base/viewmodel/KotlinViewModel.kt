package com.hjc.base.viewmodel

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.ToastUtils
import com.hjc.base.http.bean.BaseResponse
import com.hjc.base.http.exception.ApiException
import com.hjc.base.http.exception.ExceptionUtils
import com.hjc.base.http.exception.ServerCode
import com.hjc.baselib.viewmodel.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class KotlinViewModel(application: Application) : BaseViewModel(application) {

    /**
     * 请求处理(response封装)
     */
    protected fun <T> launchWrapper(
        api: suspend CoroutineScope.() -> BaseResponse<T>,
        success: CoroutineScope.(T?) -> Unit,
        error: (e: Throwable) -> Unit = {},
        isShowLoading: Boolean = false,
        isShowProgress: Boolean = false,
    ) {
        if (isShowLoading) {
            showLoading()
        }
        if (isShowProgress) {
            showProgress()
        }
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) { //异步请求接口
                    val response = api()
                    withContext(Dispatchers.Main) {
                        if (ServerCode.CODE_SUCCESS == response.errorCode) { //请求成功
                            success(response.data)
                        } else { //请求成功,Code错误,抛出ApiException
                            handleError(ApiException(response.errorMsg, response.errorCode))
                            error(ApiException(response.errorMsg, response.errorCode))
                        }
                    }
                }
            } catch (e: Throwable) { //请求失败
                handleError(e)
                error(e)
            } finally { //请求结束
                if (isShowLoading) {
                    dismissLoading()
                }
            }
        }
    }

    /**
     * 请求处理(response未封装)
     */
    protected fun <T> launchOriginal(
        api: suspend CoroutineScope.() -> T,
        success: CoroutineScope.(T?) -> Unit,
        error: (e: Throwable) -> Unit = {},
        isShowLoading: Boolean = false,
        isShowProgress: Boolean = false,
    ) {
        if (isShowLoading) {
            showLoading()
        }
        if (isShowProgress) {
            showProgress()
        }
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) { //异步请求接口
                    val response = api()
                    withContext(Dispatchers.Main) {
                        success(response)
                    }
                }
            } catch (e: Throwable) { //请求失败
                handleError(e)
                error(e)
            } finally { //请求结束
                if (isShowLoading) {
                    dismissLoading()
                }
            }
        }
    }

    /**
     * 请求处理(response未封装)
     */
    protected fun <T> launchAsync(
        api: suspend CoroutineScope.() -> T,
        success: CoroutineScope.(T?) -> Unit,
        error: (e: Throwable) -> Unit = {},
        isShowLoading: Boolean = false,
        isShowProgress: Boolean = false,
    ) {
        if (isShowLoading) {
            showLoading()
        }
        if (isShowProgress) {
            showProgress()
        }
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) { //异步请求接口
                    val response = api()
                    withContext(Dispatchers.Main) {
                        success(response)
                    }
                }
            } catch (e: Throwable) { //请求失败
                handleError(e)
                error(e)
            } finally { //请求结束
                if (isShowLoading) {
                    dismissLoading()
                }
            }
        }
    }

    /**
     * 错误统一处理
     */
    protected fun handleError(e: Throwable) {
        val errorDesc = ExceptionUtils.handleException(e)
        ToastUtils.showShort(errorDesc)
    }
}