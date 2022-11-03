package com.ecw2022.app;

import androidx.appcompat.app.AppCompatActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Dispatcher dispatcher;

    public class Dispatcher extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String flag = intent.getStringExtra("flag");
            Log.d("{ECW2022}","{ECW2022} : " + flag);
        }
    }

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.dispatcher = new Dispatcher();
        getIntent();
        registerReceiver(this.dispatcher, new IntentFilter("com.ecw2022.findmysecret.HERE"));
        Intent intent2 = new Intent();
        ArrayList<String> list = new ArrayList<String>();
        int i = 31;
        intent2.setAction("com.ecw2022.findmysecret.GIMME_FLAG");
        intent2.putExtra("f", list);
        intent2.putExtra("r", i);
            sendBroadcast(intent2);
    }
}