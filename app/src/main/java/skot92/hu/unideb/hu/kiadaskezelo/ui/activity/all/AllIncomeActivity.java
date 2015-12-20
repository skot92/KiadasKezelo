package skot92.hu.unideb.hu.kiadaskezelo.ui.activity.all;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import skot92.hu.unideb.hu.kiadaskezelo.R;
import skot92.hu.unideb.hu.kiadaskezelo.core.entity.InComeEntity;
import skot92.hu.unideb.hu.kiadaskezelo.service.InComeService;

public class AllIncomeActivity extends AppCompatActivity {

    private ListView lv;
    ArrayAdapter<InComeEntity> adapter;
    EditText inputSearch;
    InComeService inComeService;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_income_list_view);

        inComeService = new InComeService(getApplicationContext());

        List<InComeEntity> names = inComeService.findInComes();

        lv = (ListView) findViewById(R.id.list_view);
        inputSearch = (EditText) findViewById(R.id.inputSearch);

        // Adding items to listview
        adapter = new ArrayAdapter<InComeEntity>(this, R.layout.list_item, R.id.product_name, names);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),""+i,Toast.LENGTH_SHORT).show();
            }
        });

        inputSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                AllIncomeActivity.this.adapter.getFilter().filter(cs);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
            }
        });
    }
}
