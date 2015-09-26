package skot92.hu.unideb.hu.kiadaskezelo.ui;

import android.content.Intent;
import android.os.Bundle;


import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import skot92.hu.unideb.hu.kiadaskezelo.R;


public class MainActivity extends AppCompatActivity {
    private Button btnNewBuy;
    private Button btnAllList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnNewBuy = (Button) findViewById(R.id.btnNewBuyButton);
        btnAllList = (Button) findViewById(R.id.btnAllList);

        btnNewBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewActivity.class);
                startActivity(intent);
            }
        });

        btnAllList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AllBuyListActivity.class);
                startActivity(intent);
            }
        });

    }


}
