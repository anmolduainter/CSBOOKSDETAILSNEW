package com.example.joginderpal.csbooksdetails.viewPager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

/**
 * Created by joginderpal on 25-04-2017.
 */
public class ViewPagerCust_wynk extends ViewPager {

    public ViewPagerCust_wynk(Context context) {
        super(context);
    }

    public ViewPagerCust_wynk(Context context, AttributeSet attrs) {
        super(context, attrs);
        setPageTransformer(true,new ViewPagerTransformer_wynk());
        setOverScrollMode(OVER_SCROLL_NEVER);
    }

}
