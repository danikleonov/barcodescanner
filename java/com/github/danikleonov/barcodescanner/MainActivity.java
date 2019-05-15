package com.github.danikleonov.barcodescanner;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Vibrator;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
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
import com.google.zxing.client.android.Intents;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.google.zxing.integration.android.IntentIntegrator;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button scanButton;
    Button genButton;
    Button historyButton;
    Button dbButton;

    ArrayList<ListItem> arrayList;
    MyAdapter myAdapter;
    DBHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scanButton = (Button) findViewById(R.id.scanButton);
        scanButton.setOnClickListener(this);
        genButton = (Button) findViewById(R.id.genButton);
        genButton.setOnClickListener(this);
        historyButton = (Button) findViewById(R.id.historyButton);
        historyButton.setOnClickListener(this);
        dbButton = (Button) findViewById(R.id.dbButton);
        dbButton.setOnClickListener(this);

        /*final IntentIntegrator intentIntegrator = new IntentIntegrator(this);
        intentIntegrator.setBeepEnabled(true);
        intentIntegrator.setCameraId(0);

        Button floatingActionButton = (Button)findViewById(R.id.scanButton);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                intentIntegrator.initiateScan();
            }
        });*/
    }




    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.scanButton:
                Intent intent = new Intent(this, ScanActivity.class);
                startActivity(intent);
                break;
            case R.id.genButton:
                Intent intent1 = new Intent(this, GenActivity.class);
                startActivity(intent1);
                break;
            case R.id.historyButton:
                Intent intent2 = new Intent(this, HistoryActivity.class);
                startActivity(intent2);
                break;
            case R.id.dbButton:
                Intent intent3 = new Intent(this, SavedCodesActivity.class);
                startActivity(intent3);
                break;
            default:
                break;
        }
    }






}