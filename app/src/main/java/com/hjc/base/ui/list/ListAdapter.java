package com.hjc.base.ui.list;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.SparseArray;
import android.view.View;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hjc.base.R;

import java.util.List;

public class ListAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    private SparseArray<String> sparseArray = new SparseArray();

    private int focusPosition = -1;

    public ListAdapter(@Nullable List<String> data) {
        super(R.layout.item_rv_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        int position = helper.getAdapterPosition();

        helper.setText(R.id.tv_list, item);

        EditText etTest = helper.getView(R.id.et_test);
        etTest.setText(sparseArray.get(position));

//        if (focusPosition == position) {
//            etTest.requestFocus();
//            etTest.setSelection(etTest.getText().length());
//        }

        etTest.addTextChangedListener(textWatcher);

        etTest.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    focusPosition = position;
                }
            }
        });

//        etTest.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (event.getAction() == MotionEvent.ACTION_UP) {
//                    focusPosition = position;
//                }
//                return false;
//            }
//        });
    }

    @Override
    public void onViewRecycled(@NonNull BaseViewHolder holder) {
        super.onViewRecycled(holder);
        EditText etTest = holder.getView(R.id.et_test);
        etTest.removeTextChangedListener(textWatcher);
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            //保存输入的文字内容
            sparseArray.put(focusPosition, s.toString());
        }
    };

}