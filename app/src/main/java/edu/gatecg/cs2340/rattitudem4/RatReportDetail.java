package edu.gatecg.cs2340.rattitudem4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

/**
* @author team 57
* @version v1.0
* this class displays the rat report details
*/

public class RatReportDetail extends AppCompatActivity {

    private RatReport selectedRatReport;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rat_report_detail);
        Bundle bundle = getIntent().getExtras();
        String ratReport = bundle.getString("RatReport");
        String reportId = ratReport.substring(ratReport.lastIndexOf(" ")+1);
        List<RatReport> ratReports = WelcomePageActivity.dbManager.getList();
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

       idView.setText("ID: " + Integer.toString(selectedRatReport.getId()));
        dateView.setText("Date: " + selectedRatReport.getDate());
        locationView.setText("Location Type: " + selectedRatReport.getLocationType());
        zipView.setText("Incident Zip: " + Integer.toString(selectedRatReport.getIncidentZip()));
        addressView.setText("Address: " + selectedRatReport.getAddress());
        cityView.setText("City: " + selectedRatReport.getCity());
        boroughView.setText("Borough: " + selectedRatReport.getBorough());
        latitudeView.setText("Lattitude: " + Double.toString(selectedRatReport.getLatitude()));
        longitudeView.setText("Longitude: " + Double.toString(selectedRatReport.getLongitude()));
    }

    /**
     * Closes view to return back to OptionsActivity in the stack
     * @param view the current view (RatReportDetail)
     */
    public void backBtn(View view) {
        finish();
    }
}
