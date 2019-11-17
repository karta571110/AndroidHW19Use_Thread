package com.example.androidhw19;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    final static int  msgKey1 = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = findViewById(R.id.textview);

        Thread t = new Thread(runnable1);
        t.start();
    }

    private Runnable runnable1 = new Runnable() {
        @Override
        public void run() {

                try {

                    Message msg = new Message();
                    msg.what = msgKey1;
                    mHandler.postDelayed(runnable2,2000); //2秒後執行此線程
                    mHandler.sendMessage(msg);

                } catch (Exception e) {
                    e.printStackTrace();
                }

        }
    };
    private Runnable runnable2=new Runnable() {
        @Override
        public void run() {
         TextView textView=findViewById(R.id.textview);
         textView.setText("Got it");
        }
    };


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            TextView textView = findViewById(R.id.textview);
            switch (msg.what) {
                case msgKey1:
                    textView.setText("Got Message");
                    break;

            }
        }
    };

}

