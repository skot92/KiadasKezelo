package skot92.hu.unideb.hu.kiadaskezelo.ui.activity.all;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.widget.ExpandableListView;

import java.util.List;

import skot92.hu.unideb.hu.kiadaskezelo.R;
import skot92.hu.unideb.hu.kiadaskezelo.core.entity.ExpenseDetailsEntity;
import skot92.hu.unideb.hu.kiadaskezelo.core.entity.ExpenseEntity;
import skot92.hu.unideb.hu.kiadaskezelo.service.ExpenseDetailsService;
import skot92.hu.unideb.hu.kiadaskezelo.service.ExpenseService;
import skot92.hu.unideb.hu.kiadaskezelo.ui.Adapter.Group;
import skot92.hu.unideb.hu.kiadaskezelo.ui.Adapter.MyExpandableListAdapter;

/**
 * Created by skot9 on 2015. 11. 14..
 */
public class AllExpenseActivity extends AppCompatActivity{

    private  SparseArray<Group> groups = new SparseArray<>();
    private ExpenseService expenseService;
    private ExpenseDetailsService expenseDetailsService;
    private List<ExpenseEntity> expenses;

    private MyExpandableListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_expense_list_view);


        expenseDetailsService = new ExpenseDetailsService(getApplicationContext());
        expenseService = new ExpenseService(getApplicationContext());
        createData();


        ExpandableListView listView = (ExpandableListView) findViewById(R.id.listView);
        adapter = new MyExpandableListAdapter(this,
                groups);
        listView.setAdapter(adapter);


    }

    public void createData() {
        expenses = expenseService.findAll();
        for (int j = 0; j < expenses.size(); j++) {
            Group group = new Group(expenses.get(j).getName());

          List<ExpenseDetailsEntity> expenseDetails = expenseDetailsService.findById(expenses.get(j).getId());
            for (int i = 0; i < expenseDetails.size(); i++) {
               group.children.add(expenseDetails.get(i).getName());
            }
            groups.append(j, group);
        }
    }



}
