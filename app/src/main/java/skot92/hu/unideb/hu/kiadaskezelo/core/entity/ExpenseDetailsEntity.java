package skot92.hu.unideb.hu.kiadaskezelo.core.entity;

import java.io.Serializable;

/**
 * Created by skot9 on 2015. 10. 19..
 */
public class ExpenseDetailsEntity implements Serializable {

    private long id;
    private long expnseId;
    private String name;
    private String description;
    private int amount;

    public ExpenseDetailsEntity() {
    }

    public ExpenseDetailsEntity(long id, long expnseId, String name, String description, int amount) {
        this.id = id;
        this.expnseId = expnseId;
        this.name = name;
        this.description = description;
        this.amount = amount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getExpnseId() {
        return expnseId;
    }

    public void setExpnseId(long expnseId) {
        this.expnseId = expnseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ExpenseDetailsEntity{" +
                "id=" + id +
                ", expnseId=" + expnseId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                '}';
    }
}
