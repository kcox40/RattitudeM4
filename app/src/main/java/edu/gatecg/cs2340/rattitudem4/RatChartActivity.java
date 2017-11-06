package edu.gatecg.cs2340.rattitudem4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class RatChartActivity extends AppCompatActivity {

    ArrayList<Integer> monthRange = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rat_chart);
        Intent intent = getIntent();

        String startDateString = intent.getStringExtra("dateOne");
        String[] startDateParts = startDateString.split("/");
        int startMonthInt = Integer.parseInt(startDateParts[0]);

        String endDateString = intent.getStringExtra("dateTwo");
        String[] endDateParts = endDateString.split("/");
        int endMonthInt = Integer.parseInt(endDateParts[0]);

        for (int i = startMonthInt; i < endMonthInt + 1; i++) {
            monthRange.add(i - 1);
        }

        List<RatReport> reports =(List<RatReport>) WelcomePageActivity.dbManager.getDateRange(startDateString, endDateString);
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss a");
        Date startDate = null;
        Date endDate = null;
        Date dateOfReport = null;
        try {
            startDate = df.parse(startDateString);
            endDate = df.parse(endDateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //int[] monthCount = new int[stopMonth - startMonth + 1];
        Map<Integer, Integer> months = new HashMap<Integer, Integer>();

        for (RatReport r : reports) {
            String date = r.getDate();
            String[] dateParts = date.split("/");
            int month = Integer.parseInt(dateParts[0]);
            Integer oldCount = months.get(month);
            if ( oldCount == null ) {
                oldCount = 0;
            }
            months.put(month, oldCount + 1);
        }

        LineChart lineChart = (LineChart) findViewById(R.id.chart);

        //List<Entry> entries = convertDataSetToEntry(data.getDataList());
        List<Entry> entries = convertDataSetToEntry(months);

        LineDataSet dataset = new LineDataSet(entries, "# of Rat Reports");

        //Log.d("APP", "Made dataset with : " + entries.size());

        LineData data = new LineData(dataset);
        dataset.setColors(ColorTemplate.COLORFUL_COLORS); //

        dataset.setDrawFilled(true);

        lineChart.setData(data);
        lineChart.animateY(5000);

        lineChart.getDescription().setText("Rat Reports Per Month");
    }

    private List<Entry> convertDataSetToEntry(Map<Integer, Integer> data) {
        List<Entry> entries = new ArrayList<>();

        for (int i : monthRange) {
            int month = i + 1;
            int value = 0;
            if (data.get(month) != null) {
                value = data.get(month);
            }
            entries.add(new Entry(month, value));
        }

        return entries;
    }


    public class Data implements Comparable {
        public int x;
        public int y;

        public Data(int px, int py) {
            x = px;
            y = py;
        }

        @Override
        public int compareTo(Object o) {
            Data d = (Data) o;
            return x - d.x;

        }
    }

    public void back(View view) {
        finish();
    }


}
