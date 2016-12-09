package com.example.mis.finalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.example.mis.finalproject.DatabaseHelper.KEY_ID;
import static com.example.mis.finalproject.DatabaseHelper.TABLE_EVENTS;

/**
 * Created by Pauline Sarana on 12/10/2016.
 */

public class DBManager {
    private DatabaseHelper dbHelper;
    private Context context;
    private SQLiteDatabase myDb;

    public DBManager(Context c) {
        context = c;
    }

    public DBManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        myDb = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        myDb.close();
    }


    // ------------------------ "Event" table methods ----------------//

    /*
     * inserting an Event
     */
    public void insertEvent(EventItem eventItem) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.KEY_TITLE, eventItem.getTitle());
        contentValues.put(DatabaseHelper.KEY_DETAILS, eventItem.getDetails());
        contentValues.put(DatabaseHelper.KEY_FOLDER_LOCATION, eventItem.getFolderLocation());
        contentValues.put(DatabaseHelper.KEY_DATE_START, eventItem.getDateStart());
        contentValues.put(DatabaseHelper.KEY_DATE_END, eventItem.getDateEnd());
        contentValues.put(DatabaseHelper.KEY_TIME_START, eventItem.getTimeStart());
        contentValues.put(DatabaseHelper.KEY_TIME_END, eventItem.getTimeEnd());

        myDb.insert(TABLE_EVENTS, null, contentValues);
    }

    /*
     * get single Event
     */
    public EventItem getEvent(long eventId) {
        String selectQuery = "SELECT  * FROM " + TABLE_EVENTS + " WHERE "
                + KEY_ID + " = " + eventId;

        Log.e(dbHelper.LOG, selectQuery);

        Cursor cursor = myDb.rawQuery(selectQuery, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }


        EventItem eventItem = new EventItem(cursor.getString(0), cursor.getString(1), cursor.getString(2),
                cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7));

        return  eventItem;
    }

    /*
    * get Events by time and date
    */
    public ArrayList<EventItem> getEventBySchedule(String dateInString) {

        ArrayList<EventItem> tmpEventList = getAllEvents();     //get all events from database

        ArrayList<EventItem> eventList = new ArrayList<EventItem>();
        SimpleDateFormat sdf = new SimpleDateFormat(dateInString);

        Date dateStart;
        Date dateEnd;
        Date dateNow;

        for (EventItem  tmpEventItem : tmpEventList) {
            sdf = new SimpleDateFormat("yyyy-MM-dd HH:MM");

            try {

                dateStart = sdf.parse(tmpEventItem.getDateStart());
//                System.out.println(dateInString);
//                System.out.println(sdf.format(dateInString));
                dateEnd = sdf.parse(tmpEventItem.getDateEnd());
                dateNow = sdf.parse(dateInString);

                if((dateStart.before(dateNow) || dateStart.equals(dateNow)) && dateEnd.after(dateNow) || dateEnd.equals(dateNow)){
                    eventList.add(tmpEventItem);
                }

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }


        return  eventList;
    }

    /**
     * getting all events
     * */
    public ArrayList<EventItem> getAllEvents () {
        ArrayList <EventItem> eventItemList = new ArrayList<EventItem>();
        String selectQuery = "SELECT  * FROM " + TABLE_EVENTS;

        Log.e(dbHelper.LOG, selectQuery);

        Cursor cursor = myDb.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            EventItem eventItem = new EventItem(cursor.getString(0), cursor.getString(1), cursor.getString(2),
                    cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7));
            eventItemList.add(eventItem);
        }
        return eventItemList;
    }

    /*
	 * get EventItems count
	 */
    public int getEventItemsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_EVENTS;

        Cursor cursor = myDb.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();

        return count;
    }

    /*
	 * Updating an Event
	 */
    public int updateEvent(EventItem eventItem) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.KEY_TITLE, eventItem.getTitle());
        contentValues.put(DatabaseHelper.KEY_DETAILS, eventItem.getDetails());
        contentValues.put(DatabaseHelper.KEY_DATE_START, eventItem.getDateStart());
        contentValues.put(DatabaseHelper.KEY_DATE_END, eventItem.getDateEnd());
        contentValues.put(DatabaseHelper.KEY_TIME_START, eventItem.getTimeStart());
        contentValues.put(DatabaseHelper.KEY_TIME_END, eventItem.getTimeEnd());

        // updating row
        return myDb.update(TABLE_EVENTS, contentValues, KEY_ID + " = ?",
                new String[] { String.valueOf(eventItem.getId()) });
    }

    /*
	 * Deleting an EventItem
	 */
    public void deleteEventItem(long eventId) {
        myDb.delete(TABLE_EVENTS, KEY_ID + " = ?",
                new String[] { String.valueOf(eventId) });
    }



    // ------------------------ "Directory" table methods ----------------//

    /*
     * inserting an Event
     */
    private void insertImageDirectory(String eventId, String imageLocation) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.KEY_EVENT_ID, eventId);
        contentValues.put(DatabaseHelper.KEY_LOCATION, imageLocation);

        myDb.insert(DatabaseHelper.TABLE_DIRECTORIES, null, contentValues);
    }

    public void insertImageDirectory2(String dateInString, String imageName) {
        ArrayList<EventItem> eventList = getEventBySchedule(dateInString);

        for (EventItem eventItem: eventList) {
            insertImageDirectory(eventItem.getId(), eventItem.getFolderLocation() +"/" + imageName);
        }

    }


    /**
     * getting image by Event
     * */
    public ArrayList<String> getAllImageDirectories (String eventId) {
        ArrayList <String> imageDirectoryList = new ArrayList<String>();
        String selectQuery = "SELECT  * FROM " + dbHelper.TABLE_DIRECTORIES + " WHERE "
                + dbHelper.KEY_EVENT_ID + " = " + eventId;

        Log.e(dbHelper.LOG, selectQuery);

        Cursor cursor = myDb.rawQuery(selectQuery, null);

        // looping through all rows and adding the location only in the list
        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            imageDirectoryList.add(cursor.getString(2));    //location only (in colum 2)
        }
        return imageDirectoryList;
    }
}
