package com.example.homeuser.mythread2;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity2 extends AppCompatActivity {
    ProgressBar progressBar;

    ProgressHandler handler = new ProgressHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startup();
            }
        });
    }

    public void startup() {
        ProgressThread thread = new ProgressThread();
        thread.start();
    }

    class ProgressThread extends Thread {
        public void run() {
            for (int i = 0; i < 100; i++) {
                // progressBar.setProgress((i+1));

                Message message = handler.obtainMessage();
                Bundle bundle = new Bundle();
                bundle.putInt("value", (i+1));
                message.setData(bundle);
                handler.sendMessage(message);

                try {
                    Thread.sleep(200);
                } catch(Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }

    class ProgressHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            int value = msg.getData().getInt("value");
            progressBar.setProgress(value);

            super.handleMessage(msg);
        }
    }
}
