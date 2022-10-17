package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    //Button k = (Button) findViewById(R.id.button);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       /* k.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (v.getId() == R.id.button) {
                    Intent i = new Intent(MainActivity.this, LeggTilKontakt.class);
                    startActivity(i);
                }

            }
        });*/

    }


    public void kontakt(View v){
        if (v.getId() == R.id.button2) {
            Intent i = new Intent(MainActivity.this, LeggTilKontakt.class);
            startActivity(i);
        }
    }

}
