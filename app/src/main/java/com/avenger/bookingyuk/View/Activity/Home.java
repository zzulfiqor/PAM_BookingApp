package com.avenger.bookingyuk.View.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.avenger.bookingyuk.Models.ModelMahasiswa;
import com.avenger.bookingyuk.R;
import com.avenger.bookingyuk.Temp;

public class Home extends AppCompatActivity {

    ImageView btnHome, btnRoom, btnProfile;
    TextView nimMhs;
    Temp temp = new Temp();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnHome = findViewById(R.id.btnHome);
        btnRoom = findViewById(R.id.btnRoom);
        btnProfile = findViewById(R.id.btnProfile);
        nimMhs = findViewById(R.id.nim_mhs);

        nimMhs.setText(temp.getNim());

        btnRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), ListRuangan.class);
                startActivity(i);
            }
        });

        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), Profile.class);
                startActivity(i);
            }
        });
    }
}
