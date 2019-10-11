package com.example.hao.smartlightkey;


import android.hardware.Camera;
import android.hardware.Camera.Parameters;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by hao on 2018/4/5.
 */

public class FlashlightControllerLow{
    private  int i = 0;
    int count_1 ;
    int count_0 ;
    private int times;
    private List<Character> input_string = new ArrayList<>();
    private List<Integer> input_number;
    private int time;
    private Camera camera;

        public FlashlightControllerLow(List<Character> input_string){
            this.input_string = input_string;
            input_number =new ArrayList<Integer>();
            time = 40;
            inputFun();
            times = input_number.size()-1;
        }


    private void inputFun() {
        int length = 0;
        i = 0;
        while (i <= 9) {
            if (input_string.get(i) != '0' && input_string.get(i) != '1') {
            }
            else {
                if (input_string.get(i) == '1') {
                    count_1 =0;
                    input_number.add(count1());
                    length++;
                } else {
                    count_0 =0;
                    input_number.add(count0());
                    length++;
                }
            }
        }
        input_number.add(length);
    }

    private int count1(){
        if(i<9){
            count_1++;
            i++ ;
            if (input_string.get(i) == '1'){
                count1();
            }
        }
        else {
            count_1++;
            i++;
        }
        return count_1;
    }

    private int count0(){
        if(i<9) {
            count_0++;
            i++;
            if (input_string.get(i) == '0') {
                count0();
            }
        }
        else {
            count_0++;
            i++;
        }
        return count_0;
    }




    public void flash(){
        switch (times) {
            case 1 :
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        open();
                        try {
                            Thread.sleep(150);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        close();
                        try {
                            Thread.sleep(300);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        open();
                        try {
                            Thread.sleep(input_number.get(0)*time);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        close();
                    }
                }).start();
                break;

            case 3 :
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        open();
                        try {
                            Thread.sleep(150);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        close();
                        try {
                            Thread.sleep(300);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        open();
                        try {
                            Thread.sleep(input_number.get(0) * time);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        close();
                        try {
                            Thread.sleep(input_number.get(1) * time);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        open();
                        try {
                            Thread.sleep(input_number.get(2) * time);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        close();
                    }
                }).start();
                break;

            case 5 :
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        open();
                        try {
                            Thread.sleep(150);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        close();
                        try {
                            Thread.sleep(300);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        open();
                        try {
                            Thread.sleep(input_number.get(0) * time);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        close();
                        try {
                            Thread.sleep(input_number.get(1) * time);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        open();
                        try {
                            Thread.sleep(input_number.get(2) * time);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        close();
                        try {
                            Thread.sleep(input_number.get(3) * time);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        open();
                        try {
                            Thread.sleep(input_number.get(4) * time);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        close();
                    }
                }).start();
                break;

            case 7 :
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        open();
                        try {
                            Thread.sleep(150);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        close();
                        try {
                            Thread.sleep(300);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        open();
                        try {
                            Thread.sleep(input_number.get(0) * time);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        close();
                        try {
                            Thread.sleep(input_number.get(1) * time);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        open();
                        try {
                            Thread.sleep(input_number.get(2) * time);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        close();
                        try {
                            Thread.sleep(input_number.get(3) * time);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        open();
                        try {
                            Thread.sleep(input_number.get(4) * time);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        close();
                        try {
                            Thread.sleep(input_number.get(5) * time);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        open();
                        try {
                            Thread.sleep(input_number.get(6) * time);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        close();
                    }
                }).start();
                break;

            case 9 :
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        open();
                        try {
                            Thread.sleep(150);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        close();
                        try {
                            Thread.sleep(300);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        open();
                        try {
                            Thread.sleep(input_number.get(0) * time);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        close();
                        try {
                            Thread.sleep(input_number.get(1) * time);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        open();
                        try {
                            Thread.sleep(input_number.get(2) * time);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        close();
                        try {
                            Thread.sleep(input_number.get(3) * time);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        open();
                        try {
                            Thread.sleep(input_number.get(4) * time);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        close();
                        try {
                            Thread.sleep(input_number.get(5) * time);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        open();
                        try {
                            Thread.sleep(input_number.get(6) * time);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        close();
                        try {
                            Thread.sleep(input_number.get(7) * time);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        open();
                        try {
                            Thread.sleep(input_number.get(8) * time);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        close();
                    }
                }).start();
                break;

        }
    }

        private void open(){
            try {
            camera = Camera.open();
            camera.startPreview();
            Parameters parameters = camera.getParameters();
            parameters.setFlashMode(Parameters.FLASH_MODE_TORCH);
            camera.setParameters(parameters);
        } catch (Exception e) {
            e.printStackTrace();
        }
        }

        private void close() {
            try {
                Parameters parameters = camera.getParameters();
                parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                camera.setParameters(parameters);
                camera.release();
                camera = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

}
