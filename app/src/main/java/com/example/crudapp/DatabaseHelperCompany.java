package com.example.crudapp;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import androidx.annotation.Nullable;

public class DatabaseHelperCompany extends SQLiteOpenHelper {

    public final static String DATABASE_NAME="CRUD.db";
    public final static String TABLE_NAME_1="company";
    public final static String T1_COL_1="COMPANY_ID";
    public final static String T1_COL_2="COMPANY_NAME";
    public final static String T1_COL_3="LOCATION";
    public final static String T1_COL_4="SELLER_ID";
    public final static String T1_COL_5="URL";
    public final static String T1_COL_6="REVENUE";
    public final static String T1_COL_7="PRODUCT_CATEGORIES ";
    public final static String T1_COL_8="NUMBER_EMPLOYEES";
    public final static String T1_COL_9="ADDRESS";
    public final static String T1_COL_10="DECISION_MAKER";
    public final static String T1_COL_11="COMPANY_LINKEDIN_URL";
    public final static String T1_COL_12="COMPANY_AMAZON_SELLER_PAGE";
    public final static String T1_COL_13="LOGO";



    public final static String TABLE_NAME_2="employee";
    public final static String T2_COL_1="PERSON_ID";
    public final static String T2_COL_2="FULL_NAME";
    public final static String T2_COL_3="TITLE";
    public final static String T2_COL_4="LINKEDIN_URL";
    public final static String T2_COL_5="EMAIL_ADDRESS";
    public final static String T2_COL_6="JOB_FUNCTION";
    public final static String T2_COL_7="COMPANY_ID";



    public DatabaseHelperCompany(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        db.execSQL("CREATE TABLE IF NOT EXISTS "
                +TABLE_NAME_1+
                " (COMPANY_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "COMPANY_NAME TEXT NOT NULL," +
                "LOCATION TEXT NOT NULL," +
                "SELLER_ID INTEGER NOT NULL," +
                "URL TEXT NOT NULL," +
                "REVENUE INTEGER NOT NULL," +
                "PRODUCT_CATEGORIES TEXT NOT NULL," +
                "NUMBER_EMPLOYEES INTEGER ," +
                "ADDRESS TEXT NOT NULL," +
                "DECISION_MAKER TEXT," +
                "COMPANY_LINKEDIN_URL TEXT," +
                "COMPANY_AMAZON_SELLER_PAGE TEXT NOT NULL," +
                "LOGO TEXT )");

        db.execSQL("CREATE TABLE IF NOT EXISTS "
                +TABLE_NAME_2+
                " (PERSON_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "FULL_NAME TEXT NOT NULL," +
                "TITLE TEXT," +
                "LINKEDIN_URL TEXT NOT NULL," +
                "EMAIL_ADDRESS TEXT NOT NULL," +
                "JOB_FUNCTION TEXT," +
                "COMPANY_ID INTEGER NOT NULL )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_1);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_2);
        onCreate(db);
    }

    public ArrayList<String> getAllProvinces(){
        ArrayList<String> list=new ArrayList<String>();
        SQLiteDatabase db = this.getWritableDatabase();
            String selectQuery = "SELECT COMPANY_ID FROM  "+ TABLE_NAME_1;
            Cursor cursor = db.rawQuery(selectQuery, null);
            if(cursor.getCount() >0)
            {
                while (cursor.moveToNext()) {
                    String COMPANY_ID= cursor.getString(0);
                    list.add(COMPANY_ID);
                }
            }
        return list;
    }




    public boolean insertData(String COMPANY_NAME,String LOCATION,String SELLER_ID,String URL,String REVENUE,String PRODUCT_CATEGORIES,String NUMBER_EMPLOYEES,String ADDRESS,String DECISION_MAKER,String COMPANY_LINKEDIN_URL,String COMPANY_AMAZON_SELLER_PAGE,String LOGO){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();

        contentValues.put(T1_COL_2,COMPANY_NAME);
        contentValues.put(T1_COL_3,LOCATION);
        contentValues.put(T1_COL_4,SELLER_ID);
        contentValues.put(T1_COL_5,URL);
        contentValues.put(T1_COL_6,REVENUE);
        contentValues.put(T1_COL_7,PRODUCT_CATEGORIES);
        contentValues.put(T1_COL_8,NUMBER_EMPLOYEES);
        contentValues.put(T1_COL_9,ADDRESS);
        contentValues.put(T1_COL_10,DECISION_MAKER);
        contentValues.put(T1_COL_11,COMPANY_LINKEDIN_URL);
        contentValues.put(T1_COL_12,COMPANY_AMAZON_SELLER_PAGE);
        contentValues.put(T1_COL_13,LOGO);

        long result=db.insert(TABLE_NAME_1,null,contentValues);

        if (result==-1){
            return false;
        }else {
            return true;
        }

    }

    public boolean updateData(String COMPANY_ID,String COMPANY_NAME,String LOCATION,String SELLER_ID,String URL,String REVENUE,String PRODUCT_CATEGORIES,String NUMBER_EMPLOYEES,String ADDRESS,String DECISION_MAKER,String COMPANY_LINKEDIN_URL,String COMPANY_AMAZON_SELLER_PAGE,String LOGO){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();

        contentValues.put(T1_COL_1,COMPANY_ID);
        contentValues.put(T1_COL_2,COMPANY_NAME);
        contentValues.put(T1_COL_3,LOCATION);
        contentValues.put(T1_COL_4,SELLER_ID);
        contentValues.put(T1_COL_5,URL);
        contentValues.put(T1_COL_6,REVENUE);
        contentValues.put(T1_COL_7,PRODUCT_CATEGORIES);
        contentValues.put(T1_COL_8,NUMBER_EMPLOYEES);
        contentValues.put(T1_COL_9,ADDRESS);
        contentValues.put(T1_COL_10,DECISION_MAKER);
        contentValues.put(T1_COL_11,COMPANY_LINKEDIN_URL);
        contentValues.put(T1_COL_12,COMPANY_AMAZON_SELLER_PAGE);
        contentValues.put(T1_COL_13,LOGO);

        db.update(TABLE_NAME_1,contentValues,"COMPANY_ID=?",new String[]{COMPANY_ID});
        return true;
    }

    public Cursor getData(String COMPANY_ID){
        SQLiteDatabase db=this.getWritableDatabase();
        String query="SELECT * FROM "+TABLE_NAME_1+" WHERE COMPANY_ID ='"+COMPANY_ID+"'";
        Cursor cursor=db.rawQuery(query,null);
        return cursor;
    }

    public Integer deleteData(String COMPANY_ID){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(TABLE_NAME_1,"COMPANY_ID=?",new String[]{COMPANY_ID});
    }

    public Cursor getAllData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM "+TABLE_NAME_1,null);
        return cursor;
    }


