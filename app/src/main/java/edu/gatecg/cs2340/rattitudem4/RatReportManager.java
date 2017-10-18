package edu.gatecg.cs2340.rattitudem4;

import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Carlos Priddy on 10/13/2017.
 */

public class RatReportManager {
    private static List<RatReport> ratReports;
    private static DatabaseReference ratDBRef;
    private static Query ratQuery;
    public RatReportManager() {
        //Get Database Reference
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        ratDBRef = database.getReference();

        //Create internal database
        ratReports = new ArrayList<RatReport>();

        //Set Query object
        ratQuery = ratDBRef.orderByKey().limitToLast(200);

        //Initialize Query by attaching ValueEventListener
        queryInit();
    }
    /*
        Populates the Rat report database by attaching a ValueEventListener to
        the query
     */
    private void queryInit() {
        ratQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot reportSnapshot: dataSnapshot.getChildren()) {
                    RatReport report = new RatReport();
                    report.setCity((String) reportSnapshot.child("City").getValue());
                    report.setAddress((String) reportSnapshot.child("Incident Address").getValue());
                    report.setId(Integer.parseInt((String) reportSnapshot.child("Unique Key").getValue()));
                    report.setBorough((String) reportSnapshot.child("Borough").getValue());
                    report.setDate((String) reportSnapshot.child("Created Date").getValue());
                    if (!((String) reportSnapshot.child("Incident Zip").getValue()).equals("")) {
                        report.setIncidentZip(Integer.parseInt((String) reportSnapshot.child("Incident Zip").getValue()));
                    }
                    report.setLocationType((String) reportSnapshot.child("Address Type").getValue());
                    if (!((String) reportSnapshot.child("Latitude").getValue()).equals("")) {
                        report.setLatitude(Double.parseDouble((String) reportSnapshot.child("Latitude").getValue()));
                    }

                    if (!((String) reportSnapshot.child("Longitude").getValue()).equals("")) {
                        report.setLongitude(Double.parseDouble((String) reportSnapshot.child("Longitude").getValue()));
                    }

                    ratReports.add(report);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    /*
        Prints full string representations of all objects in the Query
        prints to Log.d for debug purposes
     */
    public void printDatabase() {
        Log.d("Array Size", String.valueOf(ratReports.size()));
        for (RatReport report : ratReports) {
            Log.d("Printing", report.toString());
        }
    }

    /*
        Returns a list of Rat Reports
        @return A list of Rat Reports
     */
    public List<RatReport> getList() {
        return ratReports;
    }

    /*
        Returns full string representations for all rat reports in Query
        @return List of String representations for objects in internal database
     */
    public List<String> getStringList() {
        List<String> stringList = new ArrayList<String>();
        for (RatReport report : ratReports) {
            stringList.add(report.toString());
        }
        return stringList;
    }

    /*
        Returns a short string representation of objects in internal DB
        @return short string representation
     */
    public List<String> getShortStringList() {
        List<String> shortList = new ArrayList<>();
        for (RatReport report : ratReports) {
            String line = new String();
            line = line + report.getDate() + report.getBorough() +" " +  report.getId();
            shortList.add(line);
        }
        return shortList;
    }
}

