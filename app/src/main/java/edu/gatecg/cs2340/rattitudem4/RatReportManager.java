package edu.gatecg.cs2340.rattitudem4;

import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Russell on 10/13/2017.
 */

public class RatReportManager {
    private ArrayList<Map> ratReports;
    private DatabaseReference ratDBRef;
    public RatReportManager() {
        ratReports = new ArrayList<>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        ratDBRef = database.getReference();
        databaseInit();
    }

    private void databaseInit() {
        ratDBRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//                GenericTypeIndicator<ArrayList<Map>> typeIndicator = new GenericTypeIndicator<ArrayList<Map>>() {};
//                ratReports = dataSnapshot.getValue(typeIndicator);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
//                Log.w(TAG, "postComments:onCancelled", databaseError.toException());
//                Toast.makeText(mContext, "Failed to load comments.",
//                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void printDatabase() {
        for (Map o : ratReports) {
            System.out.println(o.get("City"));
        }
    }
}
