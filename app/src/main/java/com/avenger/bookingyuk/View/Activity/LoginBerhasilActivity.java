package com.avenger.bookingyuk.View.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.avenger.bookingyuk.Preferences.Preferences;
import com.avenger.bookingyuk.R;

public class LoginBerhasilActivity extends AppCompatActivity {
    TextView tvName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_berhasil);


        tvName = findViewById(R.id.nama_selamat_datang);
        tvName.setText("Selamat datang, "+ Preferences.getLoggedInUser(getBaseContext()));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                //setelah loading maka akan langsung berpindah ke home activity
                Intent home = new Intent(getBaseContext(), Home.class);
                startActivity(home);
                finish();

            }
        },3500);
    }
}
