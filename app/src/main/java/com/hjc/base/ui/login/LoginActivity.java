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
import com.hjc.base.base.mvp.BaseMvpActivity;
import com.hjc.base.constant.RoutePath;
import com.hjc.base.ui.MainActivity;
import com.hjc.base.ui.login.contract.LoginContract;
import com.hjc.base.ui.login.model.LoginRequest;
import com.hjc.base.ui.login.presenter.LoginPresenter;
import com.hjc.base.widget.dialog.LoadingDialog;

import butterknife.BindView;

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

    private LoadingDialog loadingDialog;

    private boolean isPhoneCompleted;
    private boolean isCodeCompleted;
    private boolean isProtocolCompleted;

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
        loadingDialog = LoadingDialog.newInstance();
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void addListeners() {
        btnLogin.setOnClickListener(this);

        etPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 11) {
                    isPhoneCompleted = true;
                    if (isPhoneCompleted && isCodeCompleted && isProtocolCompleted) {
                        btnLogin.setEnabled(true);
                    }
                }else{
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
                if (s.length() >= 4 && s.length() <= 6) {
                    isCodeCompleted = true;
                    if (isPhoneCompleted && isCodeCompleted && isProtocolCompleted) {
                        btnLogin.setEnabled(true);
                    }
                }else{
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
                    if (isPhoneCompleted && isCodeCompleted && isProtocolCompleted) {
                        btnLogin.setEnabled(true);
                    }
                }else{
                    isProtocolCompleted = false;
                    btnLogin.setEnabled(false);
                }
            }
        });
    }

    @Override
    public void onSingleClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                LoginRequest loginRequest = new LoginRequest();
                loginRequest.setPhone(etPhone.getText().toString());
                loginRequest.setVerifyCode(etVerifyCode.getText().toString());
                getPresenter().login(loginRequest);
                break;

            default:
                break;
        }
    }

    @Override
    public void toMainActivity() {
        ToastUtils.showShort(" login success");
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void showLoading() {
        loadingDialog.showDialog(getSupportFragmentManager());
    }

    @Override
    public void hideLoading() {
        loadingDialog.dismiss();
    }
}
