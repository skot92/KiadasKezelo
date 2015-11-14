package skot92.hu.unideb.hu.kiadaskezelo.ui.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.Map;

import skot92.hu.unideb.hu.kiadaskezelo.R;
import skot92.hu.unideb.hu.kiadaskezelo.service.InComeService;

/**
 * Created by skot9 on 2015. 11. 13..
 */
public class FirstFragment extends Fragment {

    Map<String,Integer> incomens;
    InComeService inComeService;
    private View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.activity_all_income_chart, container, false);

        inComeService = new InComeService(getActivity().getApplicationContext());
        incomens = inComeService.findAmountGroupByDate();

        LineChart chart = (LineChart)v.findViewById(R.id.chart);

        LineData data = new LineData(getXAxisValues(),getDataSet());
        chart.setData(data);
        chart.setDescription("");
        chart.animateXY(2000, 2000);
        chart.invalidate();

        chart.setDragEnabled(true);
        chart.setScaleEnabled(true);
        return v;

    }

    public static FirstFragment newInstance(String text) {

        FirstFragment f = new FirstFragment();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
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
