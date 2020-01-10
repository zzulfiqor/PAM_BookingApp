package com.avenger.bookingyuk.View.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.avenger.bookingyuk.Models.ModelBooked;
import com.avenger.bookingyuk.Preferences.Preferences;
import com.avenger.bookingyuk.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.vivekkaushik.datepicker.DatePickerTimeline;
import com.vivekkaushik.datepicker.OnDateSelectedListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import es.dmoral.toasty.Toasty;

public class FormBookingActivity extends AppCompatActivity {

    TextView tvNama, tvNim, tvRuang, tvTglTerpilih;
    EditText etOrganisasi, etAcara;
    DatePickerTimeline datePicker;
    Button btnPinjam;
    int hari, tahun;
    int monthRead;
    Query q;
    List<Date> myList = new ArrayList<Date>();

    // Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference mhsRef = database.getReference("RuanganBooked");
    DatabaseReference ref = database.getReference();

//    Model
    ModelBooked booked;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_booking);
        componentInit();

        booked = new ModelBooked();

        tvRuang.setText(Preferences.getNamaRuangRealDipilih(getBaseContext()));
        tvNama.setText(Preferences.getLoggedInUser(getBaseContext()));
        tvNim.setText(Preferences.getLoggedInNim(getBaseContext()));



        q = mhsRef.orderByChild("id_ruang").equalTo(Preferences.getNamaRuangDipilih(getBaseContext()));
        q.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Date date = null;
                for(DataSnapshot addDate: dataSnapshot.getChildren()){
                    ModelBooked item = addDate.getValue(ModelBooked.class);
                    String pattern = "MM-dd-yyyy";
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                    try {
                        date = simpleDateFormat.parse(item.getDate_booked());
                        myList.add(date);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }

                Date[] dates = new Date[myList.size()];
                dates = myList.toArray(dates);
                datePicker.deactivateDates(dates);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        // Set a Start date (Default, 1 Jan 1970)
        datePicker.setInitialDate(2020, 0, 6);





        datePicker.setOnDateSelectedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(int year, int month, int day, int dayOfWeek) {
                monthRead = month+1;
                hari = day;
                tahun = year;

                String datenow = day+" / 0"+monthRead+" / "+year;
                tvTglTerpilih.setText(datenow);
            }

            @Override
            public void onDisabledDateSelected(int year, int month, int day, int dayOfWeek, boolean isDisabled) {
                Toasty.error(getBaseContext(), "Sudah ada yang meminjam di tanggal ini.", Toast.LENGTH_SHORT, true).show();
            }
        });

        btnPinjam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertPrompt();
            }
        });
    }

    void showAlertPrompt(){



//        get time and date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault());
        final String currentDateandTime = sdf.format(new Date());
        String idBooked = ""+Preferences.getLoggedInNim(getBaseContext())+""+currentDateandTime;

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Apakah anda yakin akan melakukan peminjaman ruang ini ?");
        alertDialogBuilder.setPositiveButton("Yakin",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        booked.setId_ruang(Preferences.getNamaRuangDipilih(getBaseContext()));
                        booked.setNIM(Preferences.getLoggedInNim(getBaseContext()));
                        booked.setAcara_booked(etAcara.getText().toString());
                        booked.setTgl_booked(hari);
                        booked.setThn_booked(tahun);
                        booked.setBulan_booked(monthRead);
                        booked.setOrganisasi_booked(etOrganisasi.getText().toString());
                        booked.setId_book(""+Preferences.getLoggedInNim(getBaseContext())+""+currentDateandTime);
                        booked.setDate_booked(""+monthRead+"-"+hari+"-"+tahun);
                        booked.setNama_ruang(Preferences.getNamaRuangRealDipilih(getBaseContext()));
                        mhsRef.child(booked.getId_book()).setValue(booked);
                        startActivity(new Intent(FormBookingActivity.this, SuccessBookActivity.class));
                        finish();
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
        etOrganisasi = findViewById(R.id.et_organisasi_form);
        etAcara = findViewById(R.id.et_acara_organisasi);
        datePicker = findViewById(R.id.datePickerTimeline);
        tvNama = findViewById(R.id.nama_form);
        tvNim = findViewById(R.id.nim_form);
        tvRuang = findViewById(R.id.nama_ruang_form);
        tvTglTerpilih = findViewById(R.id.tgl_terpilih);
        btnPinjam = findViewById(R.id.btn_pinjam);
    }
}
