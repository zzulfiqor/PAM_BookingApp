package com.avenger.bookingyuk.View.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.avenger.bookingyuk.Models.ModelBooked;
import com.avenger.bookingyuk.Preferences.Preferences;
import com.avenger.bookingyuk.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class TicketDetail extends AppCompatActivity {

    TextView tvId,tvNamaRuang, tvTanggal, tvNamadanOrganisasi, tvAcara;
    String idBooking, namaRuang;
    Button btnBatal;
    ImageView qrCode;
    ModelBooked data;
    QRGEncoder qrgEncoder;
    Bitmap bitmap;

    // Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ruangRef = database.getInstance().getReference().child("RuanganBooked");
    DatabaseReference deleteRef = database.getInstance().getReference();


    Query qDelete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_detail);
        componentInit();
        idBooking = getIntent().getExtras().getString("zhr_id_ruang");
        namaRuang = getIntent().getExtras().getString("zhr_nama_ruang");
        data = getIntent().getExtras().getParcelable("zhr_parcel");

        qDelete = deleteRef.child("RuanganBooked").orderByChild("id_book").equalTo(idBooking);

        WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        int width = point.x;
        int height = point.y;
        int smallerDimension = width < height ? width : height;
        smallerDimension = smallerDimension * 3 / 4;

        qrgEncoder = new QRGEncoder(idBooking,null, QRGContents.Type.TEXT,smallerDimension);
        try {
            bitmap = qrgEncoder.encodeAsBitmap();
            qrCode.setImageBitmap(bitmap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        tvId.setText("#"+idBooking);
        tvNamaRuang.setText(namaRuang);
        tvAcara.setText(data.getAcara_booked());
        tvNamadanOrganisasi.setText(Preferences.getLoggedInUser(getBaseContext())+" / "+data.getOrganisasi_booked());
        tvTanggal.setText(data.getDate_booked());


        btnBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               showAlertPrompt();
            }
        });
    }

    void showAlertPrompt(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Apakah anda yakin akan membatalkan peminjaman ruang ini ?");
        alertDialogBuilder.setPositiveButton("Yakin",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        qDelete.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for(DataSnapshot delData: dataSnapshot.getChildren()){
                                    delData.getRef().removeValue();
                                }
                                Preferences.setStatus(getBaseContext(),"notOk");
                                startActivity(new Intent(getBaseContext(), DeleteSuccesActivity.class));
                                finish();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }
                });

        alertDialogBuilder.setNegativeButton("Tidak",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
    void componentInit(){
        tvId = findViewById(R.id.id_booking_detail);
        tvNamaRuang = findViewById(R.id.nama_ruang_tiket_detail);
        tvTanggal = findViewById(R.id.tgl_tiket_detail);
        tvNamadanOrganisasi = findViewById(R.id.nama_dan_organisasi_detail);
        tvAcara = findViewById(R.id.acara_tiket_detail);
        btnBatal = findViewById(R.id.btnBatal);
        qrCode = findViewById(R.id.qrCode);
    }
}
