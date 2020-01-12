package com.avenger.bookingyuk.View.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.avenger.bookingyuk.AESChiper.AESUtils;
import com.avenger.bookingyuk.Models.ModelMahasiswa;
import com.avenger.bookingyuk.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import es.dmoral.toasty.Toasty;

public class RegisterActivity extends AppCompatActivity {

    TextInputEditText etNIM,etNama,etAlamat, etPassword;
    Button btnRegister;
    TextView errorMsg;
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

        hideerrorMsg();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String pass = etPassword.getText().toString();
                final String nim = etNIM.getText().toString();

                mhsRef.child(nim).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (nim.isEmpty()){
                                Toasty.error(getBaseContext(), "NIM tidak boleh kosong",Toast.LENGTH_SHORT,true).show();
                            } else if(dataSnapshot.exists()){
                                showerrorMsg();
                            } else if(TextUtils.isEmpty(pass)|| pass.length()<6){
                                Toasty.error(getBaseContext(), "Password minimal mempunyai 6 karakter",Toast.LENGTH_SHORT,true).show();
                            } else{
                                mhs.setNIM(etNIM.getText().toString());
                                mhs.setNama_mahasiswa(etNama.getText().toString());
                                mhs.setAlamat_mahasiswa(etAlamat.getText().toString());
                                mhs.setPassword_mahasiswa(EncryptPassword(pass));
                                mhsRef.child(mhs.getNIM()).setValue(mhs);
                                Toasty.success(getBaseContext(),"Mahasiswa/i berhasil terdaftar.",Toasty.LENGTH_SHORT,true).show();
                                finish();
                            }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });



            }
        });


    }

    private void showerrorMsg() {
        errorMsg.setVisibility(View.VISIBLE);
    }

    private void hideerrorMsg() {
        etPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                errorMsg.setVisibility(View.GONE);
            }
        });

        etNIM.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                errorMsg.setVisibility(View.GONE);
            }
        });

        etAlamat.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                errorMsg.setVisibility(View.GONE);
            }
        });

        etNama.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                errorMsg.setVisibility(View.GONE);
            }
        });
    }

    void componentInit(){
        etNIM = findViewById(R.id.et_nim_register_);
        etNama = findViewById(R.id.et_nama_register_);
        etAlamat = findViewById(R.id.et_alamat_register_);
        etPassword = findViewById(R.id.et_password_register_);
        btnRegister = findViewById(R.id.btn_register);
        errorMsg = findViewById(R.id.error_messsage_register);
    }

    String EncryptPassword(String sourceStr){
        String encrypted = "";
        try {
            encrypted = AESUtils.encrypt(sourceStr);
            return encrypted;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
