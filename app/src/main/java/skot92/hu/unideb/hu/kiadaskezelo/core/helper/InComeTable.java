package skot92.hu.unideb.hu.kiadaskezelo.core.helper;

/**
 * Created by skot9 on 2015. 12. 20..
 */
public class InComeTable {

    public static final String TABLE_NAME = "in_come";

    public static final String IN_COME_ID = "id";
    public static final String IN_COME_NAME = "income_name";
    public static final String IN_COME_DATE = "income_date";
    public static final String IN_COME_AMOUNT = "income_amount";


    public static final String CREATE_TABLE = "CREATE TABLE "
            + TABLE_NAME
            + "("
            + IN_COME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + IN_COME_AMOUNT + " INTEGER NOT NULL, "
            + IN_COME_NAME + " TEXT  NOT NULL, "
            + IN_COME_DATE + " datetime  NOT NULL"
            + ")";
}
