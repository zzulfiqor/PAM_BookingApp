package com.avenger.bookingyuk.View.Fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.avenger.bookingyuk.Adapter.RuanganAdapter;
import com.avenger.bookingyuk.Models.ModelRuangan;
import com.avenger.bookingyuk.R;
import com.avenger.bookingyuk.View.Activity.ListRuangan;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentListRuangan extends Fragment {

    private RecyclerView recyclerView;
    private RuanganAdapter ruanganAdapter;
    private FirebaseRecyclerAdapter<ModelRuangan, EntryViewHolder> firebaseRecyclerAdapter;
    private static DatabaseReference mDatabase;

    public FragmentListRuangan() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_list_ruangan, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Ruang");
        mDatabase.keepSynced(true);

        recyclerView = view.findViewById(R.id.rv_fragment_ruangan);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<ModelRuangan> options = new FirebaseRecyclerOptions.Builder<ModelRuangan>()
                .setQuery(mDatabase, ModelRuangan.class)
                .build();

        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<ModelRuangan, EntryViewHolder>(options) {
            @NonNull
            @Override
            public EntryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ruangan, parent, false);
                return new EntryViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull EntryViewHolder entryViewHolder, int i, @NonNull ModelRuangan data) {
                entryViewHolder.setTitle(data.getNama_ruang());
                entryViewHolder.setKapasitas(data.getKapasitas_ruang());
            }
        };

        recyclerView.setAdapter(firebaseRecyclerAdapter);
        firebaseRecyclerAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        firebaseRecyclerAdapter.stopListening();
    }

    public static class EntryViewHolder extends RecyclerView.ViewHolder {
        View mView;
        TextView tes;
        TextView kapasitas_ruang;

        public EntryViewHolder(View itemView) {
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
}
