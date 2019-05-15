package com.github.danikleonov.barcodescanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.github.danikleonov.barcodescanner.Databases.DBHelper2;
import com.google.zxing.integration.android.IntentIntegrator;

public class AddToDBActivity extends ScanActivity implements View.OnClickListener {

    Button dbSaveInfo;
    TextView dbSaveCode, dbSaveType;
    DBHelper2 helper;

    EditText dbSaveClass,dbSaveDescr;
    String gettedCode,gettedType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_db);

        dbSaveInfo = (Button)findViewById(R.id.dbSaveInfo);
        dbSaveInfo.setOnClickListener(this);
        dbSaveCode = (TextView)findViewById(R.id.dbSaveCode);
        dbSaveType = (TextView)findViewById(R.id.dbSaveType);
        dbSaveClass = findViewById(R.id.dbSaveClass);
        dbSaveDescr = findViewById(R.id.dbSaveDescr);
        helper = new DBHelper2(this);

        Intent intent = getIntent();
        gettedCode = intent.getStringExtra("code");
        gettedType = intent.getStringExtra("type");

        dbSaveCode.setText(gettedCode);
        dbSaveType.setText(gettedType);


    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.dbSaveInfo){
            String gettedClass = dbSaveClass.getText().toString().trim();
            String gettedDescr = dbSaveDescr.getText().toString().trim();
            helper.insertData(gettedCode, gettedType, gettedClass, gettedDescr );
        }
    }
}
