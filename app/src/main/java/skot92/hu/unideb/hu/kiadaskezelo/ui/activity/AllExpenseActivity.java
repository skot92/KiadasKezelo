package skot92.hu.unideb.hu.kiadaskezelo.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import skot92.hu.unideb.hu.kiadaskezelo.R;
import skot92.hu.unideb.hu.kiadaskezelo.core.expense.Expense;
import skot92.hu.unideb.hu.kiadaskezelo.core.expense.ExpensesDataSource;


public class AllExpenseActivity extends Activity {

    private ExpensesDataSource datasource;

    private ArrayAdapter<Expense> adapter;
    private ListView listview;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_expense);

        datasource = new ExpensesDataSource(this);
        datasource.open();

        listview = (ListView) findViewById(R.id.AllList);
        adapter = new ArrayAdapter<Expense>(this,
                android.R.layout.simple_list_item_1, datasource.getAllBuys());
        listview.setAdapter(adapter);
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
