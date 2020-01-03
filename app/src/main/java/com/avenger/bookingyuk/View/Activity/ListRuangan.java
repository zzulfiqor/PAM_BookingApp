package com.avenger.bookingyuk.View.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.avenger.bookingyuk.R;

public class ListRuangan extends AppCompatActivity {
    TextView btnRoom1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_ruangan);


        btnRoom1 = findViewById(R.id.btnRoom1);
        btnRoom1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), Description.class);
                startActivity(i);
            }
        });

    }
}
