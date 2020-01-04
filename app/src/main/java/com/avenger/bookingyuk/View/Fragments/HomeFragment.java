package com.avenger.bookingyuk.View.Fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.avenger.bookingyuk.Models.ModelRuangan;
import com.avenger.bookingyuk.Models.ModelTes;
import com.avenger.bookingyuk.Preferences.Preferences;
import com.avenger.bookingyuk.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    RecyclerView rvTersedia;
    private FirebaseRecyclerAdapter<ModelRuangan, EntryViewHolder> firebaseRecyclerAdapter;
    private static DatabaseReference mDatabase;
    private Query mQueryCurrent;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Ruang");
        mDatabase.keepSynced(true);

        rvTersedia = view.findViewById(R.id.rv_ruang_tersedia);
        rvTersedia.setHasFixedSize(true);
        rvTersedia.setLayoutManager(new LinearLayoutManager(getContext()));
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
            }
        };

        rvTersedia.setAdapter(firebaseRecyclerAdapter);
        firebaseRecyclerAdapter.startListening();
    }

    @Override
    public void onPause() {
        super.onPause();
        firebaseRecyclerAdapter.stopListening();
    }

    public static class EntryViewHolder extends RecyclerView.ViewHolder {
        View mView;
        TextView tes;
        public EntryViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }
        public void setTitle(String title){
            tes = (TextView) mView.findViewById(R.id.nama_ruangan);
            tes.setText(title);
        }


    }

}
