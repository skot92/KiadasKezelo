package skot92.hu.unideb.hu.kiadaskezelo.core.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import skot92.hu.unideb.hu.kiadaskezelo.core.entity.ExpenseDetailsEntity;
import skot92.hu.unideb.hu.kiadaskezelo.core.helper.ExpenseDetailsTable;

/**
 * Created by skot9 on 2015. 10. 19..
 */
public class ExpenseDetailsDAO extends AppDBDAO {

    public ExpenseDetailsDAO(Context context) {
        super(context);
    }

    public List<ExpenseDetailsEntity> findById(long expenseId) {
        super.open();
        int amount;
        String name;
        String description;
        long id;
        List<ExpenseDetailsEntity> expenses = new ArrayList<ExpenseDetailsEntity>();

        Cursor c = database. query(ExpenseDetailsTable.TABLE_NAME, new String[]{
                        ExpenseDetailsTable.EXPENSE_DETAILS_AMOUNT,
                        ExpenseDetailsTable.EXPENSE_DETAILS_DESCRIPTION,
                        ExpenseDetailsTable.EXPENSE_DETAILS_EXPENSE_ID,
                        ExpenseDetailsTable.EXPENSE_DETAILS_NAME},
                         ExpenseDetailsTable.EXPENSE_DETAILS_EXPENSE_ID + " = ?1 ",
                        new String[]{String.valueOf(expenseId)},
                        null, null, null);

        while (c.moveToNext()) {

            amount = c.getInt(c.getColumnIndex(ExpenseDetailsTable.EXPENSE_DETAILS_AMOUNT));
            name = c.getString(c.getColumnIndex(ExpenseDetailsTable.EXPENSE_DETAILS_NAME));
            id = c.getLong(2);
            description = c.getString(c.getColumnIndex(ExpenseDetailsTable.EXPENSE_DETAILS_DESCRIPTION));

            ExpenseDetailsEntity expense = new ExpenseDetailsEntity(id,expenseId,name,description,amount);
            expenses.add(expense);

        }
        c.close();
        super.close();
        return  expenses;
    }

    public List<ExpenseDetailsEntity> findAll() {
        super.open();
        int amount;
        String name;
        String description;
        Long id;
        Long expenseId;
        List<ExpenseDetailsEntity> expenses = new ArrayList<ExpenseDetailsEntity>();

        Cursor c = database. query(ExpenseDetailsTable.TABLE_NAME, new String[]{
                        ExpenseDetailsTable.EXPENSE_DETAILS_AMOUNT,
                        ExpenseDetailsTable.EXPENSE_DETAILS_DESCRIPTION,
                        ExpenseDetailsTable.EXPENSE_DETAILS_ID,
                        ExpenseDetailsTable.EXPENSE_DETAILS_EXPENSE_ID,
                        ExpenseDetailsTable.EXPENSE_DETAILS_NAME},
                        null , null, null, null, null);

        while (c.moveToNext()) {

            amount = c.getInt(c.getColumnIndex(ExpenseDetailsTable.EXPENSE_DETAILS_AMOUNT));
            name = c.getString(c.getColumnIndex(ExpenseDetailsTable.EXPENSE_DETAILS_NAME));
            id = c.getLong(2);
            description = c.getString(c.getColumnIndex(ExpenseDetailsTable.EXPENSE_DETAILS_DESCRIPTION));
            expenseId = c.getLong(3);
            ExpenseDetailsEntity expense = new ExpenseDetailsEntity(id,expenseId,name,description,amount);
            expenses.add(expense);

        }
        c.close();
        super.close();
        return  expenses;
    }

    public int save(List<ExpenseDetailsEntity> details, Long expenseId ) {
        super.open();
        int sum = 0;
        ContentValues values = new ContentValues();
        for (ExpenseDetailsEntity detail : details) {
            sum += detail.getAmount();
            values.put(ExpenseDetailsTable.EXPENSE_DETAILS_AMOUNT, detail.getAmount());
            values.put(ExpenseDetailsTable.EXPENSE_DETAILS_DESCRIPTION, detail.getDescription());
            values.put(ExpenseDetailsTable.EXPENSE_DETAILS_EXPENSE_ID, expenseId);
            values.put(ExpenseDetailsTable.EXPENSE_DETAILS_NAME, detail.getName());
            database.insert(ExpenseDetailsTable.TABLE_NAME, null, values);
        }
        super.close();
        return sum;
    }
}
