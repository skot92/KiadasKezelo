package skot92.hu.unideb.hu.kiadaskezelo.ui.activity;

import android.content.Intent;
import android.os.Bundle;


import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import skot92.hu.unideb.hu.kiadaskezelo.R;
import skot92.hu.unideb.hu.kiadaskezelo.core.dao.BalanceDAO;
import skot92.hu.unideb.hu.kiadaskezelo.core.helper.DatabaseHelper;
import skot92.hu.unideb.hu.kiadaskezelo.service.BalanceService;
import skot92.hu.unideb.hu.kiadaskezelo.ui.activity.all.AllIncomeActivity;
import skot92.hu.unideb.hu.kiadaskezelo.ui.activity.charts.AllIncomeChartActivity;
import skot92.hu.unideb.hu.kiadaskezelo.ui.fragments.MainDiagramsFragment;


public class MainActivity extends AppCompatActivity {

    private Button btnNewExpense;
    private Button btnAllExpense;
    private Button btnNewInCome;
    private Button btnAllIncome;
    private Button btnAllIncomeChart;
    private TextView tBalance;

    private BalanceService balanceService;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        balanceService = new BalanceService(getApplicationContext());

        btnNewExpense = (Button) findViewById(R.id.btnNewExpensives);
        btnAllExpense = (Button) findViewById(R.id.btnExpensives);
        btnNewInCome = (Button) findViewById(R.id.btnNewInCome);
        btnAllIncome = (Button) findViewById(R.id.btnInComeings);
        btnAllIncomeChart = (Button) findViewById(R.id.btnAllInVomeCharts);
        tBalance = (TextView) findViewById(R.id.tBalance);

        tBalance.setText(String.valueOf(balanceService.findBalance()));
        controll();
    }

    public void controll() {

        btnNewInCome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, InComeActivity.class);
                startActivity(intent);
            }
        });

        btnNewExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewExpenseActivity.class);
                startActivity(intent);
            }
        });

        btnAllIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AllIncomeActivity.class);
                startActivity(intent);
            }
        });

        btnAllIncomeChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainDiagramsFragment.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        tBalance.setText(String.valueOf(balanceService.findBalance()));
    }


}
