package com.avenger.bookingyuk.View.Fragments;


import android.content.Intent;
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
import android.widget.Button;
import android.widget.TextView;

import com.avenger.bookingyuk.Adapter.RuanganAdapter;
import com.avenger.bookingyuk.Models.ModelBooked;
import com.avenger.bookingyuk.Models.ModelMahasiswa;
import com.avenger.bookingyuk.Models.ModelRuangan;
import com.avenger.bookingyuk.Preferences.Preferences;
import com.avenger.bookingyuk.R;
import com.avenger.bookingyuk.TicketDetail;
import com.avenger.bookingyuk.View.Activity.EditProfileActivity;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentProfile extends Fragment {

    TextView nimProfil, namaProfil;
    private RecyclerView rvHistory;
    private FirebaseRecyclerAdapter<ModelBooked, EntryViewHolderHistory> firebaseRecyclerAdapter;
    private static DatabaseReference mDatabase;
    private static Query query,q2;
    CircleImageView btnEdit;
    String ruangNama;


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

        mDatabase = FirebaseDatabase.getInstance().getReference().child("RuanganBooked");
        mDatabase.keepSynced(true);

        query = FirebaseDatabase.getInstance().getReference("RuanganBooked").orderByChild("nim").equalTo(Preferences.getLoggedInNim(getContext()));

        rvHistory = view.findViewById(R.id.rv_history);
        rvHistory.setHasFixedSize(true);
        rvHistory.setLayoutManager(new LinearLayoutManager(getContext()));

        namaProfil.setText(Preferences.getLoggedInUser(getContext()));
        nimProfil.setText(Preferences.getLoggedInNim(getContext()));


    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<ModelBooked> options = new FirebaseRecyclerOptions.Builder<ModelBooked>()
                .setQuery(query, ModelBooked.class)
                .build();

        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<ModelBooked, EntryViewHolderHistory>(options) {
            @NonNull
            @Override
            public EntryViewHolderHistory onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ticket_booking, parent, false);
                return new EntryViewHolderHistory(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull final EntryViewHolderHistory entryViewHolder, int i, @NonNull final ModelBooked data) {

                mDatabase.child(data.getId_book()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        final ModelRuangan ruang_ = dataSnapshot.getValue(ModelRuangan.class);

                        q2 = FirebaseDatabase.getInstance().getReference("Ruang").orderByChild("id_ruang").equalTo(ruang_.getId_ruang());
                        q2.addChildEventListener(new ChildEventListener() {
                            @Override
                            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                                ModelRuangan ruang__ = dataSnapshot.getValue(ModelRuangan.class);
                                if (ruang_.getId_ruang().equals(ruang__.getId_ruang())){
                                    entryViewHolder.setNamaRuang(ruang__.getNama_ruang());
                                }else{

                                }
                            }

                            @Override
                            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                            }

                            @Override
                            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                            }

                            @Override
                            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                entryViewHolder.setTanggalTiket("Tanggal: "+data.getTgl_booked()+"-"+data.getBulan_booked()+"-"+data.getThn_booked());
                entryViewHolder.setOrganisasiTiket(data.getOrganisasi_booked());
                entryViewHolder.setAcaraTiket(data.getAcara_booked());


                entryViewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getContext(), TicketDetail.class));
                    }
                });
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
        TextView nama_ruang_tiket, tanggal_tiket, organisasi_tiket, acara_tiket;

        public EntryViewHolderHistory(View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setNamaRuang (String nama){
            nama_ruang_tiket = mView.findViewById(R.id.ruangan_ticket);
            nama_ruang_tiket.setText(nama);
        }

        public void setTanggalTiket(String tanggalTiket){
            tanggal_tiket = mView.findViewById(R.id.tgl_ticket);
            tanggal_tiket.setText(tanggalTiket);
        }

        public void setOrganisasiTiket(String organisasiTiket){
            organisasi_tiket = mView.findViewById(R.id.organisasi_ticket);
            organisasi_tiket.setText(organisasiTiket);
        }

        public void setAcaraTiket(String acaraTiket){
            acara_tiket = mView.findViewById(R.id.acara_tiket);
            acara_tiket.setText(acaraTiket);
        }

    }

    void componentInit(View v){
        nimProfil = v.findViewById(R.id.nim_profil);
        namaProfil = v.findViewById(R.id.nama_profil);
        btnEdit = v.findViewById(R.id.btn_edit);
    }

}
