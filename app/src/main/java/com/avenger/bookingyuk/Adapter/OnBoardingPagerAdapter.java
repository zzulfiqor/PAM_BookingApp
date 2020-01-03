package com.avenger.bookingyuk.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.avenger.bookingyuk.View.Fragments.OnBoarding1;
import com.avenger.bookingyuk.View.Fragments.OnBoarding2;
import com.avenger.bookingyuk.View.Fragments.OnBoarding3;

public class OnBoardingPagerAdapter extends androidx.fragment.app.FragmentPagerAdapter {

    public OnBoardingPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    public OnBoardingPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:return new OnBoarding1();
            case 1:return new OnBoarding2();
            case 2:return new OnBoarding3();
            default: return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
