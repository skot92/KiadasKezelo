package skot92.hu.unideb.hu.kiadaskezelo.core.entity;

import java.io.Serializable;

/**
 * Created by skot9 on 2015. 10. 18..
 */
public class BalanceEntity implements Serializable {


    private long id;
    private String date;
    private int amount;
    private String type;

    public BalanceEntity() {
    }

    public BalanceEntity(int id, String date, String type, int amount) {
        this.id = id;
        this.date = date;
        this.type = type;
        this.amount = amount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
