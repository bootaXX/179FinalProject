package com.example.mis.finalproject;

import java.util.ArrayList;

/**
 * Created by Pauline Sarana on 12/10/2016.
 */

public class FolderItem {
    private String id;
    private String folderName;
    private ArrayList<String> lisofDirectories;

    public FolderItem() {

    }
    public FolderItem(String folderName) {
        this.folderName = folderName;
        lisofDirectories = new ArrayList<>();
    }

    public FolderItem(String folderName, ArrayList<String> listofDirectories) {
        this.folderName = folderName;
        this.lisofDirectories = listofDirectories;
    }


    //setters
    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public void setLisofDirectories(ArrayList<String> lisofDirectories) {
        this.lisofDirectories = lisofDirectories;
    }


    //getters
    public String getId() {
        return id;
    }

    public String getFolderName() {
        return folderName;
    }

    public ArrayList<String> getLisofDirectories() {
        return lisofDirectories;
    }
}
