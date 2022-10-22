package com.example.database;

import java.sql.Time;
import java.util.Date;

public class Avtale {
    long _AvtaleID;
    String sted;
    String dato;
    String melding;
    String tid;

    public Avtale(String sted, String melding, String dato, String tid) {
        this.melding = melding;
        this.dato = dato;
        this.sted = sted;
        this.tid = tid;
    }

    public Avtale(long _AvtaleID, String melding, String dato, String sted, String tid) {
        this._AvtaleID = _AvtaleID;
        this.melding = melding;
        this.dato = dato;
        this.sted = sted;
        this.tid = tid;
    }

    public Avtale() {
    }

    public long get_AvtaleID() {
        return _AvtaleID;
    }

    public String getMelding() {
        return melding;
    }

    public String getSted() {
        return sted;
    }

    public String getDato() {
        return dato;
    }

    public String getTid() {
        return tid;
    }

    public void set_AvtaleID(long _ID) {
        this._AvtaleID = _AvtaleID;
    }

    public void setSted(String sted) {
        this.sted = sted;
    }

    public void setMelding(String melding) {
        this.melding = melding;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }
}
