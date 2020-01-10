package com.avenger.bookingyuk.View.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.avenger.bookingyuk.Preferences.Preferences;
import com.avenger.bookingyuk.R;

import java.util.Calendar;
import java.util.Date;

public class SplashScreen extends AppCompatActivity {
    Intent home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                //setelah loading maka akan langsung berpindah ke home activity
                Date[] dates = {Calendar.getInstance().getTime(),Calendar.getInstance().getTime()};

                if (Preferences.getStatus(getBaseContext()).equals("ok")){

                    if (Preferences.getLoggedInUser(getBaseContext()).isEmpty()){
                        home = new Intent(getBaseContext(), LoginActivity.class);
                    }else {
                        home = new Intent(getBaseContext(), Home.class);
                    }
                }else{
                    home = new Intent(getBaseContext(), OnBoardingActivity.class);
                }

                startActivity(home);
                finish();

            }
        },3000);

    }
}
