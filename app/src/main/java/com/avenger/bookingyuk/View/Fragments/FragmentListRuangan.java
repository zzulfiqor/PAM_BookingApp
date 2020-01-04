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

import com.avenger.bookingyuk.Adapter.RuanganAdapter;
import com.avenger.bookingyuk.Models.ModelRuangan;
import com.avenger.bookingyuk.Models.RuanganData;
import com.avenger.bookingyuk.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentListRuangan extends Fragment {

    private RecyclerView recyclerView;
    private RuanganAdapter ruanganAdapter;
    private ArrayList<ModelRuangan> list = new ArrayList<>();
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
        recyclerView = view.findViewById(R.id.rv_fragment_ruangan);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        list.addAll(RuanganData.getData());
        ruanganAdapter = new RuanganAdapter(list);
        recyclerView.setAdapter(ruanganAdapter);
        Log.d("isi ruangan frag: ", list.toString());

    }
}
