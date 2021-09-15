package com.hjc.module_frame.ui.zxing

import android.Manifest
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import cn.bingoogolapple.qrcode.core.BGAQRCodeUtil
import cn.bingoogolapple.qrcode.zxing.QRCodeDecoder
import cn.bingoogolapple.qrcode.zxing.QRCodeEncoder
import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.util.ImageUtils
import com.blankj.utilcode.util.ToastUtils
import com.gyf.immersionbar.ImmersionBar
import com.hjc.library_base.activity.BaseActivity
import com.hjc.library_common.router.RouteManager
import com.hjc.library_common.router.path.RouteFramePath
import com.hjc.library_common.viewmodel.CommonViewModel
import com.hjc.library_net.utils.RxSchedulers
import com.hjc.library_widget.bar.OnViewLeftClickListener
import com.hjc.module_frame.R
import com.hjc.module_frame.databinding.FrameActivityQrCodeBinding
import com.permissionx.guolindev.PermissionX
import com.permissionx.guolindev.request.ExplainScope
import io.reactivex.Observable
import io.reactivex.observers.DefaultObserver

/**
 * @Author: HJC
 * @Date: 2019/7/29 14:28
 * @Description: Zxing的使用
 */
@Route(path = RouteFramePath.URL_QR_CODE)
class QRCodeActivity : BaseActivity<FrameActivityQrCodeBinding, CommonViewModel>() {


    override fun getLayoutId(): Int {
        return R.layout.frame_activity_qr_code
    }

    override fun createViewModel(): CommonViewModel? {
        return null
    }

    override fun getImmersionBar(): ImmersionBar? {
        return ImmersionBar.with(this)
            .fitsSystemWindows(true)
            .statusBarColor(R.color.frame_color)
    }

    override fun initData(savedInstanceState: Bundle?) {

    }

    override fun addListeners() {
        mBindingView.onClickListener = this

        mBindingView.titleBar.setOnViewLeftClickListener(object : OnViewLeftClickListener {
            override fun onViewLeftClick(view: View?) {
                finish()
            }
        })
    }

    override fun onSingleClick(v: View?) {
        when (v?.id) {
            R.id.btn_scan -> scanCode()
            R.id.btn_generate -> generate()
            R.id.btn_generate_logo -> generateWithLogo()
            R.id.btn_recognition -> recognition()
            else -> {
            }
        }
    }

    /**
     * 扫描二维码
     */
    private fun scanCode() {
        PermissionX.init(this)
            .permissions(Manifest.permission.CAMERA)
            .onExplainRequestReason { scope: ExplainScope, deniedList: List<String?>? ->
                scope.showRequestReasonDialog(
                    deniedList,
                    "请授予以下权限，以便程序继续运行",
                    "确定",
                    "取消"
                )
            }
            .request { allGranted: Boolean, grantedList: List<String?>?, deniedList: List<String?>? ->
                if (allGranted) {
                    RouteManager.jump(RouteFramePath.URL_SCAN_CODE)
                } else {
                    ToastUtils.showShort("申请相机权限失败")
                }
            }
    }

    /**
     * 生成二维码
     */
    private fun generate() {
        Observable
            .just("王者荣耀")
            .map { s: String? ->
                QRCodeEncoder.syncEncodeQRCode(
                    s,
                    BGAQRCodeUtil.dp2px(this@QRCodeActivity, 160f)
                )
            }
            .compose(RxSchedulers.ioToMain())
            .subscribe(object : DefaultObserver<Bitmap?>() {
                override fun onNext(bitmap: Bitmap) {
                    mBindingView.ivCodePic.setImageBitmap(bitmap)
                }

                override fun onError(e: Throwable) {
                    ToastUtils.showShort("生成二维码失败")
                }

                override fun onComplete() {}
            })
    }

    /**
     * 生成带logo的二维码
     */
    private fun generateWithLogo() {
        Observable
            .just("英雄联盟")
            .map { s: String? ->
                val logoBitmap: Bitmap =
                    BitmapFactory.decodeResource(resources, R.mipmap.frame_icon_logo)
                QRCodeEncoder.syncEncodeQRCode(
                    s, BGAQRCodeUtil.dp2px(this@QRCodeActivity, 160f),
                    Color.parseColor("#ff0000"), logoBitmap
                )
            }
            .compose(RxSchedulers.ioToMain())
            .subscribe(object : DefaultObserver<Bitmap?>() {
                override fun onNext(bitmap: Bitmap) {
                    mBindingView.ivCodePic.setImageBitmap(bitmap)
                }

                override fun onError(e: Throwable) {
                    ToastUtils.showShort("生成带logo的二维码失败")
                }

                override fun onComplete() {}
            })
    }

    /**
     * 识别二维码
     */
    private fun recognition() {
        val drawable: Drawable? = mBindingView.ivCodePic.drawable
        if (drawable == null) {
            ToastUtils.showShort("请生成二维码")
            return
        }
        Observable
            .just(drawable)
            .map { drawable1: Drawable? ->
                val bitmap: Bitmap = ImageUtils.drawable2Bitmap(drawable1)
                QRCodeDecoder.syncDecodeQRCode(bitmap)
            }
            .compose(RxSchedulers.ioToMain())
            .subscribe(object : DefaultObserver<String?>() {
                override fun onNext(s: String) {
                    mBindingView.tvDesc.text = s
                }

                override fun onError(e: Throwable) {
                    ToastUtils.showShort("解析二维码失败")
                }

                override fun onComplete() {}
            })
    }
}