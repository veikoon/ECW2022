package com.ecw2022.findmysecret;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import java.util.ArrayList;
import java.util.Optional;
/* loaded from: classes.dex */
public class MainActivity extends Activity {
    private Dispatcher dispatcher;

    /* loaded from: classes.dex */
    public class Dispatcher extends BroadcastReceiver {
        private boolean llBvUcZW = false;
        private zjgEuTjz KyQHMVaD = new zjgEuTjz(4, 3, 2, 1, 0);
        private zjgEuTjz MpdIwtDL = new zjgEuTjz();
        private zjgEuTjz JVPwXmbF = new zjgEuTjz();

        public Dispatcher() {
        }

        private void sendFlag(Context context, int i) {
            if (!this.llBvUcZW && this.KyQHMVaD.NZBagfGX() && this.MpdIwtDL.NZBagfGX() && this.JVPwXmbF.NZBagfGX() && i == 31) {
                String string = context.getSharedPreferences("prefs", 0).getString("flag", "nope");
                Intent intent = new Intent();
                intent.setAction("com.ecw2022.findmysecret.HERE");
                intent.putExtra("flag", string);
                context.sendBroadcast(intent);
            }
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            boolean z = false;
            int intExtra = intent.getIntExtra("r", 0);
            if (intExtra < 512) {
                ArrayList<String> stringArrayListExtra = intent.getStringArrayListExtra("f");
                if (stringArrayListExtra.size() < 1024) {
                    if (stringArrayListExtra.size() < 2) {
                        boolean z2 = this.llBvUcZW;
                        if (stringArrayListExtra.size() != 0) {
                            z = true;
                        }
                        this.llBvUcZW = z2 | z;
                        sendFlag(context, intExtra);
                        return;
                    }
                    String str = stringArrayListExtra.get(0);
                    String str2 = stringArrayListExtra.get(1);
                    Optional<Integer> empty = Optional.empty();
                    if (str.equals("A")) {
                        empty = this.KyQHMVaD.zjgEuTjz();
                    } else if (str.equals("B")) {
                        empty = this.MpdIwtDL.zjgEuTjz();
                    } else if (str.equals("C")) {
                        empty = this.JVPwXmbF.zjgEuTjz();
                    }
                    if (!empty.isPresent()) {
                        this.llBvUcZW |= true;
                        return;
                    }
                    if (str2.equals("A")) {
                        this.KyQHMVaD.LWnTPoVy(empty.get());
                    } else if (str2.equals("B")) {
                        this.MpdIwtDL.LWnTPoVy(empty.get());
                    } else if (str2.equals("C")) {
                        this.JVPwXmbF.LWnTPoVy(empty.get());
                    }
                    ArrayList arrayList = new ArrayList(stringArrayListExtra.subList(2, stringArrayListExtra.size()));
                    Intent intent2 = new Intent();
                    intent2.setAction("com.ecw2022.findmysecret.GIMME_FLAG");
                    intent2.putExtra("f", arrayList);
                    intent2.putExtra("r", intExtra + 1);
                    context.sendBroadcast(intent2);
                }
            }
        }
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);
        this.dispatcher = new Dispatcher();
        getIntent();
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("prefs", 0);
        registerReceiver(this.dispatcher, new IntentFilter("com.ecw2022.findmysecret.GIMME_FLAG"));
        if (!sharedPreferences.contains("flag")) {
            try {
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.putString("flag", getIntent().getStringExtra("flag"));
                edit.apply();
                Log.i("fms", "correctly set flag");
            } catch (Exception e) {
                Log.e("fms", Log.getStackTraceString(e));
            }
        }
    }
}
