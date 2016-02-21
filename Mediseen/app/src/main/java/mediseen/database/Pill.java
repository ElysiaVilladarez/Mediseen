package mediseen.database;

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
   // private RealmList<Pill> editHistories;

//    public RealmList<Pill> getEditHistories() {
//        return editHistories;
//    }
//
//    public void setEditHistories(RealmList<Pill> editHistories) {
//        this.editHistories = editHistories;
//    }

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
