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
    private int version;
    private RealmList<NotesHistory> editHistories = new RealmList();

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

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

    public RealmList<NotesHistory> getEditHistories() {
        return editHistories;
    }

    public void setEditHistories(RealmList<NotesHistory> editHistories) {
        this.editHistories = editHistories;
    }
}
