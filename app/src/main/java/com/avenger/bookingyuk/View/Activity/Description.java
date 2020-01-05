package com.avenger.bookingyuk.View.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.avenger.bookingyuk.Models.ModelRuangan;
import com.avenger.bookingyuk.Preferences.Preferences;
import com.avenger.bookingyuk.R;
import com.bumptech.glide.Glide;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Description extends AppCompatActivity {

    CollapsingToolbarLayout collapsingToolbarLayout;
    Button btnPinjam;
    ImageView imgruangan;
    TextView tvRuangan, tvProyektor, tvAc, tvKapasitas, tvCatatan;

    String idRuang;

    // Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ruangRef = database.getInstance().getReference().child("Ruang");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        idRuang = Preferences.getNamaRuangDipilih(this);

        componentInit();
        collapsingToolbarLayout = findViewById(R.id.collapsingToolbar);
        getRuang();

        btnPinjam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), FormBookingActivity.class));
            }
        });
    }

    void getRuang(){

        ruangRef.child(idRuang).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Log.d("zhr",""+dataSnapshot.getValue());

                ModelRuangan ruang_ = dataSnapshot.getValue(ModelRuangan.class);

                if (idRuang.equals(ruang_.getId_ruang())){
                    collapsingToolbarLayout.setTitle(ruang_.getNama_ruang());

                    if (ruang_.getIs_ac()){
                        tvAc.setText("Ada");
                    }else{
                        tvAc.setText("Tidak Ada");

                    }

                    if (ruang_.getIs_projector()){
                        tvProyektor.setText("Ada");
                    }else{
                        tvProyektor.setText("Tidak Ada");

                    }
                    tvKapasitas.setText(ruang_.getKapasitas_ruang().toString()+" Orang");
                    tvCatatan.setText(ruang_.getCatatan_ruangan());
                }else{

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    void componentInit(){
        tvAc = findViewById(R.id.ada_ac_desc);
        tvKapasitas = findViewById(R.id.kapasitas_ruang_desc);
        tvProyektor = findViewById(R.id.ada_proyektor_desc);
        tvCatatan = findViewById(R.id.ruang_catatan_desc);
        btnPinjam = findViewById(R.id.btn_pinjam);
    }

}
