package com.example.joginderpal.csbooksdetails;

import android.app.ProgressDialog;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by joginderpal on 01-04-2017.
 */
public class seconActivity extends AppCompatActivity {

    int pageCount=1;
    boolean loading=true;
    List<String> li;
    List<String> li1;
    int visibleCount,totalCount,pastCount;
    RecyclerView recyclerView,recyclerView1;
    GridLayoutManager layoutManager;
    RecyclerView.Adapter adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        recyclerView= (RecyclerView) findViewById(R.id.rvactivity_main);
        recyclerView1= (RecyclerView) findViewById(R.id.rvdactivity_main);

        li=new ArrayList<>();
        li1=new ArrayList<>();


        if (getResources().getConfiguration().orientation==Configuration.ORIENTATION_PORTRAIT){
            layoutManager = new GridLayoutManager(seconActivity.this, 2);
            recyclerView.setLayoutManager(layoutManager);

            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                    if (dy>0) {
                        visibleCount = layoutManager.getChildCount();
                        totalCount = layoutManager.getItemCount();
                        pastCount=layoutManager.findFirstVisibleItemPosition();

                        if (loading) {

                            if ((visibleCount + pastCount) >= totalCount) {


                                loading = false;
                                new doit1().execute();

                            }
                        }
                    }
                }
            });


        }
        else if (getResources().getConfiguration().orientation==Configuration.ORIENTATION_LANDSCAPE){
            layoutManager = new GridLayoutManager(seconActivity.this, 3);

            recyclerView1.setLayoutManager(layoutManager);

            recyclerView1.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                    if (dy>0){

                        visibleCount=layoutManager.getChildCount();
                        totalCount=layoutManager.getItemCount();
                        pastCount=layoutManager.findFirstCompletelyVisibleItemPosition();
                        if (loading){
                            if ((visibleCount+pastCount)>totalCount){
                                loading=false;
                                new doit1().execute();

                            }
                        }

                    }


                }
            });



        }




        new doit().execute();
    }

    public class doit extends AsyncTask<Void,Void,Void> {


        ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd=new ProgressDialog(seconActivity.this);
            pd.setMessage("Loading..");
            pd.show();
        }

        @Override
        protected Void doInBackground(Void... params) {


            try {
                String sear=getIntent().getExtras().getString("search");
                Document doc= Jsoup.connect("http://it-ebooks.info/search/?q="+sear+"&type=title"+"&page="+pageCount).get();
                Elements ele=doc.getElementsByTag("table");
                for (Element el1:ele){

                    String align=el1.attr("width");
                    if (align.equals("100%")){

                        Elements tr=el1.getElementsByTag("tr");
                        for (Element tr1:tr){

                            String src=tr1.getElementsByTag("img").first().attr("src");
                            li.add(src);
                            String href=tr1.getElementsByTag("a").first().attr("href");
                            li1.add(href);

                        }

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
            pd.dismiss();
            if (getResources().getConfiguration().orientation== Configuration.ORIENTATION_PORTRAIT) {

                adapter = new com.example.joginderpal.csbooksdetails.RecyclerAdapter(li, li1, seconActivity.this);
                recyclerView.setAdapter(adapter);
            }
            else if (getResources().getConfiguration().orientation==Configuration.ORIENTATION_LANDSCAPE){
                adapter = new com.example.joginderpal.csbooksdetails.RecyclerAdapter(li, li1, seconActivity.this);
                recyclerView1.setAdapter(adapter);
                recyclerView1.addItemDecoration(new recyclerItem(30));


            }
     //       ed2.setText(li.get(0));
        }
    }


    public class doit1 extends AsyncTask<Void,Void,Void>{

        ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd=new ProgressDialog(seconActivity.this);
            pageCount++;
            pd.setMessage(String.valueOf(pageCount));
            pd.show();
        }

        @Override
        protected Void doInBackground(Void... params) {

            try {


                String sear=getIntent().getExtras().getString("search");
                Document doc= Jsoup.connect("http://it-ebooks.info/search/?q="+sear+"&type=title"+"&page="+pageCount).get();
                Elements ele=doc.getElementsByTag("table");
                for (Element el1:ele){

                    String align=el1.attr("width");
                    if (align.equals("100%")){

                        Elements tr=el1.getElementsByTag("tr");
                        for (Element tr1:tr){

                            String src=tr1.getElementsByTag("img").first().attr("src");
                            li.add(src);
                            String href=tr1.getElementsByTag("a").first().attr("href");
                            li1.add(href);

                        }

                    }

                }



            } catch (IOException e) {
                e.printStackTrace();
            }





            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            pd.dismiss();
                adapter.notifyDataSetChanged();
                loading=true;
            }


    }


}
