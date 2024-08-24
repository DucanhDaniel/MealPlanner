package data.type;

public class RecipeTitle {
    private final String title;
    private final String url;
    private final String totalViews;
    public RecipeTitle(String title, String url, String totalViews) {
        this.title = title;
        this.url = url;
        this.totalViews = totalViews;
    }
    public String getTitle() {
        return title;
    }
    public String getUrl() {
        return url;
    }
    public String getTotalViews() {
        return totalViews;
    }
    @Override
    public String toString() {
        return title + "\n" +
                "URL: " + url + "\n" +
                "Total Views: " + totalViews;
    }
}
