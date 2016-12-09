package com.example.mis.finalproject;

/**
 * Created by Pauline Sarana on 12/10/2016.
 */

public class EventItem {
    private String id;
    private String title;
    private String details;
    private String folderLocation;

    private String dateStart;
    private String dateEnd;
    private String timeStart;
    private String timeEnd;

    //constructors
    public EventItem() {}

    public EventItem(String id, String title, String details, String folderLocation, String dateStart, String dateEnd, String timeStart, String timeEnd) {
        this.id = id;
        this.title = title;
        this.details = details;
        this.folderLocation = folderLocation;

        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }

    //setters
    public void setTitle(String title) {
        this.title = title;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setFolderLocation(String folderLocation){
        this.folderLocation = folderLocation;
    }


    //setters - time and date
    public void setDateStart(String date) {
        this.dateStart = date;
    }

    public void setDateEnd(String date) {
        this.dateEnd = date;
    }

    public void setTimeStart(String time) {
        this.timeStart = time;
    }

    public void setTimeEnd(String time) {
        this.timeEnd = time;
    }


    //getters
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDetails() {
        return details;
    }

    public String getFolderLocation() {
        return folderLocation;
    }


    //getters - time and date
    public String getDateStart() {
        return  dateStart;
    }

    public String getDateEnd() {
        return  dateEnd;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public String getTimeEnd(){
        return timeEnd;
    }
}
