package com.hjc.base.ui.fragment;


import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.hjc.base.R;
import com.hjc.base.base.event.Event;
import com.hjc.base.base.event.EventManager;
import com.hjc.base.base.fragment.BaseImmersionFragment;
import com.hjc.base.constant.EventCode;
import com.hjc.base.ui.update.utils.UpdateHelper;
import com.hjc.base.utils.PhotoUtils;
import com.hjc.base.utils.permission.PermissionCallBack;
import com.hjc.base.utils.permission.PermissionManager;
import com.tencent.bugly.crashreport.CrashReport;
import com.yanzhenjie.permission.runtime.Permission;

import butterknife.BindView;


public class Tab2Fragment extends BaseImmersionFragment {
    @BindView(R.id.btn_update)
    Button btnUpdate;
    @BindView(R.id.btn_event)
    Button btnEvent;
    @BindView(R.id.btn_bugly)
    Button btnBugly;
    @BindView(R.id.btn_camera)
    Button btnCamera;
    @BindView(R.id.btn_contact)
    Button btnContact;

    public static Tab2Fragment newInstance() {
        Tab2Fragment fragment = new Tab2Fragment();
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_tab2;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        CrashReport.setUserSceneTag(mContext, 98839); // 上报后的Crash会显示该标签
    }

    @Override
    public void addListeners() {
        btnUpdate.setOnClickListener(this);
        btnEvent.setOnClickListener(this);
        btnBugly.setOnClickListener(this);
        btnCamera.setOnClickListener(this);
        btnContact.setOnClickListener(this);
    }

    @Override
    public void onSingleClick(View v) {
        switch (v.getId()) {
            case R.id.btn_update:
                UpdateHelper.check(this);
                break;

            case R.id.btn_event:
                EventManager.sendStickyEvent(new Event<>(EventCode.A, "我是event消息"));
                break;

            case R.id.btn_bugly:
                ToastUtils.showShort("测试bugly结果" + 2 / 0);
                CrashReport.testJavaCrash();
                break;

            case R.id.btn_camera:
                openCamera();
                break;

            case R.id.btn_contact:
                selectContact();
                break;
        }
    }

    /**
     * 打开相机
     */
    private void openCamera() {
        PermissionManager.getInstance()
                .with(this)
                .requestPermissionInFragment(new PermissionCallBack() {
                    @Override
                    public void onGranted() {
                        PhotoUtils.openCamera(getActivity());
                    }

                    @Override
                    public void onDenied() {
                        ToastUtils.showShort("申请相机权限失败");
                    }
                }, Permission.Group.CAMERA);
    }

    /**
     * 选择联系人
     */
    private void selectContact() {
        PermissionManager.getInstance()
                .with(this)
                .requestPermissionInFragment(new PermissionCallBack() {
                    @Override
                    public void onGranted() {
                        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                        startActivityForResult(intent, 100);
                    }

                    @Override
                    public void onDenied() {
                        ToastUtils.showShort("申请通讯录权限失败");
                    }
                }, Permission.READ_CONTACTS);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            getContacts(data);
        }
    }

    private void getContacts(Intent data) {
        if (data == null) {
            return;
        }

        Uri contactData = data.getData();
        if (contactData == null) {
            return;
        }

        Uri contactUri = data.getData();
        Cursor cursor = mContext.getContentResolver().query(contactUri, null, null, null, null);

        if (cursor.moveToFirst()) {
            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));

            LogUtils.e("name---" + name);

            String hasPhone = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
            if (hasPhone.equalsIgnoreCase("1")) {
                hasPhone = "true";
            } else {
                hasPhone = "false";
            }
            if (Boolean.parseBoolean(hasPhone)) {
                Cursor phoneCursor = mContext.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + id, null, null);

                StringBuffer sb = new StringBuffer();
                //多个号码
                while (phoneCursor.moveToNext()) {
                    String phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                    if (phoneCursor.isLast()) {
                        sb.append(phoneNumber);
                    } else {
                        sb.append(phoneNumber + ";");
                    }
                }
                LogUtils.e("phoneNumber---" + sb.toString());

                phoneCursor.close();
            } else {
                ToastUtils.showShort("该联系人没有手机号");
            }
            cursor.close();
        }
    }
}
