package skot92.hu.unideb.hu.kiadaskezelo.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import skot92.hu.unideb.hu.kiadaskezelo.R;
import skot92.hu.unideb.hu.kiadaskezelo.core.entity.InComeEntity;
import skot92.hu.unideb.hu.kiadaskezelo.service.ExpenseService;
import skot92.hu.unideb.hu.kiadaskezelo.service.InComeService;

/**
 * Created by skot9 on 2015. 12. 25..
 */
public class StatFragment extends Fragment {

    private View myFragmentView;
    private TextView allExpense;
    private TextView allInComes;

    private ExpenseService expenseService;
    private InComeService inComeService;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        myFragmentView = inflater.inflate(R.layout.fragment_stat, container, false);
        allExpense = (TextView) myFragmentView.findViewById(R.id.txAllExpense);
        allInComes = (TextView) myFragmentView.findViewById(R.id.txAllInComes);

        expenseService = new ExpenseService(getContext());
        inComeService = new InComeService(getContext());

        allExpense.setText(String.valueOf(-1 * expenseService.getSumAmount()));
        allInComes.setText("12313");
        return myFragmentView;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
