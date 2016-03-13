package com.example.admin.manipulator.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.admin.manipulator.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

import model.Integrator;
import model.Point;

public class MainActivity extends AppCompatActivity {
    LineChart mLineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLineChart = (LineChart) findViewById(R.id.chart);
        draw();
    }

    private List<Point> integrate() {
        return new Integrator().integrate();
    }

    private void draw() {
        DataHolder dataHolder = new DataHolder(integrate());
        LineData data = new LineData(dataHolder.getLabels(), getDataSet(dataHolder));
        mLineChart.setData(data);
        mLineChart.invalidate(); // refresh
    }

    private LineDataSet getDataSet(DataHolder dataHolder) {
        return new LineDataSet(dataHolder.getEntries(), "");
    }

    private static class DataHolder {
        private List<Entry> mEntries;
        private List<String> mLabels;

        public DataHolder(List<Point> points) {
            mEntries = new ArrayList<>();
            mLabels = new ArrayList<>();

            int position = 0;

            for (Point point: points) {
                mEntries.add(new Entry(point.getY(), position));
                mLabels.add("");
                position++;
            }
        }

        public List<Entry> getEntries() {
            return mEntries;
        }

        public List<String> getLabels() {
            return mLabels;
        }
    }
}
