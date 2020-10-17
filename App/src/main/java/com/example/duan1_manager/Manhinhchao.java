package com.example.duan1_manager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class Manhinhchao extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manhinhchao);

        Thread time = new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    Intent intent = new Intent(Manhinhchao.this, Login_Activity.class);
                    startActivity(intent);
                }
            }
        };
        time.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
