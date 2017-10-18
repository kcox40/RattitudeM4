package edu.gatecg.cs2340.rattitudem4;

/**
 * Created by kcox8 on 9/28/2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
/** 
 * used for logins are using the database 
 * @author team 57 
 * @version 1 
 */ 


public class LoginDataBaseAdapter
{
    static final String DATABASE_NAME = "login.db";
    static final int DATABASE_VERSION = 1;
    public static final int NAME_COLUMN = 1;
    // TODO: Create public field for each column in your table.
    // SQL Statement to create a new database.
    static final String DATABASE_CREATE = "create table "+"LOGIN"+
            "( " +"ID"+" integer primary key autoincrement,"+ "USERNAME  text,PASSWORD text); ";
     /** 
     * Variable to hold the database instance 
     */ 
    public  SQLiteDatabase db;
     /**  
     *Context of the application using the database. 
     */ 
    private final Context context;
    // Database open/upgrade helper
    private DataBaseHelper dbHelper;
     /** 
     * context specific resources and classes for our database 
     * @param _context specific resources and classes for our database 
     */ 
    public  LoginDataBaseAdapter(Context _context)
    {
        context = _context;
        dbHelper = new DataBaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    /** 
     * exception for the login data base  
     * @return is the exception is needed 
     */ 
    public  LoginDataBaseAdapter open() throws SQLException
    {
        db = dbHelper.getWritableDatabase();
        return this;
    }
     /** 
     * closes the database 
     */ 
    public void close()
    {
        db.close();
    }
    /** 
     * gets data from the database 
     * @return db returns the list of database 
     */ 
    public  SQLiteDatabase getDatabaseInstance()
    {
        return db;
    }
    /** 
     * this is the login information added to the database 
     * of users 
     * @param userName is the name of the user 
     * @param password is the password for the user 
     */ 
    public void insertEntry(String userName,String password)
    {
        ContentValues newValues = new ContentValues();
        // Assign values for each row.
        newValues.put("USERNAME", userName);
        newValues.put("PASSWORD",password);

        // Insert the row into your table
        db.insert("LOGIN", null, newValues);
        ///Toast.makeText(context, "Reminder Is Successfully Saved", Toast.LENGTH_LONG).show();
    }
    /** 
     * deletes entry of user if need be 
     * @param UserName is the username that will be deleted 
     * @return returns number of users that have been deleted 
     */ 
    public int deleteEntry(String UserName)
    {
        //String id=String.valueOf(ID);
        String where="USERNAME=?";
        int numberOFEntriesDeleted= db.delete("LOGIN", where, new String[]{UserName}) ;
        // Toast.makeText(context, "Number fo Entry Deleted Successfully : "+numberOFEntriesDeleted, Toast.LENGTH_LONG).show();
        return numberOFEntriesDeleted;
    }
    /** 
     * grabs a user 
     * @param userName is the name of the new user 
     * @return returns password 
     */ 
    public String getSinlgeEntry(String userName)
    {
        Cursor cursor=db.query("LOGIN", null, " USERNAME=?", new String[]{userName}, null, null, null);
        if(cursor.getCount()<1) // UserName Not Exist
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String password= cursor.getString(cursor.getColumnIndex("PASSWORD"));
        cursor.close();
        return password;
    }
    /** 
     * updates the user when needed 
     * @param userName is the username of the entry 
     * @param password password associated to the user 
     */ 
    public void  updateEntry(String userName,String password)
    {
        // Define the updated row content.
        ContentValues updatedValues = new ContentValues();
        // Assign values for each row.
        updatedValues.put("USERNAME", userName);
        updatedValues.put("PASSWORD",password);

        String where="USERNAME = ?";
        db.update("LOGIN",updatedValues, where, new String[]{userName});
    }
}
