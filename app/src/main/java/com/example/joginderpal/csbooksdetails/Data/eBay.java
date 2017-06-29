package com.example.joginderpal.csbooksdetails.Data;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by anmol on 29/6/17.
 */

public class eBay {

    String search="";

    public eBay(String search){
        this.search=search;
    }

    public String getPrice() throws IOException {

        Document doc= Jsoup.connect("http://www.ebay.in/sch/i.html?_from=R40&_sacat=0&_nkw="+search).get();
        Element ul=doc.getElementById("ListViewInner");
        Element li=ul.getElementById("item3ada490b19");
        if (li==null){
            return "not available";
        }
        else {
            Element ul1 = li.getElementsByTag("ul").attr("class", "lvprices").get(0);
            Element li1 = ul1.getElementsByTag("li").attr("class", "lvprice").get(0);
            String span = li1.getElementsByTag("span").attr("class", "bold").get(0).text();
            return span;
        }
    }


    public String getTitle() throws IOException {

        Document doc= Jsoup.connect("http://www.ebay.in/sch/i.html?_from=R40&_sacat=0&_nkw="+search).get();
        Element ul=doc.getElementById("ListViewInner");
        if (ul==null){
            return "not avaialable";
        }
        else {
            Element li = ul.getElementById("item3ada490b19");
            if (li==null){
                return "not available";
            }
            else{
                String h=li.getElementsByTag("h3").attr("class","lvtitle").get(0).getElementsByTag("a").get(0).text();
                 return h;
            }
        }

    }



}
