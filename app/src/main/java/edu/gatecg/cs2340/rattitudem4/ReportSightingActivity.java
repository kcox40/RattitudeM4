package edu.gatecg.cs2340.rattitudem4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by dalton on 10/18/17.
 */

public class ReportSightingActivity extends AppCompatActivity {
    private EditText dateField;
    private EditText zipcodeField;
    private EditText addressField;
    private EditText latitudeField;
    private EditText longitudeField;
    private Spinner locationTypeSpinner;
    private Spinner citySpinner;
    private Spinner boroughSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rat_report);

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

        //TODO: Add OnClick Listeners for buttons

        //TODO: Do something to set data from text fields

        //TODO: Do something to set data from spinners
    }
}
