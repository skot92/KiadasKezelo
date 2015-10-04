package skot92.hu.unideb.hu.kiadaskezelo.ui.activity;

import android.content.Intent;
import android.os.Bundle;


import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import skot92.hu.unideb.hu.kiadaskezelo.R;


public class MainActivity extends AppCompatActivity {

    private Button btnNewExpense;
    private Button btnAllExpense;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNewExpense = (Button) findViewById(R.id.btnNewExpensives);
        btnAllExpense = (Button) findViewById(R.id.btnExpensives);
        controll();
    }

    public void controll() {
        btnNewExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewExpenseActivity.class);
                startActivity(intent);
            }
        });

        btnAllExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AllExpenseActivity.class);
                startActivity(intent);
            }
        });

    }


}
