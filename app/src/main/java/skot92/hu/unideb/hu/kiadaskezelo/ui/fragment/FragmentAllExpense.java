package skot92.hu.unideb.hu.kiadaskezelo.ui.fragment;

import android.app.ListFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;

import skot92.hu.unideb.hu.kiadaskezelo.R;
import skot92.hu.unideb.hu.kiadaskezelo.core.Expense;
import skot92.hu.unideb.hu.kiadaskezelo.core.ExpensesDataSource;


public class FragmentAllExpense extends Fragment {

    public static final String TAG = "FragmentAllExpense";


    private ExpensesDataSource datasource;

    private ArrayAdapter<Expense> adapter;
    private ListView listview;
    private View v;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v =
                View.inflate(getActivity(), R.layout.fragment_all_expense, null);
        datasource = new ExpensesDataSource(v.getContext());
        datasource.open();

        v = View.inflate(getActivity(), R.layout.fragment_all_expense, null);
        listview = (ListView) v.findViewById(R.id.AllList);
        adapter = new ArrayAdapter<Expense>(v.getContext(),
                android.R.layout.simple_list_item_1, datasource.getAllBuys());
        listview.setAdapter(adapter);


        return v;
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
