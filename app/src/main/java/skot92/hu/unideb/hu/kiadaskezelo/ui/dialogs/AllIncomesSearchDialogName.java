package skot92.hu.unideb.hu.kiadaskezelo.ui.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import skot92.hu.unideb.hu.kiadaskezelo.R;
import skot92.hu.unideb.hu.kiadaskezelo.service.InComeService;
import skot92.hu.unideb.hu.kiadaskezelo.ui.Adapter.AllIncomeAdapter;
import skot92.hu.unideb.hu.kiadaskezelo.ui.activity.all.AllIncomeActivity;

/**
 * Created by skot9 on 2016. 02. 26..
 */
public class AllIncomesSearchDialogName extends Dialog {

    public Activity c;
    public Dialog d;

    private EditText name;
    private Button btnDisplay;

    public AllIncomesSearchDialogName(Activity a) {
        super(a);
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.all_income_search_name_custom_dialog);
        name = (EditText) findViewById(R.id.incomeName);
        btnDisplay = (Button) findViewById(R.id.btnDisplay);
        addListenerOnButton();

    }


    public void addListenerOnButton() {


        btnDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                InComeService inComeService = new InComeService(AllIncomesSearchDialogName.this.getContext());
                AllIncomeAdapter adapter = new AllIncomeAdapter(AllIncomesSearchDialogName.this.getContext(),
                        inComeService.findInComesSearchByName(name.getText().toString()));

                adapter.notifyDataSetChanged();
                AllIncomeActivity.lv.setAdapter(adapter);
                AllIncomesSearchDialogName.this.dismiss();


            }
        });
    }
}
