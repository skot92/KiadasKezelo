package skot92.hu.unideb.hu.kiadaskezelo.core.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import skot92.hu.unideb.hu.kiadaskezelo.core.entity.BalanceEntity;
import skot92.hu.unideb.hu.kiadaskezelo.core.entity.ExpenseEntity;
import skot92.hu.unideb.hu.kiadaskezelo.core.helper.DatabaseHelper;

/**
 * Created by skot9 on 2015. 10. 19..
 */
public class ExpenseDAO extends AppDBDAO{

    public ExpenseDAO(Context context) {
        super(context);
    }

    public long save(ExpenseEntity expense) {
        super.open();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_EXPENSE_NAME, expense.getName());
        values.put(DatabaseHelper.COLUMN_EXPENSE_DATE, expense.getDate());
        values.put(DatabaseHelper.COLUMN_EXPENSE_AMOUNT, 0);
        long expense_id = database.insert(DatabaseHelper.TABLE_EXPENSE, null, values);
        super.close();
        return  expense_id;
    }

    public long update (int sum, long id) {
        super.open();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_EXPENSE_AMOUNT, sum);
        long expense_id = database.update(DatabaseHelper.TABLE_EXPENSE,values,"id=" + id, null);
        super.close();
        return  expense_id;
    }

    public List<ExpenseEntity> getAll() {
        super.open();
        int amount;
        String name;
        String date;
        long id;
        List<ExpenseEntity> expenses = new ArrayList<ExpenseEntity>();

        Cursor c = database.rawQuery("SELECT * FROM "
                + DatabaseHelper.TABLE_EXPENSE + " ;", null);

        if(c.moveToNext()) {

            amount = c.getInt(c.getColumnIndex(DatabaseHelper.COLUMN_EXPENSE_AMOUNT));
            name = c.getString(c.getColumnIndex(DatabaseHelper.COLUMN_EXPENSE_NAME));
            id = c.getLong(c.getColumnIndex(DatabaseHelper.COLUMN_EXPENSE_ID));
            date = c.getString(c.getColumnIndex(DatabaseHelper.COLUMN_EXPENSE_DATE));

            ExpenseEntity expense = new ExpenseEntity(id,name,date,amount);
            expenses.add(expense);

        }
        c.close();
        super.close();
        return  expenses;
    }
}
