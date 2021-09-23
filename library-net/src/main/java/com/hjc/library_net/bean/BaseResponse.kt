package com.hjc.library_net.bean

/**
 * @Author: HJC
 * @Date: 2019/1/7 11:46
 * @Description: 通用Json封装
 */
class BaseResponse<T> {
    var errorCode: Int? = null
    var errorMsg: String? = null
    var data: T? = null

    override fun toString(): String {
        return "BaseResponse{errorCode=$errorCode, errorMsg='$errorMsg', data=$data}"
    }
}
