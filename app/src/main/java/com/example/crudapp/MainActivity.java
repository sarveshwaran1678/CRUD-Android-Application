package com.example.crudapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.net.URI;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

     private DatabaseHelperCompany myDB;
    private EditText editCompanyId,editCompanyName,editLocation,editSellerId,editUrl,editRevenue,editProductCategories,editNumberEmployees,editDecisionMaker,editAddress,editCompanyLinkedinUrl,editCompanyAmazonSellerPage;
    private Button editLogo;
    private ImageView logo;
    private Button buttonAdd,buttonGetData,buttonUpdate,buttonDelete,buttonViewAll;
    Button employee;
    private static final int GALLERY = 4;
     Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDB=new DatabaseHelperCompany(this);

        editCompanyId=findViewById(R.id.companyID);
        editCompanyName=findViewById(R.id.companyName);
        editLocation=findViewById(R.id.company_loc);
        editSellerId=findViewById(R.id.sellerID);
        editUrl=findViewById(R.id.url);
        editRevenue=findViewById(R.id.revenue);
        editProductCategories=findViewById(R.id.prodcatagory);
        editNumberEmployees=findViewById(R.id.employee);
        editDecisionMaker=findViewById(R.id.decision);
        editAddress=findViewById(R.id.address);
        editCompanyLinkedinUrl=findViewById(R.id.comp_linkedln_url);
        editCompanyAmazonSellerPage=findViewById(R.id.amz_Sellerpage);
        editLogo=findViewById(R.id.logo_id);
        logo=findViewById(R.id.logo);



        buttonAdd=findViewById(R.id.button_add);
        buttonUpdate=findViewById(R.id.button_update);
        buttonDelete=findViewById(R.id.button_delete);
        buttonGetData=findViewById(R.id.button_view);
        buttonViewAll=findViewById(R.id.button_viewAll);

        employee=findViewById(R.id.button_employee);

        employee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),EmployeeActivity.class);
                startActivity(i);
            }
        });

        editLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/+");
                startActivityForResult(intent, GALLERY);
            }
        });


        addData();
        getData();
        viewAll();
        updateData();
        deleteData();


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY) {
            uri = data.getData();
            logo.setImageURI(uri);
        }
    }
    //TODO:make Intent to show all data


    public void addData(){
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(editCompanyName.getText().toString().equals(String.valueOf(""))){
                    editCompanyName.setError("Enter Company Name!");
                    Toast.makeText(MainActivity.this,"Enter Company Name!",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(editUrl.getText().toString().equals(String.valueOf(""))){
                    editUrl.setError("Enter Vaild URL!");
                    Toast.makeText(MainActivity.this,"Enter Vaild URL!",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(editRevenue.getText().toString().equals(String.valueOf(""))){
                    editRevenue.setError("Enter Revenue!");
                    Toast.makeText(MainActivity.this,"Enter Revenue!",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(editProductCategories.getText().toString().equals(String.valueOf(""))){
                    editProductCategories.setError("Enter Product Categories!");
                    Toast.makeText(MainActivity.this,"Enter Product Categories!",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(editAddress.getText().toString().equals(String.valueOf(""))){
                    editAddress.setError("Enter Address!");
                    Toast.makeText(MainActivity.this,"Enter Address!",Toast.LENGTH_SHORT).show();
                    return;
                }if(editCompanyAmazonSellerPage.getText().toString().equals(String.valueOf(""))){
                    editCompanyAmazonSellerPage.setError("Enter Company Amazon Seller Page!");
                    Toast.makeText(MainActivity.this,"Enter Company Amazon Seller Page!",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(uri.toString().equals(String.valueOf(""))){
                    editLogo.setError("Select A Valid Logo!");
                    Toast.makeText(MainActivity.this,"Select A Logo!",Toast.LENGTH_SHORT).show();
                    return;
                }

                if ( !isValidUrl(editUrl.getText().toString())) {
                    editUrl.setError("Enter Valid URL!");
                    Toast.makeText(MainActivity.this, "Enter Valid URL!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if ( !isValidUrl(editCompanyAmazonSellerPage.getText().toString())) {
                    editCompanyAmazonSellerPage.setError("Enter Valid Amazon Sellers URL!");
                    Toast.makeText(MainActivity.this, "Enter Valid Amazon Sellers URL!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!editCompanyLinkedinUrl.getText().toString().equals(String.valueOf(""))) {
                    {
                        if (!isValidUrl(editCompanyLinkedinUrl.getText().toString())) {

                            editCompanyLinkedinUrl.setError("Enter Valid Company LinkedIn URL!");
                            Toast.makeText(MainActivity.this, "Enter Valid Company LinkedIn URL!", Toast.LENGTH_SHORT).show();
                            return;
                        }

                    }
                }
                if ( !isValidUrl(editCompanyAmazonSellerPage.getText().toString())) {
                    editCompanyAmazonSellerPage.setError("Enter Valid Amazon Sellers URL!");
                    Toast.makeText(MainActivity.this, "Enter Valid Amazon Sellers URL!", Toast.LENGTH_SHORT).show();
                    return;
                }



                boolean isInserted=myDB.insertData(editCompanyName.getText().toString(),editLocation.getText().toString(),editSellerId.getText().toString(),editUrl.getText().toString(),editRevenue.getText().toString(),editProductCategories.getText().toString(),editNumberEmployees.getText().toString(),editDecisionMaker.getText().toString(),editAddress.getText().toString(),editCompanyLinkedinUrl.getText().toString(),editCompanyAmazonSellerPage.getText().toString(),uri.toString());
                if(isInserted){

                    Toast.makeText(MainActivity.this,"Data Inserted",Toast.LENGTH_SHORT).show();
                    editCompanyId.setText(null);
                    editCompanyName.setText(null);
                    editLocation.setText(null);
                    editSellerId.setText(null);
                    editUrl.setText(null);
                    editRevenue.setText(null);
                    editProductCategories.setText(null);
                    editNumberEmployees.setText(null);
                    editDecisionMaker.setText(null);
                    editAddress.setText(null);
                    editCompanyLinkedinUrl.setText(null);
                    editCompanyAmazonSellerPage.setText(null);
                    logo.setImageURI(null);
                    uri=null;
                }
                else {
                    Toast.makeText(MainActivity.this,"Something went wrong",Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    public void getData(){
        buttonGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id=editCompanyId.getText().toString();
                if(id.equals(String.valueOf(""))){
                    editCompanyId.setError("Enter ID!");
                    Toast.makeText(MainActivity.this,"Enter The ID To Be Searched!",Toast.LENGTH_SHORT).show();
                    return;
                }

                Cursor cursor=myDB.getData(id);
                String data=null;

                if(cursor.moveToNext()){
                    data="Company Id: "+cursor.getString(0)+
                            "\n Company Name: "+cursor.getString(1)+
                            "\n Location: "+cursor.getString(2)+
                            "\n Seller Id: "+cursor.getString(3)+
                            "\n Url: "+cursor.getString(4)+
                            "\n Revenue: "+cursor.getString(5)+
                            "\n Product Categories: "+cursor.getString(6)+
                            "\n Number Employees: "+cursor.getString(7)+
                            "\n Address: "+cursor.getString(8)+
                            "\n Decision Maker: "+cursor.getString(9)+
                            "\n Company Linkedin Url: "+cursor.getString(10)+
                            "\n Company AmazonSeller Page: "+cursor.getString(11)+
                            "\n Logo: "+cursor.getString(12);
                    showMessage("Data: ",data);

                }
                else{
                    showMessage("Data Not Found !!","");
                }

            }
        });
    }

    public void viewAll(){
        buttonViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor=myDB.getAllData();

                //test to check empty DB
                if(cursor.getCount()==0){
                    showMessage("Error", "Nothing found in DB");
                }
                StringBuffer buffer=new StringBuffer();

                while (cursor.moveToNext()){
                    buffer.append("Company Id: "+ cursor.getString(0)+"\n");
                    buffer.append("Company Name: "+ cursor.getString(1)+"\n");
                    buffer.append("Location: "+ cursor.getString(2)+"\n");
                    buffer.append("Seller Id: "+ cursor.getString(3)+"\n");
                    buffer.append("Url: "+ cursor.getString(4)+"\n");
                    buffer.append("Revenue: "+ cursor.getString(5)+"\n");
                    buffer.append("Product Categories: "+ cursor.getString(6)+"\n");
                    buffer.append("Number Employees: "+ cursor.getString(7)+"\n");
                    buffer.append("Address: "+ cursor.getString(8)+"\n");
                    buffer.append("Decision Maker: "+ cursor.getString(9)+"\n");
                    buffer.append("Company Linkedin Url: "+ cursor.getString(10)+"\n");
                    buffer.append("Company AmazonSeller Page: "+ cursor.getString(11)+"\n");
                    buffer.append("Logo: "+ cursor.getString(12)+"\n\n");
                }
                showMessage("All Data",buffer.toString());
            }
        });
    }

    public void updateData(){
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editCompanyId.getText().toString().equals(String.valueOf(""))){
                    editCompanyId.setError("Enter ID!");
                    Toast.makeText(MainActivity.this,"Enter The ID You Want To Update!",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(editCompanyName.getText().toString().equals(String.valueOf(""))){
                    editCompanyName.setError("Enter Company Name!");
                    Toast.makeText(MainActivity.this,"Enter Company Name!",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(editUrl.getText().toString().equals(String.valueOf(""))){
                    editUrl.setError("Enter  Vaild URL!");
                    Toast.makeText(MainActivity.this,"Enter URL!",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(editRevenue.getText().toString().equals(String.valueOf(""))){
                    editRevenue.setError("Enter Revenue!");
                    Toast.makeText(MainActivity.this,"Enter Revenue!",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(editProductCategories.getText().toString().equals(String.valueOf(""))){
                    editProductCategories.setError("Enter Product Categories!");
                    Toast.makeText(MainActivity.this,"Enter Product Categories!",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(editAddress.getText().toString().equals(String.valueOf(""))){
                    editAddress.setError("Enter Address!");
                    Toast.makeText(MainActivity.this,"Enter Address!",Toast.LENGTH_SHORT).show();
                    return;
                }if(editCompanyAmazonSellerPage.getText().toString().equals(String.valueOf(""))){
                    editCompanyAmazonSellerPage.setError("Enter Company Amazon Seller Page!");
                    Toast.makeText(MainActivity.this,"Enter Company Amazon Seller Page!",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(uri.toString().equals(String.valueOf(""))){
                    editLogo.setError("Select A Valid Logo!");
                    Toast.makeText(MainActivity.this,"Select A Logo!",Toast.LENGTH_SHORT).show();
                    return;
                }
                if ( !isValidUrl(editUrl.getText().toString())) {
                    editUrl.setError("Enter Valid URL!");
                    Toast.makeText(MainActivity.this, "Enter Valid URL!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if ( !isValidUrl(editCompanyLinkedinUrl.getText().toString())) {
                    editCompanyLinkedinUrl.setError("Enter Valid Company LinkedIn URL!");
                    Toast.makeText(MainActivity.this, "Enter Valid Company LinkedIn URL!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if ( !isValidUrl(editCompanyAmazonSellerPage.getText().toString())) {
                    editCompanyAmazonSellerPage.setError("Enter Valid Amazon Sellers URL!");
                    Toast.makeText(MainActivity.this, "Enter Valid Amazon Sellers URL!", Toast.LENGTH_SHORT).show();
                    return;
                }


                boolean isUpdate=myDB.updateData(
                        editCompanyId.getText().toString(),
                        editCompanyName.getText().toString(),
                        editLocation.getText().toString(),
                        editSellerId.getText().toString(),
                        editUrl.getText().toString(),
                        editRevenue.getText().toString(),
                        editProductCategories.getText().toString(),
                        editNumberEmployees.getText().toString(),
                        editDecisionMaker.getText().toString(),
                        editAddress.getText().toString(),
                        editCompanyLinkedinUrl.getText().toString(),
                        editCompanyAmazonSellerPage.getText().toString(),
                        uri.toString()
                );

                if(isUpdate)
                    Toast.makeText(MainActivity.this,"Updated Sucessfully",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this,"Something went wrong",Toast.LENGTH_SHORT).show();

            }
        });
    }
    private boolean isValidUrl(String url) {
        Pattern p = Patterns.WEB_URL;
        Matcher m = p.matcher(url.toLowerCase());
        if(m.matches()){
            return true;}
        else{
            return false;}
    }

    public void deleteData(){
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id=editCompanyId.getText().toString();
                if(id.equals(String.valueOf(""))){
                    editCompanyId.setError("Enter ID");
                    Toast.makeText(MainActivity.this,"Enter The ID To Be Deleted!",Toast.LENGTH_SHORT).show();
                    return;
                }
                Integer deletedRow=myDB.deleteData(id);

                if(deletedRow>0){
                    Toast.makeText(MainActivity.this,"Delete Success",Toast.LENGTH_SHORT).show();
                }

                else
                {
                    Toast.makeText(MainActivity.this, "Oops!! ID not Found", Toast.LENGTH_SHORT).show();
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