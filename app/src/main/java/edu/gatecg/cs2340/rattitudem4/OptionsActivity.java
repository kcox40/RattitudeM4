package edu.gatecg.cs2340.rattitudem4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class OptionsActivity extends AppCompatActivity {
    public static RatReportManager dbManager;
    private static boolean loaded;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        if (!loaded) {
            dbManager = new RatReportManager();
            loaded = true;
        }
    }
    public void ratReportsListButton(View view) {
        Intent intent = new Intent(this, RatReportListActivity.class);
        startActivity(intent);
    }

    public void optionsBackToWelcomeButton(View view) {
        finish();
    }
}
