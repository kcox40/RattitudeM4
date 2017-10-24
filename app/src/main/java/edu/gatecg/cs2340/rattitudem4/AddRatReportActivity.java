package edu.gatecg.cs2340.rattitudem4;

import android.app.DatePickerDialog;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.zip.ZipEntry;

public class AddRatReportActivity extends AppCompatActivity implements LocationListener {
    Calendar myCalendar;
    EditText dateView;
    EditText timeView;
    Spinner locationSpinner;
    EditText zipView;
    Spinner broughSpinner;
    EditText latitude;
    EditText longitude;
    EditText addressView;
    EditText cityView;
    DatePickerDialog.OnDateSetListener datePick;
    TimePickerDialog.OnTimeSetListener timePick;
    LocationManager lm;
    Location location;
    double longitudeNum;
    double latitudeNum;
    String provider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_rat_report);
        myCalendar = Calendar.getInstance();


        dateView = (EditText) findViewById(R.id.DateText);
        timeView = (EditText) findViewById(R.id.timeText);
        locationSpinner = (Spinner) findViewById(R.id.locationSpinner);
        zipView = (EditText) findViewById(R.id.zipText);
        broughSpinner = (Spinner) findViewById(R.id.broughSpinner);
        latitude = (EditText) findViewById(R.id.latitudeText);
        longitude = (EditText) findViewById(R.id.longitudeText);
        addressView = (EditText) findViewById(R.id.addressText);
        cityView = (EditText) findViewById(R.id.cityText);

        updateLabel();
        updateTimeLabel();

        locationSpinner.setAdapter(new ArrayAdapter<LocationType>
                (this, android.R.layout.simple_spinner_item, LocationType.values()));
        broughSpinner.setAdapter(new ArrayAdapter<Brough>
                (this, android.R.layout.simple_spinner_item, Brough.values()));

        datePick = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };
        timePick = new TimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker view,int hourOfDay, int minute) {
                myCalendar.set(Calendar.HOUR, hourOfDay);
                myCalendar.set(Calendar.MINUTE, minute);
                updateTimeLabel();
            }
        };

    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        dateView.setText(sdf.format(myCalendar.getTime()));
    }
    private void updateTimeLabel() {
        String stringDate = DateFormat.getTimeInstance().format(myCalendar.getTime());

        timeView.setText(stringDate);
    }

    public void dateEditClick(View view) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, datePick, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH));
        DatePicker datePicker = datePickerDialog.getDatePicker();
        datePicker.setMaxDate(myCalendar.getTimeInMillis());
        datePickerDialog.show();
    }
    public void timeEditClick(View view) {
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, timePick,
                myCalendar.get(Calendar.HOUR), myCalendar.get(Calendar.MINUTE), true);
        timePickerDialog.show();
    }

    @Override
    public void onLocationChanged(Location location) {
        latitude.setText(location.getLatitude() + "");
        longitude.setText(location.getLongitude() + "");
    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.d("Latitude","disable");
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d("Latitude","enable");
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d("Latitude","status");
    }

    @Override
    protected void onResume() {
      super.onResume();
        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        provider = lm.getBestProvider(new Criteria(), false);

        if (checkLocationPermission()) {
            if (ContextCompat.checkSelfPermission(this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {

                //Request location updates:
                lm.requestLocationUpdates(provider, 400, 1, this);
            }
            getLocation();
        }

    }

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                new AlertDialog.Builder(this)
                        .setTitle("Requesting Permission")
                        .setMessage("allow device to acess location?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(AddRatReportActivity.this,
                                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION);
                            }
                        })
                        .create()
                        .show();


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            android.Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        //Request location updates:
                        provider = lm.getBestProvider(new Criteria(), false);
                        lm.requestLocationUpdates(provider, 400, 1, this);
                    }

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.

                }
                return;
            }

        }
    }
    public void getLocation() {

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        Location location = lm.getLastKnownLocation(provider);

        onLocationChanged(location);
    }
    public void optionsBackToWelcomeButton(View view) {
        finish();
    }

    public void createRatReport (View view) {
        //RatReport(String date, String locationType,
        //int incidentZip, String address, String city,
        //        String borough, Double latitude, Double longitude)
        DateFormat df = new SimpleDateFormat("MM/DD/yyyy HH:mm:ss a");
        String date = df.format(myCalendar.getInstance().getTime());
        String locationType = locationSpinner.getSelectedItem().toString();
        int incidentZip = 0;
        String address = "";
        String city = "";
        String borough = broughSpinner.getSelectedItem().toString();
        Double latitudeDouble = 0.0;
        Double longitudeDouble = 0.0;
        if (!zipView.getText().toString().equals("")) {
            if (zipView.getText().toString().length() == 5) {
                incidentZip = Integer.parseInt(zipView.getText().toString());
            }
        } else {
            Toast.makeText(getApplicationContext(), "Zip Field Empty", Toast.LENGTH_LONG).show();
            return;
        }
        if (!addressView.getText().toString().equals("")) {
            address = addressView.getText().toString();
        } else {
            Toast.makeText(getApplicationContext(), "Address Field Empty", Toast.LENGTH_LONG).show();
            return;
        }
        if (!cityView.getText().toString().equals("")) {
            city = cityView.getText().toString();
        } else {
            Toast.makeText(getApplicationContext(), "City Field Empty", Toast.LENGTH_LONG).show();
            return;
        }
        if (!latitude.getText().toString().equals("")) {
            latitudeDouble = Double.parseDouble(latitude.getText().toString());
        } else {
            Toast.makeText(getApplicationContext(), "Latitude Field Empty", Toast.LENGTH_LONG).show();
            return;
        }
        if (!latitude.getText().toString().equals("")) {
            longitudeDouble = Double.parseDouble(longitude.getText().toString());
        } else {
            Toast.makeText(getApplicationContext(), "Longitude Field Empty", Toast.LENGTH_LONG).show();
            return;
        }

        new RatReport(date, locationType, incidentZip,address, city, borough, latitudeDouble,
                longitudeDouble);
        finish();
    }

}
