package skot92.hu.unideb.hu.kiadaskezelo.ui.activity.in;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import skot92.hu.unideb.hu.kiadaskezelo.R;
import skot92.hu.unideb.hu.kiadaskezelo.core.dao.BalanceDAO;
import skot92.hu.unideb.hu.kiadaskezelo.core.dao.ExpenseDetailsDAO;
import skot92.hu.unideb.hu.kiadaskezelo.core.entity.BalanceEntity;
import skot92.hu.unideb.hu.kiadaskezelo.core.entity.ExpenseDetailsEntity;
import skot92.hu.unideb.hu.kiadaskezelo.core.entity.ExpenseEntity;
import skot92.hu.unideb.hu.kiadaskezelo.service.ExpenseService;

public class NewExpenseActivity extends ListActivity {

    private Button btnNewItem;
    private Button btnSaveExpense;

    EditText expenseName;
     Button expenseDate;

    private List<ExpenseDetailsEntity> detailsList;
    private List<String> values;
    private ArrayAdapter<String> adapter;


    private int mYear;
    private int mMonth;
    private int mDay;

    static final int DATE_DIALOG_ID = 1;

    ExpenseService expenseService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_expense_in);

        expenseService = new ExpenseService(getApplicationContext());

        detailsList = new ArrayList<ExpenseDetailsEntity>();
        values = new ArrayList<String>();

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);

        btnNewItem = (Button) findViewById(R.id.btnAddNewItem);
        btnSaveExpense = (Button) findViewById(R.id.btnSaveExpense);

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        controll();
    }

    public void controll() {
        btnNewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCreateNewItemDetailsDialog();
            }
        });
        btnSaveExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCreateNewExpenseDialog();
                //saveExpense();
            }
        });
    }


    public void onCreateNewItemDetailsDialog() {
        Context context = this;
        LayoutInflater li = LayoutInflater.from(context);
        View promptsView = li.inflate(R.layout.dialog_new_item_details, null);

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

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }


    public void onCreateNewExpenseDialog() {
        Context context = this;
        LayoutInflater li = LayoutInflater.from(context);
        View promptsView = li.inflate(R.layout.dialog_new_expense, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);

        expenseName = (EditText) promptsView
                .findViewById(R.id.eTExpenseName);
         expenseDate = (Button) promptsView
                .findViewById(R.id.myDatePickerButton1);

        expenseDate.setText(String.valueOf(mYear + "." + (mMonth+1) + "." + mDay + "."));

        expenseDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
            }
        });

        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                saveExpense();
                                finish();
                                Toast.makeText(getApplicationContext(), "Sikeres mentés", Toast.LENGTH_SHORT).show();
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }


    private void updateDisplay() {
        expenseDate.setText(
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


    public void saveExpense() {
        ExpenseEntity expenseEntity = new ExpenseEntity();
        expenseEntity.setAmount(0);
        expenseEntity.setName(expenseName.getText().toString());
        expenseEntity.setDate(expenseDate.getText().toString());

        Long id = expenseService.save(expenseEntity);

        ExpenseDetailsDAO expenseDetailsDAO = new ExpenseDetailsDAO(getApplicationContext());
        int sum =  expenseDetailsDAO.save(detailsList,id);

        sum = -sum;
        expenseService.update(sum, id);

        BalanceDAO balanceDAO = new BalanceDAO(getApplicationContext());
        BalanceEntity balanceEntity = new BalanceEntity();
        balanceEntity.setDate(expenseEntity.getDate());
        balanceEntity.setType("expense");
        balanceEntity.setAmount(sum);
        balanceDAO.save(balanceEntity);
    }

}


