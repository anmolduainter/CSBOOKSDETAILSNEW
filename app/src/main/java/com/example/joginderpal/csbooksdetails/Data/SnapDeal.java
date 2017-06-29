package com.example.joginderpal.csbooksdetails.Data;

import android.net.Uri;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by anmol on 29/6/17.
 */

public class SnapDeal {


    String search="";

    public SnapDeal(String search){
        this.search=search;
    }

    public String getPrice() throws IOException {
        Document doc = Jsoup.connect("https://www.snapdeal.com/search?keyword="+search+"&santizedKeyword=&catId=&categoryId=0&suggested=false&vertical=&noOfResults=20&searchState=&clickSrc=go_header&lastKeyword=&prodCatId=&changeBackToAll=false&foundInAll=false&categoryIdSearched=&cityPageUrl=&categoryUrl=&url=&utmContent=&dealDetail=&sort=rlvncy").get();
        Element section=doc.select("span.lfloat.product-price").first();
        return section.text();
    }

    public String getTitle() throws IOException {
        Document doc = Jsoup.connect("https://www.snapdeal.com/search?keyword="+search+"&santizedKeyword=&catId=&categoryId=0&suggested=false&vertical=&noOfResults=20&searchState=&clickSrc=go_header&lastKeyword=&prodCatId=&changeBackToAll=false&foundInAll=false&categoryIdSearched=&cityPageUrl=&categoryUrl=&url=&utmContent=&dealDetail=&sort=rlvncy").get();
        Element section=doc.select("p.product-title").first();
        return section.text();
    }

}
