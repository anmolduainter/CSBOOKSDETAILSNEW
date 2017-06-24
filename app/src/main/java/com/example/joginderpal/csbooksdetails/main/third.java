package com.example.joginderpal.csbooksdetails;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.joginderpal.csbooksdetails.fragment.fragment_price;
import com.example.joginderpal.csbooksdetails.viewPager.ViewPagerCust_wynk;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.BlurTransformation;

/**
 * Created by joginderpal on 01-04-2017.
 */
public class third extends AppCompatActivity {

    View view;
    boolean loading=false;
    ViewGroup viewGroup;
    float yDistance;
    float xDistance;

    ImageView im;
    RelativeLayout bottom_rl,books_rl;
    List<String> li;
    List<String> li1,relatedUrl;
    TextView tx,tx1,tx2,tx3,tx4;
    AppBarLayout appBarLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;
    FloatingActionButton fabPrice;
    View Divider;
    NestedScrollView nv;
    ConstraintLayout constraintLayout;
    ViewPagerCust_wynk viewPagerCust_wynk;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        }

        setContentView(R.layout.third);

        constraintLayout= (ConstraintLayout) findViewById(R.id.constraint_layout_third);
        tx= (TextView) findViewById(R.id.description);
        tx1= (TextView) findViewById(R.id.publisher);
        tx2= (TextView) findViewById(R.id.By);
        tx3= (TextView) findViewById(R.id.Year);
        tx4= (TextView) findViewById(R.id.Pages);
        Divider=findViewById(R.id.divider_third);
        viewPagerCust_wynk= (ViewPagerCust_wynk) findViewById(R.id.ViewPager_price);
        nv= (NestedScrollView) findViewById(R.id.nested_view);
        nv.setFillViewport(true);
        String s=getIntent().getExtras().getString("image");


        try {
            URL url = new URL("http://it-ebooks.info" +s);
            Bitmap image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            Palette.from(image).generate(new Palette.PaletteAsyncListener() {
                @Override
                public void onGenerated(Palette palette) {

                    toolbar.setBackgroundColor(palette.getVibrantColor(getResources().getColor(R.color.colorPrimary)));
                    getWindow().setStatusBarColor(palette.getVibrantColor(getResources().getColor(R.color.colorPrimary)));
                 //   constraintLayout.setBackgroundColor(palette.getDarkMutedColor(getResources().getColor(R.color.colorPrimary)));
                    Divider.setBackgroundColor(Color.WHITE);
                    tx.setTextColor(Color.WHITE);
                    tx1.setTextColor(Color.WHITE);
                    tx2.setTextColor(Color.WHITE);
                    tx3.setTextColor(Color.WHITE);
                    tx4.setTextColor(Color.WHITE);
                }
            });
        } catch(IOException e) {
            System.out.println(e);
        }
        toolbar= (Toolbar) findViewById(R.id.toolBar_third);
        setSupportActionBar(toolbar);
        collapsingToolbarLayout= (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout_third);
        appBarLayout= (AppBarLayout) findViewById(R.id.app_bar_layout_third);
        im= (ImageView) findViewById(R.id.image_books_third);

        postponeEnterTransition();

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {

            int scroll =-1;
            boolean loading1=false;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                 if (scroll==-1){
                     scroll=appBarLayout.getTotalScrollRange();
                 }

                 if (scroll+verticalOffset==0){
                     collapsingToolbarLayout.setTitle(" ");
                     loading1=true;
                 }
                else{
                     if (loading1) {
                         loading1=false;
                     }
                     collapsingToolbarLayout.setTitle(" ");
                 }

            }
        });



        Picasso.with(third.this).load("http://it-ebooks.info" +s).fit().into(im,
                new Callback() {
                    @Override
                    public void onSuccess() {
                        scheduleStartPostponedTransition(im);
                    }

                    @Override
                    public void onError() {

                    }
                });


        Glide.with(third.this).load("http://it-ebooks.info" +s).bitmapTransform(new BlurTransformation(third.this,100)).into(new SimpleTarget<GlideDrawable>() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {

                collapsingToolbarLayout.setBackground(resource);
                constraintLayout.setBackground(resource);

            }
        });


        MyPageAdapter myPageAdapter=new MyPageAdapter(getSupportFragmentManager());
        for (int i=0;i<2;i++){
            myPageAdapter.addFragment(new fragment_price(i));
        }
        viewPagerCust_wynk.setAdapter(myPageAdapter);


        new doit().execute();
    }

    public class doit extends AsyncTask<Void,Void,Void>{

        String text=" ";
        String text1=" ";
        String text2=" ";
        String text3=" ";
        String text4=" ";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {

            try {
                li=new ArrayList<>();
                relatedUrl=new ArrayList<>();
                li1=new ArrayList<>();
                Document doc= Jsoup.connect("http://it-ebooks.info"+getIntent().getExtras().getString("link")).get();
                String img=doc.getElementsByTag("img").first().attr("src");
                li.add(img);
                text=doc.getElementsByTag("span").first().text();
                Elements tr=doc.getElementsByTag("tr");
                for (Element e:tr){

                    if (e.text().contains("By:")){
                      text2=e.getElementsByTag("b").first().text();
                    }
                    if (e.text().contains("Publisher:")){
                        text1=e.getElementsByTag("b").first().text();
                    }
                    if (e.text().contains("Year:")){
                        text3=e.getElementsByTag("b").first().text();
                    }
                    if (e.text().contains("Pages:")){
                        text4=e.getElementsByTag("b").first().text();
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (!li.isEmpty()) {
                if (text2.length()>10){
                    text2=text2.substring(0,10);
                }
                tx.setText(text);
                tx1.setText(text1);
                tx2.setText(text2);
                tx3.setText(text3);
                tx4.setText(text4);
            }
            else{

            }
        }
    }

    private void scheduleStartPostponedTransition(final View sharedElement) {
        sharedElement.getViewTreeObserver().addOnPreDrawListener(
                new ViewTreeObserver.OnPreDrawListener() {
                    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public boolean onPreDraw() {
                        sharedElement.getViewTreeObserver().removeOnPreDrawListener(this);
                        startPostponedEnterTransition();
                        return true;
                    }
                });
    }



    public class MyPageAdapter extends FragmentPagerAdapter {

        List<Fragment> fragment_li=new ArrayList<>();

        public MyPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragment_li.get(position);
        }

        @Override
        public int getCount() {
            return fragment_li.size();
        }
        public void addFragment(Fragment a){
            fragment_li.add(a);
        }
    }



}
