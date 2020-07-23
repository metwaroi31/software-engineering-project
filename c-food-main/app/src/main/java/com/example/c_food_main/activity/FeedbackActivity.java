package com.example.c_food_main.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.core.content.MimeTypeFilter;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.c_food_main.R;
import com.google.common.collect.BiMap;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class FeedbackActivity<context> extends AppCompatActivity {

    public static final int CAMERA_PERM_CODE = 101;
    public static final int CAMERA_REQUEST_CODE = 102;
    public static final int GALLERY_REQUEST_CODE = 105;
    ImageView selectedImage;
    Button cameraBtn, galleryBtn;
    String currentPhotoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);
        selectedImage = findViewById(R.id.Displayimage_view);
        cameraBtn = findViewById(R.id.camera_Btn);
        galleryBtn = findViewById(R.id.gallery_Btn);

        cameraBtn.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v){
             askCameraPermissions();
               // Toast.makeText(FeedbackActivity.this, "Camera Button is Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        galleryBtn.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v){
              Intent gallery= new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
              startActivityForResult(gallery, GALLERY_REQUEST_CODE);
                //  Toast.makeText(FeedbackActivity.this, "Gallery Button is Clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void askCameraPermissions() {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA}, CAMERA_PERM_CODE);
    }else{
           openCamera() ;
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==CAMERA_PERM_CODE){
            if(grantResults.length<0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
               dispatchTakePictureIntent();
            }else{
                Toast.makeText(this, "Camera Permission is Repuired to Use camera",Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void openCamera() {
//        Toast.makeText(this, "Camera Open Repuest",Toast.LENGTH_SHORT).show();
        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(camera, CAMERA_REQUEST_CODE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @NonNull Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST_CODE) {
            if(resultCode == Activity.RESULT_OK){
                File f = new File(currentPhotoPath);
                selectedImage.setImageURI(Uri.fromFile(f));
                Log.d("tag", "ABsolute Url of Image is " + Uri.fromFile(f));

                Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                Uri contentUri = Uri.fromFile(f);
                mediaScanIntent.setData(contentUri);
                this.sendBroadcast(mediaScanIntent);
            }
        }


        if (requestCode == GALLERY_REQUEST_CODE) {
            if(resultCode == Activity.RESULT_OK){
                Uri contentUri = data.getData();
                String timeStamp = new SimpleDateFormat("yyyy_HHmmss").format(new Date());
                String imageFileName ="JPEG" + timeStamp +"."+getFileExt(contentUri);
                Log.d("tag", "onActivityResult: Fallery Image Uri " + imageFileName);
                selectedImage.setImageURI(contentUri);

            }
        }
    }

    private String getFileExt(Uri contentUri) {
        ContentResolver c = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(c.getType(contentUri));
    }

    private File createImageFile () throws IOException{
        String timeStamp = new SimpleDateFormat("yyyy_HHmmss").format(new Date());
        String imageFileName ="JPEG" + timeStamp +"_";
     //   File storageDir= getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName, ".jpg",storageDir
        );
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }
    static final int REQUEST_TAKE_PHOTO =1;
    private void dispatchTakePictureIntent(){
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if(takePictureIntent.resolveActivity(getPackageManager())!=null)
        {
            File photoFile = null;
            try{
                photoFile = createImageFile();
            }catch (IOException ex)
            {

            }
            if(photoFile!=null){
                Uri photoURI = FileProvider.getUriForFile(this,"com.example.android.fileprovider",photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,photoURI);
                startActivityForResult(takePictureIntent,REQUEST_TAKE_PHOTO);
            }
        }
    }
}