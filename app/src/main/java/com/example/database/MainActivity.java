package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    String CHANNEL_ID = "MinKanal";

    //Button k = (Button) findViewById(R.id.button);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        BroadcastReceiver myBroadcastReceiver = new MinBroadcastReceiver();
        IntentFilter filter = new IntentFilter("com.example.service.MITTSIGNAL");
        filter.addAction("com.example.service.MITTSIGNAL");
        this.registerReceiver(myBroadcastReceiver, filter);
        createNotificationChannel();

    }

    public void startService(View v) {
        /*Intent intent = new Intent(this, MinService.class);this.startService(intent);*/

        Intent intent = new Intent();
        intent.setAction("com.example.service.MITTSIGNAL");
        sendBroadcast(intent);
    }


    public void kontakt(View v){
        if (v.getId() == R.id.button2) {
            Intent i = new Intent(MainActivity.this, LeggTilKontakt.class);
            startActivity(i);
        }
    }

    public void avtale(View v){
        if (v.getId() == R.id.button) {
            Intent i = new Intent(MainActivity.this, LeggTilAvtaler.class);
            startActivity(i);
        }
    }
    private void createNotificationChannel() {
        CharSequence name = getString(R.string.channel_name);
        String description = getString(R.string.channel_description);
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel = new
                NotificationChannel(CHANNEL_ID, name, importance);
        channel.setDescription(description);
        NotificationManager notificationManager =
                getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);
    }


}
