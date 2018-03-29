package com.a5am.team.buglobalmusicfestival.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by pro on 2018/3/28.
 *
 * This is the SQL language Execution class.
 *
 * All filtering, future advanced SQL cursor/query should go here.
 *
 */

public class DatabaseExecute {

    private static final String TAG = "DatabaseExecute";

    // Define the column
    private final String[] EVENT_COLUMN = new String[] {"id", "event", "artist","place","date"};

    private Context context;
    private DatabaseHelper eventsDBHelper;

    public DatabaseExecute(Context context) {
        this.context = context;
        eventsDBHelper = new DatabaseHelper(context);
    }

    /**
     * Check whether there is data in the database.
     */
    public boolean isDataExist(){
        int count = 0;

        SQLiteDatabase db = null;
        Cursor cursor = null;

        try {
            db = eventsDBHelper.getReadableDatabase();
            // select count(Id) from Orders
            cursor = db.query(DatabaseHelper.TABLE_NAME, new String[]{"COUNT(id)"}, null, null,
                    null,
                    null, null);

            if (cursor.moveToFirst()) {
                count = cursor.getInt(0);
            }
            if (count > 0) return true;
        }
        catch (Exception e) {
            Log.e(TAG, "", e);
        }
        finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return false;
    }

    /**
     * Init the database
     * First time or the onUpgrade function called
     */
    public void initTable(){
        SQLiteDatabase db = null;

        try {
            db = eventsDBHelper.getWritableDatabase();
            db.beginTransaction();

            db.execSQL("insert into " + DatabaseHelper.TABLE_NAME + " (id, event, artist, " +
                    "place, date) values (1, 'event1', 'artist1', 'gsu1', '2018-08-01')");
            db.execSQL("insert into " + DatabaseHelper.TABLE_NAME + " (id, event, artist, " +
                    "place, date) values (2, 'event2', 'artist2', 'gsu2', '2018-08-02')");
            db.execSQL("insert into " + DatabaseHelper.TABLE_NAME + " (id, event, artist, " +
                    "place, date) values (3, 'event3', 'artist3', 'gsu3', '2018-08-01')");
            db.execSQL("insert into " + DatabaseHelper.TABLE_NAME + " (id, event, artist, " +
                    "place, date) values (4, 'event4', 'artist4', 'gsu4', '2018-08-02')");


            db.setTransactionSuccessful();
        }catch (Exception e){
            Log.e(TAG, "", e);
        }finally {
            if (db != null) {
                db.endTransaction();
                db.close();
            }
        }
    }

    /**
     * Trans SQL stuff into our Model
     */
    private Event parseOrder(Cursor cursor){
        Event event = new Event();
        event.id = (cursor.getInt(cursor.getColumnIndex("id")));
        event.event = (cursor.getString(cursor.getColumnIndex("event")));
        event.artist = (cursor.getString(cursor.getColumnIndex("artist")));
        event.place = (cursor.getString(cursor.getColumnIndex("place")));
        event.date = (cursor.getString(cursor.getColumnIndex("date")));
        return event;
    }


    /**
     * Fetch all data from the DB
     */
    public List<Event> getAllDate(){
        SQLiteDatabase db = null;
        Cursor cursor = null;

        try {
            db = eventsDBHelper.getReadableDatabase();
            // select *
            cursor = db.query(DatabaseHelper.TABLE_NAME, EVENT_COLUMN, null, null, null, null, null);

            if (cursor.getCount() > 0) {
                List<Event> orderList = new ArrayList<Event>(cursor.getCount());
                while (cursor.moveToNext()) {
                    orderList.add(parseOrder(cursor));
                }
                return orderList;
            }
        }
        catch (Exception e) {
            Log.e(TAG, "find exception: ", e);
        }
        finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
        }

        return null;
    }


}
