package uk.ac.tees.mad.w9532363.quick_meal;

public class Model_Firebase {

    private String content;
    private String title;

    public Model_Firebase()
    {

    }

    public Model_Firebase(String content, String title) {
        this.content = content;
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
