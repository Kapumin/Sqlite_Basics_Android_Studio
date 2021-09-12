package com.mel.sqlite_basics;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Database db;
    EditText editFirstname, editLastname, editMarks, editID;
    Button addButton,viewButton,updateButton,deleteButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db = new Database(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editFirstname = findViewById(R.id.fName);
        editLastname = findViewById(R.id.LName);
        editMarks = findViewById(R.id.edtMark);
        editID = findViewById(R.id.editID);


        addButton = findViewById(R.id.addButton);
        viewButton = findViewById(R.id.ViewButton);
        updateButton = findViewById(R.id.UpdateButton);
        deleteButton = findViewById(R.id.DeleteButton);


        addData();
        showData();
        UpdateData();
        Delete();

    }

    public void addData(){


        addButton.setOnClickListener(
                view -> {
                    boolean isInserted = db.Insert(editFirstname.getText().toString(),
                            editLastname.getText().toString(),editMarks.getText().toString());
                    if(isInserted)
                        Toast.makeText(MainActivity.this, "Details Successfully Inserted", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(MainActivity.this, "Insertion Failed", Toast.LENGTH_SHORT).show();

                }
        );
    }

    public  void showData(){
        viewButton.setOnClickListener(view -> {
            Cursor res = db.ViewAllData();
            if(res.getCount() == 0 ) {
                showMessage("ERROR", "NO DATA FOUND");
                return;
            }
                StringBuffer stringBuffer = new StringBuffer();
                while(res.moveToNext()){
                    stringBuffer.append("ID : "+res.getString(0)+"\n");
                    stringBuffer.append("First Name : "+res.getString(1)+"\n");
                    stringBuffer.append("Last Name : "+res.getString(2)+"\n");
                    stringBuffer.append("Mark : "+res.getString(3)+"\n");
                }
                showMessage("DATA FOUND", stringBuffer.toString());


        });
    }

    public  void UpdateData(){
            updateButton.setOnClickListener(view -> {
                boolean isUpdate = db.update(editID.getText().toString(),editFirstname.getText().toString(),editLastname.getText().toString(),editMarks.getText().toString());
                if(isUpdate)
                    Toast.makeText(MainActivity.this, "Successfully Updated", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Details Not Updated", Toast.LENGTH_SHORT).show();
            });
    }

    public void Delete(){
        deleteButton.setOnClickListener(view -> {
        Integer deletedRows = db.delete(editID.getText().toString());
        if(deletedRows>0)
            Toast.makeText(MainActivity.this, "Successfully Deleted", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(MainActivity.this, "ID not Found", Toast.LENGTH_SHORT).show();

        });
    }

    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }



}