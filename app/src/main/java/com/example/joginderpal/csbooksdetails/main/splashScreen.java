package com.example.joginderpal.csbooksdetails.main;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.joginderpal.csbooksdetails.MainActivity;
import com.example.joginderpal.csbooksdetails.R;
import com.squareup.picasso.Picasso;

/**
 * Created by joginderpal on 25-05-2017.
 */

public class splashScreen extends AppCompatActivity {

    ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        imageView= (ImageView) findViewById(R.id.back_image);
        Picasso.with(getApplicationContext()).load(R.drawable.cs).fit().into(imageView);
        new Handler().postDelayed(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void run() {

                Intent i=new Intent(splashScreen.this,MainActivity.class);
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(splashScreen.this,imageView,"csBooks");
                startActivity(i,options.toBundle());
                splashScreen.this.finish();


            }
        },3000);

    }
}
