package com.example.crudapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmployeeActivity extends AppCompatActivity {
//   private DatabaseHelperEmployee myDB;
    private DatabaseHelperCompany temp;

    private EditText editPersonId,editFullName,editTitle,editLinkedinUrl,editEmailAddress,editJobFunction;
    Spinner editCompID;
    private Button buttonAdd,buttonUpdate,buttonDelete,buttonGetData,buttonViewAll;
    Button company;
    ArrayAdapter<String> adapter;

    ArrayList<String> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);

        editPersonId=findViewById(R.id.personID);
        editFullName=findViewById(R.id.personName);
        editTitle=findViewById(R.id.title);
        editLinkedinUrl=findViewById(R.id.linkedln_url_person);
        editEmailAddress=findViewById(R.id.email_person);
        editJobFunction=findViewById(R.id.job_func);

        buttonAdd = findViewById(R.id.button_add);
        buttonUpdate = findViewById(R.id.button_update);
        buttonDelete = findViewById(R.id.button_delete);
        buttonGetData = findViewById(R.id.button_view);
        buttonViewAll = findViewById(R.id.button_viewAll);

        company= findViewById(R.id.button_company);

       // myDB=new DatabaseHelperEmployee(this);
        temp=new DatabaseHelperCompany(this);

        company.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });

        editCompID=(Spinner)findViewById(R.id.companyID_person);
        ArrayList<String> list=temp.getAllProvinces();
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, R.layout.spinner_layout, R.id.text,list);
        editCompID.setAdapter(adapter);




        //showMessage("test","Testing");
        addData1();
        getData1();
        viewAll1();
        updateData1();
        deleteData1();
    }



    public void addData1(){
        buttonAdd.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {

                                             if (editFullName.getText().toString().equals(String.valueOf(""))) {
                                                 editFullName.setError("Enter Full Name!");
                                                 Toast.makeText(EmployeeActivity.this, "Enter Full Name!", Toast.LENGTH_SHORT).show();
                                                 return;
                                             }

                                             if (editLinkedinUrl.getText().toString().equals(String.valueOf(""))) {
                                                 editLinkedinUrl.setError("Enter Valid LinkedIn URL!");
                                                 Toast.makeText(EmployeeActivity.this, "Enter LinkedIn URL!", Toast.LENGTH_SHORT).show();
                                                 return;
                                             }
                                             if (editEmailAddress.getText().toString().equals(String.valueOf(""))) {
                                                 editEmailAddress.setError("Enter Valid Email Address!");
                                                 Toast.makeText(EmployeeActivity.this, "Enter Valid Email Address!", Toast.LENGTH_SHORT).show();
                                                 return;
                                             }
                                             if ( !isValidUrl(editLinkedinUrl.getText().toString())) {
                                                 editLinkedinUrl.setError("Enter Valid LinkedIn URL!");
                                                 Toast.makeText(EmployeeActivity.this, "Enter Valid LinkedIn URL!", Toast.LENGTH_SHORT).show();
                                                 return;
                                             }
                                             if (!isValidEmail(editEmailAddress.getText().toString())) {
                                                 editEmailAddress.setError("Enter Valid Email Address!");
                                                 Toast.makeText(EmployeeActivity.this, "Enter Valid Email Address!", Toast.LENGTH_SHORT).show();
                                                 return;
                                             }




                                                 boolean isInserted = temp.insertData1(editFullName.getText().toString(), editTitle.getText().toString(), editLinkedinUrl.getText().toString(), editEmailAddress.getText().toString(), editJobFunction.getText().toString(), editCompID.getSelectedItem().toString());
                                                 if (isInserted) {
                                                     Toast.makeText(EmployeeActivity.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                                                     editPersonId.setText(null);
                                                     editFullName.setText(null);
                                                     editTitle.setText(null);
                                                     editLinkedinUrl.setText(null);
                                                     editEmailAddress.setText(null);
                                                     editJobFunction.setText(null);
                                                 } else {
                                                     Toast.makeText(EmployeeActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();

                                                 }

                                             }



            }
        );
    }

    private boolean isValidUrl(String url) {
        Pattern p = Patterns.WEB_URL;
        Matcher m = p.matcher(url.toLowerCase());
        if(m.matches()){
            return true;}
        else{
            return false;}
    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    public void getData1(){
        buttonGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id=editPersonId.getText().toString();
                if(id.equals(String.valueOf(""))){
                    editPersonId.setError("Enter ID!");
                    Toast.makeText(EmployeeActivity.this, "Enter ID To Be Searched", Toast.LENGTH_SHORT).show();
                    return;
                }

                Cursor cursor=temp.getData1(id);
                String data=null;

                if(cursor.moveToNext()){
                    data="Person Id:"+cursor.getString(0)+
                            "\n Company Id:"+cursor.getString(6)+
                            "\n Full Name:"+cursor.getString(1)+
                            "\n Title:"+cursor.getString(2)+
                            "\n LinkedinUrl:"+cursor.getString(3)+
                            "\n EmailAddress :"+cursor.getString(4)+
                            "\n JobFunction:"+cursor.getString(5);
                    showMessage("Data:",data);
                }
                else{
                    showMessage("Data Not Found !!","");
                }

            }
        });
    }

    public void viewAll1(){
        buttonViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor=temp.getAllData1();

                //test to check empty DB
                if(cursor.getCount()==0){
                    showMessage("Error", "Nothing found in DB");
                }
                StringBuffer buffer=new StringBuffer();

                while (cursor.moveToNext()){
                    buffer.append("Person Id:"+ cursor.getString(0)+"\n");
                    buffer.append("Company Id:"+ cursor.getString(6)+"\n");
                    buffer.append("Full Name:"+ cursor.getString(1)+"\n");
                    buffer.append("Title:"+ cursor.getString(2)+"\n");
                    buffer.append("Linkedin Url:"+ cursor.getString(3)+"\n");
                    buffer.append("Email Address:"+ cursor.getString(4)+"\n");
                    buffer.append("Job Function:"+ cursor.getString(5)+"\n\n");
                }
                showMessage("All Data",buffer.toString());
            }
        });
    }

    public void updateData1(){
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editPersonId.getText().toString().equals(String.valueOf(""))){
                    editPersonId.setError("Enter ID!");
                    Toast.makeText(EmployeeActivity.this,"Enter The ID To Update The Person! ",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(editFullName.getText().toString().equals(String.valueOf(""))){
                    editFullName.setError("Enter Full Name!");
                    Toast.makeText(EmployeeActivity.this,"Enter Full Name!",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(editLinkedinUrl.getText().toString().equals(String.valueOf(""))){
                    editLinkedinUrl.setError("Enter LinkedIn URL!");
                    Toast.makeText(EmployeeActivity.this,"Enter LinkedIn URL!",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(editEmailAddress.getText().toString().equals(String.valueOf(""))){
                    editEmailAddress.setError("Enter Valid Email Address!");
                    Toast.makeText(EmployeeActivity.this,"Enter Valid Email Address!",Toast.LENGTH_SHORT).show();
                    return;
                }

                if ( !isValidUrl(editLinkedinUrl.getText().toString())) {
                    editLinkedinUrl.setError("Enter Valid LinkedIn URL!");
                    Toast.makeText(EmployeeActivity.this, "Enter Valid LinkedIn URL!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!isValidEmail(editEmailAddress.getText().toString())) {
                    editEmailAddress.setError("Enter Valid Email Address!");
                    Toast.makeText(EmployeeActivity.this, "Enter Valid Email Address!", Toast.LENGTH_SHORT).show();
                    return;
                }
                boolean isUpdate=temp.updateData1(
                        editPersonId.getText().toString(),
                        editFullName.getText().toString(),
                        editTitle.getText().toString(),
                        editLinkedinUrl.getText().toString(),
                        editEmailAddress.getText().toString(),
                        editJobFunction.getText().toString(),
                        editCompID.getSelectedItem().toString()
                );

                if(isUpdate)
                    Toast.makeText(EmployeeActivity.this,"Updated Sucessfully",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(EmployeeActivity.this,"Opps",Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void deleteData1(){
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id=editPersonId.getText().toString();
                if(id.equals(String.valueOf(""))){
                    editPersonId.setError("Enter ID");
                    Toast.makeText(EmployeeActivity.this,"Enter The ID To Be Deleted!",Toast.LENGTH_SHORT).show();
                    return;
                }
                Integer deletedRow=temp.deleteData1(id);

                if(deletedRow>0){
                    Toast.makeText(EmployeeActivity.this,"Delete Success",Toast.LENGTH_SHORT).show();
                }

                else
                {
                    Toast.makeText(EmployeeActivity.this, "Opps!!", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }



    private void showMessage(String title,String message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.create();
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();

    }



}
