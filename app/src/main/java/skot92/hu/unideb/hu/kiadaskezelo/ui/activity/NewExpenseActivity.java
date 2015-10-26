package skot92.hu.unideb.hu.kiadaskezelo.ui.activity;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import skot92.hu.unideb.hu.kiadaskezelo.R;
import skot92.hu.unideb.hu.kiadaskezelo.core.entity.ExpenseDetailsEntity;

public class NewExpenseActivity extends ListActivity {

    private Button btnNewItem;
    private Button btnSaveExpense;

    private List<ExpenseDetailsEntity> detailsList;
    private List<String> values;
    private ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);

        detailsList = new ArrayList<ExpenseDetailsEntity>();
        values = new ArrayList<String>();

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);



        btnNewItem = (Button) findViewById(R.id.btnAddNewItem);
        btnSaveExpense = (Button) findViewById(R.id.btnSaveExpense);

        controll();
    }

    public void controll() {
        btnNewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCreateDialog();
            }
        });
    }




    public void onCreateDialog() {
        Context context = this;
        LayoutInflater li = LayoutInflater.from(context);
        View promptsView = li.inflate(R.layout.dialog_item_details, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);

        final EditText itemName = (EditText) promptsView
                .findViewById(R.id.eTDetailsName);
        final EditText itemDescreption = (EditText) promptsView
                .findViewById(R.id.eTDetailsDescrption);
        final EditText itemAmount = (EditText) promptsView
                .findViewById(R.id.eTdetailsAmount);

        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                String name = itemName.getText().toString();
                                String description = itemDescreption.getText().toString();
                                int amount = Integer.parseInt(itemAmount.getText().toString());
                                ExpenseDetailsEntity detail = new ExpenseDetailsEntity();
                                detail.setName(name);
                                detail.setDescription(description);
                                detail.setAmount(amount);
                                detailsList.add(detail);
                                values.add(detail.getName());
                                adapter.notifyDataSetChanged();
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }

}