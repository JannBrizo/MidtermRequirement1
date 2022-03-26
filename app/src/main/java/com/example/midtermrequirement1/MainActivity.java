package com.example.midtermrequirement1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    EditText student, course, address, phone, email;
    Button create, retrieve, delete, update;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        student = findViewById(R.id.student);
        course = findViewById(R.id.course);
        address = findViewById(R.id.address);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);

        create = findViewById(R.id.button);
        retrieve = findViewById(R.id.button2);
        update = findViewById(R.id.button3);
        delete = findViewById(R.id.button4);
        DB =  new DBHelper(this);

        create.setOnClickListener(new OnClickListener() {
            @Override

            public void onClick(View view){
                String studentTXT = student.getText().toString();
                String courseTXT = course.getText().toString();
                String addressTXT = address.getText().toString();
                String phoneTXT = phone.getText().toString();
                String emailTXT = email.getText().toString();

                Boolean checkinsertdata = DB.insertuserdata(studentTXT, courseTXT, addressTXT, phoneTXT, emailTXT);
                if(checkinsertdata==true)
                    Toast.makeText(MainActivity.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
            }

        });

        update.setOnClickListener(new OnClickListener() {
            @Override

            public void onClick(View view){
                String studentTXT = student.getText().toString();
                String courseTXT = course.getText().toString();
                String addressTXT = address.getText().toString();
                String phoneTXT = phone.getText().toString();
                String emailTXT = email.getText().toString();

                Boolean checkupdatedata = DB.updateuserdata(studentTXT, courseTXT, addressTXT, phoneTXT, emailTXT);
                if(checkupdatedata==true)
                    Toast.makeText(MainActivity.this, "New Entry Updated", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "New Entry Not Updated", Toast.LENGTH_SHORT).show();
            }

        });

        delete.setOnClickListener(new OnClickListener() {
            @Override

            public void onClick(View view){
                String studentTXT = student.getText().toString();

                Boolean checkdeletedata = DB.deletedata(studentTXT);
                if(checkdeletedata==true)
                    Toast.makeText(MainActivity.this, "Entry Deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Entry Not Deleted", Toast.LENGTH_SHORT).show();
            }

        });

        retrieve.setOnClickListener(new OnClickListener() {
            @Override

            public void onClick(View view){
                Cursor res = DB.getdata();
                if(res.getCount() == 0){
                    Toast.makeText(MainActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("student :" +res.getString(0)+"\n");
                    buffer.append("course :" +res.getString(1)+"\n");
                    buffer.append("address :" +res.getString(2)+"\n");
                    buffer.append("phone :" +res.getString(3)+"\n");
                    buffer.append("email :" +res.getString(4)+"\n\n");

                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }

        });
    }
}