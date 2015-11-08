package skot92.hu.unideb.hu.kiadaskezelo.ui.activity.charts;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import skot92.hu.unideb.hu.kiadaskezelo.R;
import skot92.hu.unideb.hu.kiadaskezelo.core.dao.InComeDAO;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Map;

public class AllIncomeChartActivity extends AppCompatActivity {

    Map<String,Integer> incomens;
    InComeDAO inComeDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_income_chart);

        inComeDAO = new InComeDAO(getApplicationContext());
        incomens = inComeDAO.findAmountGroupByDate();

        LineChart chart = (LineChart) findViewById(R.id.chart);

        LineData data = new LineData(getXAxisValues(),getDataSet());
        chart.setData(data);
        chart.setDescription("");
        chart.animateXY(2000, 2000);
        chart.invalidate();

        chart.setDragEnabled(true);
        chart.setScaleEnabled(true);
    }

    private ArrayList<LineDataSet> getDataSet() {
        ArrayList<LineDataSet> dataSets = null;

        ArrayList<Entry> valueSet1 = new ArrayList<>();
        int i = 0;
        for (Map.Entry<String, Integer> entry : incomens.entrySet()) {
            BarEntry v1 = new BarEntry(entry.getValue(),i);
            valueSet1.add(v1);
            ++i;
        }


        LineDataSet barDataSet1 = new LineDataSet(valueSet1, "Bevételek");
        barDataSet1.setColor(Color.rgb(0, 155, 0));

        dataSets = new ArrayList<>();
        dataSets.add(barDataSet1);
        return dataSets;
    }

    private ArrayList<String> getXAxisValues() {

        ArrayList<String> xAxis = new ArrayList<>();
        int i = 0;
        for (Map.Entry<String, Integer> entry : incomens.entrySet()) {
            xAxis.add(i,entry.getKey());
            ++i;
        }
        return xAxis;
    }
}
