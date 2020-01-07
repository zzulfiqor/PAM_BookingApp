package com.avenger.bookingyuk.View.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.avenger.bookingyuk.Adapter.OnBoardingPagerAdapter;
import com.avenger.bookingyuk.Preferences.Preferences;
import com.avenger.bookingyuk.R;

public class OnBoardingActivity extends AppCompatActivity {

    ViewPager onBoardingPager;
    OnBoardingPagerAdapter adapter;
    Button btn_skip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);
        IntializeComponrnts();

        btn_skip = findViewById(R.id.button_skip);
        btn_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), LoginActivity.class);
                startActivity(i);
                Preferences.setStatus(getBaseContext(),"ok");
            }
        });

        adapter = new OnBoardingPagerAdapter(getSupportFragmentManager());
        onBoardingPager.setAdapter(adapter);


    }

    protected void IntializeComponrnts(){
        onBoardingPager = findViewById(R.id.on_boarding_pager);
    }

}


