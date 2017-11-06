package edu.gatecg.cs2340.rattitudem4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class DateSelectionActivity extends AppCompatActivity {

    Spinner startMonthSpinner;
    Spinner stopMonthSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_selection_page);

        startMonthSpinner = (Spinner) findViewById(R.id.startMonthSpinner);
        stopMonthSpinner = (Spinner) findViewById(R.id.stopMonthSpinner);

        startMonthSpinner.setAdapter(new ArrayAdapter<Months>
                (this, android.R.layout.simple_spinner_item, Months.values()));
        stopMonthSpinner.setAdapter(new ArrayAdapter<Months>
                (this, android.R.layout.simple_spinner_item, Months.values()));
    }

    public void graphPage(View view) {
        if (startMonthSpinner.getSelectedItemPosition()
                > stopMonthSpinner.getSelectedItemPosition()) {
            Toast.makeText(getApplicationContext(), "Start month must be before " +
                    "or the same as end month", Toast.LENGTH_LONG).show();
            return;
        }
        Intent intent = new Intent(this, ChartViewActivity.class);
        intent.putExtra("StartDate", startMonthSpinner.getSelectedItemPosition());
        intent.putExtra("StopDate", stopMonthSpinner.getSelectedItemPosition());
        startActivity(intent);
    }

    public void back(View view) {
        finish();
    }
}
