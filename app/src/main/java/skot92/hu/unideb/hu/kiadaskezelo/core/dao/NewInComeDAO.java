package skot92.hu.unideb.hu.kiadaskezelo.core.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import skot92.hu.unideb.hu.kiadaskezelo.core.entity.BalanceEntity;
import skot92.hu.unideb.hu.kiadaskezelo.core.entity.InComeEntity;
import skot92.hu.unideb.hu.kiadaskezelo.core.helper.DatabaseHelper;

/**
 * Created by skot9 on 2015. 10. 18..
 */
public class NewInComeDAO extends AppDBDAO {
    public NewInComeDAO(Context context) {
        super(context);
    }

    public void save(InComeEntity inCome) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_IN_COME_NAME, inCome.getName());
        values.put(DatabaseHelper.COLUMN_IN_COME_DATE, inCome.getDate());
        values.put(DatabaseHelper.COLUMN_IN_COME_AMOUNT, inCome.getAmount());
        long inCome_id = database.insert(DatabaseHelper.TABLE_IN_COME, null, values);

        BalanceDAO balanceDAO = new BalanceDAO(mContext);
        BalanceEntity balanceEntity = new BalanceEntity();
        balanceEntity.setAmount(inCome.getAmount());
        balanceEntity.setDate(inCome.getDate());
        balanceEntity.setType("in");
        balanceDAO.save(balanceEntity);

    }

    public List<String> getInComeNames() {
        List<String> names = new ArrayList<String>();
        Cursor cursor = database.query( DatabaseHelper.TABLE_IN_COME,
                new String[]{DatabaseHelper.COLUMN_IN_COME_NAME}, null, null, DatabaseHelper.COLUMN_IN_COME_NAME, null, null);
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_IN_COME_NAME));
            names.add(name);
        }
        return names;
    }
}
