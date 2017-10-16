package edu.gatecg.cs2340.rattitudem4;

import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Russell on 10/13/2017.
 */

public class RatReportManager {
    //private ArrayList<Map<String, String>> ratReports;
    private static List<RatReport> ratReports;
    private static DatabaseReference ratDBRef;
    private static Query ratQuery;
    public RatReportManager() {
        //ratReports = new ArrayList<Map<String, String>>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        ratDBRef = database.getReference();
        ratReports = new ArrayList<RatReport>();
        ratQuery = ratDBRef.orderByKey().limitToLast(50);
        queryInit();
       // databaseInit();
    }

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
//    private void databaseInit() {
//        ratDBRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot databaseSnapshot) {
//                for (DataSnapshot reportSnapshot: databaseSnapshot.getChildren()) {
//                    Log.d("PRINTINGDB", (String) reportSnapshot.child("City").getValue());
//                    ratReports.add((String) reportSnapshot.child("City").getValue());
//                }
////                GenericTypeIndicator<ArrayList<Map<String, String>>> typeIndicator = new GenericTypeIndicator<ArrayList<Map<String, String>>>() {};
////                ratReports = (List<Object>) dataSnapshot.getValue();
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
////                Log.w(TAG, "postComments:onCancelled", databaseError.toException());
////                Toast.makeText(mContext, "Failed to load comments.",
////                        Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

    public void printDatabase() {
        Log.d("Array Size", String.valueOf(ratReports.size()));
        for (RatReport report : ratReports) {
            Log.d("Printing", report.toString());
        }
    }

    public List<RatReport> getList() {
        return ratReports;
    }

    public List<String> getStringList() {
        List<String> stringList = new ArrayList<String>();
        for (RatReport report : ratReports) {
            stringList.add(report.toString());
        }
        return stringList;
    }

    public List<String> getShortStringList() {
        List<String> shortList = new ArrayList<>();
        for (RatReport report : ratReports) {
            String line = new String();
            line = line + report.getDate() + report.getBorough();
            shortList.add(line);
        }
        return shortList;
    }
}
