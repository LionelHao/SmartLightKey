package com.example.hao.smartlightkey;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Decoder decoder;  //用于编码的类
    private FlashlightController flashlightController;   //  用于控制闪光灯的类
    private FlashlightControllerLow flashlightControllerLow; //  用于低版本控制闪光灯的类
    private String input_string;
    private List<Integer> rememberkeys;
    private boolean not_recive_tag = true;

    private FingerprintManagerCompat manager; //指纹认证类


    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = getSharedPreferences("fingerflag",MODE_PRIVATE);
        if(sharedPreferences.getBoolean("flag",false)){
            setContentView(R.layout.activity_finger);
            manager = FingerprintManagerCompat.from(this);
            manager.authenticate(null, 0, null, new MyCallBack(), null);
        }
        else {

            //初始化界面布局
            setContentView(R.layout.activity_main);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.setDrawerListener(toggle);
            toggle.syncState();
            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);

            //设置开门按钮，调用二维码扫描活动
            Button open_door = (Button) findViewById(R.id.open_door);
            open_door.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (not_recive_tag){
                        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA}, 1);
                        } else {
                            startActivityForResult(new Intent(MainActivity.this, ScanActivity.class), 1);
                        }
                   }
                   else{
                        if (Build.VERSION.SDK_INT >= 24) {
                            //使用CameraManage类开启闪光灯
                            flashlightController = new FlashlightController(decoder.intbuffer,MainActivity.this);
                            flashlightController.flash();


                        } else{
                            //继续使用Camera类打开闪光灯
                            flashlightControllerLow = new FlashlightControllerLow(decoder.intbuffer);
                            flashlightControllerLow.flash();

                        }
                    }
                }
            });
        }

    }

    //重写方法用于动态申请权限
    @Override
    public void onRequestPermissionsResult(int requestCode,String[] peissions,int[] grantResults){
        switch (requestCode){
            case 1:
                if(grantResults.length > 0  && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    startActivityForResult(new Intent(MainActivity.this,ScanActivity.class),1);}
                else {
                    Toast.makeText(this,"you need camera permission",Toast.LENGTH_LONG).show();
                }
        }
    }
    //处理传回字符
    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data ){
        switch(requestCode){
            case 1 :
                if(resultCode == RESULT_OK){
                    input_string = data.getStringExtra("data_return");
                    TextView show_qrcode = (TextView) findViewById(R.id.show_result);
                    show_qrcode.setText("收到的信息："+input_string);
                    SharedPreferences sharedPreferences = getSharedPreferences("Devicekeys",MODE_PRIVATE);
                    int i;
                    rememberkeys = new ArrayList<>();
                    for(i=0;i<8;i++){
                        rememberkeys.add(sharedPreferences.getInt("key"+i,9));
                    }

                    decoder = new Decoder(input_string ,rememberkeys);
                    decoder.decode();
                    decoder.intbuffer.add(0,'1');
                    decoder.intbuffer.add('1');
                    not_recive_tag = false;

                }
                break;
            default:
        }
    }

    //设置指纹响应函数

    public class MyCallBack extends FingerprintManagerCompat.AuthenticationCallback {
        private static final String TAG = "MyCallBack";

        // 当出现错误的时候回调此函数，比如多次尝试都失败了的时候，errString是错误信息
        @Override
        public void onAuthenticationError(int errMsgId, CharSequence errString) {
            Log.d(TAG, "onAuthenticationError: " + errString);
        }

        // 当指纹验证失败的时候会回调此函数，失败之后允许多次尝试，失败次数过多会停止响应一段时间然后再停止sensor的工作
        @Override
        public void onAuthenticationFailed() {
            Log.d(TAG, "onAuthenticationFailed: " + "验证失败");
            Toast.makeText(MainActivity.this,"验证失败,请重试！",Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
            Log.d(TAG, "onAuthenticationHelp: " + helpString);
        }

        // 当验证的指纹成功时会回调此函数，然后不再监听指纹sensor
        @Override
        public void onAuthenticationSucceeded(FingerprintManagerCompat.AuthenticationResult
                                                      result) {
//            //初始化界面布局
            setContentView(R.layout.activity_main);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    MainActivity.this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.setDrawerListener(toggle);
            toggle.syncState();
            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(MainActivity.this);

            //设置开门按钮，调用二维码扫描活动
            Button open_door = (Button) findViewById(R.id.open_door);

            open_door.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (not_recive_tag){
                        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA}, 1);
                        } else {
                            startActivityForResult(new Intent(MainActivity.this, ScanActivity.class), 1);
                        }
                    }
                    else{
                        if (Build.VERSION.SDK_INT >= 24) {
                            //使用CameraManage类开启闪光灯
                            flashlightController = new FlashlightController(decoder.intbuffer,MainActivity.this);
                            flashlightController.flash();


                        } else{
                            //继续使用Camera类打开闪光灯
                            flashlightControllerLow = new FlashlightControllerLow(decoder.intbuffer);
                            flashlightControllerLow.flash();

                        }
                    }
                }
            });

        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (true) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_finger) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
            dialog.setTitle("使用指纹验证");
            dialog.setMessage("开启后启动应用将验证你的指纹，是否开启？");
            dialog.setCancelable(false);
            dialog.setPositiveButton("开启",new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    SharedPreferences.Editor editor = getSharedPreferences("fingerflag",MODE_PRIVATE).edit();
                    editor.putBoolean("flag",true);
                    editor.apply();
                }
            });
            dialog.setNegativeButton("取消",new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    SharedPreferences.Editor editor = getSharedPreferences("fingerflag",MODE_PRIVATE).edit();
                    editor.putBoolean("flag",false);
                    editor.apply();
                }
            });
            dialog.show();

        }
         else if (id == R.id.nav_manage) {

            startActivity(new Intent(MainActivity.this,DeviceManageActivity.class));

        }

         else if (id == R.id.nav_person) {

            startActivity(new Intent(MainActivity.this,registerActivity.class));

        }
        else if (id == R.id.nav_message) {
            startActivity(new Intent(MainActivity.this,loginActivity.class));
        }
//
//        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
