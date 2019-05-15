package com.github.danikleonov.barcodescanner;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Vibrator;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.util.Linkify;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.github.danikleonov.barcodescanner.Adapters.MyAdapter;
import com.github.danikleonov.barcodescanner.Databases.DBHelper;
import com.github.danikleonov.barcodescanner.Databases.DBHelper2;
import com.github.danikleonov.barcodescanner.Model.ListItem;
import com.github.danikleonov.barcodescanner.Model.ListItem2;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class ScanActivity extends Activity implements View.OnClickListener {

    Button scanBtn;

    Intent sendToActivity;


    AlertDialog.Builder ad;
    Context context;
    DBHelper helper;
    DBHelper2 helper2;
    ArrayList<ListItem2> arrayList2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        scanBtn = (Button)findViewById(R.id.scan_button);
        scanBtn.setOnClickListener(this);
        helper = new DBHelper(this);
        helper2 = new DBHelper2(this);





    }

    public void onClick(View v){
        if(v.getId()==R.id.scan_button){
            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            scanIntegrator.initiateScan();
        }
    }




        public void onActivityResult(int requestCode, int resultCode, final Intent intent) {
            IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode,
                    resultCode, intent);

            if (scanningResult != null) {
                final String scanContent = scanningResult.getContents();
                final String scanFormat = scanningResult.getFormatName();
                if(scanContent != null){ helper.insertData(scanContent, scanFormat);}

                String isInBase = helper2.getDescr(scanContent,scanFormat);

                if(isInBase != null){
                    AlertDialog.Builder builder = new AlertDialog.Builder(ScanActivity.this);
                    builder.setTitle("Этот Штрих-код обнаружен в базе данных!")
                            .setMessage(scanFormat+ " CONTENT: " + scanContent + "\n Описание: "+ isInBase)
                            .setCancelable(false)
                            .setNegativeButton("Ок",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.cancel();
                                        }
                                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
                else{

                    AlertDialog.Builder builder = new AlertDialog.Builder(ScanActivity.this);
                    // Говорим между какими Activity будет происходить связь
                    sendToActivity = new Intent(this, AddToDBActivity.class);

                    // указываем первым параметром ключ, а второе значение
                    // по ключу мы будем получать значение с Intent
                    sendToActivity.putExtra("code", scanContent);
                    sendToActivity.putExtra("type", scanFormat);
                    builder.setTitle("Обнаружен новый Штрих-код!")
                            .setMessage("FORMAT: "+scanFormat+ " CONTENT: " + scanContent +  "\nДобавить в базу даных? ")
                            .setCancelable(false)
                            .setNegativeButton("Нет",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.cancel();
                                        }
                                    })
                            .setPositiveButton("Да",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            // показываем новое Activity
                                            startActivity(sendToActivity);
                                        }
                                    });

                    AlertDialog alert = builder.create();
                    alert.show();
                }


            }
            else{
                Toast toast = Toast.makeText(getApplicationContext(),
                        "No scan data received!", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
}
