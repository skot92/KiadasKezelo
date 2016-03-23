package skot92.hu.unideb.hu.kiadaskezelo.core.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import skot92.hu.unideb.hu.kiadaskezelo.core.entity.BalanceEntity;
import skot92.hu.unideb.hu.kiadaskezelo.core.helper.BalanceTable;

/**
 * Created by skot9 on 2015. 10. 18..
 */
public class BalanceDAO extends AppDBDAO {
    public BalanceDAO(Context context) {
        super(context);
    }

    public long save(BalanceEntity balance) {
        super.open();
        ContentValues values = new ContentValues();
        values.put(BalanceTable.BALANCE_AMOUNT, balance.getAmount());
        values.put(BalanceTable.BALANCE_DATE, balance.getDate());
        values.put(BalanceTable.BALANCE_TYPE, balance.getType());
        // insert row
        long balance_id = database.insert(BalanceTable.TABLE_NAME, null, values);
        super.close();
        return  balance_id;
    }

    public int getBalance() {
        super.open();
        Cursor c = database.rawQuery("SELECT SUM(" + BalanceTable.BALANCE_AMOUNT + ") FROM "
                + BalanceTable.TABLE_NAME + " ;", null);
        int amount;
        if(c.moveToFirst())
            amount = c.getInt(0);
        else
            amount = 0;
        c.close();
        super.close();
        return  amount;
    }
}
