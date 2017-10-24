package edu.gatecg.cs2340.rattitudem4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView;

import com.google.android.gms.tasks.Tasks;
/** 
 * @author team 57 
 * @version 1 
 */ 

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
                WelcomePageActivity.dbManager.getShortStringList().toArray());
        list.setAdapter(ad);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapter, View v, int position, long id) {
                String selItem = (String) adapter.getItemAtPosition(position);
                Log.d("check on click", selItem.toString());
                Intent intent = new Intent(RatReportListActivity.this, RatReportDetail.class);
                intent.putExtra("RatReport", selItem);
                startActivity(intent);
            }
        });
    }
    
    /** 
     * back putton from the rat report page 
     * @param view looking at the backbutton for the rat report view 
     */ 

    public void ratReportListBackButton(View view) {
        finish();
    }
}
