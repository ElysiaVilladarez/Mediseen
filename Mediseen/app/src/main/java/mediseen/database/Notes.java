package mediseen.database;


import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by elysi on 2/20/2016.
 */
public class Notes extends RealmObject {
    private String title;
    private String text;
    private String updatedDayOfWeek;
    private String updatedDate;
    private RealmList<Notes> editHistories;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUpdatedDayOfWeek() {
        return updatedDayOfWeek;
    }

    public void setUpdatedDayOfWeek(String updatedDayOfWeek) {
        this.updatedDayOfWeek = updatedDayOfWeek;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public RealmList<Notes> getEditHistories() {
        return editHistories;
    }

    public void setEditHistories(RealmList<Notes> editHistories) {
        this.editHistories = editHistories;
    }
}
