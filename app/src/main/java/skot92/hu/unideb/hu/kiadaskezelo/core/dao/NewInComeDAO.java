package skot92.hu.unideb.hu.kiadaskezelo.core.dao;

import android.content.ContentValues;
import android.content.Context;
import android.provider.ContactsContract;

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
        values.put(DatabaseHelper.KEY_IN_COME_ENTITY_NAME, inCome.getName());
        values.put(DatabaseHelper.KEY_IN_COME_ENTITY_DATE, inCome.getDate());
        values.put(DatabaseHelper.KEY_IN_COME_ENTITY_AMOUNT, inCome.getAmount());
        long inCome_id = database.insert(DatabaseHelper.TABLE_IN_COME, null, values);

        ContentValues values1 = new ContentValues();
        values1.put(DatabaseHelper.KEY_IN_BALANCE_TYPE, "in");
        values1.put(DatabaseHelper.KEY_IN_BALANCE_DATE, inCome.getDate());
        values1.put(DatabaseHelper.KEY_IN_BALANCE_AMOUNT, inCome.getAmount());
        long inCome_id1 = database.insert(DatabaseHelper.TABLE_BALANCE,null,values1);
    }
}
