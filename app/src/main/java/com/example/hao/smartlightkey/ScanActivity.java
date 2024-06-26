package com.example.hao.smartlightkey;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zxing.ZXingView;

public class ScanActivity extends Activity implements QRCodeView.Delegate {

    private QRCodeView mQR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (ContextCompat.checkSelfPermission(ScanActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(ScanActivity.this, new String[]{Manifest.permission.CAMERA}, 1);
        } else {
            setContentView(R.layout.activity_scan);

            mQR = (ZXingView) findViewById(R.id.zx_view);

            //设置结果处理
            mQR.setResultHandler(this);

            //开始读取二维码
            mQR.startSpot();
        }
    }

    /**
     * 扫描二维码方法大全（已知）
     *
     * mQR.startCamera();               开启预览，但是并未开始识别
     * mQR.stopCamera();                停止预览，并且隐藏扫描框
     * mQR.startSpot();                 开始识别二维码
     * mQR.stopSpot();                  停止识别
     * mQR.startSpotAndShowRect();      开始识别并显示扫描框
     * mQR.stopSpotAndHiddenRect();     停止识别并隐藏扫描框
     * mQR.showScanRect();              显示扫描框
     * mQR.hiddenScanRect();            隐藏扫描框
     * mQR.openFlashlight();            开启闪光灯
     * mQR.closeFlashlight();           关闭闪光灯
     *
     * mQR.startSpotDelay(ms)           延迟ms毫秒后开始识别
     */

    /**
     * 扫描二维码成功
     *
     * @param result
     */
    @Override
    public void onScanQRCodeSuccess(String result) {

        Intent intent = new Intent();
        intent.putExtra("data_return", result);
        setResult(RESULT_OK, intent);
        //停止预览
        mQR.stopCamera();
        finish();

    }

    /**
     * 打开相机出错
     */
    @Override
    public void onScanQRCodeOpenCameraError() {
        Toast.makeText(ScanActivity.this, "打开相机出错！请检查是否开启权限！", Toast.LENGTH_SHORT).show();
    }

    /**
     * 震动
     */
    private void vibrate() {
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(200);
    }

    @Override
    protected void onStart() {
        super.onStart();

        //启动相机
        mQR.startCamera();
    }

    @Override
    protected void onStop() {
        mQR.stopCamera();
        super.onStop();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] peissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    setContentView(R.layout.activity_scan);

                    mQR = (ZXingView) findViewById(R.id.zx_view);

                    //设置结果处理
                    mQR.setResultHandler(this);

                    //开始读取二维码
                    mQR.startSpot();
                }
                else {
                    Toast.makeText(this,"you need permission", Toast.LENGTH_LONG).show();
                }
        }
    }
}
