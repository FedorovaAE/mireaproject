package ru.mirea.fedorova.mireaproject;

public class HistoryElement {
    private String heading;
    private String dateTime;
    private String text;

    public HistoryElement(String heading, String dateTime, String text){
        this.heading = heading;
        this.dateTime = dateTime;
        this.text = text;
    }

    public String getHeading() {
        return this.heading;
    }

    public String getDateTime() {
        return this.dateTime;
    }

    public String getText() {
        return this.text;
    }
}
