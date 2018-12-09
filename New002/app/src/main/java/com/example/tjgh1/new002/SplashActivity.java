package com.example.tjgh1.new002;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {   //3초 대기
            Thread.sleep(3000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        //메인 액티비티로 넘어감
        startActivity(new Intent(this, MainActivity.class));
        finish();


    }
}
