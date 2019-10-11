package com.example.hao.smartlightkey;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;

public class DeviceEditActivity extends AppCompatActivity {
    EditText new_device_name;
    Button update;
    Button num0;
    Button num1;
    Button num2;
    Button num3;
    Button num4;
    Button num5;
    Button num6;
    Button num7;
    private  List<Integer>  input_keys;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_edit);
        new_device_name = (EditText) findViewById(R.id.device_eidt_rename);
        update = (Button) findViewById(R.id.device_edit_sure);
        num0 = (Button) findViewById(R.id.device_key0);
        num1 = (Button) findViewById(R.id.device_key1);
        num2 = (Button) findViewById(R.id.device_key2);
        num3 = (Button) findViewById(R.id.device_key3);
        num4 = (Button) findViewById(R.id.device_key4);
        num5 = (Button) findViewById(R.id.device_key5);
        num6 = (Button) findViewById(R.id.device_key6);
        num7 = (Button) findViewById(R.id.device_key7);
        input_keys = new ArrayList<>();

        num0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input_keys.add(0);
                num0.setEnabled(false);
            }
        });
        num1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input_keys.add(1);
                num1.setEnabled(false);
            }
        });
        num2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input_keys.add(2);
                num2.setEnabled(false);
            }
        });
        num3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input_keys.add(3);
                num3.setEnabled(false);
            }
        });
        num4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input_keys.add(4);
                num4.setEnabled(false);
            }
        });
        num5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input_keys.add(5);
                num5.setEnabled(false);
            }
        });
        num6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input_keys.add(6);
                num6.setEnabled(false);
            }
        });
        num7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input_keys.add(7);
                num7.setEnabled(false);
            }
        });


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num = getIntent().getIntExtra("dN",0);
                SharedPreferences sharedPreferences = getSharedPreferences("DeviceManage",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("deviceNumber"+(num+1),new_device_name.getText().toString());
                SharedPreferences sharedPreferences2 = getSharedPreferences("Devicekeys",MODE_PRIVATE);
                SharedPreferences.Editor editor2 = sharedPreferences2.edit();
                int i;
                for(i=0;i<8;i++){
                editor2.putInt("key"+i,input_keys.get(i));
                }
                editor.apply();
                editor2.apply();
                finish();
            }
        });
    }

}
