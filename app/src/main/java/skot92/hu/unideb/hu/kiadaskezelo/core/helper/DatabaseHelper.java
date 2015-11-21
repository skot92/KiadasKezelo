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
    private static final int DATABASE_VERSION = 2;

    // Database Name
    private static final String DATABASE_NAME = "kiadas_es_bevetelkezelo";

    // Table Names
    public static final String TABLE_IN_COME = "in_come";
    public static final String TABLE_BALANCE = "balance";
    public static final String TABLE_EXPENSE = "expense";
    public static final String TABLE_EXPENSE_DETAILS = "expense_details";

    //COLUMN TABLE_IN_COME
    public static final String COLUMN_IN_COME_ID = "id";
    public static final String COLUMN_IN_COME_NAME = "income_name";
    public static final String COLUMN_IN_COME_DATE = "income_date";
    public static final String COLUMN_IN_COME_AMOUNT = "income_amount";

    //COLUMN TABLE_BALANCE
    public static final String COLUMN_BALANCE_ID = "id";
    public static final String COLUMN_BALANCE_DATE = "balance_date";
    public static final String COLUMN_BALANCE_TYPE = "balance_type";
    public static final String COLUMN_BALANCE_AMOUNT = "balance_amount";

    //COLUMN TABLE_EXPENSE
    public static final String COLUMN_EXPENSE_ID = "id";
    public static final String COLUMN_EXPENSE_DATE = "expense_date";
    public static final String COLUMN_EXPENSE_NAME = "expense_name";
    public static final String COLUMN_EXPENSE_AMOUNT = "expense_amount";

    //COLUMN TABLE_EXPENSE_DETAILS
    public static final String COLUMN_EXPENSE_DETAILS_ID = "id";
    public static final String COLUMN_EXPENSE_DETAILS_EXPENSE_ID = "expense_id";
    public static final String COLUMN_EXPENSE_DETAILS_NAME = "expense_details_name";
    public static final String COLUMN_EXPENSE_DETAILS_DESCRIPTION = "expense_details_description";
    public static final String COLUMN_EXPENSE_DETAILS_AMOUNT = "expense_details_amount";

    private static DatabaseHelper instance;

    // Table Create Statements
    private static final String CREATE_TABLE_IN_COME = "CREATE TABLE "
            + TABLE_IN_COME
            + "("
            + COLUMN_IN_COME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_IN_COME_AMOUNT + " INTEGER NOT NULL, "
            + COLUMN_IN_COME_NAME + " TEXT  NOT NULL, "
            + COLUMN_IN_COME_DATE + " datetime  NOT NULL"
            + ")";

    private static final String CREATE_TABLE_BALANCE = "CREATE TABLE "
            + TABLE_BALANCE
            + "("
            + COLUMN_BALANCE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_BALANCE_AMOUNT + " INTEGER NOT NULL, "
            + COLUMN_BALANCE_DATE + " datetime  NOT NULL, "
            + COLUMN_BALANCE_TYPE + " TEXT  NOT NULL"
            + ")";

    private static final String CREATE_TABLE_EXPENSE = "CREATE TABLE "
            + TABLE_EXPENSE
            + "("
            + COLUMN_EXPENSE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_EXPENSE_AMOUNT + " INTEGER NOT NULL, "
            + COLUMN_EXPENSE_DATE + " datetime  NOT NULL, "
            + COLUMN_EXPENSE_NAME + " TEXT  NOT NULL"
            + ")";

    private static final String CREATE_TABLE_EXPENSE_DETAILS = "CREATE TABLE "
            + TABLE_EXPENSE_DETAILS
            + "("
            + COLUMN_EXPENSE_DETAILS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_EXPENSE_DETAILS_EXPENSE_ID + " INTEGER NOT NULL, "
            + COLUMN_EXPENSE_DETAILS_NAME + " TEXT  NOT NULL, "
            + COLUMN_EXPENSE_DETAILS_DESCRIPTION + " TEXT, "
            + COLUMN_EXPENSE_DETAILS_AMOUNT + " INTEGER NOT NULL, "
            + "FOREIGN KEY(" + COLUMN_EXPENSE_DETAILS_EXPENSE_ID
                + ") REFERENCES " + TABLE_EXPENSE + "(" + COLUMN_EXPENSE_ID + ")"
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
        try {
            db.execSQL(CREATE_TABLE_IN_COME);
            db.execSQL(CREATE_TABLE_BALANCE);
            db.execSQL(CREATE_TABLE_EXPENSE);
            db.execSQL(CREATE_TABLE_EXPENSE_DETAILS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_IN_COME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BALANCE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXPENSE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXPENSE_DETAILS);

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
