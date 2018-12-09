package com.example.tjgh1.new002;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
/*
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;*/

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static MemoryModel selectedModel = null;
    private SwipeRefreshLayout mSwipeRefresh;
    boolean canDo = false;
    MemoryAdapter adapter;
    RecyclerView recycler_view;
    private int adCount = 0;
    public static  boolean startActivity = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
        }

        recycler_view = (RecyclerView)findViewById(R.id.recycler_view);
        adapter = new MemoryAdapter(this);

        recycler_view.setAdapter(adapter);
        recycler_view.setLayoutManager(new LinearLayoutManager(this));

        mSwipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipeLayout);
        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // 이 부분에 리플래시 시키고 싶으신 것을 넣어 주시면 됩니다.

                refreshData();
            }
        });
        mSwipeRefresh.setDistanceToTriggerSync(500);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE,  Manifest.permission.MANAGE_DOCUMENTS}, 0);
        }
        else if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED )
        {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE,  Manifest.permission.MANAGE_DOCUMENTS}, 0);
        }
        else
        {
            canDo = true;
        }


    }




    private void sort(String sort)
    {
        adapter.refreshData(sort);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(startActivity)
        {
            startActivity = false;
        }
        else {
            adCount++;

            adapter.refreshData();

            if (adCount % 5 == 0) {
            //    ShowAd();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
    }


    private  void ShowAddMemory()
    {
        startActivity = true;

        if(canDo) {
            final Intent intent = new Intent(this, AddActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

            startActivityForResult(intent, 500);
        }
        else
        {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.MANAGE_DOCUMENTS}, 0);
        }

    }

    private  void ShowSearchMemory()
    {
        startActivity = true;

        if(canDo) {
            final Intent intent = new Intent(this, SearchActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivityForResult(intent, 500);
        }
        else
        {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.MANAGE_DOCUMENTS}, 0);
        }

    }

    //데이터 갱신.
    private void refreshData()
    {
        adapter.refreshData();
        mSwipeRefresh.setRefreshing(false);
    }




    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.addShelter:
                Intent intent1 = new Intent(getApplicationContext(), AddActivity.class);
                startActivity(intent1);
                return true;
            case R.id.searchShelter:
                Intent intent2 = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(intent2);
                return true;
            case R.id.searchMap:
                Intent intent3 = new Intent(getApplicationContext(), MapActivity.class);
                startActivity(intent3);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }





}
