package com.example.over_lay_pop_up.database_;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHandler extends SQLiteOpenHelper {

    // creating a constant variables for our database.
    // below variable is for our database name.
    private static final String DB_NAME ="iCRM12.db";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "call_pop_up";
    private static final String ID = "id";
    private static final String CALL_STATUS = "call_status";
    private static final String NOTE = "note";
    private static final String REMINDER = "reminder";



    // creating a constructor for our database handler.
    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);

    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
        // on below line we are creating
        // an sqlite query and we are
        // setting our column names
        // along with their data types.
        String query = "CREATE TABLE " + TABLE_NAME + "( "
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT , "
                + CALL_STATUS + " TEXT ,"
                + NOTE + " TEXT ,"
                + REMINDER + " TEXT ) ";


        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query);
    }

    // this method is use to add new course to our sqlite database.
    public void setCallStatus(String callStatus) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(CALL_STATUS, callStatus);
        values.put(REMINDER, "");
        values.put(NOTE, "");

        // after adding all values we are passing
        // content values to our table.
       db.insert(TABLE_NAME, null, values);

        // at last we are closing our
        // database after adding database.
        db.close();
    }

    public void setNote(String note) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();


        values.put(CALL_STATUS, "");
        values.put(REMINDER, "");
        values.put(NOTE, note);

        db.insert(TABLE_NAME, null, values);

        db.close();
    }

    public void setReminder(String reminder) {


        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();


        values.put(CALL_STATUS, "");
        values.put(REMINDER, reminder);
        values.put(NOTE, "");

        db.insert(TABLE_NAME, null, values);

        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    public void getData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorCourses = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (cursorCourses.moveToFirst()) {
            do {

                Log.i("ID",cursorCourses.getString(0));
                Log.i("Call Status",cursorCourses.getString(1));
                Log.i("Note",cursorCourses.getString(2));
                Log.i("Reminder",cursorCourses.getString(3));


            } while (cursorCourses.moveToNext());
            // moving our cursor to next.
        }

    }
}
