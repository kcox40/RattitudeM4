package edu.gatecg.cs2340.rattitudem4;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

    public void seeMapsButton(View view) {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss a");
        String date = df.format(myCalendar.getTime());
        String datetwo = df.format(myCalendarTwo.getTime());
        Intent intent = new Intent(this, RatMapsActivity.class);
        startActivity(intent);
    }

    public void mapBackToWelcomeButton(View view) {
        finish();
    }
}

