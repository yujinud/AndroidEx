package com.example.tjgh1.new002;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.nostra13.universalimageloader.core.process.BitmapProcessor;

import java.util.ArrayList;
import java.util.List;

public class MemoryAdapter extends RecyclerView.Adapter {
    public List<MemoryModel> list;
    DBHelper db;
    Activity context;
    ImageLoader imageLoader;
    private DisplayImageOptions options;
    private ImageLoaderConfiguration config;

    public MemoryAdapter(Activity context)
    {
        this.context = context;
        this.list = new ArrayList<MemoryModel>();
        db = new DBHelper(context);

        list = db.GetAllMemory();

        imageLoader = ImageLoader.getInstance(); // Get singleton instance
        config = ImageLoaderConfiguration.createDefault(context);
        imageLoader.init(config);

    }

    public void refreshData(String sort)
    {
        list.clear();
        list = db.GetAllMemory(sort);
        notifyDataSetChanged();
    }

    /*public  void searchData(String keyword)
    {
        list.clear();
        list = db.GetMemory(keyword);
        notifyDataSetChanged();
    }*/

    public void refreshData()
    {

        list.clear();
        list = db.GetAllMemory();
        notifyDataSetChanged();
    }

    @Override
    public MemoryViewHoder onCreateViewHolder(
            ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.memory_viewholder, viewGroup, false);
        return new MemoryViewHoder(itemView);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {




        final MemoryViewHoder viewHolder = (MemoryViewHoder)holder;

        if(options == null)
        {
            options = new DisplayImageOptions.Builder()
                    .imageScaleType(ImageScaleType.EXACTLY)
                    .resetViewBeforeLoading(true)
                    .cacheInMemory(true)
                    .cacheOnDisk(true)
                    .considerExifParams(false)
                    .postProcessor(new BitmapProcessor() {
                        @Override
                        public Bitmap process(Bitmap bmp) {
                            return Bitmap.createScaledBitmap(bmp, viewHolder.memoryImage.getWidth(), viewHolder.memoryImage.getHeight(), false);
                        }
                    })
                    .build();
        }

        viewHolder.comment.setText(list.get(position).Comment);
        viewHolder.registDate.setText(list.get(position).RegDate);
        viewHolder.memoryTitle.setText(list.get(position).Title);
        viewHolder.namename.setText(list.get(position).UserName);

        Uri selectedImageUri = Uri.parse(list.get(position).ImageUrl.toString());

        if(list.get(position).ImageUrl.toString().equals(""))
        {
        }
        else {

            imageLoader.displayImage(selectedImageUri.toString(), viewHolder.memoryImage, options, new ImageLoadingListener() {
                @Override
                public void onLoadingStarted(String imageUri, View view) {

                    String aaa =imageUri;
                }
                @Override
                public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                    String aaa =imageUri;
                }
                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                    String aaa =imageUri;
                }
                @Override
                public void onLoadingCancelled(String imageUri, View view) {
                    String aaa =imageUri;
                }
            }, new ImageLoadingProgressListener() {
                @Override
                public void onProgressUpdate(String imageUri, View view, int current, int total) {
                    String aaa =imageUri;
                }
            });

        }
        viewHolder.rootLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.selectedModel = list.get(position);
                MainActivity.startActivity = true;
                ShowEditMemory();
            }
        });

    }

    private void ShowEditMemory()
    {
        final Intent intent = new Intent(context, EditMemoryActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        context.startActivityForResult(intent, 500);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

}
