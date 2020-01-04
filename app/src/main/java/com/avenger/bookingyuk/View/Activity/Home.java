package com.avenger.bookingyuk.View.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.avenger.bookingyuk.Preferences.Preferences;
import com.avenger.bookingyuk.R;
import com.avenger.bookingyuk.View.Fragments.FragmentListRuangan;
import com.avenger.bookingyuk.View.Fragments.FragmentProfile;
import com.avenger.bookingyuk.View.Fragments.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Home extends AppCompatActivity {

    TextView nimMhs;

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

            Fragment selectedFragment = null;

            switch (menuItem.getItemId()){
                case R.id.menu_list:
                    selectedFragment = new FragmentListRuangan(); break;
                case R.id.menu_home:
                    selectedFragment = new HomeFragment(); break;
                case R.id.menu_profile:
                    selectedFragment = new FragmentProfile();break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();

        return true;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        nimMhs = findViewById(R.id.nim_mhs);

        nimMhs.setText(Preferences.getLoggedInUser(getBaseContext()));
        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigationView);
        bottomNav.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();

    }
}
