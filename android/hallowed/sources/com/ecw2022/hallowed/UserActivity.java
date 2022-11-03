package com.ecw2022.hallowed;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;
/* loaded from: classes.dex */
public class UserActivity extends AppCompatActivity {
    Button buttonLogin;
    TextInputEditText textInputEditTextPassword;
    TextInputEditText textInputEditTextUsername;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_user);
        this.textInputEditTextUsername = (TextInputEditText) findViewById(R.id.username);
        this.textInputEditTextPassword = (TextInputEditText) findViewById(R.id.password);
        Button button = (Button) findViewById(R.id.buttonLogin);
        this.buttonLogin = button;
        button.setOnClickListener(new View.OnClickListener() { // from class: com.ecw2022.hallowed.UserActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                String.valueOf(UserActivity.this.textInputEditTextUsername.getText());
                String.valueOf(UserActivity.this.textInputEditTextPassword.getText());
                Toast.makeText(UserActivity.this, "This service is not available yet!", 0).show();
            }
        });
    }
}
