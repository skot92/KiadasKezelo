package skot92.hu.unideb.hu.kiadaskezelo.ui.activity.all;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import skot92.hu.unideb.hu.kiadaskezelo.R;
import skot92.hu.unideb.hu.kiadaskezelo.core.entity.ExpenseDetailsEntity;
import skot92.hu.unideb.hu.kiadaskezelo.service.ExpenseDetailsService;

/**
 * Created by skot9 on 2015. 11. 16..
 */
public class ExpenseDetailsActivity extends AppCompatActivity {

    private List<ExpenseDetailsEntity> detailsEntities;
    private ArrayAdapter<ExpenseDetailsEntity> adapter;
    private ExpenseDetailsService expenseDetailsService;
    private ListView lv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_expense_list_view);

        detailsEntities = new ArrayList<>();
        expenseDetailsService = new ExpenseDetailsService(getApplicationContext());
        int i = getIntent().getIntExtra("index",0);
        detailsEntities = expenseDetailsService.findById(i);
        lv = (ListView) findViewById(R.id.list_view);
        // Adding items to listview
        adapter = new ArrayAdapter<ExpenseDetailsEntity>(this, R.layout.list_item, R.id.product_name, detailsEntities);
        lv.setAdapter(adapter);

    }
}
