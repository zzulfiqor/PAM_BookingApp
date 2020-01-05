package com.avenger.bookingyuk.View.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.avenger.bookingyuk.Models.ModelMahasiswa;
import com.avenger.bookingyuk.Preferences.Preferences;
import com.avenger.bookingyuk.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditProfileActivity extends AppCompatActivity {

    TextInputEditText etNim, etNama, etAlamat, etPassword;
    Button btnKirim;

    ModelMahasiswa mhs;

    // Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference mhsRef = database.getReference("Mhs");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        componentInit();
        mhs = new ModelMahasiswa();

        etAlamat.setText(Preferences.getLoggedInAlamat(this));
        etNama.setText((Preferences.getLoggedInUser(this)));
        etNim.setText(Preferences.getLoggedInNim(this));
        etNim.setEnabled(false);
        etNim.setFocusable(false);

        etNama.setText(Preferences.getLoggedInUser(this));

        btnKirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pass = etPassword.getText().toString();
                String nim = etNim.getText().toString();
                String nama = etNama.getText().toString();
                if (nim.isEmpty()){
                    Toast.makeText(getBaseContext(), "NIM Harus Diisi",Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    mhs.setNIM(etNim.getText().toString());
                }
                mhs.setNama_mahasiswa(etNama.getText().toString());
                mhs.setAlamat_mahasiswa(etAlamat.getText().toString());
                if (TextUtils.isEmpty(pass)||pass.length()<8) {

                    Toast.makeText(getBaseContext(), "Damn Your Password is to short !!",Toast.LENGTH_SHORT).show();

                    return;
                } else {
                    mhs.setPassword_mahasiswa(pass);
                }

                mhsRef.child(mhs.getNIM()).setValue(mhs);
                Preferences.setLoggedInUser(getBaseContext(),nama);
                Toast.makeText(getBaseContext(),"Data berhasil diubah",Toast.LENGTH_LONG).show();

                Intent i = new Intent(EditProfileActivity.this, Home.class);
                startActivity(i);
                finish();
            }
        });
    }

    void showAlertLogout(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Apakah yakin anda akan membatalkan edit profil ?");
        alertDialogBuilder.setPositiveButton("Yakin",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
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
        etNim = findViewById(R.id.et_nim_edit);
        etNama = findViewById(R.id.et_nama_edit);
        etAlamat = findViewById(R.id.et_alamat_edit);
        etPassword = findViewById(R.id.et_password_edit);
        btnKirim = findViewById(R.id.btn_kirim);
    }

    @Override
    public void onBackPressed() {
        showAlertLogout();
    }

}
