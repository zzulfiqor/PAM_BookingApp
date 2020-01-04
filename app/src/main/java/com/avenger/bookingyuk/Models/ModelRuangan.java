package com.avenger.bookingyuk.Models;

public class ModelRuangan {
    private int photo;
    private String namaRuangan;
    private String waktuBooking;
    private String event;

    public String getNamaRuangan() {
        return namaRuangan;
    }

    public void setNamaRuangan(String namaRuangan) {
        this.namaRuangan = namaRuangan;
    }

    public String getWaktuBooking() {
        return waktuBooking;
    }

    public void setWaktuBooking(String waktuBooking) {
        this.waktuBooking = waktuBooking;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }
}
