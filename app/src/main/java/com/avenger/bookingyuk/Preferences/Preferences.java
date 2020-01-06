package com.avenger.bookingyuk.Preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Preferences {
    /** Pendeklarasian key-data berupa String, untuk sebagai wadah penyimpanan data.
     * Jadi setiap data mempunyai key yang berbeda satu sama lain */
    static final String KEY_USERNAME_SEDANG_LOGIN = "Username_logged_in";
    static final String KEY_NIM_SEDANG_LOGIN = "Nim_logged_in";
    static final String KEY_STATUS_SEDANG_LOGIN = "Status_logged_in";
    static final String KEY_ALAMAT_SEDANG_LOGIN = "Alamat_logged_in";
    static final String KEY_NAMA_RUANG_DIPILIH = "Nama_ruang_dipilih";
    static final String KEY_NAMA_REAL_RUANGAN_DIPILIH = "Nama_real_ruangan_dipilih";
    static final String STATUS = "status_state";


    /** Pendlakarasian Shared Preferences yang berdasarkan paramater context */
    private static SharedPreferences getSharedPreference(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }



    /** Deklarasi Edit Preferences dan mengubah data */
    public static void setStatus(Context context, String username){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(STATUS, username);
        editor.apply();
    }

    /** Mengembalikan nilai dari key KEY_NAMA_RUANG_DIPILIH berupa String */
    public static String getStatus(Context context){
        return getSharedPreference(context).getString(STATUS,"");
    }




    /** Deklarasi Edit Preferences dan mengubah data */
    public static void setNamaRuangRealDipilih(Context context, String username){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(KEY_NAMA_REAL_RUANGAN_DIPILIH, username);
        editor.apply();
    }

    /** Mengembalikan nilai dari key KEY_NAMA_RUANG_DIPILIH berupa String */
    public static String getNamaRuangRealDipilih(Context context){
        return getSharedPreference(context).getString(KEY_NAMA_REAL_RUANGAN_DIPILIH,"");
    }


    /** Deklarasi Edit Preferences dan mengubah data */
    public static void setNamaRuangDipilih(Context context, String username){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(KEY_NAMA_RUANG_DIPILIH, username);
        editor.apply();
    }

    /** Mengembalikan nilai dari key KEY_NAMA_RUANG_DIPILIH berupa String */
    public static String getNamaRuangDipilih(Context context){
        return getSharedPreference(context).getString(KEY_NAMA_RUANG_DIPILIH,"");
    }


    /** Deklarasi Edit Preferences dan mengubah data
     *  yang memiliki key KEY_ALAMAT_SEDANG_LOGIN dengan parameter username */
    public static void setLoggedInAlamat(Context context, String username){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(KEY_ALAMAT_SEDANG_LOGIN, username);
        editor.apply();
    }

    /** Mengembalikan nilai dari key KEY_USERNAME_SEDANG_LOGIN berupa String */
    public static String getLoggedInAlamat(Context context){
        return getSharedPreference(context).getString(KEY_ALAMAT_SEDANG_LOGIN,"");
    }


    /** Deklarasi Edit Preferences dan mengubah data
     *  yang memiliki key KEY_USERNAME_SEDANG_LOGIN dengan parameter username */
    public static void setLoggedInUser(Context context, String username){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(KEY_USERNAME_SEDANG_LOGIN, username);
        editor.apply();
    }
    /** Mengembalikan nilai dari key KEY_USERNAME_SEDANG_LOGIN berupa String */
    public static String getLoggedInUser(Context context){
        return getSharedPreference(context).getString(KEY_USERNAME_SEDANG_LOGIN,"");
    }

    /** Deklarasi Edit Preferences dan mengubah data
     *  yang memiliki key KEY_NIM_SEDANG_LOGIN dengan parameter username */
    public static void setLoggedInNim(Context context, String nim){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(KEY_NIM_SEDANG_LOGIN, nim);
        editor.apply();
    }
    /** Mengembalikan nilai dari key KEY_NIM_SEDANG_LOGIN berupa String */
    public static String getLoggedInNim(Context context){
        return getSharedPreference(context).getString(KEY_NIM_SEDANG_LOGIN,"");
    }


    /** Deklarasi Edit Preferences dan menghapus data, sehingga menjadikannya bernilai default
     *  khusus data yang memiliki key KEY_USERNAME_SEDANG_LOGIN dan KEY_NIM_SEDANG_LOGIN */
    public static void clearLoggedInUser (Context context){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.remove(KEY_USERNAME_SEDANG_LOGIN);
        editor.remove(KEY_STATUS_SEDANG_LOGIN);
        editor.remove(KEY_NIM_SEDANG_LOGIN);
        editor.remove(KEY_ALAMAT_SEDANG_LOGIN);
        editor.apply();
    }

    public static void clearRuangTerpilih (Context context){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.remove(KEY_NAMA_RUANG_DIPILIH);
        editor.remove(KEY_NAMA_REAL_RUANGAN_DIPILIH);
        editor.apply();
    }
}
