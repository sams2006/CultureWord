package com.barmej.culturalwords;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.File;

public class ShareActivity extends AppCompatActivity {

    ImageView imageView ;
    EditText editText ;
    int getImage ;
    String mTitle ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_share);


        imageView = findViewById(R.id.image_view_question);
        editText = findViewById(R.id.edit_text_share_title) ;
         getImage = getIntent().getIntExtra("share_image" , R.drawable.icon_6) ;
        imageView.setImageResource(getImage);

        SharedPreferences sharedPreferences = getSharedPreferences("app_perf" , MODE_PRIVATE) ;
        String getTitle = sharedPreferences.getString("title" , "") ;
        editText.setText(getTitle);


    }

    public void share(View view) {
        Resources resources = getResources();
        Uri imageUri = new Uri.Builder()
                .scheme(ContentResolver.SCHEME_ANDROID_RESOURCE)
                .authority(resources.getResourcePackageName(getImage))
                .appendPath(resources.getResourceTypeName(getImage))
                .appendPath(resources.getResourceEntryName(getImage))
                .build();

         mTitle = editText.getText().toString();
         saveTitle();
        Intent intentShare = new Intent() ;
        intentShare.setAction(Intent.ACTION_SEND) ;
        intentShare.putExtra(Intent.EXTRA_STREAM , imageUri) ;
        intentShare.putExtra(Intent.EXTRA_TEXT , mTitle) ;
        intentShare.setType("image/*");
        startActivity(Intent.createChooser(intentShare , null));

    }

    private void saveTitle() {
        SharedPreferences sharedPreferences = getSharedPreferences("app_perf", MODE_PRIVATE) ;
        SharedPreferences.Editor editor = sharedPreferences.edit() ;
        editor.putString("title" ,mTitle);
        editor.apply();
    }
}