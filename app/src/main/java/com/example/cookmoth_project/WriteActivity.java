package com.example.cookmoth_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class WriteActivity extends AppCompatActivity {

    private LinearLayout linearLayout;
    private int stepCounter = 1;

    private ImageView back_icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        getSupportActionBar().hide();

        linearLayout = findViewById(R.id.linearLayout);

        Button submitButton = findViewById(R.id.button);
        Button buttonAddStep = findViewById(R.id.button_add_step);


        back_icon = (ImageView) findViewById(R.id.back_icon);

        back_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 메인 화면으로 돌아가기
                Intent intent = new Intent(WriteActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        buttonAddStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {addStep();
            }
        });
    }

    private void addStep() {



        // 순서 표시
        TextView stepLabel = new TextView(this);
        stepLabel.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        stepLabel.setText("순서 " + stepCounter);

        // 박스 레이아웃 생성
        LinearLayout stepLayout = new LinearLayout(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(0, 20, 0, 0); // 위쪽에 20dp의 marginTop 추가
        stepLayout.setLayoutParams(layoutParams);

        stepLayout.setOrientation(LinearLayout.VERTICAL);
        stepLayout.setPadding(16, 16, 16, 16);
        stepLayout.setBackgroundResource(R.drawable.box_background); // 박스 배경 (XML에서 정의 필요)

        // 새 이미지 버튼 추가
        ImageButton newImageButton = new ImageButton(this);
        newImageButton.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                480 // 적절한 크기로 설정
        ));


        newImageButton.setImageResource(R.drawable.ic_add_photo); // 아이콘 설정
        newImageButton.setContentDescription("사진 추가");

        // 새 EditText 추가 (내용)
        EditText newEditTextContent = new EditText(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        newEditTextContent.setLayoutParams(params);

        newEditTextContent.setHint("내용을 입력하세요");
        newEditTextContent.setInputType(android.text.InputType.TYPE_TEXT_FLAG_MULTI_LINE);

        // 박스 레이아웃에 뷰 추가
        stepLayout.addView(stepLabel);
        stepLayout.addView(newImageButton);
        stepLayout.addView(newEditTextContent);

        // 박스 레이아웃을 재료 추가 섹션 위에 추가
        linearLayout.addView(stepLayout, linearLayout.getChildCount() - 5 );

        stepCounter++;
    }
}
