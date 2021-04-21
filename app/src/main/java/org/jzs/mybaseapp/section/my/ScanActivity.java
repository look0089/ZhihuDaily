package org.jzs.mybaseapp.section.my;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import org.jzs.mybaseapp.common.widget.MyDialog;
import org.jzs.zhihudaily.R;

import cn.bingoogolapple.qrcode.core.QRCodeView;

public class ScanActivity extends Activity implements QRCodeView.Delegate {

    private QRCodeView mQRCodeView;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);
        initUI();
    }

    @Override
    protected void onStart() {

        super.onStart();
        // 开启摄像头开始预览
        mQRCodeView.startCamera();
        // 开始扫描
        mQRCodeView.startSpot();
    }

    @Override
    protected void onStop() {
        mQRCodeView.stopCamera();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        mQRCodeView.onDestroy();
        super.onDestroy();
    }

    private void initUI() {
        mQRCodeView = (QRCodeView) findViewById(R.id.zxingview);
        tvResult = (TextView) findViewById(R.id.tv_result);
        mQRCodeView.setDelegate(this);
    }

    @Override
    public void onScanQRCodeSuccess(String result) {
        MyDialog myDialog = new MyDialog();
        myDialog.showDialog(this, "扫描结果:" + result);
        mQRCodeView.startSpot();
    }

    @Override
    public void onScanQRCodeOpenCameraError() {

    }

}
