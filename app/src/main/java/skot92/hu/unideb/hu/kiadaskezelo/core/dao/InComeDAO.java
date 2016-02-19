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
import skot92.hu.unideb.hu.kiadaskezelo.core.helper.InComeTable;

/**
 * Created by skot9 on 2015. 10. 18..
 */
public class InComeDAO extends AppDBDAO {
    public InComeDAO(Context context) {
        super(context);
    }

    public long save(InComeEntity inCome) {
        super.open();
        ContentValues values = new ContentValues();
        values.put(InComeTable.IN_COME_NAME, inCome.getName());
        values.put(InComeTable.IN_COME_DATE, inCome.getDate());
        values.put(InComeTable.IN_COME_AMOUNT, inCome.getAmount());
        long inCome_id = database.insert(InComeTable.TABLE_NAME, null, values);

        BalanceDAO balanceDAO = new BalanceDAO(mContext);
        BalanceEntity balanceEntity = new BalanceEntity();
        balanceEntity.setAmount(inCome.getAmount());
        balanceEntity.setDate(inCome.getDate());
        balanceEntity.setType("in");
        balanceDAO.save(balanceEntity);
        super.close();
        return inCome_id;

    }

    public List<String> getInComeNamesGroupByNames() {
        super.open();
        List<String> names = new ArrayList<String>();
        Cursor cursor = database.query(InComeTable.TABLE_NAME,
                new String[]{InComeTable.IN_COME_NAME}, null, null, InComeTable.IN_COME_NAME, null, null);
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex(InComeTable.IN_COME_NAME));
            names.add(name);
        }
        cursor.close();
        super.close();
        return names;
    }

    public List<String> getInComeNames() {
        super.open();
        List<String> names = new ArrayList<String>();
        Cursor cursor = database.query(InComeTable.TABLE_NAME,
                new String[]{InComeTable.IN_COME_NAME}, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex(InComeTable.IN_COME_NAME));
            names.add(name);
        }
        cursor.close();
        super.close();
        return names;
    }

    public List<InComeEntity> getInCome(String orderBy, String descOrAsc) {
        super.open();
        Cursor cursor;

        switch (orderBy) {
            case "name":
                cursor = database.query(InComeTable.TABLE_NAME, new String[]{
                        InComeTable.IN_COME_AMOUNT, InComeTable.IN_COME_DATE,
                        InComeTable.IN_COME_NAME, InComeTable.IN_COME_ID}, null, null, null, null, InComeTable.IN_COME_NAME + " " + descOrAsc);
                break;
            case "date":
                cursor = database.query(InComeTable.TABLE_NAME, new String[]{
                        InComeTable.IN_COME_AMOUNT, InComeTable.IN_COME_DATE,
                        InComeTable.IN_COME_NAME, InComeTable.IN_COME_ID}, null, null, null, null, InComeTable.IN_COME_DATE + " " + descOrAsc);
                break;
            case "amount":
                cursor = database.query(InComeTable.TABLE_NAME, new String[]{
                        InComeTable.IN_COME_AMOUNT, InComeTable.IN_COME_DATE,
                        InComeTable.IN_COME_NAME, InComeTable.IN_COME_ID}, null, null, null, null, InComeTable.IN_COME_AMOUNT + " " + descOrAsc);
                break;
            default:
                cursor = database.query(InComeTable.TABLE_NAME, new String[]{
                        InComeTable.IN_COME_AMOUNT, InComeTable.IN_COME_DATE,
                        InComeTable.IN_COME_NAME, InComeTable.IN_COME_ID}, null, null, null, null, null);
        }


        List<InComeEntity> inComeEntities = new ArrayList<InComeEntity>();
        while (cursor.moveToNext()) {
            InComeEntity entity = new InComeEntity();
            entity.setName(cursor.getString(cursor.getColumnIndex(InComeTable.IN_COME_NAME)));
            entity.setDate(cursor.getString(cursor.getColumnIndex(InComeTable.IN_COME_DATE)));
            entity.setAmount(cursor.getInt(cursor.getColumnIndex(InComeTable.IN_COME_AMOUNT)));
            entity.setId(cursor.getLong(cursor.getColumnIndex(InComeTable.IN_COME_ID)));
            inComeEntities.add(entity);
        }
        cursor.close();
        super.close();
        return inComeEntities;
    }


    public Map<String, Integer> findAmountGroupByDate() {
        super.open();
        Map<String, Integer> res = new HashMap<>();
        Cursor cursor = database.query(InComeTable.TABLE_NAME, new String[]{"sum( " +
                        InComeTable.IN_COME_AMOUNT + ")", InComeTable.IN_COME_DATE},
                null, null, InComeTable.IN_COME_DATE, null, null);
        while (cursor.moveToNext()) {
            int amount = cursor.getInt(0);
            String date = cursor.getString(1);
            res.put(date, amount);
        }
        cursor.close();
        super.close();
        return res;
    }

    public List<InComeEntity> getInComeGroubByName() {
        super.open();
        Cursor cursor = database.query(InComeTable.TABLE_NAME, new String[]{"sum( " +
                        InComeTable.IN_COME_AMOUNT + ")", InComeTable.IN_COME_NAME},
                null, null, InComeTable.IN_COME_NAME, null, null);

        List<InComeEntity> inComeEntities = new ArrayList<InComeEntity>();
        while (cursor.moveToNext()) {
            InComeEntity entity = new InComeEntity();
            entity.setName(cursor.getString(cursor.getColumnIndex(InComeTable.IN_COME_NAME)));
            entity.setAmount(cursor.getInt(0));
            inComeEntities.add(entity);
        }
        cursor.close();
        super.close();
        return inComeEntities;
    }


}
