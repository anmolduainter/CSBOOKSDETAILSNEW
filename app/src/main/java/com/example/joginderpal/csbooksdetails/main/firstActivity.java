package com.example.joginderpal.csbooksdetails.main;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;

import com.example.joginderpal.csbooksdetails.R;

/**
 * Created by joginderpal on 14-05-2017.
 */
public class firstActivity extends AppCompatActivity {

    FloatingActionButton fab;

    View view;
    boolean loading=false;
    ViewGroup viewGroup;
    float yDistance;
    float xDistance;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_activity_main);
        fab= (FloatingActionButton) findViewById(R.id.floatingAction_first_activity);

        final float xFab=fab.getX()+fab.getWidth()/2;
        final float yFab=fab.getY()+fab.getHeight()/2;
        Display mdisp=getWindowManager().getDefaultDisplay();
        Point mdispSize=new Point();
        mdisp.getSize(mdispSize);
        final int xCenter=mdispSize.x/3;
        final int yCenter=mdispSize.y/3;
        xDistance=Math.abs(xFab-xCenter);
        yDistance=Math.abs(yFab-yCenter);

        fab.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {

                loading=true;
                LayoutInflater li= (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view=li.inflate(R.layout.category_main,null);
                viewGroup= (ViewGroup) findViewById(R.id.first_relative_main);
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                params.addRule(RelativeLayout.CENTER_IN_PARENT,RelativeLayout.CENTER_IN_PARENT);
                viewGroup.addView(view,0,params);
                  fab.setVisibility(View.GONE);
                final Animator anim= ViewAnimationUtils.createCircularReveal(view,(int)fab.getX(),(int)fab.getY(),0,Math.max(view.getWidth(),view.getHeight()));
                anim.setDuration(300);
                anim.start();

            }
        });
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBackPressed() {

        if (loading) {

            int a=(view.getTop()+view.getBottom())/2;
            int b=(view.getLeft()+view.getRight())/2;
            Animator anim = ViewAnimationUtils.createCircularReveal(view,b,a,view.getHeight(),fab.getWidth()/2);
            anim.setDuration(300);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    viewGroup.removeView(view);
                    fab.setVisibility(View.VISIBLE);
                    TranslateAnimation translateAnimation = new TranslateAnimation(-xDistance, 0, -yDistance, 0);
                    translateAnimation.setDuration(200);
                    fab.startAnimation(translateAnimation);

                }
            },300);

            anim.start();

            loading=false;

        }
        else{

            super.onBackPressed();

        }
    }
}
