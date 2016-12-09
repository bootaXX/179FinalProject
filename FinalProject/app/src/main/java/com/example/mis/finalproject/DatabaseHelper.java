package com.example.mis.finalproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

/**
 * Created by Pauline Sarana on 12/10/2016.
 */

public class DatabaseHelper extends SQLiteOpenHelper{
    //Logcat Tag
    public static final String LOG = "DatabaseHelper";
    //public static final String LOG_TAG = DatabaseHelper.class.getSimpleName();


    public static final int DB_VERSION = 1;    //Database Version
    public static final String DB_NAME = "FLEX.DB";    //Database Name

    //Table Names
    public static final String TABLE_EVENTS = "events";
    public static final String TABLE_FOLDERS = "folders";
    public static final String TABLE_DIRECTORIES = "directories";


    //Common column names
    public static final String KEY_ID = "id";

    //TABLE_EVENTS Column Names
    public static final String KEY_TITLE = "title";
    public static final String KEY_DETAILS = "details";
    public static final String KEY_FOLDER_LOCATION = "folderLocation";
    public static final String KEY_DATE_START = "dateStart";
    public static final String KEY_DATE_END = "dateEnd";
    public static final String KEY_TIME_START = "timeStart";
    public static final String KEY_TIME_END = "timeEnd";

    //TABLE_DIRECTORIES Column Names
//    public static final String KEY_FOLDER_ID = "folderId";
    public static final String KEY_EVENT_ID = "eventId";
    public static final String KEY_LOCATION = "location";


    //Table Create Statements
    //TABLE_EVENTS create statement
    private static final String CREATE_TABLE_EVENTS = "CREATE TABLE " + TABLE_EVENTS + "(" +
            KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            KEY_TITLE + " TEXT NOT NULL, " +
            KEY_DETAILS + " TEXT, " +
            KEY_FOLDER_LOCATION + " TEXT NOT NULL, " +
            KEY_DATE_START + " TEXT NOT NULL, " +
            KEY_DATE_END + " TEXT NOT NULL, " +
            KEY_TIME_START + " TEXT NOT NULL, " +
            KEY_TIME_END + " TEXT NOT NULL)";

    //TABLE_DIRECTORIES create satement
    private static final String CREATE_TABLE_DIRECTORIES = "CREATE TABLE " + TABLE_DIRECTORIES + "(" +
            KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            KEY_EVENT_ID + " INTEGER REFERENCES "+ TABLE_EVENTS+"("+ KEY_ID + ") ON DELETE CASCADE ON UPDATE CASCADE, " +
            KEY_LOCATION + " TEXT NOT NULL)";


    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // creating required tables
        db.execSQL(CREATE_TABLE_EVENTS);
        db.execSQL(CREATE_TABLE_DIRECTORIES);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_EVENTS);
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_DIRECTORIES);

        //create tables
        onCreate(db);
    }

    @Override
    public void onConfigure(SQLiteDatabase db){
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN) {
            db.setForeignKeyConstraintsEnabled(true);
        }
    }
}
