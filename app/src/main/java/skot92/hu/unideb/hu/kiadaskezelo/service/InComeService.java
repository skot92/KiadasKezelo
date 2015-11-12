package skot92.hu.unideb.hu.kiadaskezelo.service;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import skot92.hu.unideb.hu.kiadaskezelo.core.dao.InComeDAO;
import skot92.hu.unideb.hu.kiadaskezelo.core.entity.InComeEntity;
import skot92.hu.unideb.hu.kiadaskezelo.core.helper.DatabaseHelper;

/**
 * Created by skot9 on 2015. 11. 12..
 */
public class InComeService {

    InComeDAO inComeDAO;

    public InComeService(Context context) {
        inComeDAO = new InComeDAO(context);
    }

    public long save(InComeEntity inCome) {
        return inComeDAO.save(inCome);
    }


    public List<String> getInComeNamesGroupByNames() {
        return inComeDAO.getInComeNamesGroupByNames();
    }

    public List<String> findInComeNames() {
        return inComeDAO.getInComeNames();
    }

    public List<InComeEntity> findInComes() {
        return  inComeDAO.getInCome();
    }


    public Map<String, Integer> findAmountGroupByDate() {
        return  inComeDAO.findAmountGroupByDate();
    }

}
