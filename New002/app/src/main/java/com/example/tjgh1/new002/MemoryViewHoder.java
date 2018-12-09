package com.example.tjgh1.new002;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MemoryViewHoder extends RecyclerView.ViewHolder {

    public TextView registDate;
    public TextView memoryTitle;
    public TextView namename;
    public TextView comment;
    public LinearLayout rootLayout;
    public ImageView memoryImage;

    public MemoryViewHoder(View itemView){
        super(itemView);

        registDate = (TextView)itemView.findViewById(R.id.registDate);
        memoryTitle = (TextView)itemView.findViewById(R.id.memoryTitle);
        namename = (TextView)itemView.findViewById(R.id.namename);
        comment = (TextView)itemView.findViewById(R.id.comment);
        rootLayout = (LinearLayout)itemView.findViewById(R.id.rootLayout);
        memoryImage = (ImageView)itemView.findViewById(R.id.memoryImage);
    }



}
