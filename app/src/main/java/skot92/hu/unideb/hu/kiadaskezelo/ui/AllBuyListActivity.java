package skot92.hu.unideb.hu.kiadaskezelo.ui;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.util.List;

import skot92.hu.unideb.hu.kiadaskezelo.R;
import skot92.hu.unideb.hu.kiadaskezelo.core.Expense;
import skot92.hu.unideb.hu.kiadaskezelo.core.ExpensesDataSource;

/**
 * Created by skot9 on 2015. 09. 25..
 */
public class AllBuyListActivity extends ListActivity {
    private ExpensesDataSource datasource;
    private ArrayAdapter<Expense> listAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        datasource = new ExpensesDataSource(this);
        datasource.open();

        List<Expense> values = datasource.getAllBuys();

        ArrayAdapter<Expense> adapter = new ArrayAdapter<Expense>(this,
                android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);
    }
}