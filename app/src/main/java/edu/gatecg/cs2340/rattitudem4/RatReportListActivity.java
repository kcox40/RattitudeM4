package edu.gatecg.cs2340.rattitudem4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class RatReportListActivity extends AppCompatActivity {
    ListView list;
    String[] s = new String[] {"Hello", "Testing", "Maybe", "This", "is", "working"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rat_report);
        list = (ListView) findViewById(R.id.rat_report_list);
        ArrayAdapter ad = new ArrayAdapter(RatReportListActivity.this, android.R.layout.simple_expandable_list_item_1, s);
        list.setAdapter(ad);
    }

    public void ratReportBackButton(View view) {
        finish();
    }
}
