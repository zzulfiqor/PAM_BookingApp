package com.avenger.bookingyuk.View.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.avenger.bookingyuk.Models.ModelMahasiswa;
import com.avenger.bookingyuk.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    TextInputEditText etNIM,etNama,etAlamat, etPassword;
    Button btnRegister;

    ModelMahasiswa mhs;

    // Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference mhsRef = database.getReference("Mhs");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        componentInit();
        mhs = new ModelMahasiswa();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mhs.setNIM(etNIM.getText().toString());
                mhs.setNama_mahasiswa(etNama.getText().toString());
                mhs.setAlamat_mahasiswa(etAlamat.getText().toString());
                mhs.setPassword_mahasiswa(etPassword.getText().toString());
                mhsRef.child(mhs.getNIM()).setValue(mhs);

                Toast.makeText(getBaseContext(),"Mahasiswa : "+mhs.getNama_mahasiswa()+" Berhasil terdaftar",Toast.LENGTH_LONG).show();

                Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });


    }

    void componentInit(){
        etNIM = findViewById(R.id.et_nim_register_);
        etNama = findViewById(R.id.et_nama_register_);
        etAlamat = findViewById(R.id.et_alamat_register_);
        etPassword = findViewById(R.id.et_password_register_);
        btnRegister = findViewById(R.id.btn_register);
    }
}
