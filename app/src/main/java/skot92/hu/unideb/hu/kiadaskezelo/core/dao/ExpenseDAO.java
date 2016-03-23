package skot92.hu.unideb.hu.kiadaskezelo.core.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import skot92.hu.unideb.hu.kiadaskezelo.core.entity.ExpenseEntity;
import skot92.hu.unideb.hu.kiadaskezelo.core.helper.ExpenseTable;

/**
 * Created by skot9 on 2015. 10. 19..
 */
public class ExpenseDAO extends AppDBDAO {

    public ExpenseDAO(Context context) {
        super(context);
    }

    public long save(ExpenseEntity expense) {
        super.open();
        ContentValues values = new ContentValues();
        values.put(ExpenseTable.EXPENSE_NAME, expense.getName());
        Log.d("save", expense.getDate());
        values.put(ExpenseTable.EXPENSE_DATE, expense.getDate());
        values.put(ExpenseTable.EXPENSE_AMOUNT, 0);
        long expense_id = database.insert(ExpenseTable.TABLE_NAME, null, values);
        super.close();
        return expense_id;
    }

    public long update(int sum, long id) {
        super.open();
        ContentValues values = new ContentValues();
        values.put(ExpenseTable.EXPENSE_AMOUNT, sum);
        long expense_id = database.update(ExpenseTable.TABLE_NAME, values, "id=" + id, null);
        super.close();
        return expense_id;
    }

    public List<ExpenseEntity> getAll() {
        super.open();
        List<ExpenseEntity> expenses = new ArrayList<ExpenseEntity>();

        Cursor c = database.query(ExpenseTable.TABLE_NAME, new String[]{
                        ExpenseTable.EXPENSE_AMOUNT,
                        ExpenseTable.EXPENSE_DATE,
                        ExpenseTable.EXPENSE_NAME,
                        ExpenseTable.EXPENSE_ID},
                null, null, null, null, null);

        while (c.moveToNext()) {

            int amount = c.getInt(c.getColumnIndex(ExpenseTable.EXPENSE_AMOUNT));
            String name = c.getString(c.getColumnIndex(ExpenseTable.EXPENSE_NAME));
            long id = c.getLong(c.getColumnIndex(ExpenseTable.EXPENSE_ID));
            String date = c.getString(c.getColumnIndex(ExpenseTable.EXPENSE_DATE));

            ExpenseEntity expense = new ExpenseEntity(id, name, date, amount);
            expenses.add(expense);

        }
        c.close();
        super.close();
        return expenses;
    }

    public int getSumAmount() {
        super.open();
        Cursor c = database.query(ExpenseTable.TABLE_NAME, new String[]{
                        "SUM( " + ExpenseTable.EXPENSE_AMOUNT + " )"},
                null, null, null, null, null);

        if (c.moveToFirst()) {
            return c.getInt(0);
        }
        c.close();
        super.close();
        return -1;
    }

    public int getSumAmountByDate(String fromDate) {
        super.open();
        
        Cursor c1 = database.rawQuery("SELECT " + ExpenseTable.EXPENSE_AMOUNT + " , " + ExpenseTable.EXPENSE_DATE
                + " FROM " + ExpenseTable.TABLE_NAME, null);

        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.US);
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = sdf.parse(fromDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date dateExpense = null;
        int sum = 0;
        while (c1.moveToNext()) {
            try {
                dateExpense = sdf1.parse(c1.getString(1));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (dateExpense.compareTo(date) > 0) {
                sum += c1.getInt(0);
            }
        }
        c1.close();
        super.close();
        return sum;
    }


}
