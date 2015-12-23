package skot92.hu.unideb.hu.kiadaskezelo.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import skot92.hu.unideb.hu.kiadaskezelo.R;
import skot92.hu.unideb.hu.kiadaskezelo.core.entity.InComeEntity;
import skot92.hu.unideb.hu.kiadaskezelo.service.InComeService;


public class NewIncomeFragment extends Fragment  {

    private Button mPickDate;
    private Button btnAddNew;
    private AutoCompleteTextView etName;
    private EditText etPrice;

    private View myFragmentView;
    static final int DATE_DIALOG_ID = 0;
    private InComeService inComeService;
    private DatePickerFragment picker;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        myFragmentView = inflater.inflate(R.layout.fragment_new_income, container, false);

        inComeService = new InComeService(getContext());
        mPickDate = (Button) myFragmentView.findViewById(R.id.myDatePickerButton);
        btnAddNew = (Button) myFragmentView.findViewById(R.id.btnAddNewInCome);
        etPrice = (EditText) myFragmentView.findViewById(R.id.etInComePrice);
        etName = (AutoCompleteTextView) myFragmentView.findViewById(R.id.etInComeName);


        mPickDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                picker = new DatePickerFragment();
                //picker.setViewId(mPickDate.getId());
                picker.setButton(mPickDate);
                picker.show(getFragmentManager(), "datePicker");
            }
        });

        btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InComeEntity income = new InComeEntity();
                try {
                    income.setDate(mPickDate.getText().toString());
                    income.setName(etName.getText().toString());
                    income.setAmount(Integer.parseInt(etPrice.getText().toString()));
                    if (income.getName().equals("")) {
                        throw new Exception("nincs nev");
                    }
                    inComeService.save(income);
                    Toast.makeText(getContext(), "Sikeres mentés", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getContext(), "Nem sikerült lementeni", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return myFragmentView;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}