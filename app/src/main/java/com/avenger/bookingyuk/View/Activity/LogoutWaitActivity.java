package com.avenger.bookingyuk.View.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.avenger.bookingyuk.R;

public class LogoutWaitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout_wait);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                //setelah loading maka akan langsung berpindah ke home activity
                Intent home = new Intent(getBaseContext(), LoginActivity.class);
                startActivity(home);
                finish();

            }
        },2100);

    }
}
