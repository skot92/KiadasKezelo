package skot92.hu.unideb.hu.kiadaskezelo.ui.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import skot92.hu.unideb.hu.kiadaskezelo.R;
import skot92.hu.unideb.hu.kiadaskezelo.service.InComeService;
import skot92.hu.unideb.hu.kiadaskezelo.ui.Adapter.AllIncomeAdapter;
import skot92.hu.unideb.hu.kiadaskezelo.ui.activity.all.AllIncomeActivity;

/**
 * Created by skot9 on 2016. 02. 26..
 */
public class AllIncomesSearchDialogAmount extends Dialog{

    public Activity c;
    public Dialog d;

    private RadioGroup radioSexGroup;
    private RadioButton radioButton;
    private EditText sum;
    private Button btnDisplay;

    public AllIncomesSearchDialogAmount(Activity a) {
        super(a);
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.all_income_search_amount_custom_dialog);
        sum = (EditText) findViewById(R.id.dialogSum);
        addListenerOnButton();

    }


    public void addListenerOnButton() {

        radioSexGroup = (RadioGroup) findViewById(R.id.radioSex);
        btnDisplay = (Button) findViewById(R.id.btnDisplay);

        btnDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = radioSexGroup.getCheckedRadioButtonId();

                try {
                    radioButton = (RadioButton) findViewById(selectedId);
                    if(sum.getText().toString().equals("")) {
                        throw  new NullPointerException("sum is null");
                    }
                    InComeService inComeService = new InComeService(AllIncomesSearchDialogAmount.this.getContext());
                    AllIncomeAdapter adapter = new AllIncomeAdapter(AllIncomesSearchDialogAmount.this.getContext(),
                            inComeService.findInComesSearchByAmount(radioButton.getText().toString(), sum.getText().toString()));
                    adapter.notifyDataSetChanged();
                    AllIncomeActivity.lv.setAdapter(adapter);
                    Toast.makeText(getContext(),radioButton.getText().toString(),Toast.LENGTH_SHORT).show();
                    AllIncomesSearchDialogAmount.this.dismiss();

                } catch (NullPointerException e) {
                    e.printStackTrace();
                    Toast.makeText(getContext(), R.string.no_select_ralation_or_amount,Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
