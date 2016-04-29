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

import skot92.hu.unideb.hu.kiadaskezelo.core.entity.BalanceEntity;
import skot92.hu.unideb.hu.kiadaskezelo.core.entity.InComeEntity;
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
        Log.d("date", inCome.getDate());
        balanceEntity.setType("in");
        balanceDAO.save(balanceEntity);
        super.close();
        return inCome_id;

    }

    public List<InComeEntity> getInCome(String orderBy, String descOrAsc) {
        super.open();
        Cursor cursor;

        if (orderBy.equals("")) {
            cursor = database.query(InComeTable.TABLE_NAME, new String[]{
                    InComeTable.IN_COME_AMOUNT, InComeTable.IN_COME_DATE,
                    InComeTable.IN_COME_NAME, InComeTable.IN_COME_ID}, null, null, null, null, null);
        } else {
            cursor = database.query(InComeTable.TABLE_NAME, new String[]{
                            InComeTable.IN_COME_AMOUNT, InComeTable.IN_COME_DATE,
                            InComeTable.IN_COME_NAME, InComeTable.IN_COME_ID}, null, null, null, null,
                    orderBy + " " + descOrAsc);
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


    public List<InComeEntity> findInComesSearchByAmount(String searchBy, String sum) {
        super.open();
        Cursor cursor;
        cursor = database.query(InComeTable.TABLE_NAME, new String[]{
                        InComeTable.IN_COME_AMOUNT, InComeTable.IN_COME_DATE,
                        InComeTable.IN_COME_NAME, InComeTable.IN_COME_ID},
                InComeTable.IN_COME_AMOUNT + " " + searchBy + "?", new String[]{sum}, null, null, null);

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

    public List<InComeEntity> findInComesSearchByDate(String name) {
        super.open();
        Cursor cursor;
        cursor = database.query(InComeTable.TABLE_NAME, new String[]{
                        InComeTable.IN_COME_AMOUNT, InComeTable.IN_COME_DATE,
                        InComeTable.IN_COME_NAME, InComeTable.IN_COME_ID},
                InComeTable.IN_COME_NAME + " LIKE " + "?", new String[]{name}, null, null, null);

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

    public List<InComeEntity> findInComesSearchByDate(String searchBy, String date) {
        Log.d("date", date);
        Log.d("searchBy", searchBy);

        super.open();
        Cursor cursor;
        cursor = database.query(InComeTable.TABLE_NAME, new String[]{
                        InComeTable.IN_COME_AMOUNT, InComeTable.IN_COME_DATE,
                        InComeTable.IN_COME_NAME, InComeTable.IN_COME_ID},
                InComeTable.IN_COME_DATE + " " + searchBy + "Datetime(?)", new String[]{date}, null, null, null);

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

    public int getSumAmount() {
        super.open();
        Cursor c = database.query(InComeTable.TABLE_NAME, new String[]{
                        "SUM( " + InComeTable.IN_COME_AMOUNT + " )"},
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

        Cursor c1 = database.rawQuery("SELECT " + InComeTable.IN_COME_AMOUNT + " , " + InComeTable.IN_COME_DATE
                + " FROM " + InComeTable.TABLE_NAME, null);

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

    public List<InComeEntity> findAll() {
        super.open();
        int amount;
        String name;
        String date;
        Long id;
        List<InComeEntity> incomes = new ArrayList<InComeEntity>();

        Cursor c = database.query(InComeTable.TABLE_NAME, new String[]{
                        InComeTable.IN_COME_AMOUNT,
                        InComeTable.IN_COME_NAME,
                        InComeTable.IN_COME_DATE,
                        InComeTable.IN_COME_ID,},
                null, null,InComeTable.IN_COME_NAME, null, null);

        while (c.moveToNext()) {

            amount = c.getInt(c.getColumnIndex(InComeTable.IN_COME_AMOUNT));
            name = c.getString(c.getColumnIndex(InComeTable.IN_COME_NAME));
            id = c.getLong(c.getColumnIndex(InComeTable.IN_COME_ID));
            date = c.getString(c.getColumnIndex(InComeTable.IN_COME_DATE));
            InComeEntity expense = new InComeEntity(id,name,date,amount);
            incomes.add(expense);

        }
        c.close();
        super.close();
        return incomes;
    }

}
