package com.ecw2022.helloworld;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.recyclerview.widget.ItemTouchHelper;
/* loaded from: classes.dex */
public class CryptoActivity extends Activity {
    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        try {
            Intent intent = getIntent();
            intent.putExtra("flag", getApplicationContext().getSharedPreferences("prefs", 0).getString("flag", "nope"));
            setResult(ItemTouchHelper.Callback.DEFAULT_DRAG_ANIMATION_DURATION, intent);
            finish();
        } catch (Exception e) {
            Log.e("FAIL", Log.getStackTraceString(e));
        }
    }
}
