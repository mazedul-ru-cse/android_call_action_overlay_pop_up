package com.example.over_lay_pop_up.follow_up;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHandler extends SQLiteOpenHelper {

    // creating a constant variables for our database.
    // below variable is for our database name.
    private static final String DB_NAME ="iCRM.db";

    // below int is our database version
    private static final int DB_VERSION = 2;

    // below variable is for our table name.
    private static final String TABLE_NAME = "follo_up";

    // below variable is for our id column.
    private static final String ID_COL = "id";

    // below variable is for our course name column
    private static final String NEXT_FOLLOW_UP = "next_follow_up";

    // below variable id for our course duration column.
    private static final String NOTE = "note";

    // below variable for our course description column.
    private static final String CALL_STATUS = "call_status";

    // below variable is for our course tracks column.
    private static final String ASSIGN_TO = "assign_to";
    private static final String OTHER = "other";

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
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + NEXT_FOLLOW_UP + " DATETIME,"
                + NOTE + " TEXT,"
                + CALL_STATUS + " TEXT,"
                + ASSIGN_TO + " TEXT,"
                + OTHER + " TEXT)";

        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query);
    }

    // this method is use to add new course to our sqlite database.
    public void addNewFollowUp(String nextFollowUp, String note, String callStatus, String assignTo, String other) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(NEXT_FOLLOW_UP, nextFollowUp);
        values.put(NOTE, note);
        values.put(CALL_STATUS, callStatus  );
        values.put(ASSIGN_TO, assignTo);
        values.put(OTHER, other);

        // after adding all values we are passing
        // content values to our table.
        db.insert(TABLE_NAME, null, values);

        // at last we are closing our
        // database after adding database.
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    void readData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorCourses = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (cursorCourses.moveToFirst()) {
            do {

                Log.i("NextFollowUp",cursorCourses.getString(1));
                Log.i("Note",cursorCourses.getString(2));
                Log.i("Call status",cursorCourses.getString(3));
                Log.i("Assign to",cursorCourses.getString(4));
                Log.i("Category",cursorCourses.getString(5));

            } while (cursorCourses.moveToNext());
            // moving our cursor to next.
        }

    }
}
