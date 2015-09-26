package skot92.hu.unideb.hu.kiadaskezelo.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import skot92.hu.unideb.hu.kiadaskezelo.R;
import skot92.hu.unideb.hu.kiadaskezelo.core.Expense;
import skot92.hu.unideb.hu.kiadaskezelo.core.ExpensesDataSource;

/**
 * Created by skot9 on 2015. 09. 25..
 */
public class NewActivity extends AppCompatActivity {

    private ExpensesDataSource datasource;

    private EditText name;
    private EditText description;
    private EditText price;
    private EditText date;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        name = (EditText) findViewById(R.id.editName);
        description = (EditText) findViewById(R.id.editDescreption);
        price = (EditText) findViewById(R.id.editPrice);
        date = (EditText) findViewById(R.id.editDate);


        datasource = new ExpensesDataSource(this);
        datasource.open();


    }


    public void onClickNew(View view) {
        Expense comment = null;
        try {
            comment = datasource.createBuy(name.getText().toString(), description.getText().toString(),
                    Integer.parseInt(price.getText().toString()),
                    date.getText().toString());
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Hiba történt", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {
        datasource.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        datasource.close();
        super.onPause();
    }

}
