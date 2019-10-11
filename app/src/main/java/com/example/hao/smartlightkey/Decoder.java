package com.example.hao.smartlightkey;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hao on 2018/4/14.
 */

public class Decoder extends AppCompatActivity {
    private List<Integer> keys;
    private String origionCode;
    public List<Character> intbuffer;

    public Decoder(String origionCode , List<Integer> keys){
        this.origionCode = origionCode;
        this.keys = keys;

    }

    public void decode(){
        int i;
        intbuffer = new ArrayList<>();
        for (i=0;i<8;i++){
            intbuffer.add(origionCode.charAt(keys.get(i)));
        }

    }
//    public void manage_key(int key,int keyNum){
//        SharedPreferences sharedPreferences = getSharedPreferences("DeviceNum"+DeviceNum,MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putInt("KEY"+keyNum,key);
//        editor.apply();
//    }

    public void test(){
        SharedPreferences sharedPreferences = getSharedPreferences("DeviceNum"+0,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("KEY"+0,1);
        editor.putInt("KEY"+1,0);
        editor.putInt("KEY"+2,1);
        editor.putInt("KEY"+3,0);
        editor.putInt("KEY"+4,1);
        editor.putInt("KEY"+5,0);
        editor.putInt("KEY"+6,1);
        editor.putInt("KEY"+7,0);
        editor.apply();
    }

}
