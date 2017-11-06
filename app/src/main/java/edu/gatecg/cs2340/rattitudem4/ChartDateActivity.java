package edu.gatecg.cs2340.rattitudem4;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ChartDateActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_chart_date);
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
        SimpleDateFormat f = new SimpleDateFormat("dd-MMM-yyyy");
        long milliseconds = 0;
        try {
            Date d = f.parse("31-December-2016");
            milliseconds = d.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        datePicker.setMinDate(milliseconds);
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

    public void seeChartButton(View view) {
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

        Intent intent = new Intent(this, RatChartActivity.class);

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
