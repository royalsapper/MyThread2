package com.example.homeuser.mythread2;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ProgressBar progressBar;

    Handler handler = new Handler();

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

        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // showToast("버튼이 눌렸어요.");
                        showSnackBar("버튼이 눌렸어요.");
                    }
                }, 5000);
            }
        });
    }

    public void showSnackBar(String data) {
        Snackbar.make(progressBar, data, Snackbar.LENGTH_LONG).show();
    }

    public void showToast(String data) {
        Toast.makeText(getApplicationContext(), data, Toast.LENGTH_LONG).show();
    }

    public void startup() {
        ProgressThread thread = new ProgressThread();
        thread.start();
    }

    class ProgressThread extends Thread {
        public void run() {
            for (int i = 0; i < 100; i++) {
                // progressBar.setProgress((i+1));

                final int value  = i+1;
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setProgress((value));
                    }
                });


                try {
                    Thread.sleep(200);
                } catch(Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }

}
