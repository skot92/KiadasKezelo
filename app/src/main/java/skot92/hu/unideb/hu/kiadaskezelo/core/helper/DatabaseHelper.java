package skot92.hu.unideb.hu.kiadaskezelo.core.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by skot9 on 2015. 10. 18..
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    // Logcat tag
    private static final String LOG = "DatabaseHelper";

    // Database Version
    private static final int DATABASE_VERSION = 4;

    // Database Name
    private static final String DATABASE_NAME = "kiadas_es_bevetelkezelo";


    private static DatabaseHelper instance;



    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized DatabaseHelper getHelper(Context context) {
        if (instance == null)
            instance = new DatabaseHelper(context);
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // creating required tables
        try {
            db.execSQL(InComeTable.CREATE_TABLE);
            db.execSQL(BalanceTable.CREATE_TABLE);
            db.execSQL(ExpenseTable.CREATE_TABLE);
            db.execSQL(ExpenseDetailsTable.CREATE_TABLE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + InComeTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + BalanceTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ExpenseTable.CREATE_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + ExpenseDetailsTable.TABLE_NAME);

        // create new tables
        onCreate(db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            // Enable foreign key constraints
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }

}
