package org.jzs.mybaseapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.VideoView;

import org.jzs.mybaseapp.common.base.BaseActivity;
import org.jzs.mybaseapp.common.system.SharedPreferencesUtil;
import org.jzs.mybaseapp.section.main.MainActivity;
import org.jzs.zhihudaily.R;


public class SplashActivity extends BaseActivity {

    private VideoView videoview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initVideoView();
        skip();
    }

    /**
     * 播放视频
     */
    private void initVideoView() {

        videoview = (VideoView) findViewById(R.id.videoview);
        videoview.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.intro));
        videoview.start();
        videoview.setOnCompletionListener(mp -> {

            Intent intent = null;
            boolean isFirstTime = SharedPreferencesUtil.getBoolean("splash", true);
            if (isFirstTime) {
                intent = new Intent(SplashActivity.this, MainActivity.class);
                SharedPreferencesUtil.saveBoolean("splash", false);
            } else {
                intent = new Intent(SplashActivity.this, MainActivity.class);
            }
            startActivity(intent);
            finish();
        });
    }

    /**
     * 跳过
     */
    private void skip() {
        Button tv_skip = (Button) findViewById(R.id.tv_skip);
        tv_skip.setOnClickListener(v -> {

            Intent intent;
            boolean isFirstTime = SharedPreferencesUtil.getBoolean("splash", true);
            if (isFirstTime) {
                intent = new Intent(SplashActivity.this, MainActivity.class);
                SharedPreferencesUtil.saveBoolean("splash", false);
            } else {
                intent = new Intent(SplashActivity.this, MainActivity.class);
            }
            startActivity(intent);
            videoview.stopPlayback();
            finish();
        });
    }
}
