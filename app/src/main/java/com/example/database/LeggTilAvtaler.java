package com.example.database;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class LeggTilAvtaler extends AppCompatActivity {
    EditText datoinn;
    EditText stedinn;
    EditText meldinginn;
    EditText tidinn;
    EditText idinn;
    TextView utskrift;
    DBHandler dbHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avtaler);
        datoinn = (EditText) findViewById(R.id.dato);
        stedinn = (EditText) findViewById(R.id.sted);
        meldinginn = (EditText) findViewById(R.id.melding);
        tidinn = (EditText) findViewById(R.id.tid);
        idinn = (EditText) findViewById(R.id.min_id);
        utskrift = (TextView) findViewById(R.id.utskrift);
        dbHelper = new DBHandler(this);
        db=dbHelper.getWritableDatabase();
    }

    public void leggtil(View v) {
        Avtale avtale = new Avtale(stedinn.getText().toString(), meldinginn.getText().toString(), datoinn.getText().toString(), tidinn.getText().toString());
        dbHelper.leggTilAvtale(db,avtale);
    }

    public void visalle(View v) {
        String tekst = "";
        List<Avtale> avtaler = dbHelper.finnAlleAvtaler(db);
        for (Avtale avtale : avtaler) {
            tekst = tekst + "Id: " + avtale.get_AvtaleID() + ",Dato: " + avtale.getDato() + " ,Sted: " + avtale.getSted()  + " ,Melding: " + avtale.getMelding() + " ,Tid: " + avtale.getTid() + "\n";
        }

        utskrift.setText(tekst);
    }
    public void slett(View v) {
        Long avtaleid = (Long.parseLong(idinn.getText().toString()));
        dbHelper.slettAvtale(db,avtaleid);
    }
    public void oppdater(View v) {
        Avtale avtale = new Avtale();
        avtale.setSted(stedinn.getText().toString());
        avtale.setDato(datoinn.getText().toString());
        avtale.setMelding(meldinginn.getText().toString());
        avtale.setTid(tidinn.getText().toString());
        avtale.set_AvtaleID(Long.parseLong(idinn.getText().toString()));
        dbHelper.oppdaterAvtale(db, avtale);
    }
    @Override
    protected void onDestroy() {
        dbHelper.close();
        super.onDestroy(); }
}
