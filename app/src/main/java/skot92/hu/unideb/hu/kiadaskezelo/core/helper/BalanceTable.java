package skot92.hu.unideb.hu.kiadaskezelo.core.helper;

/**
 * Created by skot9 on 2015. 12. 20..
 */
public class BalanceTable {

    public static final String TABLE_NAME = "balance";

    public static final String BALANCE_ID = "id";
    public static final String BALANCE_DATE = "balance_date";
    public static final String BALANCE_TYPE = "balance_type";
    public static final String BALANCE_AMOUNT = "balance_amount";


    public static final String CREATE_TABLE = "CREATE TABLE "
            + TABLE_NAME
            + "("
            + BALANCE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + BALANCE_AMOUNT + " INTEGER NOT NULL, "
            + BALANCE_DATE + " datetime  NOT NULL, "
            + BALANCE_TYPE + " TEXT  NOT NULL"
            + ")";
}
