package com.hjc.library_net.security

import android.annotation.SuppressLint
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLSession

class TrustAllHostnameVerifier : HostnameVerifier {

    @SuppressLint("BadHostnameVerifier")
    override fun verify(hostname: String, session: SSLSession): Boolean {
        return true
    }
}