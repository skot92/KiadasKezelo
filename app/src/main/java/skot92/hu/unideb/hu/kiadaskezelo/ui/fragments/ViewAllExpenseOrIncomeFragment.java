package skot92.hu.unideb.hu.kiadaskezelo.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import skot92.hu.unideb.hu.kiadaskezelo.R;
import skot92.hu.unideb.hu.kiadaskezelo.service.InComeService;
import skot92.hu.unideb.hu.kiadaskezelo.ui.activity.all.AllExpenseActivity;
import skot92.hu.unideb.hu.kiadaskezelo.ui.activity.all.AllIncomeActivity;


public class ViewAllExpenseOrIncomeFragment extends Fragment {

    private View myFragmentView;
    private Button allExpense;
    private Button allInCome;
    private InComeService inComeService;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inComeService = new InComeService(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myFragmentView = inflater.inflate(R.layout.fragment_all_expense_or_income, container, false);
        allExpense = (Button) myFragmentView.findViewById(R.id.btAllExpense);
        allInCome = (Button) myFragmentView.findViewById(R.id.btAllInCome);

        allExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AllExpenseActivity.class);
                startActivity(intent);
            }
        });

        allInCome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AllIncomeActivity.class);
                startActivity(intent);
            }
        });
        return myFragmentView;
    }

}
