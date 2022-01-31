package com.erinfa.onlineschoolmangamentadmin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.HashMap;

public class AddPdf extends AppCompatActivity {
    private CardView addPdf;

    private EditText pdfTitle;
    private Button uploadPdfBtn;

    private final int REQ=1;
    private Uri pdfData;

    private DatabaseReference databaseReference;
    private StorageReference storageReference;

    private String pdfName,title;
    String downloadUrl="";
    private ProgressDialog pd;

    private TextView pdfTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pdf);

        addPdf=findViewById(R.id.addPdf);
        pdfTitle=findViewById(R.id.pdfTitle);
        uploadPdfBtn=findViewById(R.id.uploadPdfBtn);

        pd=new ProgressDialog(this);

        databaseReference= FirebaseDatabase.getInstance().getReference();
        storageReference= FirebaseStorage.getInstance().getReference();

        pdfTextView=findViewById(R.id.pdfTextView);


        addPdf.setOnClickListener(view -> openGallery());

        uploadPdfBtn.setOnClickListener(view -> {
            title=pdfTitle.getText().toString();
            if (title.isEmpty()){
                pdfTitle.setError("Title is Empty");
                pdfTitle.requestFocus();
            }else if (pdfData==null){
                Toast.makeText(AddPdf.this,"Please Upload Pdf",Toast.LENGTH_SHORT).show();
            }else {
                uploadPdf();
            }
        });
    }

    private void uploadPdf() {
        pd.setTitle("Please wait...");
        pd.setMessage("Uploading pdf");
        pd.show();
        StorageReference reference=storageReference.child("pdf/"+pdfName+"-"+System.currentTimeMillis()+".pdf");
        reference.putFile(pdfData).addOnSuccessListener(taskSnapshot -> {
            Task<Uri> uriTask=taskSnapshot.getStorage().getDownloadUrl();
            while (!uriTask.isComplete());
            Uri uri=uriTask.getResult();
            downloadUrl=String.valueOf(uri);
            uploadData();
        }).addOnFailureListener(e -> {
            pd.dismiss();
            Toast.makeText(AddPdf.this,"Somethings went wrong",Toast.LENGTH_SHORT).show();

        });
    }

    private void uploadData() {
        String uniqueKey=databaseReference.child("pdf").push().getKey();

        HashMap<String, String> data= new HashMap<>();
        data.put("pdfTitle",title);
        data.put("pdfUrl",downloadUrl);

        assert uniqueKey != null;
        databaseReference.child("pdf").child(uniqueKey).setValue(data).addOnCompleteListener(task -> {
            pd.dismiss();
            Toast.makeText(AddPdf.this,"Pdf Uploaded Successfully",Toast.LENGTH_SHORT).show();
            pdfTitle.setText("");


        }).addOnFailureListener(e -> {
            pd.dismiss();
            Toast.makeText(AddPdf.this,"Failed to upload Pdf",Toast.LENGTH_SHORT).show();

        });
    }

    private void openGallery() {
       Intent intent=new Intent();
       intent.setType("application/pdf");
       intent.setAction(Intent.ACTION_GET_CONTENT);
       startActivityForResult(Intent.createChooser(intent,"Select pdf file"),REQ);
    }


    @SuppressLint("Range")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==REQ && resultCode==RESULT_OK){
            pdfData=data.getData();
            if (pdfData.toString().startsWith("content://")){
                Cursor cursor=null;
                try {
                    cursor=AddPdf.this.getContentResolver().query(pdfData,null,
                            null,null,null);
                    if (cursor !=null && cursor.moveToFirst()){
                        pdfName=cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }else if (pdfData.toString().startsWith("file://")){
                pdfName=new File(pdfData.toString()).getName();
            }
            pdfTextView.setText(pdfName);
        }
    }
}