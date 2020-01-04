package com.avenger.bookingyuk.Models;

import android.util.Log;

import com.avenger.bookingyuk.R;

import java.util.ArrayList;

public class RuanganData {
    private static int[] ruangangambar = {
        R.drawable.cinema,
        R.drawable.cinema
    };

    private static String[] namaruangan = {
            "Ruang Cinema",
            "Ruang Cinema Gedung 6"
    };

    public static ArrayList<ModelRuangan> getData(){
        ArrayList<ModelRuangan> list = new ArrayList<>();
        for (int i = 0; i < ruangangambar.length; i++) {
            ModelRuangan ruangan = new ModelRuangan();
            ruangan.setPhoto(ruangangambar[i]);
            ruangan.setNamaRuangan(namaruangan[i]);
            list.add(ruangan);
        }
        Log.d("isi List:", list.toString());
        return list;

    }

}
