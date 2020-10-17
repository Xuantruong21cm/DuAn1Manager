package com.example.duan1_manager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login_Activity extends AppCompatActivity {
    EditText edt_username ;
    EditText edt_password ;
    Button btn_Login ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);
        edt_username = findViewById(R.id.edt_userName);
        edt_password = findViewById(R.id.edt_passWord);
        btn_Login = findViewById(R.id.btn_signIn);
        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edt_username.getText().toString().trim();
                String password = edt_password.getText().toString().trim();
                if (username.isEmpty() || password.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Nhập Đầy Đủ Tài Khoản Mật Khẩu",Toast.LENGTH_SHORT).show();
                }else {
                    if (username.equals("admin") && password.equals("admin")){
                        Intent intent = new Intent(Login_Activity.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }else {
                        Toast.makeText(getApplicationContext(),"Sai Tài Khoản Mật Khẩu",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
