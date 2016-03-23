package skot92.hu.unideb.hu.kiadaskezelo.ui.dialogs;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Calendar;

import skot92.hu.unideb.hu.kiadaskezelo.R;
import skot92.hu.unideb.hu.kiadaskezelo.service.InComeService;
import skot92.hu.unideb.hu.kiadaskezelo.ui.Adapter.AllIncomeAdapter;
import skot92.hu.unideb.hu.kiadaskezelo.ui.activity.all.AllIncomeActivity;

/**
 * Created by skot9 on 2016. 02. 26..
 */
public class AllIncomesSearchDialogDate extends Dialog {

    public Activity c;
    public Dialog d;

    private RadioGroup radioSexGroup;
    private RadioButton radioButton;
    private Button btnSearch;
    private Button btnDate;
    private String searchDate;
    private int mYear;
    private int mMonth;
    private int mDay;

    public AllIncomesSearchDialogDate(Activity a) {
        super(a);
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.all_income_search_date_custom_dialog);
        btnDate = (Button) findViewById(R.id.btnDate);

        addListenerOnButton();

    }


    public void addListenerOnButton() {

        radioSexGroup = (RadioGroup) findViewById(R.id.radioSex);
        btnSearch = (Button) findViewById(R.id.btnDisplay);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = radioSexGroup.getCheckedRadioButtonId();
                InComeService inComeService;
                try {
                    Context context = AllIncomesSearchDialogDate.this.getContext();
                    inComeService = new InComeService(context);
                    radioButton = (RadioButton) findViewById(selectedId);
                    AllIncomeAdapter adapter = new AllIncomeAdapter(context, inComeService.findInComesSearchByDate(radioButton.getText().toString(), AllIncomesSearchDialogDate.this.searchDate));


                    adapter.notifyDataSetChanged();
                    AllIncomeActivity.lv.setAdapter(adapter);
                    Toast.makeText(getContext(), radioButton.getText().toString(), Toast.LENGTH_SHORT).show();
                    AllIncomesSearchDialogDate.this.dismiss();
                    Log.d("succes", "succes");
                } catch (NullPointerException e) {
                    e.printStackTrace();
                    Toast.makeText(getContext(), R.string.no_select_ralation_or_amount, Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dpd = new DatePickerDialog(AllIncomesSearchDialogDate.this.getContext(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                AllIncomesSearchDialogDate.this.searchDate = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                                Log.d("dateBtnDate", AllIncomesSearchDialogDate.this.searchDate);
                            }
                        }, mYear, mMonth, mDay);
                dpd.show();
            }
        });
    }


}
