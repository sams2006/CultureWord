package com.barmej.culturalwords;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

public class ShareActivity extends AppCompatActivity {

    ImageView imageView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);


        imageView = findViewById(R.id.image_view_question);
        int getImage = getIntent().getIntExtra("share_image" , R.drawable.icon_6) ;
        imageView.setImageResource(getImage);


    }
}