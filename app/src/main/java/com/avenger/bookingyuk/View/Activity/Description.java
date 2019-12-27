package com.avenger.bookingyuk.View.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.avenger.bookingyuk.R;

public class Description extends AppCompatActivity {

    Button btnPesan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

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
