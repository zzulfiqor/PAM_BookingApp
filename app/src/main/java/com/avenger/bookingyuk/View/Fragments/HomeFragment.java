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
import android.widget.ImageView;
import android.widget.TextView;

import com.avenger.bookingyuk.Models.ModelRuangan;
import com.avenger.bookingyuk.Models.ModelTes;
import com.avenger.bookingyuk.Preferences.Preferences;
import com.avenger.bookingyuk.R;
import com.avenger.bookingyuk.View.Activity.Description;
import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    TextView namaHome;

    RecyclerView rvTersedia, rvSemnas, rvRapat;
    private FirebaseRecyclerAdapter<ModelRuangan, EntryViewHolder> firebaseRecyclerAdapter;
    private FirebaseRecyclerAdapter<ModelRuangan, EntryViewHolder2> firebaseRecyclerAdapter2;
    private FirebaseRecyclerAdapter<ModelRuangan, EntryViewHolder2> firebaseRecyclerAdapter3;


    private static DatabaseReference mDatabase;
    private Query mQueryCurrent,rapatQuery;

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
        namaHome = view.findViewById(R.id.nama_home);
        namaHome.setText(Preferences.getLoggedInUser(getContext()));

        mQueryCurrent = FirebaseDatabase.getInstance().getReference("Ruang").orderByChild("kapasitas_ruang").startAt(299);
        rapatQuery = FirebaseDatabase.getInstance().getReference("Ruang").orderByChild("kapasitas_ruang").endAt(200);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Ruang");
        mDatabase.keepSynced(true);

//        =======================================================================================================
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);

        LinearLayoutManager layoutManager2
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);

        LinearLayoutManager layoutManager3
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
//        =======================================================================================================


//        =======================================================================================================
        rvTersedia = view.findViewById(R.id.rv_ruang_tersedia);
        rvTersedia.setHasFixedSize(true);
        rvTersedia.setLayoutManager(layoutManager);
//        =======================================================================================================


//        =======================================================================================================
        rvSemnas = view.findViewById(R.id.rv_ruang_lebih_dari_100);
        rvSemnas.setHasFixedSize(true);
        rvSemnas.setLayoutManager(layoutManager2);
//        =======================================================================================================

//        =======================================================================================================
        rvRapat = view.findViewById(R.id.rv_ruang_rapat);
        rvRapat.setHasFixedSize(true);
        rvRapat.setLayoutManager(layoutManager3);
//        =======================================================================================================
    }

    @Override
    public void onStart() {
        super.onStart();

//        ===========================================================================================================
        FirebaseRecyclerOptions<ModelRuangan> options = new FirebaseRecyclerOptions.Builder<ModelRuangan>()
                .setQuery(mDatabase, ModelRuangan.class)
                .build();

        FirebaseRecyclerOptions<ModelRuangan> options2 = new FirebaseRecyclerOptions.Builder<ModelRuangan>()
                .setQuery(mQueryCurrent, ModelRuangan.class)
                .build();

        FirebaseRecyclerOptions<ModelRuangan> options3 = new FirebaseRecyclerOptions.Builder<ModelRuangan>()
                .setQuery(rapatQuery, ModelRuangan.class)
                .build();
//        ===========================================================================================================
//        Ini adapter recycler view Utama

        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<ModelRuangan, EntryViewHolder>(options) {
            @NonNull
            @Override
            public EntryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ruangan_tersedia, parent, false);
                return new EntryViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull EntryViewHolder entryViewHolder, int i, @NonNull ModelRuangan data) {
                entryViewHolder.setTitle(data.getNama_ruang());
                entryViewHolder.setPhoto(data.getFoto_ruangan());

                final String idRuang = data.getId_ruang();
                final String namaRuang = data.getNama_ruang();

                entryViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(getContext(), Description.class);
                        Preferences.setNamaRuangDipilih(getContext(), idRuang);
                        Preferences.setNamaRuangRealDipilih(getContext(), namaRuang);
                        startActivity(i);
                    }
                });
            }
        };
