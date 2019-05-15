package com.github.danikleonov.barcodescanner;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class GenActivity extends AppCompatActivity {

    private EditText etInput;
    private Button btnCreateQr;
    private ImageView imageView;

    private String[] codeTypes = {"AZTEC","CODABAR","CODE_128","CODE_39","CODE_93","DATA_MATRIX",
        "EAN_13","EAN_8","ITF","MAXICODE","QR_CODE","PDF_417","RSS_EXPANDED","UPC_A","UPC_E", "UPC_EAN_EXTENSION"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gen);

        etInput = findViewById(R.id.etInput);
        btnCreateQr = findViewById(R.id.btnCreate);
        imageView = findViewById(R.id.imageView);

        ArrayAdapter<String> codeTypesAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, codeTypes);
        codeTypesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        final Spinner spTypes = (Spinner) findViewById(R.id.spTypes);
        spTypes.setAdapter(codeTypesAdapter);

        spTypes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(),parent.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnCreateQr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = etInput.getText().toString().trim();
                String type = spTypes.getSelectedItem().toString();


                if(text != null){
                        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                        BitMatrix bitMatrix = null;
                        try {
                            if(type == "AZTEC" ){
                                bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.AZTEC,500,500);
                            }
                            if(type == "CODABAR" ){
                                bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.CODABAR,500,500);
                            }
                            if(type == "CODE_128" ){
                                bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.CODE_128,500,500);
                            }
                            if(type == "CODE_39" ){
                                bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.CODE_39,500,500);
                            }
                            if(type == "CODE_93" ){
                                bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.CODE_93,500,500);
                            }
                            if(type == "DATA_MATRIX" ){
                                bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.DATA_MATRIX,500,500);
                            }
                            if(type == "EAN_13" ){
                                bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.EAN_13,500,500);
                            }
                            if(type == "EAN_8" ){
                                bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.EAN_8,500,500);
                            }
                            if(type == "ITF" ){
                                bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.ITF,500,500);
                            }
                            if(type == "MAXICODE" ){
                                bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.MAXICODE,500,500);
                            }
                            if(type == "QR_CODE" ){
                                bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.QR_CODE,500,500);
                            }
                            if(type == "PDF_417" ){
                                bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.PDF_417,500,500);
                            }
                            if(type == "RSS_EXPANDED" ){
                                bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.RSS_EXPANDED,500,500);
                            }
                            if(type == "UPC_A" ){
                                bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.UPC_A,500,500);
                            }
                            if(type == "UPC_E" ){
                                bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.UPC_E,500,500);
                            }
                            if(type == "UPC_EAN_EXTENSION" ){
                                bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.UPC_EAN_EXTENSION,500,500);
                            }
                            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                            imageView.setImageBitmap(bitmap);
                        } catch (WriterException e) {
                            e.printStackTrace();
                        }


                }
            }
        });
    }
}
