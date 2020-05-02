package com.ehrpatient;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class DashboardActivity extends AppCompatActivity {


    IntentIntegrator qrscan;
    Button scacn;
    String UidPatient;
    TextView logo;
    Point size;
    TextView DocWelcomeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        final Activity activity = this;

        scacn = findViewById(R.id.qr);
//        scacn.animate().translationX(size.x*-.5f).withStartAction(new Runnable() {
//            @Override
//            public void run() {
//                scacn.animate().translationX(size.x/-12);
//                scacn.animate().translationX(size.x/-10);
//                scacn.animate().translationX(size.x/-8);
//                scacn.animate().translationX(size.x/-6);
//                scacn.animate().translationX(size.x/-4);
//            }
//        });

        scacn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qrscan = new IntentIntegrator(activity);
                qrscan.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                qrscan.setPrompt("Scan");
                qrscan.setCameraId(0);
                qrscan.setBeepEnabled(true);
                qrscan.setOrientationLocked(true);
                qrscan.setBarcodeImageEnabled(false);
                qrscan.setCaptureActivity(CaptureActivity.class);
                qrscan.initiateScan();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Result Not Found", Toast.LENGTH_SHORT).show();
            } else {
                UidPatient = result.getContents();
                Toast.makeText(this, UidPatient, Toast.LENGTH_SHORT).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
