package skot92.hu.unideb.hu.kiadaskezelo.core.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.List;

import skot92.hu.unideb.hu.kiadaskezelo.core.entity.ExpenseDetailsEntity;
import skot92.hu.unideb.hu.kiadaskezelo.core.entity.ExpenseEntity;
import skot92.hu.unideb.hu.kiadaskezelo.core.helper.DatabaseHelper;

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

        Cursor c = database. query(DatabaseHelper.TABLE_EXPENSE_DETAILS, new String[]{
                        DatabaseHelper.COLUMN_EXPENSE_DETAILS_AMOUNT,
                        DatabaseHelper.COLUMN_EXPENSE_DETAILS_DESCRIPTION,
                        DatabaseHelper.COLUMN_EXPENSE_DETAILS_ID,
                        DatabaseHelper.COLUMN_EXPENSE_DETAILS_NAME},
                        DatabaseHelper.COLUMN_EXPENSE_DETAILS_EXPENSE_ID + " = ?1 ",
                        new String[]{String.valueOf(expenseId)},
                        null, null, null);

        while (c.moveToNext()) {

            amount = c.getInt(c.getColumnIndex(DatabaseHelper.COLUMN_EXPENSE_DETAILS_AMOUNT));
            name = c.getString(c.getColumnIndex(DatabaseHelper.COLUMN_EXPENSE_DETAILS_NAME));
            id = c.getLong(c.getColumnIndex(DatabaseHelper.COLUMN_EXPENSE_DETAILS_ID));
            description = c.getString(c.getColumnIndex(DatabaseHelper.COLUMN_EXPENSE_DETAILS_DESCRIPTION));

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
            values.put(DatabaseHelper.COLUMN_EXPENSE_DETAILS_AMOUNT, detail.getAmount());
            values.put(DatabaseHelper.COLUMN_EXPENSE_DETAILS_DESCRIPTION, detail.getDescription());
            values.put(DatabaseHelper.COLUMN_EXPENSE_DETAILS_EXPENSE_ID, expenseId);
            values.put(DatabaseHelper.COLUMN_EXPENSE_DETAILS_NAME, detail.getName());
            database.insert(DatabaseHelper.TABLE_EXPENSE_DETAILS, null, values);
        }
        super.close();
        return sum;
    }
}
