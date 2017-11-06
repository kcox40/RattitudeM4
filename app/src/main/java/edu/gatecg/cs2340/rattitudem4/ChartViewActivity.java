package edu.gatecg.cs2340.rattitudem4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ChartViewActivity extends AppCompatActivity {

    private DataSetExample data = new DataSetExample();
    int startMonth;
    int stopMonth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart_view);

        Bundle bundle = getIntent().getExtras();
        startMonth = bundle.getInt("StartDate");
        stopMonth = bundle.getInt("StopDate");

        LineChart lineChart = (LineChart) findViewById(R.id.chart);

        //get ratReports
        List<RatReport> ratReports=  WelcomePageActivity.dbManager.getList();
        List<Entry> entries = convertDataSetToEntry(ratReports);
        //List<Entry> entries = convertDataSetToEntry(data.getDataList());

        LineDataSet dataset = new LineDataSet(entries, ""+ startMonth + stopMonth + "");

        LineData data = new LineData(dataset);
        dataset.setColors(ColorTemplate.COLORFUL_COLORS); //

        dataset.setDrawFilled(true);

        lineChart.setData(data);
        lineChart.animateY(5000);

        lineChart.getDescription().setText("Rat Reports Per Month");
    }

    public void back(View view) {
        finish();
    }

    //private List<Entry> convertDataSetToEntry(List<Data> data)
    private List<Entry> convertDataSetToEntry(List<RatReport> data) {
        List<Entry> entries = new ArrayList<>();
        int[] monthCount = new int[stopMonth - startMonth + 1];
        Map<Integer, Integer> months = new HashMap<Integer, Integer>();

//        for (Data d : data) {
//            entries.add(new Entry(d.x, d.y));
//        }


        for (RatReport r: data) {
            String fullDate = r.getDate();
            String[] dateParts = fullDate.split("/");
            int month = Integer.parseInt(dateParts[0]);
            if (month >= startMonth && month >= stopMonth) {
                Integer oldCount = months.get(month);
                if ( oldCount == null ) {
                    oldCount = 0;
                }
                months.put(month, oldCount + 1);
            }
        }

        int j = 0;
        for (int i = startMonth; i < stopMonth + 1; i++) {
            monthCount[j] = months.get(i);
            j++;
        }

        for (int i = startMonth; i < stopMonth + 1; i++) {
            //
        }




        return entries;
    }

    public class DataSetExample {
        private List<Data> dataList = new ArrayList<>();

        public DataSetExample() {
            generateDummyData();
        }

        private void generateDummyData() {
            Random rand = new Random();

            for (int i = 0; i < 10; ++i) {
                dataList.add(new Data(rand.nextInt(10), i));
            }

            Collections.sort(dataList);
        }

        public List<Data> getDataList() { return dataList; }
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
}
