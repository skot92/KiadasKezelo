package skot92.hu.unideb.hu.kiadaskezelo.core;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by skot9 on 2015. 09. 25..
 */
public class ExpenseSQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_BUY = "buy";
    public static final String COLUMN_ID = "_id";

    public static final String COLUMN_NAME = "comment";
    public static final String COLUMN_DESCERPTION = "descerption";
    public static final String COLUMN_PIERCE = "pierce";
    public static final String COLUMN_DATE = "date";

    private static final String DATABASE_NAME = "buy.db";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_BUY + "(" + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_NAME + " text not null,"
            + COLUMN_DESCERPTION + " text not null,"
            + COLUMN_PIERCE + " INTEGER not null,"
            + COLUMN_DATE + " text not null" + ");";

    public ExpenseSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_BUY);
        onCreate(sqLiteDatabase);

    }
}
