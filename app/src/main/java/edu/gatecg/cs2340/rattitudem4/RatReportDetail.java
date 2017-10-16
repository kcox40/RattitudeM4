package edu.gatecg.cs2340.rattitudem4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class RatReportDetail extends AppCompatActivity {

    private RatReport selectedRatReport;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rat_report_detail);
        Bundle bundle = getIntent().getExtras();
        String ratReport = bundle.getString("RatReport");
        String reportId = ratReport.substring(ratReport.lastIndexOf(" ")+1);
        List<RatReport> ratReports = OptionsActivity.dbManager.getList();
        for (RatReport r : ratReports) {
            if (Integer.toString(r.getId()).equals(reportId)) {
                selectedRatReport = r;
            }
        }
        TextView idView = (TextView) findViewById(R.id.id);
        TextView dateView = (TextView) findViewById(R.id.date);
        TextView locationView = (TextView) findViewById(R.id.locationType);
        TextView zipView = (TextView) findViewById(R.id.zip);
        TextView addressView = (TextView) findViewById(R.id.address);
        TextView cityView = (TextView) findViewById(R.id.city);
        TextView boroughView = (TextView) findViewById(R.id.borough);
        TextView latitudeView = (TextView) findViewById(R.id.latitude);
        TextView longitudeView = (TextView) findViewById(R.id.longitude);

       idView.setText(Integer.toString(selectedRatReport.getId()));
        dateView.setText(selectedRatReport.getDate());
        locationView.setText(selectedRatReport.getLocationType());
        zipView.setText(Integer.toString(selectedRatReport.getIncidentZip()));
        addressView.setText(selectedRatReport.getAddress());
        cityView.setText(selectedRatReport.getCity());
        boroughView.setText(selectedRatReport.getBorough());
        latitudeView.setText(Double.toString(selectedRatReport.getLatitude()));
        longitudeView.setText(Double.toString(selectedRatReport.getLongitude()));
    }

    public void backBtn(View view) {
        finish();
    }
}
