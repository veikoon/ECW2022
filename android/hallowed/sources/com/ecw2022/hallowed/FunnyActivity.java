package com.ecw2022.hallowed;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;
/* loaded from: classes.dex */
public class FunnyActivity extends AppCompatActivity {
    Button funnyButton;
    int i;
    Random rn = new Random();

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_funny);
        final TextView textView = (TextView) findViewById(R.id.result);
        Button button = (Button) findViewById(R.id.funnyButton);
        this.funnyButton = button;
        button.setOnClickListener(new View.OnClickListener() { // from class: com.ecw2022.hallowed.FunnyActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                FunnyActivity funnyActivity = FunnyActivity.this;
                funnyActivity.i = funnyActivity.rn.nextInt(10);
                textView.setText("Your result: " + Integer.toString(FunnyActivity.this.i));
                if (FunnyActivity.this.i < 10) {
                    Toast.makeText(FunnyActivity.this, "Try Again !", 0).show();
                } else {
                    Toast.makeText(FunnyActivity.this, "Nobody can beat me !", 0).show();
                }
            }
        });
    }
}
