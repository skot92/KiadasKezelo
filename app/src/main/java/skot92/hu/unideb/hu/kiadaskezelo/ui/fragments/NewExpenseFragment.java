package skot92.hu.unideb.hu.kiadaskezelo.ui.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import skot92.hu.unideb.hu.kiadaskezelo.R;
import skot92.hu.unideb.hu.kiadaskezelo.core.dao.BalanceDAO;
import skot92.hu.unideb.hu.kiadaskezelo.core.dao.ExpenseDetailsDAO;
import skot92.hu.unideb.hu.kiadaskezelo.core.entity.BalanceEntity;
import skot92.hu.unideb.hu.kiadaskezelo.core.entity.ExpenseDetailsEntity;
import skot92.hu.unideb.hu.kiadaskezelo.core.entity.ExpenseEntity;
import skot92.hu.unideb.hu.kiadaskezelo.service.ExpenseService;


public class NewExpenseFragment extends ListFragment {

    private Button btnNewItem;
    private Button btnSaveExpense;
    View myFragmentView;

    EditText expenseName;
    Button expenseDate;

    private List<ExpenseDetailsEntity> detailsList;
    private List<String> values;
    private ArrayAdapter<String> adapter;


    private int mYear;
    private int mMonth;
    private int mDay;
    private DatePickerFragment picker;
    static final int DATE_DIALOG_ID = 1;

    ExpenseService expenseService;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        myFragmentView = inflater.inflate(R.layout.fragment_new_expense, container, false);
        btnNewItem = (Button) myFragmentView.findViewById(R.id.btnAddNewItem);
        btnSaveExpense = (Button) myFragmentView.findViewById(R.id.btnSaveExpense);


        controll();

        return myFragmentView;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        expenseService = new ExpenseService(getActivity().getApplicationContext());

        detailsList = new ArrayList<ExpenseDetailsEntity>();
        values = new ArrayList<String>();

        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);

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
        final Context context = getContext();
        LayoutInflater li = LayoutInflater.from(context);
        View promptsView = li.inflate(R.layout.dialog_new_item_details,null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context,R.style.FullDialogTheme);

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
                .setPositiveButton(R.string.ok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                try {
                                    String name = itemName.getText().toString();
                                    String description = itemDescreption.getText().toString();
                                    int amount = Integer.parseInt(itemAmount.getText().toString());
                                    if (name.equals("") || description.equals("")) {
                                        throw new Exception(context.getString(R.string.error_no_filled));
                                    }
                                    ExpenseDetailsEntity detail = new ExpenseDetailsEntity();
                                    detail.setName(name);
                                    detail.setDescription(description);
                                    detail.setAmount(amount);
                                    detailsList.add(detail);
                                    values.add(detail.getName());
                                    adapter.notifyDataSetChanged();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    Toast.makeText(getContext(), R.string.is_not_add, Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                .setNegativeButton(getString(R.string.cancel),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, 600);
        alertDialog.show();

    }


    public void onCreateNewExpenseDialog() {
        Context context = getContext();
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

        expenseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                picker = new DatePickerFragment();
                picker.setButton(expenseDate);
                picker.show(getFragmentManager(), "datePicker");
            }
        });


        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton(getString(R.string.ok),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                try {
                                    saveExpense();
                                    Toast.makeText(getContext(), R.string.succes_save, Toast.LENGTH_SHORT).show();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    Toast.makeText(getContext(), R.string.faliled_save, Toast.LENGTH_SHORT).show();
                                }

                                adapter.clear();
                            }
                        })
                .setNegativeButton(R.string.cancel,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }


    public void saveExpense() throws Exception {
        Long id = null;
        ExpenseEntity expenseEntity = new ExpenseEntity();
        expenseEntity.setAmount(0);
        expenseEntity.setName(expenseName.getText().toString());
        expenseEntity.setDate(expenseDate.getText().toString());


        if (expenseEntity.getName().equals("")) {
            throw new Exception("Nincs nev");
        }
        if (expenseEntity.getDate().equals("Dátum")) {
            throw new Exception("Nincs datum");
        }
        id = expenseService.save(expenseEntity);
        ExpenseDetailsDAO expenseDetailsDAO = new ExpenseDetailsDAO(getContext());
        int sum = expenseDetailsDAO.save(detailsList, id);

        sum = -sum;
        expenseService.update(sum, id);

        BalanceDAO balanceDAO = new BalanceDAO(getContext());
        BalanceEntity balanceEntity = new BalanceEntity();
        balanceEntity.setDate(expenseEntity.getDate());
        balanceEntity.setType("expense");
        balanceEntity.setAmount(sum);
        balanceDAO.save(balanceEntity);

    }
}
