package com.hjc.library_common.viewmodel

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.ToastUtils
import com.hjc.library_base.viewmodel.BaseViewModel
import com.hjc.library_net.bean.BaseResponse
import com.hjc.library_net.exception.ApiException
import com.hjc.library_net.exception.ExceptionUtils
import com.hjc.library_net.exception.ServerCode
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

open class KotlinViewModel(application: Application) : BaseViewModel(application) {

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
        viewModelScope.launch(Dispatchers.Main) {
            try {
                //IO线程请求接口,结束后自动切回Main线程
                val response = withContext(Dispatchers.IO) {
                    api()
                }
                //Main线程更新UI
                if (ServerCode.CODE_SUCCESS == response.errorCode) { //请求成功
                    success(response.data)
                } else { //请求成功,Code错误,抛出ApiException
                    handleError(ApiException(response.errorMsg, response.errorCode))
                    error(ApiException(response.errorMsg, response.errorCode))
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
        viewModelScope.launch(Dispatchers.Main) {
            try {
                val response = withContext(Dispatchers.IO) { //异步请求接口
                    api()
                }
                success(response)
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