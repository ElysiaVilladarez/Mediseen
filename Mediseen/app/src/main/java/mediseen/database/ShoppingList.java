package mediseen.database;

import io.realm.RealmObject;

/**
 * Created by elysi on 2/20/2016.
 */
public class ShoppingList extends RealmObject {
    private Pill pillToBuy;
    private boolean bought;
    private int howManyBought;

    public Pill getPillToBuy() {
        return pillToBuy;
    }

    public void setPillToBuy(Pill pillToBuy) {
        this.pillToBuy = pillToBuy;
    }

    public boolean isBought() {
        return bought;
    }

    public void setBought(boolean bought) {
        this.bought = bought;
    }

    public int getHowManyBought() {
        return howManyBought;
    }

    public void setHowManyBought(int howManyBought) {
        this.howManyBought = howManyBought;
    }
}
