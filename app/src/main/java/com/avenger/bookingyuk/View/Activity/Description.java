package com.avenger.bookingyuk.View.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.avenger.bookingyuk.Models.ModelRuangan;
import com.avenger.bookingyuk.R;
import com.bumptech.glide.Glide;

public class Description extends AppCompatActivity {

    Button btnPesan;
    ImageView imgruangan;
    TextView tvRuangan;
    public static final String EXTRA_NAME = "Name";
    public static final String EXTRA_PHOTO = "Photo";
    String name;
    int photo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        name = getIntent().getStringExtra(EXTRA_NAME);
        photo = getIntent().getIntExtra(EXTRA_PHOTO,0);
        tvRuangan = findViewById(R.id.nm_ruangan);
        imgruangan = findViewById(R.id.gmb_ruangan);
        tvRuangan.setText(name);
        Glide.with(getApplicationContext())
                .load(photo)
                .into(imgruangan);
        btnPesan = findViewById(R.id.btnPesan);
        btnPesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), PesananFinal.class);
                startActivity(i);
            }
        });
    }
}
