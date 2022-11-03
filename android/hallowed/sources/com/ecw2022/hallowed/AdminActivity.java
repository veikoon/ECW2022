package com.ecw2022.hallowed;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
/* loaded from: classes.dex */
public class AdminActivity extends AppCompatActivity {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        try {
            Intent intent = new Intent();
            intent.putExtra("flag", getApplicationContext().getSharedPreferences("prefs", 0).getString("flag", "nope"));
            setResult(300, intent);
            finish();
        } catch (Exception e) {
            Log.e("FAIL", Log.getStackTraceString(e));
        }
    }
}
