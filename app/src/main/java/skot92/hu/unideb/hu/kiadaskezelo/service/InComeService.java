package skot92.hu.unideb.hu.kiadaskezelo.service;

import android.content.Context;

import java.util.List;

import skot92.hu.unideb.hu.kiadaskezelo.core.dao.InComeDAO;
import skot92.hu.unideb.hu.kiadaskezelo.core.entity.InComeEntity;

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
        if (descOrAsc.equals("ASC")) {
            descOrAsc = "DESC";
            return inComeDAO.getInCome(orderBy, descOrAsc);
        } else {
            descOrAsc = "ASC";
            return inComeDAO.getInCome(orderBy, descOrAsc);
        }
    }


    public List<InComeEntity> findInComesSearchByAmount(String searchBy, String sum) {
        if (searchBy.compareTo("Nagyobb") == 0) {
            return inComeDAO.findInComesSearchByAmount(">", sum);
        }

        if (searchBy.compareTo("Kisebb") == 0) {
            return inComeDAO.findInComesSearchByAmount("<", sum);
        }

        return inComeDAO.findInComesSearchByAmount("=", sum);

    }

    public List<InComeEntity> findInComesSearchByName(String name) {

        return inComeDAO.findInComesSearchByName(name);

    }


}
