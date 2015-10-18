package skot92.hu.unideb.hu.kiadaskezelo.core.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import skot92.hu.unideb.hu.kiadaskezelo.core.entity.BalanceEntity;
import skot92.hu.unideb.hu.kiadaskezelo.core.helper.DatabaseHelper;

/**
 * Created by skot9 on 2015. 10. 18..
 */
public class BalanceDAO extends AppDBDAO {
    public BalanceDAO(Context context) {
        super(context);
    }

    public void save(BalanceEntity balance) {

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.KEY_IN_BALANCE_AMOUNT, balance.getAmount());
        values.put(DatabaseHelper.KEY_IN_BALANCE_DATE, balance.getDate());
        values.put(DatabaseHelper.KEY_IN_BALANCE_TYPE, balance.getType());
        // insert row
        long balance_id = database.insert(DatabaseHelper.TABLE_BALANCE, null, values);
    }

    public int getBalance() {

        Cursor c = database.rawQuery("SELECT SUM(" + DatabaseHelper.KEY_IN_BALANCE_AMOUNT + ") FROM "
                + DatabaseHelper.TABLE_BALANCE + " ;", null);
        int amount;
        if(c.moveToFirst())
            amount = c.getInt(0);
        else
            amount = 0;
        c.close();
        return  amount;
    }
}
