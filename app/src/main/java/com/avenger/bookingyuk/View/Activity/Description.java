package com.avenger.bookingyuk.View.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.avenger.bookingyuk.Models.ModelRuangan;
import com.avenger.bookingyuk.Preferences.Preferences;
import com.avenger.bookingyuk.R;
import com.bumptech.glide.Glide;
import com.google.android.material.appbar.CollapsingToolbarLayout;

public class Description extends AppCompatActivity {

    CollapsingToolbarLayout collapsingToolbarLayout;
    Button btnPesan;
    ImageView imgruangan;
    TextView tvRuangan;

    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        collapsingToolbarLayout = findViewById(R.id.collapsingToolbar);


        name = Preferences.getLoggedInUser(this);
        collapsingToolbarLayout.setTitle(name);


    }
}
