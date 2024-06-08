package com.example.cookmoth_project;

import static com.example.cookmoth_project.MainActivity.user;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {


    private ImageView back_icon;
    private Button signBtn, button_login;

    private EditText edit_text_username;
    private EditText edit_text_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();

        edit_text_username = (EditText) findViewById(R.id.edit_text_username);
        edit_text_password = (EditText) findViewById(R.id.edit_text_password);
        button_login = (Button) findViewById(R.id.button_login);


        back_icon = (ImageView) findViewById(R.id.back_icon);

        back_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        signBtn = (Button) findViewById(R.id.button_sign_up);


        signBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_text_username.setText("");
                edit_text_password.setText("");
                Intent intent = new Intent(LoginActivity.this, SignActivity.class);
                startActivity(intent);
            }
        });


        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user.getUserID().equals(edit_text_username.getText().toString()) && (user.getPassword().equals(edit_text_password.getText().toString()))) {
                    Toast.makeText(getApplicationContext(), "로그인에 성공했습니다!", Toast.LENGTH_SHORT).show();
                    setResult(Activity.RESULT_OK);
                    finish();

                }else{
                    Toast.makeText(getApplicationContext(), "아이디나 비밀번호가 잘못되었습니다!!", Toast.LENGTH_SHORT).show();
                    edit_text_password.setText("");
                }

            }
        });



    }
}