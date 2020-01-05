package com.avenger.bookingyuk.View.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.avenger.bookingyuk.Preferences.Preferences;
import com.avenger.bookingyuk.R;
import com.vivekkaushik.datepicker.DatePickerTimeline;
import com.vivekkaushik.datepicker.OnDateSelectedListener;

public class FormBookingActivity extends AppCompatActivity {

    TextView tvNama, tvNim, tvRuang;
    EditText etOrganisasi, etAcara;
    DatePickerTimeline datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_booking);
        componentInit();

        tvRuang.setText(Preferences.getNamaRuangDipilih(getBaseContext()));
        tvNama.setText(Preferences.getLoggedInUser(getBaseContext()));
        tvNim.setText(Preferences.getLoggedInNim(getBaseContext()));

        // Set a Start date (Default, 1 Jan 1970)
        datePicker.setInitialDate(2020, 0, 6);
        datePicker.setOnDateSelectedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(int year, int month, int day, int dayOfWeek) {
                int monthRead = month+1;


            }

            @Override
            public void onDisabledDateSelected(int year, int month, int day, int dayOfWeek, boolean isDisabled) {

            }
        });



    }


    void componentInit(){
        etOrganisasi = findViewById(R.id.et_organisasi_form);
        etAcara = findViewById(R.id.et_acara_organisasi);
        datePicker = findViewById(R.id.datePickerTimeline);
        tvNama = findViewById(R.id.nama_form);
        tvNim = findViewById(R.id.nim_form);
        tvRuang = findViewById(R.id.nama_ruang_form);
    }
}
