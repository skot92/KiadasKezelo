package skot92.hu.unideb.hu.kiadaskezelo.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import skot92.hu.unideb.hu.kiadaskezelo.R;
import skot92.hu.unideb.hu.kiadaskezelo.core.expense.Expense;
import skot92.hu.unideb.hu.kiadaskezelo.core.expense.ExpensesDataSource;

public class NewExpenseActivity extends Activity {


    private EditText name;
    private EditText description;
    private EditText price;
    private EditText date;
    private Button btnNewBuy;

    private ExpensesDataSource datasource;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_expense);;

        name = (EditText) findViewById(R.id.editName);
        description = (EditText) findViewById(R.id.editDescreption);
        price = (EditText) findViewById(R.id.editPrice);
        date = (EditText) findViewById(R.id.editDate);
        btnNewBuy = (Button) findViewById(R.id.btnAddNew);

        datasource = new ExpensesDataSource(this);
        datasource.open();

        btnNewBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Expense comment = datasource.createBuy(
                            name.getText().toString(), description.getText().toString(),
                            Integer.parseInt(price.getText().toString()), date.getText().toString());
                    Toast.makeText(getApplicationContext(), "Siker", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Hiba tortent", Toast.LENGTH_LONG).show();
                }
            }
        });


    }




    @Override
    public void onResume() {
        datasource.open();
        super.onResume();
    }

    @Override
    public void onPause() {
        datasource.close();
        super.onPause();
    }

}