package com.example.easyfinance;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer;

import java.util.List;

public class ScanActivity extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;

    private Button captureBtn, detectBtn;
    private ImageView imageView;
    private TextView textView;
    private Bitmap imageBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

         captureBtn = findViewById(R.id.button_capture_photo);
         detectBtn = findViewById(R.id.detect_text_button);
         textView = findViewById(R.id.text_view);
         imageView = findViewById(R.id.image_view);

         captureBtn.setOnClickListener(v -> {
             dispatchTakePictureIntent();
             textView.setText("");
         });

         detectBtn.setOnClickListener(v -> detectTextFromImage());

    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imageBitmap);
        }
    }

    private void detectTextFromImage() {
        FirebaseVisionImage firebaseVisionImage = FirebaseVisionImage.fromBitmap(imageBitmap);
        FirebaseVisionTextRecognizer firebaseVisionTextRecognizer = FirebaseVision.getInstance().getOnDeviceTextRecognizer();
        firebaseVisionTextRecognizer.processImage(firebaseVisionImage).addOnSuccessListener(firebaseVisionText -> {
            displayTextFromImage(firebaseVisionText);
        }).addOnFailureListener(e -> {
            Toast.makeText(ScanActivity.this,"Error: "+e.getMessage(),Toast.LENGTH_SHORT);
            Log.d("ERROR",e.getMessage());
        });
    }

    private void displayTextFromImage(FirebaseVisionText firebaseVisionText) {
        List<FirebaseVisionText.TextBlock> blockList = firebaseVisionText.getTextBlocks();
        if(blockList.size() == 0){
            Toast.makeText(ScanActivity.this, "No Text Found", Toast.LENGTH_LONG);
        } else{
            for(FirebaseVisionText.TextBlock block: firebaseVisionText.getTextBlocks()){
                String text = block.getText();
                textView.setText(text);
            }
        }
    }
}
