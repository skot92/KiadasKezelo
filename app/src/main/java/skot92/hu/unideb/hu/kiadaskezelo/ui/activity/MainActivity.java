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


public class MainActivity extends AppCompatActivity {

    private Button btnNewExpense;
    private Button btnAllExpense;
    private Button btnNewInCome;
    private TextView tBalance;

    private BalanceDAO balanceDAO;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        balanceDAO = new BalanceDAO(getApplicationContext());

        btnNewExpense = (Button) findViewById(R.id.btnNewExpensives);
        btnAllExpense = (Button) findViewById(R.id.btnExpensives);
        btnNewInCome = (Button) findViewById(R.id.btnNewInCome);
        tBalance = (TextView) findViewById(R.id.tBalance);

        tBalance.setText(String.valueOf(balanceDAO.getBalance()));
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
    }

    @Override
    public void onResume() {
        super.onResume();
        tBalance.setText(String.valueOf(balanceDAO.getBalance()));
    }


}
