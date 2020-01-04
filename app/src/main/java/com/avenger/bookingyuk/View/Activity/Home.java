package com.avenger.bookingyuk.View.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.avenger.bookingyuk.Preferences.Preferences;
import com.avenger.bookingyuk.R;
import com.avenger.bookingyuk.View.Fragments.FragmentListRuangan;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Home extends AppCompatActivity {

    TextView nimMhs;

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()){
                case R.id.menu_list:
                    FragmentListRuangan fragmentListRuangan = new FragmentListRuangan();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, fragmentListRuangan, fragmentListRuangan.getClass().getSimpleName());
                    fragmentTransaction.commit();
                    return true;
            }
            return false;
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

    }
}
