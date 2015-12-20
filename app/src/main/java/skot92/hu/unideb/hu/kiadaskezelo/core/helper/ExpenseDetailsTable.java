package skot92.hu.unideb.hu.kiadaskezelo.core.helper;

/**
 * Created by skot9 on 2015. 12. 20..
 */
public class ExpenseDetailsTable {

    public static final String TABLE_NAME = "expense_details";

    public static final String EXPENSE_DETAILS_ID = "id";
    public static final String EXPENSE_DETAILS_EXPENSE_ID = "expense_id";
    public static final String EXPENSE_DETAILS_NAME = "expense_details_name";
    public static final String EXPENSE_DETAILS_DESCRIPTION = "expense_details_description";
    public static final String EXPENSE_DETAILS_AMOUNT = "expense_details_amount";


    public static final String CREATE_TABLE = "CREATE TABLE "
            + TABLE_NAME
            + "("
            + EXPENSE_DETAILS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + EXPENSE_DETAILS_EXPENSE_ID + " INTEGER NOT NULL, "
            + EXPENSE_DETAILS_NAME + " TEXT  NOT NULL, "
            + EXPENSE_DETAILS_DESCRIPTION + " TEXT, "
            + EXPENSE_DETAILS_AMOUNT + " INTEGER NOT NULL, "
            + "FOREIGN KEY(" + EXPENSE_DETAILS_EXPENSE_ID
            + ") REFERENCES " + ExpenseTable.TABLE_NAME + "(" + ExpenseTable.EXPENSE_ID + ")"
            + ")";
}
