package com.github.danikleonov.barcodescanner;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.github.danikleonov.barcodescanner.Adapters.MyAdapter;
import com.github.danikleonov.barcodescanner.Adapters.MyAdapter2;
import com.github.danikleonov.barcodescanner.Databases.DBHelper;
import com.github.danikleonov.barcodescanner.Databases.DBHelper2;
import com.github.danikleonov.barcodescanner.Model.ListItem;
import com.github.danikleonov.barcodescanner.Model.ListItem2;

import java.util.ArrayList;

public class SavedCodesActivity extends AppCompatActivity implements View.OnClickListener {

    Button cleanSaved;
    Button addSaved;

    RecyclerView recyclerView2;
    ArrayList<ListItem2> arrayList;
    MyAdapter2 myAdapter2;
    DBHelper2 helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_codes);

        cleanSaved = (Button) findViewById(R.id.cleanSaved);
        cleanSaved.setOnClickListener(this);
        addSaved = (Button) findViewById(R.id.addSaved);
        addSaved.setOnClickListener(this);

        recyclerView2 = (RecyclerView) findViewById(R.id.recyclerView2);
        recyclerView2.setHasFixedSize(true);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        helper = new DBHelper2(this);

        arrayList = helper.getAllInformation();


        if (arrayList.size() > 0) {

            myAdapter2 = new MyAdapter2(arrayList, this);
            recyclerView2.setAdapter(myAdapter2);

        } else {
            Toast.makeText(getApplicationContext(), "No Data found", Toast.LENGTH_LONG).show();
        }
    }



    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.cleanSaved:
                    helper.dropTable();
                break;
            case R.id.addSaved:
                helper.insertData("33355578793","QR_CODE","Свитч","D-Link Switch k3-21");
                break;

            default:
                break;
        }
    }
}
