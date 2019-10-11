package com.example.hao.smartlightkey;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

public class DeviceManageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_device_manage);
            int i = 0;
            List<String> strings = new ArrayList<String>();
            SharedPreferences sharedPreferences = getSharedPreferences("DeviceManage",MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("deviceCount",1);
            editor.apply();
            while (i<sharedPreferences.getInt("deviceCount",0)){
                    strings.add(sharedPreferences.getString("deviceNumber"+(i+1),null));
                    i++;
            }
            RecyclerView recyclerView = (RecyclerView)findViewById(R.id.device_recyclerview);
            LinearLayoutManager layoutManager = new LinearLayoutManager(DeviceManageActivity.this);
            recyclerView.setLayoutManager(layoutManager);
            DeviceListAdapter deviceListAdapter = new DeviceListAdapter(strings);
            deviceListAdapter.setOnItemClickListener(new DeviceListAdapter.OnItemClickListener(){
                    @Override
                    public void onClick(int position) {
                            Intent intent = new Intent(DeviceManageActivity.this,DeviceEditActivity.class);
                            intent.putExtra("dN",position);
                            startActivity(intent);
                    }
            });
            recyclerView.setAdapter(deviceListAdapter);

    }



}
