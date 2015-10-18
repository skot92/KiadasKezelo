package skot92.hu.unideb.hu.kiadaskezelo.core.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import skot92.hu.unideb.hu.kiadaskezelo.core.entity.BalanceEntity;
import skot92.hu.unideb.hu.kiadaskezelo.core.entity.InComeEntity;

/**
 * Created by skot9 on 2015. 10. 18..
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    // Logcat tag
    private static final String LOG = "DatabaseHelper";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "kiadas_es_bevetelkezelo";

    // Table Names
    public static final String TABLE_IN_COME = "in_come";
    public static final String TABLE_BALANCE = "balance";

    // Common column names
    public static final String KEY_IN_COME_ENTITY_ID = "id";
    public static final String KEY_IN_COME_ENTITY_NAME = "income_name";
    public static final String KEY_IN_COME_ENTITY_DATE = "income_date";
    public static final String KEY_IN_COME_ENTITY_AMOUNT = "income_amount";

    public static final String KEY_IN_BALANCE_ID = "id";
    public static final String KEY_IN_BALANCE_DATE = "balance_date";
    public static final String KEY_IN_BALANCE_TYPE= "balance_type";
    public static final String KEY_IN_BALANCE_AMOUNT = "balance_amount";

    private static DatabaseHelper instance;

    // Table Create Statements
    // Todo table create statement
    private static final String CREATE_TABLE_IN_COME_ENTITY_ = "CREATE TABLE "
            + TABLE_IN_COME
            + "("
            + KEY_IN_COME_ENTITY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_IN_COME_ENTITY_AMOUNT + " INTEGER NOT NULL, "
            + KEY_IN_COME_ENTITY_NAME + " TEXT  NOT NULL, "
            + KEY_IN_COME_ENTITY_DATE + " datetime  NOT NULL"
            + ")";

    private static final String CREATE_TABLE_BALANCE_ENTITY = "CREATE TABLE "
            + TABLE_BALANCE
            + "("
            + KEY_IN_BALANCE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_IN_BALANCE_AMOUNT + " INTEGER NOT NULL, "
            + KEY_IN_BALANCE_DATE + " datetime  NOT NULL, "
            + KEY_IN_BALANCE_TYPE + " TEXT  NOT NULL"
            + ")";


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
        db.execSQL(CREATE_TABLE_IN_COME_ENTITY_);
        db.execSQL(CREATE_TABLE_BALANCE_ENTITY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_IN_COME_ENTITY_);
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_BALANCE_ENTITY);

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
