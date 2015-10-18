package skot92.hu.unideb.hu.kiadaskezelo.core.entity;

import java.io.Serializable;

/**
 * Created by skot9 on 2015. 10. 19..
 */
public class ExpenseEntity implements Serializable {

    private long id;
    private String name;
    private String date;
    private int amount;

    public ExpenseEntity() {
    }

    public ExpenseEntity(long id, String name, String date, int amount) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.amount = amount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
