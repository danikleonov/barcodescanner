package com.github.danikleonov.barcodescanner;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.github.danikleonov.barcodescanner.Adapters.MyAdapter;
import com.github.danikleonov.barcodescanner.Databases.DBHelper;
import com.github.danikleonov.barcodescanner.Adapters.MyAdapter;
import com.github.danikleonov.barcodescanner.Model.ListItem;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity implements View.OnClickListener {

        RecyclerView recyclerView;
        ArrayList<ListItem> arrayList;
        MyAdapter myAdapter;
        DBHelper helper;

        Button deleteHistory;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_history);

            deleteHistory = (Button) findViewById(R.id.deleteHistory);
            deleteHistory.setOnClickListener(this);


            recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            helper = new DBHelper(this);

            arrayList = helper.getAllInformation();


            if (arrayList.size() > 0) {

                myAdapter = new MyAdapter(arrayList, this);
                recyclerView.setAdapter(myAdapter);

            } else {
                Toast.makeText(getApplicationContext(), "No Data found", Toast.LENGTH_LONG).show();
            }

            // on swipe left or right to deletee item data

           /* new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                     ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                @Override
                public int getMovementFlags( RecyclerView recyclerView,  RecyclerView.ViewHolder target) {
                    return 0;
                }

                @Override
                public boolean onMove( RecyclerView recyclerView,  RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                    return false;
                }

                @Override
                public void onSwiped( RecyclerView.ViewHolder viewHolder, int direction) {
                    final int position = viewHolder.getAdapterPosition();
                    ListItem listItem = arrayList.get(position);

                    helper.deleteRow(listItem.getId());

                    arrayList.remove(position);
                    myAdapter.notifyItemRemoved(position);
                    myAdapter.notifyItemRangeChanged(position,arrayList.size());
                }
            }).attachToRecyclerView(recyclerView);*/

            /*final IntentIntegrator intentIntegrator = new IntentIntegrator(this);
            intentIntegrator.setBeepEnabled(true);
            intentIntegrator.setCameraId(0);



            FloatingActionButton floatingActionButton = (FloatingActionButton)findViewById(R.id.fab);
            floatingActionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    intentIntegrator.initiateScan();
                }
            });
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);

            if (result != null){
                if (result.getContents() == null){
                    Toast.makeText(getApplicationContext(),"No result found",Toast.LENGTH_SHORT).show();
                }
                else{
                    boolean isInserted = helper.insertData(result.getFormatName(),result.getContents());

                    if(isInserted){
                        arrayList.clear();
                        arrayList = helper.getAllInformation();
                        myAdapter = new MyAdapter(arrayList,this);
                        myAdapter.notifyDataSetChanged();
                    }
                }
            }
            else{
                super.onActivityResult(requestCode, resultCode,data);
            } */
        }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.deleteHistory:
                helper.dropTable();
                break;

            default:
                break;
        }
    }

}
