package com.example.joginderpal.csbooksdetails;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by joginderpal on 01-04-2017.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {


    List<String> li;
    List<String> li1;
    Context ctx;


    public RecyclerAdapter(List<String> li, List<String> li1, Context ctx) {

        this.li=li;
        this.ctx=ctx;
        this.li1=li1;

    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private int currentitem;
        public ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
          image= (ImageView) itemView.findViewById(R.id.image_books);
            itemView.setOnClickListener(new View.OnClickListener() {
                @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                @Override
                public void onClick(View view) {
                    int position=getAdapterPosition();
                    Intent i=new Intent(ctx, com.example.joginderpal.csbooksdetails.third.class);
                    i.putExtra("image",li.get(position));
                    i.putExtra("link",li1.get(position));
                    ActivityOptionsCompat options = ActivityOptionsCompat.
                            makeSceneTransitionAnimation((Activity) ctx,image,"Books_photo");
                    ctx.startActivity(i,options.toBundle());

                }
            });
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout,parent,false);
        RecyclerView.ViewHolder v=new ViewHolder(view);
        return (ViewHolder) v;
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.ViewHolder holder, int position) {

        Picasso.with(ctx).load("http://it-ebooks.info"+li.get(position)).fit().into(holder.image);

    }

    @Override
    public int getItemCount() {
        return li.size();
    }

}
