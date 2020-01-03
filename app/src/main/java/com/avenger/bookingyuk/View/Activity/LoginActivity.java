package com.avenger.bookingyuk.View.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.avenger.bookingyuk.Models.ModelMahasiswa;
import com.avenger.bookingyuk.Preferences.Preferences;
import com.avenger.bookingyuk.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin;
    EditText etEmail, etPassword;
    TextView tvRegister;
    ModelMahasiswa mhs;
    ImageView logoAmikom;
    String transitionName = "logo_amikom";

    // Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference mhsRef = database.getInstance().getReference().child("Mhs");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        componentInit();

        mhs = new ModelMahasiswa();


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String nim_mhs = etEmail.getText().toString();
                final String pass_mhs = etPassword.getText().toString();

                mhsRef.child(nim_mhs).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        ModelMahasiswa mhs_ = dataSnapshot.getValue(ModelMahasiswa.class);

                        if (pass_mhs.equals(mhs_.getPassword_mahasiswa())){
                            Toast.makeText(getBaseContext(),"Login Berhasil, Selamat datang :"+mhs_.getNama_mahasiswa(),Toast.LENGTH_LONG).show();
                            Preferences.setLoggedInUser(getBaseContext(),mhs_.getNIM());

                            Intent intent = new Intent(LoginActivity.this, Home.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(getBaseContext(),"Login Gagal",Toast.LENGTH_LONG).show();

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.d("error_firebase_zhr",databaseError.getMessage());
                    }
                });
            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View sharedView = logoAmikom;
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                ActivityOptions transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this, sharedView, transitionName);
                startActivity(i, transitionActivityOptions.toBundle());
            }
        });
    }

    void componentInit(){
        btnLogin = findViewById(R.id.btn_login);
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        logoAmikom = findViewById(R.id.logoAmikom);
        tvRegister = findViewById(R.id.tv_register);
    }


}
