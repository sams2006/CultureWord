package com.barmej.culturalwords;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final int PERMISSIONS_WRITE_EXTERNAL_STORAGE = 123;
    ImageView imageViewQuestions ;
    int getRandomQuestion ;

    int[] arrayImageQuestions = {
            R.drawable.icon_1 ,
            R.drawable.icon_2 ,
            R.drawable.icon_3 ,
            R.drawable.icon_4 ,
            R.drawable.icon_5 ,
            R.drawable.icon_6 ,
            R.drawable.icon_7 ,
            R.drawable.icon_8 ,
            R.drawable.icon_9 ,
            R.drawable.icon_10 ,
            R.drawable.icon_11 ,
            R.drawable.icon_12 ,
            R.drawable.icon_13 ,

    };

    String[] arrayAnswer ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageViewQuestions = findViewById(R.id.image_view_question);
        arrayAnswer = getResources().getStringArray(R.array.answers);
    }

    /**
     * يجب عليك كتابة الكود الذي يقوم بمشاركة الصورة في هذه الدالة
     */
    private void shareImage() {
        // كود مشاركة الصورة هنا
    }

    /**
     *  هذه الدالة تقوم بطلب صلاحية الكتابة على ال external storage حتى يمكن حفظ ملف الصورة
     * <p>
     * وفي حال الحصول على الصلاحية تقوم باستدعاء دالة shareImage لمشاركة الصورة
     **/
    private void checkPermissionAndShare() {
        // insertImage في النسخ من آندرويد 6 إلى آندرويد 9 يجب طلب الصلاحية لكي نتمكن من استخدام الدالة
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // هنا لا يوجد صلاحية للتطبيق ويجب علينا طلب الصلاحية منه عن طريك الكود التالي
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                // بسبب عدم منح المستخدم الصلاحية للتطبيق فمن الأفضل شرح فائدتها له عن طريق عرض رسالة تشرح ذلك
                // هنا نقوم بإنشاء AlertDialog لعرض رسالة تشرح للمستخدم فائدة منح الصلاحية
                AlertDialog alertDialog = new AlertDialog.Builder(this)
                        .setTitle(R.string.permission_title)
                        .setMessage(R.string.permission_explanation)
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // requestPermissions عند الضغط على زر منح نقوم بطلب الصلاحية عن طريق الدالة
                                ActivityCompat.requestPermissions(MainActivity.this,
                                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                        PERMISSIONS_WRITE_EXTERNAL_STORAGE);
                            }
                        }).setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //  عند الضغط على زر منع نقوم بإخفاء الرسالة وكأن شيء لم يكن
                                dialogInterface.dismiss();
                            }
                        }).create();

                // نقوم بإظهار الرسالة بعد إنشاء alertDialog
                alertDialog.show();

            } else {
                // لا داعي لشرح فائدة الصلاحية للمستخدم ويمكننا طلب الصلاحية منه
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        PERMISSIONS_WRITE_EXTERNAL_STORAGE);
            }

        } else {
            // الصلاحية ممنوحه مسبقا لذلك يمكننا مشاركة الصورة
            shareImage();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        // يتم استدعاء هذه الدالة بعد اختيار المستخدم احد الخيارين من رسالة طلب الصلاحية
        if (requestCode == PERMISSIONS_WRITE_EXTERNAL_STORAGE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // تم منح الصلاحية من قبل المستخدم لذلك يمكننا مشاركة الصورة الآن
                shareImage();
            } else {
                // لم يتم منح الصلاحية من المستخدم لذلك لن نقوم بمشاركة الصورة، طبعا يمكننا تنبيه المستخدم بأنه لن يتم مشاركة الصورة لسبب عدم منح الصلاحية للتطبيق
                Toast.makeText(MainActivity.this, R.string.permission_explanation, Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }

    public void changeQuestionButton(View view) {

        Random randomQuestion = new Random() ;
         getRandomQuestion = randomQuestion.nextInt(arrayImageQuestions.length);
        imageViewQuestions.setImageResource(arrayImageQuestions[getRandomQuestion]);
    }

    public void openAnswer(View view) {
        Intent intentAnswer = new Intent(this , AnswerActivity.class);
        intentAnswer.putExtra("open_answer" ,arrayAnswer[getRandomQuestion]);
        startActivity(intentAnswer);
    }
}
