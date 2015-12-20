package skot92.hu.unideb.hu.kiadaskezelo.ui.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

import skot92.hu.unideb.hu.kiadaskezelo.R;
import skot92.hu.unideb.hu.kiadaskezelo.core.entity.InComeEntity;
import skot92.hu.unideb.hu.kiadaskezelo.service.InComeService;

public class InComeActivity extends AppCompatActivity {

    private int mYear;
    private int mMonth;
    private int mDay;

    private Button mPickDate;
    private Button btnAddNew;
    private AutoCompleteTextView etName;
    private EditText etPrice;


    static final int DATE_DIALOG_ID = 0;

    private InComeService inComeService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_income_in);

        inComeService = new InComeService(getApplicationContext());
        mPickDate = (Button) findViewById(R.id.myDatePickerButton);
        btnAddNew = (Button) findViewById(R.id.btnAddNewInCome);
        etName = (AutoCompleteTextView) findViewById(R.id.etInComeName);
        etPrice = (EditText) findViewById(R.id.etInComePrice);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item,
                        inComeService.getInComeNamesGroupByNames());

        etName.setThreshold(0);
        etName.setAdapter(adapter);

        mPickDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
            }
        });
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        updateDisplay();

        btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InComeEntity income = new InComeEntity();
                try {
                    income.setDate(mPickDate.getText().toString());
                    income.setName(etName.getText().toString());
                    income.setAmount(Integer.parseInt(etPrice.getText().toString()));
                    if (income.getName().equals("")) {
                        throw  new Exception("nincs nev");
                    }
                    inComeService.save(income);
                    Toast.makeText(getApplicationContext(), "Sikeres mentés", Toast.LENGTH_SHORT).show();
                } catch ( Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Nem sikerült lementeni", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    private void updateDisplay() {
        mPickDate.setText(
                new StringBuilder()
                        .append(mMonth + 1).append("-")
                        .append(mDay).append("-")
                        .append(mYear).append(" ")
        );
    }

    private DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker view, int year,
                                      int monthOfYear, int dayOfMonth) {
                    mYear = year;
                    mMonth = monthOfYear;
                    mDay = dayOfMonth;
                    updateDisplay();
                }
            };

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this,
                        mDateSetListener,
                        mYear, mMonth, mDay);
        }
        return null;
    }
}
