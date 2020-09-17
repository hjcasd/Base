package com.hjc.base.ui.home.adapter;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.hjc.base.R;
import com.hjc.base.databinding.ItemArticleBinding;
import com.hjc.base.bean.ArticleBean;

import org.jetbrains.annotations.NotNull;

public class ArticleAdapter extends BaseQuickAdapter<ArticleBean.DataBean.DatasBean, BaseViewHolder> {

    public ArticleAdapter() {
        super(R.layout.item_article);
    }

    @Override
    protected void onItemViewHolderCreated(@NotNull BaseViewHolder viewHolder, int viewType) {
        DataBindingUtil.bind(viewHolder.itemView);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, ArticleBean.DataBean.DatasBean item) {
        if (item == null) {
            return;
        }

        ItemArticleBinding binding = helper.getBinding();
        if (binding != null) {
            binding.setArticleBean(item);
        }
    }
}
