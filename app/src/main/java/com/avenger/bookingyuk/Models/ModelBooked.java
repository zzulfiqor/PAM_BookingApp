package com.avenger.bookingyuk.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class ModelBooked implements Parcelable {



    public String getId_book() {
        return id_book;
    }

    public void setId_book(String id_book) {
        this.id_book = id_book;
    }

    public String getId_ruang() {
        return id_ruang;
    }

    public void setId_ruang(String id_ruang) {
        this.id_ruang = id_ruang;
    }

    public String getNIM() {
        return NIM;
    }

    public void setNIM(String NIM) {
        this.NIM = NIM;
    }

    public String getAcara_booked() {
        return acara_booked;
    }

    public void setAcara_booked(String acara_booked) {
        this.acara_booked = acara_booked;
    }

    public String getOrganisasi_booked() {
        return organisasi_booked;
    }

    public void setOrganisasi_booked(String organisasi_booked) {
        this.organisasi_booked = organisasi_booked;
    }

    public int getBulan_booked() {
        return bulan_booked;
    }

    public void setBulan_booked(int bulan_booked) {
        this.bulan_booked = bulan_booked;
    }

    public int getTgl_booked() {
        return tgl_booked;
    }

    public void setTgl_booked(int tgl_booked) {
        this.tgl_booked = tgl_booked;
    }

    public int getThn_booked() {
        return thn_booked;
    }

    public void setThn_booked(int thn_booked) {
        this.thn_booked = thn_booked;
    }

    public String getDate_booked() {
        return date_booked;
    }

    public void setDate_booked(String date_booked) {
        this.date_booked = date_booked;
    }

    public ModelBooked() {
    }

    public ModelBooked(int bulan_booked, int tgl_booked, int thn_booked, String id_book, String id_ruang, String NIM, String acara_booked, String organisasi_booked,String date_booked,String nama_ruang) {
        this.bulan_booked = bulan_booked;
        this.tgl_booked = tgl_booked;
        this.thn_booked = thn_booked;
        this.id_book = id_book;
        this.id_ruang = id_ruang;
        this.NIM = NIM;
        this.acara_booked = acara_booked;
        this.organisasi_booked = organisasi_booked;
        this.date_booked = date_booked;
        this.nama_ruang = nama_ruang;
    }

    int bulan_booked;
    int tgl_booked;
    int thn_booked;
    String id_book;
    String id_ruang;
    String NIM;
    String acara_booked;
    String organisasi_booked;
    String date_booked;
    String nama_ruang;

    public String getNama_ruang() {
        return nama_ruang;
    }

    public void setNama_ruang(String nama_ruang) {
        this.nama_ruang = nama_ruang;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.bulan_booked);
        dest.writeInt(this.tgl_booked);
        dest.writeInt(this.thn_booked);
        dest.writeString(this.id_book);
        dest.writeString(this.id_ruang);
        dest.writeString(this.NIM);
        dest.writeString(this.acara_booked);
        dest.writeString(this.organisasi_booked);
        dest.writeString(this.date_booked);
        dest.writeString(this.nama_ruang);
    }

    protected ModelBooked(Parcel in) {
        this.bulan_booked = in.readInt();
        this.tgl_booked = in.readInt();
        this.thn_booked = in.readInt();
        this.id_book = in.readString();
        this.id_ruang = in.readString();
        this.NIM = in.readString();
        this.acara_booked = in.readString();
        this.organisasi_booked = in.readString();
        this.date_booked = in.readString();
        this.nama_ruang = in.readString();
    }

    public static final Parcelable.Creator<ModelBooked> CREATOR = new Parcelable.Creator<ModelBooked>() {
        @Override
        public ModelBooked createFromParcel(Parcel source) {
            return new ModelBooked(source);
        }

        @Override
        public ModelBooked[] newArray(int size) {
            return new ModelBooked[size];
        }
    };
}
