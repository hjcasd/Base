package com.hjc.base.ui.search;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.hjc.base.R;
import com.hjc.base.ui.search.adapter.TestAdapter;
import com.hjc.base.widget.text.DeleteEditText;

import java.util.ArrayList;
import java.util.List;

public class SearchView extends LinearLayout {
    private Context mContext;
    private DeleteEditText etSearch;
    private RecyclerView rvResult;

    public SearchView(Context context) {
        super(context);
        this.mContext = context;
        init();
    }

    public SearchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init();
    }

    public SearchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        init();
    }

    private void init() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_search_view, this);

        etSearch = view.findViewById(R.id.et_search);
        rvResult = view.findViewById(R.id.rv_result);

        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rvResult.setLayoutManager(manager);

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    getData();
                    rvResult.setVisibility(View.VISIBLE);
                } else {
                    rvResult.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void getData() {
        List<String> list = new ArrayList<>();
        list.add("111111111111111");
        list.add("222222222222222");
        list.add("333333333333333");
        list.add("444444444444444");
        list.add("555555555555555");
        list.add("666666666666666");
        TestAdapter testAdapter = new TestAdapter(mContext, list);
        rvResult.setAdapter(testAdapter);
    }

}
