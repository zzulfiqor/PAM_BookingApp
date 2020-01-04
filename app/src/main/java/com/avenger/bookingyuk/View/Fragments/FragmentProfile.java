package com.avenger.bookingyuk.View.Fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.avenger.bookingyuk.Preferences.Preferences;
import com.avenger.bookingyuk.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentProfile extends Fragment {

    TextView nimProfil, namaProfil;

    public FragmentProfile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        componentInit(view);

        namaProfil.setText(Preferences.getLoggedInUser(getContext()));

    }

    void componentInit(View v){
        nimProfil = v.findViewById(R.id.nim_profil);
        namaProfil = v.findViewById(R.id.nama_profil);
    }

}
