package edu.gatecg.cs2340.rattitudem4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.tasks.Tasks;

public class RatReportListActivity extends AppCompatActivity {
    ListView list;
    private RatReportManager dbManager;
    String[] s = new String[] {"Hello", "Testing", "Maybe", "This", "is"
            , "working", "Checking", "Out", "The", "Scroll", "Bar", "?"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rat_report);
        list = (ListView) findViewById(R.id.rat_report_list);
        ArrayAdapter ad = new ArrayAdapter(RatReportListActivity.this,
                android.R.layout.simple_expandable_list_item_1,
                OptionsActivity.dbManager.getShortStringList().toArray());
        list.setAdapter(ad);
        //TODO We need to get the rat reports from the database
        // and we need to extract the Unique ID for each rat report, the Burrough,
        // and the date.
        //Once an item is clicked it will open RatReportDetailActivity
        //that will show all details for that rat report.
    }


    public void ratReportListBackButton(View view) {
        finish();
    }
}
