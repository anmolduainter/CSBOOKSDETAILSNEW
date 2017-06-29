package com.example.joginderpal.csbooksdetails.Data;

import android.net.Uri;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by anmol on 24/6/17.
 */

public class Amazon {

    String search="";

    public Amazon(String search){
        this.search=search;
    }


    public String getPrice() throws IOException {
        Document doc = Jsoup.connect("http://www.amazon.in/s/ref=nb_sb_noss_1?url=search-alias%3Daps&field-keywords=" + search).get();
        Element element = doc.getElementById("s-results-list-atf");
        Element li = element.getElementsByTag("li").get(0);
        if (li == null) {
            return "Srry There is problem You can find that on link below";
        } else {
            Element a = li.getElementsByTag("a").get(4);
            return a.text();
        }
    }

    public String getTitle() throws IOException {

        Document doc= Jsoup.connect("http://www.amazon.in/s/ref=nb_sb_noss_1?url=search-alias%3Daps&field-keywords="+search).get();
        Element element=doc.getElementById("s-results-list-atf");
        Element li=element.getElementsByTag("li").get(0);
        Element a=li.getElementsByTag("a").get(1);
        return a.text();

    }


}
