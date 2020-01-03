package com.avenger.bookingyuk.Models;

public class ModelMahasiswa {
    private String NIM;

    public String getNIM() {
        return NIM;
    }

    public void setNIM(String NIM) {
        this.NIM = NIM;
    }

    public String getPassword_mahasiswa() {
        return password_mahasiswa;
    }

    public void setPassword_mahasiswa(String password_mahasiswa) {
        this.password_mahasiswa = password_mahasiswa;
    }

    public String getNama_mahasiswa() {
        return nama_mahasiswa;
    }

    public void setNama_mahasiswa(String nama_mahasiswa) {
        this.nama_mahasiswa = nama_mahasiswa;
    }

    public String getAlamat_mahasiswa() {
        return alamat_mahasiswa;
    }

    public void setAlamat_mahasiswa(String alamat_mahasiswa) {
        this.alamat_mahasiswa = alamat_mahasiswa;
    }

    private String password_mahasiswa;
    private String nama_mahasiswa;
    private String alamat_mahasiswa;
}
