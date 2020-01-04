package com.avenger.bookingyuk.View.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.avenger.bookingyuk.Adapter.RuanganAdapter;
import com.avenger.bookingyuk.Models.ModelRuangan;
import com.avenger.bookingyuk.Models.RuanganData;
import com.avenger.bookingyuk.R;

import java.util.ArrayList;

public class ListRuangan extends AppCompatActivity {

    RecyclerView rvruangan;
    private ArrayList<ModelRuangan> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_ruangan);
        rvruangan = findViewById(R.id.rv_ruangan);
        rvruangan.setHasFixedSize(true);

        list.addAll(RuanganData.getData());
        show();
    }

    private void show(){
        rvruangan.setLayoutManager(new LinearLayoutManager(this));
        RuanganAdapter ruanganAdapter = new RuanganAdapter(list);
        rvruangan.setAdapter(ruanganAdapter);
    }
}
