package skot92.hu.unideb.hu.kiadaskezelo.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Calendar;

import skot92.hu.unideb.hu.kiadaskezelo.R;
import skot92.hu.unideb.hu.kiadaskezelo.service.BalanceService;
import skot92.hu.unideb.hu.kiadaskezelo.service.ExpenseService;
import skot92.hu.unideb.hu.kiadaskezelo.service.InComeService;

/**
 * Created by skot9 on 2015. 12. 25..
 */
public class StatFragment extends Fragment {

    private View myFragmentView;
    private TextView allExpense;
    private TextView allInComes;
    private TextView txLast30DayExpense;
    private TextView txLast30DayInCome;
    private TextView txSumBalance;

    private ExpenseService expenseService;
    private InComeService inComeService;
    private BalanceService balanceService;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        myFragmentView = inflater.inflate(R.layout.fragment_stat, container, false);
        allExpense = (TextView) myFragmentView.findViewById(R.id.txAllExpense);
        allInComes = (TextView) myFragmentView.findViewById(R.id.txAllInComes);
        txLast30DayExpense = (TextView) myFragmentView.findViewById(R.id.txLast30DayExpense);
        txLast30DayInCome = (TextView) myFragmentView.findViewById(R.id.txLast30DayInCome);
        txSumBalance = (TextView) myFragmentView.findViewById(R.id.txSumBalance);

        expenseService = new ExpenseService(getContext());
        inComeService = new InComeService(getContext());
        balanceService = new BalanceService(getContext());

        allExpense.setText(String.valueOf(-1 * expenseService.getSumAmount()));
        allInComes.setText(String.valueOf(inComeService.getSumAmount()));
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -30);
        txLast30DayExpense.setText(
                String.valueOf(-1 * expenseService.getSumAmountByDate(calendar.getTime().toString())));
        txLast30DayInCome.setText(
                String.valueOf(inComeService.getSumAmountByDate(calendar.getTime().toString())));
        txSumBalance.setText(String.valueOf(balanceService.findBalance()));


        return myFragmentView;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


}
