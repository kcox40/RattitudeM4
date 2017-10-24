package edu.gatecg.cs2340.rattitudem4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/** 
 * @author team 57 
 * @version 1 
 */ 

public class WelcomePageActivity extends AppCompatActivity {

    public static RatReportManager dbManager;
    private static boolean loaded;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);
        if (!loaded) {
            dbManager = new RatReportManager();
            loaded = true;
        }
    }

    /** 
     * Called when someone hits the "log out" button 
     * @param view of the goBack button  
     */
    public void goBackBtn(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, MainLoginActivity.class);
        startActivity(intent);
    }
    /** 
     * Called when someone hits the "report rat" button 
     * @param view of the report rat button  
     */ 
    public void ratReportsButton(View view) {
        Intent intent = new Intent(this, RatReportListActivity.class);
        startActivity(intent);
    }

    public void addNewRatReportButton(View view) {
        Intent intent = new Intent(this, AddRatReportActivity.class);
        startActivity(intent);
    }
}
