package com.example.cookmoth_project;

import static com.example.cookmoth_project.MainActivity.user;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class SignActivity extends AppCompatActivity {

    private ImageView back_icon;
    private EditText id;
    private Button id_Btn, nick_Btn, sign_up_btn;
    private EditText pwd;
    private EditText isPwd;
    private EditText nickName;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);


        getSupportActionBar().hide();
        back_icon = (ImageView) findViewById(R.id.back_icon);

        back_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        id = (EditText) findViewById(R.id.edit_text_id);
        id_Btn =(Button) findViewById(R.id.button_check_id);
        pwd =(EditText) findViewById(R.id.edit_text_password);
        isPwd =(EditText) findViewById(R.id.edit_text_password_confirm);
        nickName =(EditText) findViewById(R.id.edit_text_nickname);
        nick_Btn =(Button) findViewById(R.id.button_check_nickname);
        sign_up_btn =(Button) findViewById(R.id.button_register);

        id_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "사용할 수 있는 아이디입니다!. ", Toast.LENGTH_SHORT).show();
            }
        });

        nick_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "사용할 수 있는 닉네임입니다!. ", Toast.LENGTH_SHORT).show();
            }
        });

        sign_up_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pwd.getText().toString().equals(isPwd.getText().toString())){
                    user.signUp(id.getText().toString(), pwd.getText().toString(), nickName.getText().toString());
                    Toast.makeText(getApplicationContext(), "회원가입 성공!!", Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    isPwd.setText("");
                    Toast.makeText(getApplicationContext(), "같은 비밀번호를 입력하셔야 합니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}