package skot92.hu.unideb.hu.kiadaskezelo;

import android.test.AndroidTestCase;
import android.util.Log;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import skot92.hu.unideb.hu.kiadaskezelo.core.dao.InComeDAO;
import skot92.hu.unideb.hu.kiadaskezelo.core.entity.InComeEntity;

/**
 * Created by skot9 on 2015. 11. 08..
 */
public class InComeDAOTest extends AndroidTestCase {

    private static final Logger logger = Logger.getLogger(InComeDAOTest.class.getName());

    InComeDAO inComeDAO;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        inComeDAO = new InComeDAO(getContext());
    }

    public void findAmountGroupByDateTest() {
        Map<String, Integer> incomes = inComeDAO.findAmountGroupByDate();
        for (Map.Entry<String, Integer> entry : incomes.entrySet()) {
            logger.info(entry.getKey() + ": " + entry.getValue());
            System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
            Log.i("info",entry.getKey() + ": " + entry.getValue());
        }
    }


    public void getInComeGroubByNameTest() {
       List<InComeEntity> incomes = inComeDAO.getInComeGroubByName();
        for (InComeEntity in: incomes) {
            Log.d("asd",in.getDate());
            Log.i("asd",in.getName());
            Log.i("",Integer.toString(in.getAmount()));
        }
    }
}