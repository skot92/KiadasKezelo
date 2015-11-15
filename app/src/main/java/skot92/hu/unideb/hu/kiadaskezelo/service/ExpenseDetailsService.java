package skot92.hu.unideb.hu.kiadaskezelo.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import skot92.hu.unideb.hu.kiadaskezelo.core.dao.ExpenseDetailsDAO;
import skot92.hu.unideb.hu.kiadaskezelo.core.entity.ExpenseDetailsEntity;
import skot92.hu.unideb.hu.kiadaskezelo.core.helper.DatabaseHelper;

/**
 * Created by skot9 on 2015. 11. 12..
 */
public class ExpenseDetailsService {

    ExpenseDetailsDAO expenseDetailsDAO;

    public  ExpenseDetailsService(Context context){
        expenseDetailsDAO = new ExpenseDetailsDAO(context);
    }

    public List<ExpenseDetailsEntity> findById(long expenseId) {
       return expenseDetailsDAO.findById(expenseId);
    }

    public int save(List<ExpenseDetailsEntity> details, Long expenseId ) {
        return expenseDetailsDAO.save(details,expenseId);
    }
}