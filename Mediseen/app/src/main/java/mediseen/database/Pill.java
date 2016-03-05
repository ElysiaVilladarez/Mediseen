package mediseen.database;

import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by elysi on 2/20/2016.
 * This is a table for all the pills the user creates
 */
public class Pill extends RealmObject {
    private String name;
    private String dosage;
    private int amountInInventory;
    private int amountTillShopping;
    private Date updatedDate;
    private RealmList<PillHistory> editHistories = new RealmList();

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public RealmList<PillHistory> getEditHistories() {
        return editHistories;
    }

    public void setEditHistories(RealmList<PillHistory> editHistories) {
        this.editHistories = editHistories;
    }

    public String getName() {
        return name;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public int getAmountInInventory() {
        return amountInInventory;
    }

    public void setAmountInInventory(int amountInInventory) {
        this.amountInInventory = amountInInventory;
    }

    public int getAmountTillShopping() {
        return amountTillShopping;
    }

    public void setAmountTillShopping(int amountTillShopping) {
        this.amountTillShopping = amountTillShopping;
    }


    public void setName(String name) {
        this.name = name;
    }
}
