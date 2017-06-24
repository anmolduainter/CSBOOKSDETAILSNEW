package com.example.joginderpal.csbooksdetails;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by joginderpal on 02-04-2017.
 */
public class recyclerItem extends RecyclerView.ItemDecoration {

    int space ;
    public recyclerItem(int space) {
        this.space=space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        outRect.top=space;
        outRect.left=space;
        outRect.right=space;
        outRect.bottom=space;

    }
}
