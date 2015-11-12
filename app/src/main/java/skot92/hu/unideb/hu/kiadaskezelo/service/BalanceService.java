package skot92.hu.unideb.hu.kiadaskezelo.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import skot92.hu.unideb.hu.kiadaskezelo.core.dao.BalanceDAO;
import skot92.hu.unideb.hu.kiadaskezelo.core.entity.BalanceEntity;
import skot92.hu.unideb.hu.kiadaskezelo.core.helper.DatabaseHelper;

/**
 * Created by skot9 on 2015. 11. 12..
 */
public class BalanceService {

    BalanceDAO balanceDAO;

    public BalanceService(Context context){
        balanceDAO = new BalanceDAO(context);
    }

    public long save(BalanceEntity balance) {
        return balanceDAO.save(balance);
    }

    public int findBalance() {
       return balanceDAO.getBalance();
    }
}
