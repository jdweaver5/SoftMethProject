package drinkSmart;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Josh on 3/27/2018.
 */

public class profile extends SQLiteOpenHelper {
    String name;
    int age;
    int height;
    int weight;
    String gender;
    public static final String DB_NAME = "drinkSmart.db";
    public static final int DB_VERSION = 1;

    public static final String TAG = profile.class.getSimpleName();
    public static final String PROFILE_TABLE = "Profiles";
    public static final String COLUMN_NAME = "Name";
    public static final String COLUMN_AGE = "Age";
    public static final String COLUMN_HEIGHT = "Height";
    public static final String COLUMN_WEIGHT = "Weight";
    public static final String COLUMN_GENDER = "Gender";
    public static final String COLUMN_ID = "_id";

    /*
    create table users (
        id integer primary key autoincrement,
        name text,
        age text,
        height text,
        weight text,
        gender text);
     */
    public static final String CREATE_TABLE_USERS = "CREATE TABLE" + PROFILE_TABLE + "("
            + COLUMN_ID + "INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_NAME + "TEXT,"
            + COLUMN_AGE + "TEXT," + COLUMN_HEIGHT + "TEXT," + COLUMN_WEIGHT + "TEXT,"
            + COLUMN_GENDER + "TEXT);";
    public profile(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_TABLE_USERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS " + PROFILE_TABLE);
        onCreate(database);
    }

    public void addProfile(String name, int age, int height, int weight, String gender) {
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_AGE, age);
        values.put(COLUMN_HEIGHT, height);
        values.put(COLUMN_WEIGHT, weight);
        values.put(COLUMN_GENDER, gender);

        long id = database.insert(PROFILE_TABLE, null, values);

        Log.d(TAG, "Profile inserted" + id);
    }

    public boolean getProfile(String name, int age, int height, int weight, String gender) {
        //HashMap<String, String> user = new HashMap<String, String>();
        String selectQuery = "select + from +  " + PROFILE_TABLE + " where " +
                COLUMN_NAME + " = " + name + "'" + " and " + "'" +
                COLUMN_AGE + " = " + "'" + age + "'" + "and " +
                COLUMN_HEIGHT + " = " + "'" + height + "'" + " and " +
                COLUMN_WEIGHT + " = " + "'" + weight + "'" + " and " +
                COLUMN_GENDER + " = " + "'" + gender + "'";

        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        //move first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            //user.put("name", cursor.getString(1));
            return true;
        }
        cursor.close();
        database.close();

        return false;
    }
}

