package com.barmej.culturalwords;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AnswerActivity extends AppCompatActivity {

    TextView answerText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        answerText = findViewById(R.id.text_view_answer);
        String answer = getIntent().getStringExtra(Constants.OPEN_ANSWER);
        answerText.setText(answer);
    }

    public void buttonBack(View view) {
        finish();
    }
}