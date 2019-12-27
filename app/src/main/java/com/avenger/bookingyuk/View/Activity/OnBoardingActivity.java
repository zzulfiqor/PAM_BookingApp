package com.avenger.bookingyuk.View.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.avenger.bookingyuk.Adapter.OnBoardingPagerAdapter;
import com.avenger.bookingyuk.R;

public class OnBoardingActivity extends AppCompatActivity {

    ViewPager onBoardingPager;
    OnBoardingPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);
        IntializeComponrnts();

        adapter = new OnBoardingPagerAdapter(getSupportFragmentManager());
        onBoardingPager.setAdapter(adapter);


    }

    protected void IntializeComponrnts(){
        onBoardingPager = findViewById(R.id.on_boarding_pager);
    }

}


