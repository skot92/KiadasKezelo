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
import skot92.hu.unideb.hu.kiadaskezelo.ui.activity.all.AllIncomeActivity;

/**
 * Created by skot9 on 2015. 11. 12..
 */
public class InComeService {

    private InComeDAO inComeDAO;
    private String descOrAsc = "ASC";

    public InComeService(Context context) {
        inComeDAO = new InComeDAO(context);
    }

    public long save(InComeEntity inCome) {
        return inComeDAO.save(inCome);
    }




    public List<InComeEntity> findInComes(String orderBy) {
        if(descOrAsc.equals("ASC")) {
            descOrAsc="DESC";
            return inComeDAO.getInCome(orderBy, descOrAsc);
        }
        else {
            descOrAsc="ASC";
            return inComeDAO.getInCome(orderBy, descOrAsc);
        }
    }



}
