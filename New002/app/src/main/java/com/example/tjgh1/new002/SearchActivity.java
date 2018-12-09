package com.example.tjgh1.new002;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class SearchActivity extends AppCompatActivity {

    private SwipeRefreshLayout mSwipeRefresh;

    Button searchButton;
    EditText searchKeyword;
    SearchAdapter adapter;
    RecyclerView recycler_view;
    public  static boolean startActivity = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        setResult(RESULT_OK);

        searchButton = (Button)findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                search();


            }
        });

        searchKeyword = (EditText)findViewById(R.id.searchKeyword);
        recycler_view = (RecyclerView)findViewById(R.id.recycler_view);
        adapter = new SearchAdapter(this);

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
        mSwipeRefresh.setDistanceToTriggerSync(700);

        MainActivity.startActivity = false;
    }

    private void search()
    {
        adapter.searchData(searchKeyword.getText().toString());
    }


    @Override
    protected void onResume() {
        super.onResume();

        if(startActivity)
        {
            startActivity = false;
        }
        else {
            refreshData();
        }
    }

    //데이터 갱신.
    private void refreshData()
    {
        adapter.searchData(searchKeyword.getText().toString());
        mSwipeRefresh.setRefreshing(false);
    }

    //홈으로 메뉴
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_home,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if(id==R.id.menu_home){
            Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent1);
            return true;
        }
        else{
        return super.onOptionsItemSelected(item);}
    }


}
