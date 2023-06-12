package com.quynhlm.dev.activity_lab8;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class Animation extends AppCompatActivity {

    ImageView imageView;
    Button btn1,btn2,btn3,btnoff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        imageView = findViewById(R.id.imgquat);
        btn1 = findViewById(R.id.btnnhanh);
        btn2 = findViewById(R.id.btnvua);
        btn3 = findViewById(R.id.btncham);
        btnoff = findViewById(R.id.btnoff);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.view.animation.Animation animation = AnimationUtils.loadAnimation(Animation.this,R.anim.anim_nhanh);
                imageView.startAnimation(animation);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.view.animation.Animation animation = AnimationUtils.loadAnimation(Animation.this,R.anim.anim_vua);
                imageView.startAnimation(animation);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.view.animation.Animation animation = AnimationUtils.loadAnimation(Animation.this,R.anim.anim_cham);
                imageView.startAnimation(animation);
            }
        });
        btnoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.clearAnimation();
            }
        });
    }
}