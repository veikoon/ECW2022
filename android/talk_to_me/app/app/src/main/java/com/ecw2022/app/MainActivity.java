package com.ecw2022.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent();
        intent.setClassName("com.ecw2022.helloworld", "com.ecw2022.helloworld.CryptoActivity");
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == ItemTouchHelper.Callback.DEFAULT_DRAG_ANIMATION_DURATION){
                String result=data.getStringExtra("flag");
                Log.d("{ECW2022}","{ECW2022} : "+ result);
            }
        }
    }
}