package skot92.hu.unideb.hu.kiadaskezelo.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import skot92.hu.unideb.hu.kiadaskezelo.R;
import skot92.hu.unideb.hu.kiadaskezelo.ui.activity.chart.PieChartActivity;

/**
 * Created by skot9 on 2016. 03. 21..
 */
public class ChartsFragment extends Fragment {

    private View myFragmentView;
    private Button pieChart;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myFragmentView = inflater.inflate(R.layout.fragment_charts, container, false);
        pieChart = (Button) myFragmentView.findViewById(R.id.btPie);



        pieChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PieChartActivity.class);
                startActivity(intent);
            }
        });
        return myFragmentView;
    }
}
