package com.hjc.base.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.ToastUtils;
import com.hjc.base.R;
import com.hjc.base.constant.RoutePath;
import com.hjc.base.model.request.LoginReq;
import com.hjc.base.model.response.LoginResp;
import com.hjc.base.ui.MainActivity;
import com.hjc.base.ui.login.contract.LoginContract;
import com.hjc.base.ui.login.presenter.LoginPresenter;
import com.hjc.base.utils.SchemeUtils;
import com.hjc.baselib.mvp.BaseMvpActivity;

import butterknife.BindView;

/**
 * @Author: HJC
 * @Date: 2019/7/29 14:23
 * @Description: 登录页
 */
@Route(path = RoutePath.URL_LOGIN)
public class LoginActivity extends BaseMvpActivity<LoginContract.View, LoginPresenter> implements LoginContract.View {

    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_verify_code)
    EditText etVerifyCode;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.tv_get_code)
    TextView tvGetCode;
    @BindView(R.id.cb_agree)
    CheckBox cbAgree;
    @BindView(R.id.tv_protocol)
    TextView tvProtocol;

    private boolean isPhoneCompleted = false;
    private boolean isCodeCompleted = false;
    private boolean isProtocolCompleted = true;

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected LoginContract.View createView() {
        return this;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void addListeners() {
        btnLogin.setOnClickListener(this);
        tvProtocol.setOnClickListener(this);

        etPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 11) {
                    isPhoneCompleted = true;
                    if (isCodeCompleted && isProtocolCompleted) {
                        btnLogin.setEnabled(true);
                    }
                } else {
                    isPhoneCompleted = false;
                    btnLogin.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etVerifyCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 4) {
                    isCodeCompleted = true;
                    if (isPhoneCompleted && isProtocolCompleted) {
                        btnLogin.setEnabled(true);
                    }
                } else {
                    isCodeCompleted = false;
                    btnLogin.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        cbAgree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    isProtocolCompleted = true;
                    if (isPhoneCompleted && isCodeCompleted) {
                        btnLogin.setEnabled(true);
                    }
                } else {
                    isProtocolCompleted = false;
                    btnLogin.setEnabled(false);
                }
            }
        });
    }

    @Override
    public void onSingleClick(View v) {
        switch (v.getId()) {

            // 登录
            case R.id.btn_login:
                LoginReq loginReq = new LoginReq();
                loginReq.setPhoneNo(etPhone.getText().toString());
                loginReq.setVerifyCode(etVerifyCode.getText().toString());
                getPresenter().login(loginReq);
                break;

            // 登录
            case R.id.tv_protocol:
                SchemeUtils.jumpToWeb(this, "https://www.baidu.com", "百度一下");
                break;

            default:
                break;
        }
    }

    @Override
    public void toMainActivity(LoginResp result) {
        ToastUtils.showShort("登录成功");

        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