    public boolean insertData1(String FULL_NAME,String TITLE,String LINKEDIN_URL,String EMAIL_ADDRESS,String JOB_FUNCTION,String COMPANY_ID){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();


        contentValues.put(T2_COL_2,FULL_NAME);
        contentValues.put(T2_COL_3,TITLE);
        contentValues.put(T2_COL_4,LINKEDIN_URL);
        contentValues.put(T2_COL_5,EMAIL_ADDRESS);
        contentValues.put(T2_COL_6,JOB_FUNCTION);
        contentValues.put(T2_COL_7,COMPANY_ID);

        long result=db.insert(TABLE_NAME_2, null, contentValues);

        if (result==-1){
            return false;
        }else {
            return true;
        }

    }

    public boolean updateData1(String PERSON_ID,String FULL_NAME,String TITLE,String LINKEDIN_URL,String EMAIL_ADDRESS,String JOB_FUNCTION,String COMPANY_ID){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();

        contentValues.put(T2_COL_1,PERSON_ID);
        contentValues.put(T2_COL_2,FULL_NAME);
        contentValues.put(T2_COL_3,TITLE);
        contentValues.put(T2_COL_4,LINKEDIN_URL);
        contentValues.put(T2_COL_5,EMAIL_ADDRESS);
        contentValues.put(T2_COL_6,JOB_FUNCTION);
        contentValues.put(T2_COL_7,COMPANY_ID);


        db.update(TABLE_NAME_2,contentValues,"PERSON_ID=?",new String[]{PERSON_ID});
        return true;
    }

    public Cursor getData1(String PERSON_ID){
        SQLiteDatabase db=this.getWritableDatabase();
        String query="SELECT * FROM "+TABLE_NAME_2+" WHERE PERSON_ID ='"+PERSON_ID+"'";
        Cursor cursor=db.rawQuery(query,null);
        return cursor;
    }



    public Integer deleteData1(String PERSON_ID){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(TABLE_NAME_2,"PERSON_ID=?",new String[]{PERSON_ID});
    }

    public Cursor getAllData1(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM "+TABLE_NAME_2,null);
        return cursor;
    }

}