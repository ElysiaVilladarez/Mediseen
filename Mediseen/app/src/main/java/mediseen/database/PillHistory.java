package mediseen.database;

import java.util.Date;

import io.realm.RealmObject;

/**
 * Created by elysi on 3/5/2016.
 */
public class PillHistory extends RealmObject {
    private Date createdDate;
    private int amountInInventory;

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public int getAmountInInventory() {
        return amountInInventory;
    }

    public void setAmountInInventory(int amountInInventory) {
        this.amountInInventory = amountInInventory;
    }
}
