package skot92.hu.unideb.hu.kiadaskezelo.core.helper;

/**
 * Created by skot9 on 2015. 12. 20..
 */
public class ExpenseTable {

    public static final String TABLE_NAME = "expense";
    public static final String EXPENSE_ID = "id";
    public static final String EXPENSE_DATE = "expense_date";
    public static final String EXPENSE_NAME = "expense_name";
    public static final String EXPENSE_AMOUNT = "expense_amount";

    public static final String CREATE_TABLE = "CREATE TABLE "
            + TABLE_NAME
            + "("
            + EXPENSE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + EXPENSE_AMOUNT + " INTEGER NOT NULL, "
            + EXPENSE_DATE + " datetime  NOT NULL, "
            + EXPENSE_NAME + " TEXT  NOT NULL"
            + ")";
}
