package com.ecw2022.hallowed;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
/* loaded from: classes.dex */
public class MainActivity extends AppCompatActivity {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.userButton);
        Button button2 = (Button) findViewById(R.id.adminButton);
        Button button3 = (Button) findViewById(R.id.funnyButton);
        try {
            SharedPreferences.Editor edit = getApplicationContext().getSharedPreferences("prefs", 0).edit();
            edit.putString("flag", getIntent().getStringExtra("flag"));
            edit.apply();
        } catch (Exception e) {
            Log.e("FAIL", Log.getStackTraceString(e));
        }
        button3.setOnClickListener(new View.OnClickListener() { // from class: com.ecw2022.hallowed.MainActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(MainActivity.this, "com.ecw2022.WHAT_A_FUNNY_PERM") != 0) {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{"com.ecw2022.WHAT_A_FUNNY_PERM"}, 1);
                    return;
                }
                MainActivity.this.startActivity(new Intent(MainActivity.this.getApplicationContext(), FunnyActivity.class));
            }
        });
        button2.setOnClickListener(new View.OnClickListener() { // from class: com.ecw2022.hallowed.MainActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(MainActivity.this, "com.ecw2022.ADMIN_PERM_D0_N0T_ACCESS") != 0) {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{"com.ecw2022.ADMIN_PERM_D0_N0T_ACCESS"}, 2);
                    return;
                }
                MainActivity.this.startActivity(new Intent(MainActivity.this.getApplicationContext(), AdminActivity.class));
            }
        });
        button.setOnClickListener(new View.OnClickListener() { // from class: com.ecw2022.hallowed.MainActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(MainActivity.this, "com.ecw2022.USER_PERM") != 0) {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{"com.ecw2022.USER_PERM"}, 3);
                    return;
                }
                MainActivity.this.startActivity(new Intent(MainActivity.this.getApplicationContext(), UserActivity.class));
            }
        });
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i == 1) {
            if (iArr[0] == 0) {
                Toast.makeText(this, "Getting some fun...", 0).show();
                startActivity(new Intent(getApplicationContext(), FunnyActivity.class));
            } else {
                Toast.makeText(this, "No fun :(", 0).show();
            }
        }
        if (i == 2) {
            if (iArr[0] == 0) {
                Toast.makeText(this, "Accessing Admin Part", 0).show();
                startActivity(new Intent(getApplicationContext(), AdminActivity.class));
            } else {
                Toast.makeText(this, "You are not admin !", 0).show();
            }
        }
        if (i != 3) {
            return;
        }
        if (iArr[0] == 0) {
            Toast.makeText(this, "Accessing User Part", 0).show();
            startActivity(new Intent(getApplicationContext(), UserActivity.class));
            return;
        }
        Toast.makeText(this, "You are not user ?", 0).show();
    }
}
