package com.ecw2022.helloworld;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
/* loaded from: classes.dex */
public class MainActivity extends AppCompatActivity {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);
        try {
            SharedPreferences.Editor edit = getApplicationContext().getSharedPreferences("prefs", 0).edit();
            edit.putString("flag", getIntent().getStringExtra("flag"));
            edit.apply();
        } catch (Exception e) {
            Log.e("FAIL", Log.getStackTraceString(e));
        }
    }
}
