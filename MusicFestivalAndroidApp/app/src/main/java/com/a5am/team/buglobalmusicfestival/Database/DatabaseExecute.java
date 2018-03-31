package com.a5am.team.buglobalmusicfestival.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
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
    private final String[] EVENT_COLUMN = new String[] {"id", "event", "aid","place","date"};
    private final String[] ARTIST_COLUMN = new String[] {"aid", "artist", "country","website",
            "spotify"};
    private final String[] EVENT_ARTIST = new String[] {"id", "event", "aid","place","date",
            "artist","country","website","spotify"};

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

            // insert Artist
            db.execSQL("insert into " + DatabaseHelper.ARTIST_TABLE + "(aid, artist, country, " +
                    "website, spotify) values (1, 'Jupiter & Okwess', 'Congo', 'http://www" +
                    ".jupiterandokwess.com', 'sp-1')");
            db.execSQL("insert into " + DatabaseHelper.ARTIST_TABLE + "(aid, artist, country, " +
                    "website, spotify) values (2, 'Ladama', 'Latin AmericaBrazil, Colombia, " +
                    "Venezuela'," +
                    " 'http://www.ladamaproject.org/english#ladama-english', 'sp-2')");
            db.execSQL("insert into " + DatabaseHelper.ARTIST_TABLE + "(aid, artist, country, " +
                    "website, spotify) values (3, 'Dina Elwedidi', 'Egypt', 'http://www" +
                    ".dinaelwedidi.com/', 'sp-3')");
            db.execSQL("insert into " + DatabaseHelper.ARTIST_TABLE + "(aid, artist, country, " +
                    "website, spotify) values (4, 'Orquesta El Macabeo', 'Puerto Rico', 'http://" +
                    "orquestaelmacabeo.com/', 'sp-4')");
            db.execSQL("insert into " + DatabaseHelper.ARTIST_TABLE + "(aid, artist, country, " +
                    "website, spotify) values (5, 'Zhou Family', 'China', 'http://www" +
                    ".almaartistbooking.com/profile/zhou-family-band/', 'sp-5')");
            db.execSQL("insert into " + DatabaseHelper.ARTIST_TABLE + "(aid, artist, country, " +
                    "website, spotify) values (6, 'Kaumakaiwa Kanakaole', 'Hawaii', " +
                    "'http://pasifika-artists.com/index.php?page=kaumakaiwa', 'sp-6')");



            // insert Event
            db.execSQL("insert into " + DatabaseHelper.TABLE_NAME + " (id, event, aid, " +
                    "place, date) values (1, 'Jupiter & Okwess Perform', 1, 'GSU Metcalf " +
                    "Ballroom', '2018-08-01')");
            db.execSQL("insert into " + DatabaseHelper.TABLE_NAME + " (id, event, aid, " +
                    "place, date) values (2, 'Ladama Perform', 2, 'GSU Conference Auditorium', " +
                    "'2018-08-02')");
            db.execSQL("insert into " + DatabaseHelper.TABLE_NAME + " (id, event, aid, " +
                    "place, date) values (3, 'Dina Elwedidi Perform', 3, 'GSU Metcalf Ballroom', " +
                    "'2018-08-01')");
            db.execSQL("insert into " + DatabaseHelper.TABLE_NAME + " (id, event, aid, " +
                    "place, date) values (4, 'Orquesta El Macabeo Perform', 4, 'GSU BU Central (basement)" +
                    "', '2018-08-02')");
            db.execSQL("insert into " + DatabaseHelper.TABLE_NAME + " (id, event, aid, " +
                    "place, date) values (5, 'Zhou Family Perform', 5, 'Tsai Performance Center" +
                    "', '2018-08-01')");
            db.execSQL("insert into " + DatabaseHelper.TABLE_NAME + " (id, event, aid, " +
                    "place, date) values (6, 'Kaumakaiwa Kanakaole Perform', 6, 'Tsai Performance Center" +
                    "', '2018-08-02')");


            db.setTransactionSuccessful();
            Log.e(TAG,"Init Database success, insert!");
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
    private Event parseEvent(Cursor cursor){
        Event event = new Event();
        event.id = (cursor.getInt(cursor.getColumnIndex("id")));
        event.event = (cursor.getString(cursor.getColumnIndex("event")));
        event.aid = (cursor.getInt(cursor.getColumnIndex("aid")));
        event.place = (cursor.getString(cursor.getColumnIndex("place")));
        event.date = (cursor.getString(cursor.getColumnIndex("date")));
        event.artist = (cursor.getString(cursor.getColumnIndex("artist")));
        return event;
    }

    private Artist parseArtist(Cursor cursor){
        Artist artist = new Artist();
        artist.aid = (cursor.getInt(cursor.getColumnIndex("aid")));
        artist.artist = (cursor.getString(cursor.getColumnIndex("artist")));
        artist.country = (cursor.getString(cursor.getColumnIndex("country")));
        artist.website = (cursor.getString(cursor.getColumnIndex("website")));
        artist.spotify = (cursor.getString(cursor.getColumnIndex("spotify")));
        return artist;
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
            String selectQuery = "SELECT e.id, e.event, e.place, e.date, a.aid, a.artist, a" +
                    ".country, a.website, a.spotify " +
                    " FROM " + DatabaseHelper.TABLE_NAME +
                    " e, " +
                    DatabaseHelper.ARTIST_TABLE + " a WHERE e.aid=a.aid";
//            cursor = db.query(DatabaseHelper.TABLE_NAME, EVENT_COLUMN, null, null, null, null, null);
            cursor = db.rawQuery(selectQuery, null);
            if (cursor.getCount() > 0) {
                Log.e(TAG,"find cursor of all data!");
                List<Event> eventList = new ArrayList<Event>(cursor.getCount());
                while (cursor.moveToNext()) {
                    eventList.add(parseEvent(cursor));
                }
                return eventList;
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
