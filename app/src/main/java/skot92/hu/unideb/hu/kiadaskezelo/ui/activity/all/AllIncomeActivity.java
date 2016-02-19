package skot92.hu.unideb.hu.kiadaskezelo.ui.activity.all;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import skot92.hu.unideb.hu.kiadaskezelo.R;
import skot92.hu.unideb.hu.kiadaskezelo.core.entity.InComeEntity;
import skot92.hu.unideb.hu.kiadaskezelo.service.InComeService;
import skot92.hu.unideb.hu.kiadaskezelo.ui.Adapter.AllIncomeAdapter;

public class AllIncomeActivity extends AppCompatActivity {

    private ListView lv;
    EditText inputSearch;
    InComeService inComeService;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_income_list_view);

        inComeService = new InComeService(getApplicationContext());

        lv = (ListView) findViewById(R.id.list_view);
        inputSearch = (EditText) findViewById(R.id.inputSearch);

        AllIncomeAdapter adapter = new AllIncomeAdapter(this, inComeService.findInComes(""));
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),""+i,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        AllIncomeAdapter adapter;


        switch (item.getItemId())
        {
            case R.id.income_sort_by_amount:
                adapter = new AllIncomeAdapter(this, inComeService.findInComes("amount"));
                adapter.notifyDataSetChanged();
                lv.setAdapter(adapter);
                return true;

            case R.id.income_sort_by_date:
                adapter = new AllIncomeAdapter(this, inComeService.findInComes("date"));
                adapter.notifyDataSetChanged();
                lv.setAdapter(adapter);
                return true;

            case R.id.income_sort_by_name:
                adapter = new AllIncomeAdapter(this, inComeService.findInComes("name"));
                adapter.notifyDataSetChanged();
                lv.setAdapter(adapter);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
