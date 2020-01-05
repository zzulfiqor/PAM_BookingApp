package com.avenger.bookingyuk.Models;

public class ModelBooked {


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

    public ModelBooked() {
    }

    public ModelBooked(int bulan_booked, int tgl_booked, int thn_booked, String id_book, String id_ruang, String NIM, String acara_booked, String organisasi_booked) {
        this.bulan_booked = bulan_booked;
        this.tgl_booked = tgl_booked;
        this.thn_booked = thn_booked;
        this.id_book = id_book;
        this.id_ruang = id_ruang;
        this.NIM = NIM;
        this.acara_booked = acara_booked;
        this.organisasi_booked = organisasi_booked;
    }

    int bulan_booked;
    int tgl_booked;
    int thn_booked;
    String id_book;
    String id_ruang;
    String NIM;
    String acara_booked;
    String organisasi_booked;

}
