package skot92.hu.unideb.hu.kiadaskezelo.service;

import android.content.Context;

import java.util.List;

import skot92.hu.unideb.hu.kiadaskezelo.core.dao.ExpenseDAO;
import skot92.hu.unideb.hu.kiadaskezelo.core.entity.ExpenseEntity;

/**
 * Created by skot9 on 2015. 11. 12..
 */
public class ExpenseService {

    ExpenseDAO expenseDAO;

    public ExpenseService(Context context) {
        expenseDAO = new ExpenseDAO(context);
    }

    public long save(ExpenseEntity expense) {
        return expenseDAO.save(expense);
    }

    public long update(int sum, long id) {
        return expenseDAO.update(sum, id);
    }

    public List<ExpenseEntity> findAll() {
        return expenseDAO.getAll();
    }

    public int getSumAmount() {
        return expenseDAO.getSumAmount();
    }

    public int getSumAmountByDate(String fromDate) {
        return expenseDAO.getSumAmountByDate(fromDate);
    }
}
