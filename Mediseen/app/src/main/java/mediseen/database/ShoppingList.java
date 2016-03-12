package mediseen.database;

import io.realm.RealmObject;

/**
 * Created by elysi on 2/20/2016.
 */
public class ShoppingList extends RealmObject {
    private Pill pillToBuy;

    public Pill getPillToBuy() {
        return pillToBuy;
    }

    public void setPillToBuy(Pill pillToBuy) {
        this.pillToBuy = pillToBuy;
    }
}
