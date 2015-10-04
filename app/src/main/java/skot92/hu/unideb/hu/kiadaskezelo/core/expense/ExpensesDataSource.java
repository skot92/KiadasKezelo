package skot92.hu.unideb.hu.kiadaskezelo.core.expense;

/**
 * Created by skot9 on 2015. 09. 25..
 */

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

//DAO
public class ExpensesDataSource {

    private SQLiteDatabase database;
    private ExpenseSQLiteHelper dbHelper;
    private String[] allColumns = {
            ExpenseSQLiteHelper.COLUMN_ID,
            ExpenseSQLiteHelper.COLUMN_NAME,
            ExpenseSQLiteHelper.COLUMN_DATE,
            ExpenseSQLiteHelper.COLUMN_PIERCE,
            ExpenseSQLiteHelper.COLUMN_DESCERPTION
    };

    public ExpensesDataSource(Context context) {
        dbHelper = new ExpenseSQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Expense createBuy(String name, String date, int pierce, String descreption) {
        ContentValues values = new ContentValues();
        values.put(ExpenseSQLiteHelper.COLUMN_NAME, name);
        values.put(ExpenseSQLiteHelper.COLUMN_DATE, date);
        values.put(ExpenseSQLiteHelper.COLUMN_PIERCE, pierce);
        values.put(ExpenseSQLiteHelper.COLUMN_DESCERPTION, descreption);

        long insertId = database.insert(ExpenseSQLiteHelper.TABLE_BUY, null,
                values);

        Cursor cursor = database.query(ExpenseSQLiteHelper.TABLE_BUY,
                allColumns, ExpenseSQLiteHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Expense newComment = cursorToBuy(cursor);
        cursor.close();
        return newComment;
    }

    public void deleteBuy(Expense comment) {
        long id = comment.getId();
        database.delete(ExpenseSQLiteHelper.TABLE_BUY, ExpenseSQLiteHelper.COLUMN_ID
                + " = " + id, null);
    }

    public List<Expense> getAllBuys() {
        List<Expense> comments = new ArrayList<Expense>();

        Cursor cursor = database.query(ExpenseSQLiteHelper.TABLE_BUY,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Expense comment = cursorToBuy(cursor);
            comments.add(comment);
            cursor.moveToNext();
        }
        cursor.close();
        return comments;
    }

    private Expense cursorToBuy(Cursor cursor) {
        Expense expense = new Expense();
        expense.setId(cursor.getInt(cursor.getColumnIndex(ExpenseSQLiteHelper.COLUMN_ID)));
        expense.setItemName(cursor.getString((cursor.getColumnIndex(ExpenseSQLiteHelper.COLUMN_NAME))));
        expense.setItemDescription(cursor.getString((cursor.getColumnIndex(ExpenseSQLiteHelper.COLUMN_DESCERPTION))));
        expense.setPierce(cursor.getInt((cursor.getColumnIndex(ExpenseSQLiteHelper.COLUMN_PIERCE))));
        expense.setDate(cursor.getString((cursor.getColumnIndex(ExpenseSQLiteHelper.COLUMN_DATE))));


        return expense;
    }
}

