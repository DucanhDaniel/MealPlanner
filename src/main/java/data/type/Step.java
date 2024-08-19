package data.type;

import java.util.ArrayList;

public class Step {
    private int number;
    private String stepTitle;
    private String stepDescription;
    private ArrayList<String> imageURLs;
    public Step(int number, String stepTitle, String stepDescription, ArrayList<String> imageURLs) {
        this.number = number;
        this.stepTitle = stepTitle;
        this.stepDescription = stepDescription;
        this.imageURLs = imageURLs;
    }
    public Step(){
        imageURLs = new ArrayList<>();
    }
    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public String getStepTitle() {
        return stepTitle;
    }
    public void setStepTitle(String stepTitle) {
        this.stepTitle = stepTitle;
    }
    public String getStepDescription() {
        return stepDescription;
    }
    public void setStepDescription(String stepDescription) {
        this.stepDescription = stepDescription;
    }
    public ArrayList<String> getImageURLs() {
        return imageURLs;
    }
    public void setImageURLs(ArrayList<String> imageURLs) {
        this.imageURLs = imageURLs;
    }
    public void addImageURL(String imageURL) {
        imageURLs.add(imageURL);
    }
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(number + ". " + stepTitle + "\n" + stepDescription);
        result.append("Image URLs:\n");
        for (String url : imageURLs) {
            result.append("- ").append(url).append("\n");
        }
        return result.toString();
    }
}
