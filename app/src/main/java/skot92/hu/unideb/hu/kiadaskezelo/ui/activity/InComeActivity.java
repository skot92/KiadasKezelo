package skot92.hu.unideb.hu.kiadaskezelo.ui.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

import skot92.hu.unideb.hu.kiadaskezelo.R;
import skot92.hu.unideb.hu.kiadaskezelo.core.dao.NewInComeDAO;
import skot92.hu.unideb.hu.kiadaskezelo.core.entity.InComeEntity;

public class InComeActivity extends AppCompatActivity {

    private int mYear;
    private int mMonth;
    private int mDay;

    private Button mPickDate;
    private Button btnAddNew;
    private EditText etName;
    private EditText etPrice;


    static final int DATE_DIALOG_ID = 0;

    NewInComeDAO newInComeDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);

        newInComeDAO = new NewInComeDAO(getApplicationContext()); //= new DatabaseHelper(getApplicationContext());
        mPickDate = (Button) findViewById(R.id.myDatePickerButton);
        btnAddNew = (Button) findViewById(R.id.btnAddNewInCome);
        etName = (EditText) findViewById(R.id.etInComeName);
        etPrice = (EditText) findViewById(R.id.etInComePrice);

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
                income.setDate(mPickDate.getText().toString());
                income.setName(etName.getText().toString());
                income.setAmount(Integer.parseInt(etPrice.getText().toString()));
                try {
                    if (income.getName().equals("")) {
                        throw  new Exception("nincs nev");
                    }
                    newInComeDAO.save(income);
                    Toast.makeText(getApplicationContext(), "Sikeres mentés", Toast.LENGTH_SHORT).show();
                } catch ( Exception e) {
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
