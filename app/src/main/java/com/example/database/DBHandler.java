package com.example.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {

    static String TABLE_KONTAKTER = "Kontakter";
    static String TABLE_AVTALER = "Avtaler";
    static String KEY_STED = "Sted";
    static String KEY_MELDING = "Melding";
    static String KEY_DATO = "Dato";
    static String KEY_TID = "Tid";
    static String KEY_ID = "_ID";
    static String KEY_AvtaleID = "_ID";
    static String KEY_NAME = "Navn";
    static String KEY_PH_NO = "Telefon";
    static int DATABASE_VERSION = 3;
    static String DATABASE_NAME = "Mappe2";

    public DBHandler(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String LAG_TABELL = "CREATE TABLE " + TABLE_KONTAKTER + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT," + KEY_PH_NO + " TEXT" + ")";
        String LAG_TABELL2 = "CREATE TABLE " + TABLE_AVTALER + "(" + KEY_AvtaleID + " INTEGER PRIMARY KEY," + KEY_STED + " TEXT," + KEY_MELDING + " TEXT," + KEY_DATO + " TEXT, " + KEY_TID + " TEXT " + ")";
        Log.d("SQL", LAG_TABELL);
        Log.d("SQL", LAG_TABELL2);
        db.execSQL(LAG_TABELL);
        db.execSQL(LAG_TABELL2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_KONTAKTER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_AVTALER);
        onCreate(db);
    }

    public void leggTilKontakt(SQLiteDatabase db,Kontakt kontakt) {
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, kontakt.getNavn());
        values.put(KEY_PH_NO, kontakt.getTelefon());
        db.insert(TABLE_KONTAKTER, null, values);
}

    public void leggTilAvtale(SQLiteDatabase db,Avtale avtale) {
        ContentValues values = new ContentValues();
        values.put(KEY_STED, avtale.getSted());
        values.put(KEY_MELDING, avtale.getMelding());
        values.put(KEY_DATO, avtale.getDato());
        values.put(KEY_TID, avtale.getTid());
        db.insert(TABLE_AVTALER, null, values);
    }

    public List<Kontakt> finnAlleKontakter(SQLiteDatabase db) {
        List<Kontakt> kontaktListe = new ArrayList<Kontakt>();
        String selectQuery = "SELECT * FROM " + TABLE_KONTAKTER;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) { do {
            Kontakt kontakt = new Kontakt();
            kontakt.set_ID(cursor.getLong(0));
            kontakt.setNavn(cursor.getString(1));
            kontakt.setTelefon(cursor.getString(2));
            kontaktListe.add(kontakt);
        } while (cursor.moveToNext()); } cursor.close();
        return kontaktListe;
    }

    public List<Avtale> finnAlleAvtaler(SQLiteDatabase db) {
        List<Avtale> avtaleListe = new ArrayList<Avtale>();
        String selectQuery = "SELECT * FROM " + TABLE_AVTALER;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) { do {
            Avtale avtale = new Avtale();
            avtale.set_AvtaleID(cursor.getLong(0));;
            avtale.setSted(cursor.getString(1));
            avtale.setMelding(cursor.getString(2));
            avtale.setDato(cursor.getString(3));
            avtale.setTid(cursor.getString(4));

            avtaleListe.add(avtale);
        } while (cursor.moveToNext()); } cursor.close();
        return avtaleListe;
    }
    public void slettKontakt(SQLiteDatabase db, Long inn_id) {
        db.delete(TABLE_KONTAKTER, KEY_ID + " =? ",
            new String[]{Long.toString(inn_id)});
    }

    public void slettAvtale(SQLiteDatabase db, Long inn_id) {
        db.delete(TABLE_AVTALER, KEY_ID + " =? ",
                new String[]{Long.toString(inn_id)});
    }

    public int oppdaterKontakt(SQLiteDatabase db, Kontakt kontakt) {
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, kontakt.getNavn());
        values.put(KEY_PH_NO, kontakt.getTelefon());
        int endret = db.update(TABLE_KONTAKTER, values, KEY_ID + "= ?", new String[]{String.valueOf(kontakt.get_ID())});
        return endret;
    }


    public int oppdaterAvtale(SQLiteDatabase db, Avtale avtale) {
        ContentValues values = new ContentValues();
        values.put(KEY_STED, avtale.getSted());
        values.put(KEY_MELDING, avtale.getMelding());
        values.put(KEY_DATO, avtale.getDato());
        values.put(KEY_TID, avtale.getTid());
        int endret = db.update(TABLE_AVTALER, values, KEY_ID + "= ?", new String[]{String.valueOf(avtale.get_AvtaleID())});
        return endret;
    }
}
