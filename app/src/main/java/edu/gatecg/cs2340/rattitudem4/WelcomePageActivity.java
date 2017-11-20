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
    private static boolean dataBaseLoaded;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);
        if (!dataBaseLoaded) {
            dbManager = new RatReportManager();
            dataBaseLoaded = true;
        }
    }

    /** 
     * Called when "log out" button is pressed
     * @param view is the view class used to display log out button
     */
    public void goBackBtn(View view) {
        Intent intent = new Intent(this, MainLoginActivity.class);
        startActivity(intent);
    }
    /** 
     * Called when "report rat" button is pressed
     * @param view is the view class used to display report rat button
     */ 
    public void ratReportsButton(View view) {
        Intent intent = new Intent(this, RatReportListActivity.class);
        startActivity(intent);
    }
    /**
    * Called when "new rat report" button is pressed
    * @param view is the view class used display new rat report
    */
    public void addNewRatReportButton(View view) {
        Intent intent = new Intent(this, AddRatReportActivity.class);
        startActivity(intent);
    }

    /**
     * Called when "view map" button is pressed
     * @param view is the view class used to display map activity
     */
    public void ratReportMapsButton(View view) {
        Intent intent = new Intent(this, PickADateForMapActivity.class);
        startActivity(intent);
    }

    /**
     * Called when "rat chart" button is pressed
     * @param view is the view class used to display chart activity
     */
    public void ratReportChartsButton(View view) {
        Intent intent = new Intent(this, ChartDateActivity.class);
        startActivity(intent);
    }
}
