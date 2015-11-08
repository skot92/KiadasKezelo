package skot92.hu.unideb.hu.kiadaskezelo.core.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import skot92.hu.unideb.hu.kiadaskezelo.core.entity.BalanceEntity;
import skot92.hu.unideb.hu.kiadaskezelo.core.entity.InComeEntity;
import skot92.hu.unideb.hu.kiadaskezelo.core.helper.DatabaseHelper;

/**
 * Created by skot9 on 2015. 10. 18..
 */
public class InComeDAO extends AppDBDAO {
    public InComeDAO(Context context) {
        super(context);
    }

    public long save(InComeEntity inCome) {
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

        return inCome_id;

    }

    public List<String> getInComeNamesGroupByNames() {
        List<String> names = new ArrayList<String>();
        Cursor cursor = database.query( DatabaseHelper.TABLE_IN_COME,
                new String[]{DatabaseHelper.COLUMN_IN_COME_NAME}, null, null, DatabaseHelper.COLUMN_IN_COME_NAME, null, null);
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_IN_COME_NAME));
            names.add(name);
        }
        return names;
    }

    public List<String> getInComeNames() {
        List<String> names = new ArrayList<String>();
        Cursor cursor = database.query( DatabaseHelper.TABLE_IN_COME,
                new String[]{DatabaseHelper.COLUMN_IN_COME_NAME}, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_IN_COME_NAME));
            names.add(name);
        }
        return names;
    }

    public List<InComeEntity> getInCome() {
        Cursor cursor = database.query(DatabaseHelper.TABLE_IN_COME, new String[]{
                DatabaseHelper.COLUMN_IN_COME_AMOUNT, DatabaseHelper.COLUMN_IN_COME_DATE,
                DatabaseHelper.COLUMN_IN_COME_NAME,DatabaseHelper.COLUMN_IN_COME_ID}, null, null, null, null, null) ;

        List<InComeEntity> inComeEntities = new ArrayList<InComeEntity>();
        while (cursor.moveToNext()) {
            InComeEntity entity = new InComeEntity();
            entity.setName(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_IN_COME_NAME)));
            entity.setDate(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_IN_COME_DATE)));
            entity.setAmount(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_IN_COME_AMOUNT)));
            entity.setId(cursor.getLong(cursor.getColumnIndex(DatabaseHelper.COLUMN_IN_COME_ID)));
            inComeEntities.add(entity);
        }
        return  inComeEntities;
    }


    public Map<String, Integer> findAmountGroupByDate(){
        Map<String, Integer> res = new HashMap<>();
        Cursor cursor = database.query(DatabaseHelper.TABLE_IN_COME, new String[]{"sum( " +
                        DatabaseHelper.COLUMN_IN_COME_AMOUNT + ")", DatabaseHelper.COLUMN_IN_COME_DATE},
                null, null, DatabaseHelper.COLUMN_IN_COME_DATE, null, null);
        while (cursor.moveToNext()) {
            int amount = cursor.getInt(0);
            String date = cursor.getString(1);
            res.put(date, amount);
        }
        return res;
    }



}
