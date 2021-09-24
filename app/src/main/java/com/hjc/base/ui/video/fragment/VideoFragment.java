package com.hjc.base.ui.video.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.hjc.base.R;
import com.hjc.base.databinding.FragmentVideoBinding;
import com.hjc.base.viewmodel.CommonViewModel;
import com.hjc.library_base.fragment.BaseFragment;

/**
 * @Author: HJC
 * @Date: 2019/7/26 10:42
 * @Description: Tab1
 */
public class VideoFragment extends BaseFragment<FragmentVideoBinding, CommonViewModel> {

    public static VideoFragment newInstance() {
        return new VideoFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_video;
    }

    @Override
    public CommonViewModel createViewModel() {
        return null;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        //设置播放器
        SimpleExoPlayer player = new SimpleExoPlayer.Builder(mContext).build();
        mBindingView.playerView.setPlayer(player);

        //播放视频
        String videoUrl = "https://v-cdn.zjol.com.cn/276982.mp4";
        MediaItem mediaItem = MediaItem.fromUri(videoUrl);
        player.setMediaItem(mediaItem);
        player.prepare();
//        player.play();
    }

    @Override
    public void addListeners() {
        mBindingView.setOnClickListener(this);
    }

    @Override
    public void onSingleClick(View v) {

    }

}
