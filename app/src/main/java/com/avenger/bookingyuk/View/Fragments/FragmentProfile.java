package com.avenger.bookingyuk.View.Fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.avenger.bookingyuk.Adapter.RuanganAdapter;
import com.avenger.bookingyuk.Models.ModelRuangan;
import com.avenger.bookingyuk.Preferences.Preferences;
import com.avenger.bookingyuk.R;
import com.avenger.bookingyuk.View.Activity.EditProfileActivity;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentProfile extends Fragment {

    TextView nimProfil, namaProfil;
    private RecyclerView rvHistory;
    private FirebaseRecyclerAdapter<ModelRuangan, EntryViewHolderHistory> firebaseRecyclerAdapter;
    private static DatabaseReference mDatabase;
    private static Query query;
    CircleImageView btnEdit;


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

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), EditProfileActivity.class));
            }
        });

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Ruang");
        mDatabase.keepSynced(true);

//        query = FirebaseDatabase.getInstance().getReference("Ruang").orderByChild("is_ac").equalTo(true);
        rvHistory = view.findViewById(R.id.rv_history);
        rvHistory.setHasFixedSize(true);
        rvHistory.setLayoutManager(new LinearLayoutManager(getContext()));

        namaProfil.setText(Preferences.getLoggedInUser(getContext()));
        nimProfil.setText(Preferences.getLoggedInNim(getContext()));
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<ModelRuangan> options = new FirebaseRecyclerOptions.Builder<ModelRuangan>()
                .setQuery(mDatabase, ModelRuangan.class)
                .build();

        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<ModelRuangan, EntryViewHolderHistory>(options) {
            @NonNull
            @Override
            public EntryViewHolderHistory onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ruangan, parent, false);
                return new EntryViewHolderHistory(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull EntryViewHolderHistory entryViewHolder, int i, @NonNull ModelRuangan data) {
                entryViewHolder.setTitle(data.getNama_ruang());
                entryViewHolder.setKapasitas("Kapasitas: "+data.getKapasitas_ruang());
            }
        };

        rvHistory.setAdapter(firebaseRecyclerAdapter);
        firebaseRecyclerAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        firebaseRecyclerAdapter.stopListening();
    }

    public static class EntryViewHolderHistory extends RecyclerView.ViewHolder {
        View mView;
        TextView tes;
        TextView kapasitas_ruang;

        public EntryViewHolderHistory(View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setTitle(String title){
            tes = mView.findViewById(R.id.nama_ruangan);
            tes.setText(title);
        }

        public void setKapasitas(String kapasitas){
            kapasitas_ruang = mView.findViewById(R.id.kapsitas_ruangan);
            kapasitas_ruang.setText(kapasitas);
        }

    }

    void componentInit(View v){
        nimProfil = v.findViewById(R.id.nim_profil);
        namaProfil = v.findViewById(R.id.nama_profil);
        btnEdit = v.findViewById(R.id.btn_edit);
    }

}
