package edu.gatecg.cs2340.rattitudem4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddRatReportActivity extends AppCompatActivity {
    private EditText dateField;
    private EditText zipcodeField;
    private EditText addressField;
    private EditText latitudeField;
    private EditText longitudeField;
    private Spinner locationTypeSpinner;
    private Spinner citySpinner;
    private Spinner boroughSpinner;

    private int id;
    private String date;
    private String locationType;
    private int incidentZip;
    private String address;
    private String city;
    private String borough;
    private Double latitude;
    private Double longitude;

    private RatReport ratReport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_rat_report);
        dateField = (EditText) findViewById(R.id.editText_date);
        zipcodeField = (EditText) findViewById(R.id.editText_zipcode);
        addressField = (EditText) findViewById(R.id.editText_address);
        latitudeField = (EditText) findViewById(R.id.editText_latitude);
        longitudeField = (EditText) findViewById(R.id.editText_longitude);
        locationTypeSpinner = (Spinner) findViewById(R.id.spinner_locationType);
        citySpinner = (Spinner) findViewById(R.id.spinner_city);
        boroughSpinner = (Spinner) findViewById(R.id.spinner_borough);

        // Set up the adapter to display the allowable location types in the spinner
        ArrayAdapter<String> adapter_locationTypes = new ArrayAdapter(this,android.R.layout.simple_spinner_item, RatReport.allowedLocationTypes);
        adapter_locationTypes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationTypeSpinner.setAdapter(adapter_locationTypes);

        // Set up the adapter to display the allowable cities in the spinner
        ArrayAdapter<String> adapter_cities = new ArrayAdapter(this,android.R.layout.simple_spinner_item, RatReport.allowedCities);
        adapter_cities.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        citySpinner.setAdapter(adapter_cities);

        // Set up the adapter to display the allowable boroughs in the spinner
        ArrayAdapter<String> adapter_boroughs = new ArrayAdapter(this,android.R.layout.simple_spinner_item, RatReport.allowedBoroughs);
        adapter_boroughs.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        boroughSpinner.setAdapter(adapter_boroughs);

        DateFormat df = new SimpleDateFormat("MM/DD/yyyy HH:mm:ss a");
        String date = df.format(Calendar.getInstance().getTime());
    }

    /**
     * Button handler for the add new student button
     * @param view the button
     */
    protected void onAddPressed(View view) {
        Log.d("Edit", "Add Student");
        Model model = Model.getInstance();

        id;
        date = dateField.getText().toString();
        locationType = (String) locationTypeSpinner.getSelectedItem();
        incidentZip = (int) zipcodeField.getText().toString();
        address = addressField.getText().toString();
        city = (String) citySpinner.getSelectedItem();
        borough = (String) boroughSpinner.getSelectedItem();
        latitude = latitudeField.getText().toString();
        longitude = longitudeField.getText().toString();

        ratReport = new RatReport()
        r.setName(nameField.getText().toString());
        _student.setMajor((String) majorSpinner.getSelectedItem());
        _student.setStanding((ClassStanding) standingSpinner.getSelectedItem());

        Log.d("Edit", "Got new student data: " + _student);
        if (!editing) {
            model.addStudent(_student);
        }  else {
            model.replaceStudentData(_student);
        }

        finish();
    }

}
