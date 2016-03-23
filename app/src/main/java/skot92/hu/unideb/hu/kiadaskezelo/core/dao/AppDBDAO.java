package skot92.hu.unideb.hu.kiadaskezelo.core.dao;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import skot92.hu.unideb.hu.kiadaskezelo.core.helper.DatabaseHelper;

/**
 * Created by skot9 on 2015. 10. 18..
 */
public class AppDBDAO {

    protected SQLiteDatabase database;
    private DatabaseHelper dbHelper;
    protected Context mContext;

    public AppDBDAO(Context context) {
        this.mContext = context;
        dbHelper = DatabaseHelper.getHelper(mContext);
        //open();

    }

    public void open() throws SQLException {
        if (dbHelper == null)
            dbHelper = DatabaseHelper.getHelper(mContext);
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
        //database = null;
        database.close();
    }
}
