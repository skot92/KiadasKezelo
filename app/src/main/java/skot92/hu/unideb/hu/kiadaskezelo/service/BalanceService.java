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
    InComeService inComeService;
    ExpenseService expenseService;

    public BalanceService(Context context){
        inComeService = new InComeService(context);
        expenseService = new ExpenseService(context);
        balanceDAO = new BalanceDAO(context);
    }

    public long save(BalanceEntity balance) {
        return balanceDAO.save(balance);
    }

    public int findBalance() {
        int income = inComeService.getSumAmount();
        int expense = -1 * expenseService.getSumAmount();
        int a = income - expense;
       return a;
    }
}
