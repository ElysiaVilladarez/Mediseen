package mediseen.database;


import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by elysi on 2/20/2016.
 */
public class Notes extends RealmObject {
    private String title;
    private String text;
    private Date updatedDate;
    private RealmList<Notes> editHistories = new RealmList<>();

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

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public RealmList<Notes> getEditHistories() {
        return editHistories;
    }

    public void setEditHistories(RealmList<Notes> editHistories) {
        this.editHistories = editHistories;
    }
}
