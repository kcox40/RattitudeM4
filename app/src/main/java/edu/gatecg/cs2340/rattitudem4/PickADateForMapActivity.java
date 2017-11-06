package edu.gatecg.cs2340.rattitudem4;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class PickADateForMapActivity extends AppCompatActivity {
    private Calendar myCalendar;
    private Calendar myCalendarTwo;
    private Calendar myCalendarNow;
    DatePickerDialog.OnDateSetListener datePick;
    DatePickerDialog.OnDateSetListener datePickTwo;
    EditText dateView;
    EditText dateViewTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_adate_for_map);
        myCalendar = Calendar.getInstance();
        myCalendarTwo = Calendar.getInstance();
        myCalendarNow = Calendar.getInstance(Locale.US);
        dateView = (EditText) findViewById(R.id.date_text_map);
        dateViewTwo = (EditText) findViewById(R.id.date_text_map_two);
        updateLabel();
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
        datePickTwo = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendarTwo.set(Calendar.YEAR, year);
                myCalendarTwo.set(Calendar.MONTH, monthOfYear);
                myCalendarTwo.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };
    }

    /**
     * picks the date desired to be viewed
     * @param view is the view class used display the date
     */
    public void dateEditClick(View view) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, datePick, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH));
        DatePicker datePicker = datePickerDialog.getDatePicker();
        datePicker.setMaxDate(myCalendarNow.getTimeInMillis());
        datePickerDialog.show();
    }
    /**
     * picks the date desired to be viewed
     * @param view is the view class used display the date
     */
    public void dateEditClickTwo(View view) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, datePickTwo, myCalendarTwo
                .get(Calendar.YEAR), myCalendarTwo.get(Calendar.MONTH),
                myCalendarTwo.get(Calendar.DAY_OF_MONTH));
        DatePicker datePicker = datePickerDialog.getDatePicker();
        datePicker.setMaxDate(myCalendarNow.getTimeInMillis());
        datePickerDialog.show();
    }


    /*
        Updates the Label
     */
    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        dateView.setText(sdf.format(myCalendar.getTime()));
        dateViewTwo.setText(sdf.format(myCalendarTwo.getTime()));
    }

    /**
     * Shows the maps activity
     * @param view
     */
    public void seeMapsButton(View view) {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss a");
        String date = df.format(myCalendar.getTime());
        String dateTwo = df.format(myCalendarTwo.getTime());
        Date startDate = null;
        Date endDate = null;
        Date dateOfReport = null;
        try {
            startDate = df.parse(date);
            endDate = df.parse(dateTwo);
        } catch (ParseException e) {
            e.printStackTrace();
        }
//        String[] dateArray = date.split(" ");
//        String[] dateArrayTwo = dateTwo.split(" ");
//        String[] justDate = dateArray[0].split("/");
//        String[] justDateTwo = dateArrayTwo[0].split("/");
//        int month = Integer.parseInt(justDate[0]);
//        int day = Integer.parseInt(justDate[1]);
//        int year = Integer.parseInt(justDate[2]);
//        int monthTwo = Integer.parseInt(justDateTwo[0]);
//        int dayTwo = Integer.parseInt(justDateTwo[1]);
//        int yearTwo = Integer.parseInt(justDateTwo[2]);
//        Log.d("Date Checking", date);
//        Log.d("Date Checking", dateArray.toString());
//        Log.d("Date Checking", justDate.toString());
//        Log.d("Date Checking", String.valueOf(month));
//        Log.d("Date Checking", String.valueOf(day));
//        Log.d("Date Checking", String.valueOf(year));
        Intent intent = new Intent(this, RatMapsActivity.class);
//        if (year > yearTwo) {
//            Toast.makeText(getApplicationContext(), "The first date must be before the second date", Toast.LENGTH_LONG).show();
//            return;
//        } else if (year < yearTwo) {
//            intent.putExtra("dateOne", date);
//            intent.putExtra("dateTwo", dateTwo);
//            startActivity(intent);
//        }
//
//        if (month > monthTwo) {
//            Toast.makeText(getApplicationContext(), "The first date must be before the second date", Toast.LENGTH_LONG).show();
//            return;
//        } else if (month < monthTwo) {
//            intent.putExtra("dateOne", date);
//            intent.putExtra("dateTwo", dateTwo);
//            startActivity(intent);
//        }
//        if (day > dayTwo) {
//            Toast.makeText(getApplicationContext(), "The first date must be before the second date", Toast.LENGTH_LONG).show();
//            return;
//        } else {
//            intent.putExtra("dateOne", date);
//            intent.putExtra("dateTwo", dateTwo);
//            startActivity(intent);
//        }
        if (startDate.after(endDate)) {
            Toast.makeText(getApplicationContext(), "The Start date must be before the End date", Toast.LENGTH_LONG).show();
            return;
        } else {
            intent.putExtra("dateOne", date);
            intent.putExtra("dateTwo", dateTwo);
            startActivity(intent);
        }

    }

    public void mapBackToWelcomeButton(View view) {
        finish();
    }
}

