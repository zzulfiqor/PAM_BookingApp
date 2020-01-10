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

import com.avenger.bookingyuk.AESChiper.AESUtils;
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
    TextView tvRegister, errorMsg;
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

        etPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                errorMsg.setVisibility(View.GONE);
            }
        });

        etEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                errorMsg.setVisibility(View.GONE);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String nim_mhs = etEmail.getText().toString();
                final String pass_mhs = etPassword.getText().toString();

                if (nim_mhs == null || pass_mhs == null){
                    showerrorMsg();
                }else{
                        mhsRef.child(nim_mhs).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()){
                                    ModelMahasiswa mhs_ = dataSnapshot.getValue(ModelMahasiswa.class);


                                    if (pass_mhs.equals(decryptPassword(mhs_.getPassword_mahasiswa()))){

                                        Preferences.setLoggedInUser(getBaseContext(),mhs_.getNama_mahasiswa());
                                        Preferences.setLoggedInNim(getBaseContext(),mhs_.getNIM());
                                        Preferences.setLoggedInAlamat(getBaseContext(),mhs_.getAlamat_mahasiswa());
                                        View sharedView = logoAmikom;
                                        ActivityOptions transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this, sharedView, transitionName);

                                        Intent intent = new Intent(LoginActivity.this, LoginBerhasilActivity.class);
                                        startActivity(intent, transitionActivityOptions.toBundle());
                                    }else{
                                        showerrorMsg();
                                    }
                                }else{
                                    showerrorMsg();
                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                            }
                        });

                }
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

    private void showerrorMsg() {
        errorMsg.setVisibility(View.VISIBLE);
    }

    void componentInit(){
        btnLogin = findViewById(R.id.btn_login);
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        logoAmikom = findViewById(R.id.logoAmikom);
        tvRegister = findViewById(R.id.tv_register);
        errorMsg = findViewById(R.id.error_messsage);
    }

    String decryptPassword(String encrypted){
        String decrypted = "";
        try {
            decrypted = AESUtils.decrypt(encrypted);
            return decrypted;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