//        =============================================================================================================
//        Ini adapter recycler view semnas

        firebaseRecyclerAdapter2 = new FirebaseRecyclerAdapter<ModelRuangan, EntryViewHolder2>(options2) {
            @NonNull
            @Override
            public EntryViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ruangan_semnas, parent, false);
                return new EntryViewHolder2(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull EntryViewHolder2 entryViewHolder, int i, @NonNull ModelRuangan data) {
                entryViewHolder.setNama(data.getNama_ruang());
                entryViewHolder.setKapasitas(data.getKapasitas_ruang()+" Kapasitas");
                entryViewHolder.setPhoto(data.getFoto_ruangan());
                final String idRuang = data.getId_ruang();
                final String namaRuang = data.getNama_ruang();

                entryViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(getContext(), Description.class);
                        Preferences.setNamaRuangDipilih(getContext(), idRuang);
                        Preferences.setNamaRuangRealDipilih(getContext(), namaRuang);
                        startActivity(i);
                    }
                });
            }
        };
//        ===============================================================================================================
//        Ini adapter recycler view rapat

        firebaseRecyclerAdapter3 = new FirebaseRecyclerAdapter<ModelRuangan, EntryViewHolder2>(options3) {
            @NonNull
            @Override
            public EntryViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ruangan_semnas, parent, false);
                return new EntryViewHolder2(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull EntryViewHolder2 entryViewHolder, int i, @NonNull ModelRuangan data) {
                entryViewHolder.setNama(data.getNama_ruang());
                entryViewHolder.setKapasitas(data.getKapasitas_ruang()+" Kapasitas");
                entryViewHolder.setPhoto(data.getFoto_ruangan());
                final String idRuang = data.getId_ruang();
                final String namaRuang = data.getNama_ruang();

                entryViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(getContext(), Description.class);
                        Preferences.setNamaRuangDipilih(getContext(), idRuang);
                        Preferences.setNamaRuangRealDipilih(getContext(), namaRuang);
                        startActivity(i);
                    }
                });
            }
        };

//        ===============================================================================================================

        rvTersedia.setAdapter(firebaseRecyclerAdapter);
        firebaseRecyclerAdapter.startListening();

        rvSemnas.setAdapter(firebaseRecyclerAdapter2);
        firebaseRecyclerAdapter2.startListening();

        rvRapat.setAdapter(firebaseRecyclerAdapter3);
        firebaseRecyclerAdapter3.startListening();
    }

    @Override
    public void onPause() {
        super.onPause();
        firebaseRecyclerAdapter3.stopListening();
        firebaseRecyclerAdapter2.stopListening();
        firebaseRecyclerAdapter.stopListening();
    }

    public static class EntryViewHolder extends RecyclerView.ViewHolder {
        View mView;
        TextView tes;
        ImageView ivRuangan;

        public EntryViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }
        public void setTitle(String title){
            tes = (TextView) mView.findViewById(R.id.nama_ruang_tersedia);
            tes.setText(title);
        }

        public void setPhoto(String url){
            ivRuangan = mView.findViewById(R.id.imageView4);
            Glide.with(mView.getContext())
                    .load(url)
                    .centerCrop()
                    .into(ivRuangan);
        }


    }

    public static class EntryViewHolder2 extends RecyclerView.ViewHolder {
        View mView;
        TextView kapasitas, nama;
        ImageView ivRuangan;

        public EntryViewHolder2(View itemView) {
            super(itemView);
            mView = itemView;
        }
        public void setNama(String title){
            nama = (TextView) mView.findViewById(R.id.nama_ruang_semnas);
            nama.setText(title);
        }

        public void setKapasitas(String title){
            kapasitas = (TextView) mView.findViewById(R.id.kapsitas_ruang_semnas);
            kapasitas.setText(title);
        }

        public void setPhoto(String url){
            ivRuangan = mView.findViewById(R.id.image_ruang_semnas);
            Glide.with(mView.getContext())
                    .load(url)
                    .centerCrop()
                    .into(ivRuangan);
        }


    }

}
