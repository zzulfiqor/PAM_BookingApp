package com.avenger.bookingyuk.Models;

public class ModelRuangan {
    private String nama_ruang;
    private String catatan_ruangan;
    private String foto_ruangan;
    private Boolean is_ac;
    private Boolean is_projector;
    private Integer kapasitas_ruang;
    private String id_ruang;

    public String getId_ruang() {
        return id_ruang;
    }

    public void setId_ruang(String id_ruang) {
        this.id_ruang = id_ruang;
    }

    public ModelRuangan(String nama_ruang, String catatan_ruangan, String foto_ruangan, Boolean is_ac, Boolean is_projector, Integer kapasitas_ruang, String id_ruang) {
        this.nama_ruang = nama_ruang;
        this.catatan_ruangan = catatan_ruangan;
        this.foto_ruangan = foto_ruangan;
        this.is_ac = is_ac;
        this.is_projector = is_projector;
        this.kapasitas_ruang = kapasitas_ruang;
        this.id_ruang = id_ruang;
    }

    public ModelRuangan() {
    }

    public String getNama_ruang() {
        return nama_ruang;
    }

    public void setNama_ruang(String nama_ruang) {
        this.nama_ruang = nama_ruang;
    }

    public String getCatatan_ruangan() {
        return catatan_ruangan;
    }

    public void setCatatan_ruangan(String catatan_ruangan) {
        this.catatan_ruangan = catatan_ruangan;
    }

    public String getFoto_ruangan() {
        return foto_ruangan;
    }

    public void setFoto_ruangan(String foto_ruangan) {
        this.foto_ruangan = foto_ruangan;
    }

    public Boolean getIs_ac() {
        return is_ac;
    }

    public void setIs_ac(Boolean is_ac) {
        this.is_ac = is_ac;
    }

    public Boolean getIs_projector() {
        return is_projector;
    }

    public void setIs_projector(Boolean is_projector) {
        this.is_projector = is_projector;
    }

    public Integer getKapasitas_ruang() {
        return kapasitas_ruang;
    }

    public void setKapasitas_ruang(Integer kapasitas_ruang) {
        this.kapasitas_ruang = kapasitas_ruang;
    }
}
