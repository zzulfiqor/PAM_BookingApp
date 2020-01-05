package com.avenger.bookingyuk.View.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.avenger.bookingyuk.Preferences.Preferences;
import com.avenger.bookingyuk.R;
import com.avenger.bookingyuk.SuccessBookActivity;
import com.vivekkaushik.datepicker.DatePickerTimeline;
import com.vivekkaushik.datepicker.OnDateSelectedListener;

public class FormBookingActivity extends AppCompatActivity {

    TextView tvNama, tvNim, tvRuang, tvTglTerpilih;
    EditText etOrganisasi, etAcara;
    DatePickerTimeline datePicker;
    Button btnPinjam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_booking);
        componentInit();

        tvRuang.setText(Preferences.getNamaRuangRealDipilih(getBaseContext()));
        tvNama.setText(Preferences.getLoggedInUser(getBaseContext()));
        tvNim.setText(Preferences.getLoggedInNim(getBaseContext()));

        // Set a Start date (Default, 1 Jan 1970)
        datePicker.setInitialDate(2020, 0, 6);
        datePicker.setOnDateSelectedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(int year, int month, int day, int dayOfWeek) {
                int monthRead = month+1;
                String datenow = day+" / 0"+monthRead+" / "+year;
                tvTglTerpilih.setText(datenow);
            }

            @Override
            public void onDisabledDateSelected(int year, int month, int day, int dayOfWeek, boolean isDisabled) {

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
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Apakah anda yakin akan melakukan peminjaman ruang ini ?");
        alertDialogBuilder.setPositiveButton("Yakin",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
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
