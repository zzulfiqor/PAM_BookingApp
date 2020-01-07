package com.avenger.bookingyuk.View.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.avenger.bookingyuk.R;
import com.avenger.bookingyuk.View.Activity.Home;

public class SuccessBookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_book);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();

            }
        },3500);
    }
}
