package skot92.hu.unideb.hu.kiadaskezelo.ui.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import skot92.hu.unideb.hu.kiadaskezelo.R;

/**
 * Created by skot9 on 2016. 02. 26..
 */
public class AllIncomesSearchDialog extends Dialog{

    public Activity c;
    public Dialog d;

    private RadioGroup radioSexGroup;
    private RadioButton radioButton;
    private EditText sum;
    private Button btnDisplay;

    public AllIncomesSearchDialog(Activity a) {
        super(a);
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog);
        sum = (EditText) findViewById(R.id.dialogSum);
        addListenerOnButton();

    }


    public int getSum() {
        return Integer.parseInt(sum.getText().toString());
    }

    public String getSelectedRadioButton() {
        return radioButton.getText().toString();
    }


    public void addListenerOnButton() {

        radioSexGroup = (RadioGroup) findViewById(R.id.radioSex);
        btnDisplay = (Button) findViewById(R.id.btnDisplay);

        btnDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = radioSexGroup.getCheckedRadioButtonId();

                radioButton = (RadioButton) findViewById(selectedId);
                Toast.makeText(getContext(),radioButton.getText().toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
