package skot92.hu.unideb.hu.kiadaskezelo.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import skot92.hu.unideb.hu.kiadaskezelo.R;
import skot92.hu.unideb.hu.kiadaskezelo.core.Expense;
import skot92.hu.unideb.hu.kiadaskezelo.core.ExpensesDataSource;

public class FragmentNewExpenese extends Fragment {

    public static final String TAG = "FragmentNewExpenese";

    private EditText name;
    private EditText description;
    private EditText price;
    private EditText date;
    private Button btnNewBuy;

    private ExpensesDataSource datasource;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =
                View.inflate(getActivity(), R.layout.fragment_new_expense, null);

        name = (EditText) v.findViewById(R.id.editName);
        description = (EditText) v.findViewById(R.id.editDescreption);
        price = (EditText) v.findViewById(R.id.editPrice);
        date = (EditText) v.findViewById(R.id.editDate);
        btnNewBuy = (Button) v.findViewById(R.id.btnAddNew);

        btnNewBuy.setOnClickListener(newExpenseClickListener);

        datasource = new ExpensesDataSource(v.getContext());
        datasource.open();


        return v;
    }


    private View.OnClickListener newExpenseClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            try {
                Expense comment = datasource.createBuy(
                        name.getText().toString(), description.getText().toString(),
                        Integer.parseInt(price.getText().toString()), date.getText().toString());
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getContext(),"Hiba tortent",Toast.LENGTH_LONG).show();
            }
        }
    };



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