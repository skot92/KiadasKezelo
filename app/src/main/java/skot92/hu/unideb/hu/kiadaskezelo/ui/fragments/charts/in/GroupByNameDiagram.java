package skot92.hu.unideb.hu.kiadaskezelo.ui.fragments.charts.in;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.animation.AnimationEasing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.Legend.LegendPosition;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.Highlight;
import com.github.mikephil.charting.utils.PercentFormatter;

import java.util.ArrayList;
import java.util.List;

import skot92.hu.unideb.hu.kiadaskezelo.R;
import skot92.hu.unideb.hu.kiadaskezelo.core.dao.InComeDAO;
import skot92.hu.unideb.hu.kiadaskezelo.core.entity.InComeEntity;
import skot92.hu.unideb.hu.kiadaskezelo.service.InComeService;


/**
 * Created by skot9 on 2015. 11. 13..
 */
public class GroupByNameDiagram extends Fragment implements OnChartValueSelectedListener{

    InComeService inComeService;
    private PieChart mChart;
    private View v;
    private TextView tvX, tvY;

    private InComeDAO inComeDAO;
    private List<InComeEntity> inComeEntities;

    //private Typeface tf;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.activity_piechart, container, false);
        inComeDAO = new InComeDAO(getActivity().getApplicationContext());
        inComeEntities = new ArrayList<>();
        inComeEntities = inComeDAO.getInComeGroubByName();

        inComeService = new InComeService(getActivity().getApplicationContext());

        tvX = (TextView) v.findViewById(R.id.tvXMax);
        tvY = (TextView) v.findViewById(R.id.tvYMax);

        mChart = (PieChart)v.findViewById(R.id.chart1);
        mChart.setUsePercentValues(true);
        mChart.setDescription("");

        mChart.setDrawHoleEnabled(true);
        mChart.setHoleColorTransparent(true);

        mChart.setHoleRadius(58f);
        mChart.setTransparentCircleRadius(61f);

        mChart.setDrawCenterText(true);

        mChart.setRotationAngle(0);
        mChart.setRotationEnabled(true);
       // mChart.setHighlightPerTapEnabled(true);

        // mChart.setUnit(" €");
        // mChart.setDrawUnitsInChart(true);

        // add a selection listener
        mChart.setOnChartValueSelectedListener(this);

        setData();

        mChart.animateY(1400, AnimationEasing.EasingOption.EaseInBack);
        mChart.spin(2000, 0, 360);

        Legend l = mChart.getLegend();
        l.setPosition(LegendPosition.RIGHT_OF_CHART);
        l.setXEntrySpace(10f);
        l.setYEntrySpace(5f);
        l.setYOffset(0f);
        return v;
    }

    public static GroupByNameDiagram newInstance(String text) {
        GroupByNameDiagram f = new GroupByNameDiagram();
        Bundle b = new Bundle();
        b.putString("msg", text);
        f.setArguments(b);
        return f;
    }


    private void setData() {


        ArrayList<Entry> yVals1 = new ArrayList<Entry>();
        for (int i = 0; i < inComeEntities.size(); i++) {
            yVals1.add(new Entry(inComeEntities.get(i).getAmount(), i));
        }
        ArrayList<String> xVals = new ArrayList<String>();
        for (int i = 0; i < inComeEntities.size(); i++) {
            xVals.add(i,inComeEntities.get(i).getName());
        }

        PieDataSet dataSet = new PieDataSet(yVals1, "");
        dataSet.setSliceSpace(5f);
        dataSet.setSelectionShift(5f);

        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);
        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);
        dataSet.setSelectionShift(0f);

        PieData data = new PieData(xVals, dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(10f);
        data.setValueTextColor(Color.BLACK);

        mChart.setNoDataTextDescription("Nincs bevétel");
        mChart.setData(data);

        mChart.highlightValues(null);

        mChart.invalidate();
    }


    @Override
    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {

    }

    @Override
    public void onNothingSelected() {
        Log.i("PieChart", "nothing selected");
    }


}
