package com.avenger.bookingyuk.View.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.avenger.bookingyuk.R;

public class FormBookingActivity extends AppCompatActivity {

    EditText etOrganisasi, etAcara;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_booking);
    }
}
