package mediseen.search;

/**
 * Created by elysi on 3/27/2016.
 */
public class SearchObject {
    private String title, body;

    public SearchObject(String t, String b){
        title = t;
        body = b;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }
}
