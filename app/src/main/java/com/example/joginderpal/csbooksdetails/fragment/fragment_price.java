package com.example.joginderpal.csbooksdetails.fragment;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.joginderpal.csbooksdetails.Data.Amazon;
import com.example.joginderpal.csbooksdetails.R;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.BlurTransformation;

/**
 * Created by joginderpal on 25-04-2017.
 */
@SuppressLint("ValidFragment")
public class fragment_price extends Fragment {

    int i;
    List<String> title,price;
    RequestQueue requestQueue;
    ImageView amazonImage;
    RelativeLayout rel,rel1;
    TextView priceText,titleText;
    ProgressBar progressBar;

    public fragment_price(int i) {
        this.i=i;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.fragment_price,container,false);
        rel= (RelativeLayout) v.findViewById(R.id.rel_price);
        rel1= (RelativeLayout) v.findViewById(R.id.rel_price_sub);
        amazonImage= (ImageView) v.findViewById(R.id.amazon_image);
        priceText= (TextView) v.findViewById(R.id.fragment_title_text1);
        titleText= (TextView) v.findViewById(R.id.fragment_price_text1);
        progressBar= (ProgressBar) v.findViewById(R.id.google_progress);
        title=new ArrayList<>();
        price=new ArrayList<>();
        switch (i){
            case 0:

                Picasso.with(getActivity()).load("http://www.amazonsellerslawyer.com/wp-content/uploads/2016/03/amazon-claim.png").fit().into(amazonImage);
                Glide.with(getActivity()).load("http://www.amazonsellerslawyer.com/wp-content/uploads/2016/03/amazon-claim.png").bitmapTransform(new BlurTransformation(getActivity(),100)).into(new SimpleTarget<GlideDrawable>() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        rel.setBackground(resource);
                        rel1.setBackground(resource);
                    }
                });
                break;


//            case 1:
//                Picasso.with(getActivity()).load("https://c.slashgear.com/wp-content/uploads/2017/01/ebay-sign-header.jpg").fit().into(amazonImage);
//                Glide.with(getActivity()).load("https://c.slashgear.com/wp-content/uploads/2017/01/ebay-sign-header.jpg").bitmapTransform(new BlurTransformation(getActivity(),100)).into(new SimpleTarget<GlideDrawable>() {
//                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
//                    @Override
//                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
//                        rel.setBackground(resource);
//                        rel1.setBackground(resource);
//                    }
//                });
//
//                titleText.setText("JAVA");
//                priceText.setText("Rs.200");
//
//                break;

        }


        new doit().execute();

        return v;
    }


    class doit extends AsyncTask<Void,Void,Void>{

        String price="";
        String title="";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            Amazon amazon=new Amazon("java");
            try {
                price=amazon.getPrice();
                title=amazon.getTitle();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressBar.setVisibility(View.INVISIBLE);
            priceText.setText(price);
            titleText.setText(title);
        }
    }

}
