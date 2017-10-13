package edu.gatecg.cs2340.rattitudem4;

/**
 * Created by daltontouch on 10/13/2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;

public class RatReportDataBaseAdapter
{
    static final int NUMBER_OF_ENTRIES = 100;
    static final String DATABASE_NAME = "rats.db";
    static final int DATABASE_VERSION = 1;
    public static final int NAME_COLUMN = 1;
    public ArrayList<String[]> ratSightingList;

    private CSVReader csvRatData;

    // TODO: Create public field for each column in your table.
    // SQL Statement to create a new database.
    static final String DATABASE_CREATE = "create table "+"RATS"+
            "( "+"ID" + " integer primary key autoincrement," + "DATE text,LOCATION text,ZIPCODE"
            + " text,ADDRESS text,CITY text,BOROUGH text,LATITUDE text,LONGITUDE text); ";
    // Variable to hold the database instance
    public SQLiteDatabase db;
    // Context of the application using the database.
    private final Context context;
    // Database open/upgrade helper
    private RatDataBaseHelper dbHelper;
    public RatReportDataBaseAdapter(Context _context)
    {
        context = _context;
        dbHelper = new RatDataBaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
        // If rats.db empty, run buildRatDataBaseFromFile()
//        if (tableEmpty()) {
//            buildRatDataBaseFromFile();
//        }
        buildRatDataBaseFromFile();
    }

    public RatReportDataBaseAdapter open() throws SQLException
    {
        db = dbHelper.getWritableDatabase();
        return this;
    }
    public void close() {
        db.close();
    }

    public SQLiteDatabase getDatabaseInstance() {
        return db;
    }

    public void insertEntry(String date, String location, String zipcode, String address,
                            String city, String borough, String latitude, String longitude) {
        ContentValues newValues = new ContentValues();
        // Assign values for each row.
        newValues.put("DATE", date);
        newValues.put("LOCATION", location);
        newValues.put("ZIPCODE", zipcode);
        newValues.put("ADDRESS", address);
        newValues.put("CITY", city);
        newValues.put("BOROUGH", borough);
        newValues.put("LATITUDE", latitude);
        newValues.put("LONGITUDE", longitude);

        // Insert the row into your table
        db.insert("RATS", null, newValues);
        ///Toast.makeText(context, "Reminder Is Successfully Saved", Toast.LENGTH_LONG).show();
    }

    /**
// What do I use rather than Username??

    public int deleteEntry(String ID) {
        //String id=String.valueOf(ID);
        String where = "ID=?";
        int numberOFEntriesDeleted = db.delete("RATS", where, new String[]{ID});
        // Toast.makeText(context, "Number fo Entry Deleted Successfully : "+numberOFEntriesDeleted, Toast.LENGTH_LONG).show();
        return numberOFEntriesDeleted;
    }
     **/

    public String[] getSingleEntry(String ID) {
        Cursor cursor = db.query("RATS", null, " ID=?", new String[]{ID}, null, null, null);
        if (cursor.getCount() < 1) // UserName Not Exist
        {
            cursor.close();
            return new String[]{"NOT EXIST"};
        }
        cursor.moveToFirst();
        String[] sightingInfo;

        String date = cursor.getString(cursor.getColumnIndex("DATE"));
        String location = cursor.getString(cursor.getColumnIndex("LOCATION"));
        String zipcode = cursor.getString(cursor.getColumnIndex("ZIPCODE"));
        String address = cursor.getString(cursor.getColumnIndex("ADDRESS"));
        String city = cursor.getString(cursor.getColumnIndex("CITY"));
        String borough = cursor.getString(cursor.getColumnIndex("BOROUGH"));
        String latitude = cursor.getString(cursor.getColumnIndex("LATITUDE"));
        String longitude = cursor.getString(cursor.getColumnIndex("LONGITUDE"));

        sightingInfo = new String[]{date, location, zipcode, address, city, borough, latitude, longitude};
        cursor.close();
        return sightingInfo;
    }

    public String[] ratInfoStringArr() {
//        System.out.println(tableEmpty());
        if (tableEmpty()) {
            buildRatDataBaseFromFile();
        }
        ArrayList<String> infoList = new ArrayList<>();
        String infoLine;
        Cursor cursor = db.query("RATS", new String[]{"DATE", "LOCATION", "BOROUGH"}, null, null, null, null, "DATE DESC");
        if (cursor.moveToFirst()) {
            infoLine = cursor.getString(cursor.getColumnIndex("DATE")) + "   "
                    + cursor.getString(cursor.getColumnIndex("LOCATION")) + "   "
                    + cursor.getString(cursor.getColumnIndex("BOROUGH"));
            infoList.add(infoLine);
            while (!cursor.isAfterLast()) {
                infoLine = cursor.getString(cursor.getColumnIndex("DATE")) + "   "
                        + cursor.getString(cursor.getColumnIndex("LOCATION")) + "   "
                        + cursor.getString(cursor.getColumnIndex("BOROUGH"));
                infoList.add(infoLine);
                cursor.moveToNext();
            }
            cursor.close();
            return infoList.toArray(new String[infoList.size()]);
        } else {
            throw new java.util.NoSuchElementException("Database appears to be empty\n");
        }
    }

/**
    public void updateEntry(String userName, String password) {
        // Define the updated row content.
        ContentValues updatedValues = new ContentValues();
        // Assign values for each row.
        updatedValues.put("USERNAME", userName);
        updatedValues.put("PASSWORD", password);

        String where = "USERNAME = ?";
        db.update("LOGIN", updatedValues, where, new String[]{userName});
    }
 **/

//    // https://stackoverflow.com/questions/10111166/get-all-rows-from-sqlite
//    public ArrayList<String[]> listRatSightingData() {
//        Cursor cursor = db.rawQuery("select * from table", null);
//        if (cursor.moveToFirst()) {
//            while(!cursor.isAfterLast()) {
//                String[] info = cursor
//            }
//        }
//    }

    /**
     * This should return an arraylist containing String arrays of all the data for each rat sighting.
     */
    public ArrayList<String[]> listRatSightingData() {
        Integer i = 0;
        while (i <= 100) {
            String searchIndex = i.toString();
            ratSightingList.add(getSingleEntry(searchIndex));
        }
        return ratSightingList;
    }

    /**
     * We've got to figure out how to call this method only once, the first time the app is opened
     * or if rats.db is empty.
     */
    public void buildRatDataBaseFromFile() {
//        db = open();
        csvRatData = new CSVReader();
        ArrayList<String[]> ratData = csvRatData.csvToArray();
//        ArrayList<String[]> existingRatData = csvRatData.getRatData();
        System.out.println(ratData);

        for (String[] line: ratData) {
            insertEntry(line[0], line[1], line[2], line[3], line[4], line[5], line[6], line[7]);
        }
    }

    /**
     * Method to check if the RATS table is empty
     * @return true if table is empty
     */
    public boolean tableEmpty() {
        boolean isEmpty = true;
        String query = "select exists(select 1 from " + "RATS" + ");";
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        if (count == 1) {
            isEmpty = false;
        }
        cursor.close();
        return isEmpty;
    }

}

