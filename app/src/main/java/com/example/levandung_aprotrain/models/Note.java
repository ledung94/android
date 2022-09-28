package com.example.levandung_aprotrain.models;

import java.util.Date;

public class Note {
    private int id;
    private String noidung;
    private boolean quantrong;
    private Date ngaytao;

    public Note() {
    }

    public Note(String noidung, boolean quantrong, Date ngaytao) {
        this.noidung = noidung;
        this.quantrong = quantrong;
        this.ngaytao = ngaytao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public boolean isQuantrong() {
        return quantrong;
    }

    public void setQuantrong(boolean quantrong) {
        this.quantrong = quantrong;
    }

    public Date getNgaytao() {
        return ngaytao;
    }

    public void setNgaytao(Date ngaytao) {
        this.ngaytao = ngaytao;
    }
}
