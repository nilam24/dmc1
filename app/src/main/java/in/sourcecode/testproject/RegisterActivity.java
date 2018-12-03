package in.sourcecode.testproject;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class RegisterActivity extends AppCompatActivity {

    //name,email,phone, image , password...

    ContentResolver contentResolver;
    ContentValues contentValues;
    Cursor cursor;
    User user;
    List<User> userList;
    EditText em,nm,ps,c,oc;
    String email1,name1,pass,contact1,ocupation1;
    Button registerbtn;
    ImageView img;
    byte[]imageBytes=null;
    Uri uri= HelperContract.HelperEntry.COTENT_URI_TAB;
    String projection[]={};  //HelperContract.HelperEntry.COLUMN_ID, HelperContract.HelperEntry.COLUMN_NAME, HelperContract.HelperEntry.COLUMN_Contact, HelperContract.HelperEntry.COLUMN_EmailId, HelperContract.HelperEntry.COLUMN_PASS, HelperContract.HelperEntry.COLUMN_OCCUPATION, HelperContract.HelperEntry.COLUMN_IMAGE};
    String seleArg[]={};
    String selection="";
    int PICK_IMAGE_REQUEST=1;
    Bitmap bitmap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);

        img = (ImageView) findViewById(R.id.imageProfile);
        nm = (EditText) findViewById(R.id.editTextNM);
        c = (EditText) findViewById(R.id.editTextCon);
        em = (EditText) findViewById(R.id.editTextEM);
        ps = (EditText) findViewById(R.id.editTextPS);
        oc = (EditText) findViewById(R.id.editTextOC);
        registerbtn = (Button) findViewById(R.id.button2);

        user = new User();
        userList = new ArrayList<>();
        contentResolver = getContentResolver();
        contentValues = new ContentValues();

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImag();
            }
        });

        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveProfile(imageBytes);

//                name1 = nm.getText().toString().trim();
//                contact1 = c.getText().toString().trim();
//                email1 = em.getText().toString().trim();
//                pass = ps.getText().toString().trim();
//                ocupation1 = oc.getText().toString().trim();
//
//
//                if (name1.length() == 0) {
//                    nm.setHint("name ");
//
//                }
//                if (contact1.length() == 0) {
//                    c.setHint("contact");
//                }
//                if (email1.length() == 0) {
//                    em.setHint("emailId");
//                }
//                if (pass.length() == 0) {
//                    ps.setHint("password");
//                }
//                if (ocupation1.length() == 0) {
//                    oc.setHint("occupation");
//                }
//
//                if ((name1.length() != 0) && (contact1.length() != 0) && (email1.length() != 0) && (pass.length() != 0) && (ocupation1.length() != 0)) {
//
//                    contentValues.put(HelperContract.HelperEntry.COLUMN_NAME,name1);
//                    Log.e("content values","1"+contentValues);
//                    contentValues.put(HelperContract.HelperEntry.COLUMN_Contact,contact1);
//                    Log.e("content values","2"+contentValues);
//                    contentValues.put(HelperContract.HelperEntry.COLUMN_EmailId,email1);
//                    Log.e("content values","3"+contentValues);
//                    contentValues.put(HelperContract.HelperEntry.COLUMN_PASS,pass);
//                    Log.e("content values","4"+contentValues);
//                    contentValues.put(HelperContract.HelperEntry.COLUMN_OCCUPATION,ocupation1);
//                    Log.e("content values","5"+contentValues);
//                    contentValues.put(HelperContract.HelperEntry.COLUMN_IMAGE,imageBytes);
//                    Log.e("content values","6"+contentValues);
//                    contentValues.putAll(contentValues);
//                    contentResolver.insert(uri,contentValues);
//                    Log.e("content values",""+contentValues);
//                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
//                    startActivity(intent);
//                }

            }
        });
    }

    private Bitmap uploadImag() {

        Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent,""),PICK_IMAGE_REQUEST);

        return bitmap;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if((requestCode==PICK_IMAGE_REQUEST)&&(resultCode==RESULT_OK)&&(data!=null)&&(data.getData()!=null))
        {
            Uri uri=data.getData();
            try {
                bitmap= MediaStore.Images.Media.getBitmap(this.contentResolver,uri);
                ByteArrayOutputStream outstrem=new ByteArrayOutputStream();
                imageBytes=outstrem.toByteArray();
                bitmap.compress(Bitmap.CompressFormat.JPEG,30,outstrem);
                img.setImageBitmap(bitmap);
                img.setMaxWidth(img.getWidth());
                img.getMaxWidth();
                img.setMaxHeight(img.getHeight());
                img.getMaxHeight();
                Log.e("imagess==",""+imageBytes);


            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    protected void saveProfile(byte[]bytes)
    {
        name1 = nm.getText().toString().trim();
        contact1 = c.getText().toString().trim();
        email1 = em.getText().toString().trim();
        pass = ps.getText().toString().trim();
        ocupation1 = oc.getText().toString().trim();
        bytes=imageBytes;


        if (name1.length() == 0) {

            nm.setHint("name ");

        }
        if (contact1.length() == 0) {
            c.setHint("contact");
        }
        if (email1.length() == 0) {
            em.setHint("emailId");
        }
        if (pass.length() == 0) {
            ps.setHint("password");
        }
        if (ocupation1.length() == 0) {
            oc.setHint("occupation");
        }
        if(bytes==null)
        {
            Toast.makeText(this,"pick image of small size",Toast.LENGTH_LONG).show();
            return;
        }

       // if ((name1.length() != 0) && (contact1.length() != 0) && (email1.length() != 0) && (pass.length() != 0) && (ocupation1.length() != 0)&&(bytes!=null))
        try
        {

            contentValues.put(HelperContract.HelperEntry.COLUMN_NAME,name1);
            //Log.e("content values","1"+contentValues);
            contentValues.put(HelperContract.HelperEntry.COLUMN_Contact,contact1);
            //Log.e("content values","2"+contentValues);
            contentValues.put(HelperContract.HelperEntry.COLUMN_EmailId,email1);
            //Log.e("content values","3"+contentValues);
            contentValues.put(HelperContract.HelperEntry.COLUMN_PASS,pass);
            //Log.e("content values","4"+contentValues);
            contentValues.put(HelperContract.HelperEntry.COLUMN_OCCUPATION,ocupation1);
            //Log.e("content values","5"+contentValues);
            contentValues.put(HelperContract.HelperEntry.COLUMN_IMAGE,imageBytes);
            Log.e("content values","6"+contentValues);
            contentValues.putAll(contentValues);
            if(contentValues!=null) {
                contentResolver.insert(uri, contentValues);
                Log.e("content values", "" + contentValues);
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);

                Toast.makeText(this, "profile information saved" + contentValues, Toast.LENGTH_LONG).show();
            }
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
        }

    }
}
/*
  if ((requestCode == PICK_IMAGE_REQUEST) && (resultCode == RESULT_OK) && (data != null) && (data.getData() != null)) {
          Uri uri = data.getData();
          try {

          bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, uri);
          ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
          bitmap.compress(Bitmap.CompressFormat.JPEG, 30, outputStream);
          bytesArray = outputStream.toByteArray();

          imageProduct.setImageBitmap(bitmap);
          imageProduct.setMaxWidth(imageProduct.getWidth());
          imageProduct.getMaxWidth();
          imageProduct.setMaxHeight(imageProduct.getHeight());
          imageProduct.getMaxHeight();

          } catch (IOException e) {
          Log.e(TAG, "" + e.getMessage());
          }
          }
          */