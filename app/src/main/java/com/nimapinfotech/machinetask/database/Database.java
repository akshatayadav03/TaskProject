package com.nimapinfotech.machinetask.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.Image;

import com.nimapinfotech.machinetask.offline.ImagesResponse;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    long dbInsert;
    private static Database mInstance = null;

    // Database Version
    private static final int DATABASE_VERSION = 1;

    //Database Name
    private static final String DATABASE_NAME = "MachineTest";

    //Table Name
    private static final String TABLE_IMAGES = "images";

    //column
    private static final String KEY_ID = "id";
    private static final String KEY_IMAGE = "image";

    //create table

    private static final String CREATE_TABLE_IMAGE = "CREATE TABLE " + TABLE_IMAGES
            + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_IMAGE + " TEXT" + ")";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized Database getInstance(Context context){
        if (mInstance == null){
            mInstance = new Database(context.getApplicationContext());
        }
        return mInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_IMAGE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_IMAGES);

        // create new tables
        onCreate(sqLiteDatabase);
    }

    public void insertImage(String imagePath) {
        ContentValues cv = new ContentValues();
        cv.put(KEY_IMAGE, imagePath);
        SQLiteDatabase db = this.getWritableDatabase();
        dbInsert = db.insert(TABLE_IMAGES, null, cv);

        if (dbInsert != -1) {
            System.out.println("Data insert");
        } else {
            System.out.println("Something wrong");
        }
    }
    public List<ImagesResponse> getImagePath() {
        ArrayList<ImagesResponse> imagesResponseArrayList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String select_query = "SELECT  image  FROM " + TABLE_IMAGES;
        Cursor cursor = db.rawQuery(select_query, null);
        if (cursor.moveToFirst()) {
            do {
                ImagesResponse imagesResponse = new ImagesResponse();
                imagesResponse.setImage(cursor.getString(cursor.getColumnIndex(KEY_IMAGE)));
                imagesResponseArrayList.add(imagesResponse);

            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return imagesResponseArrayList;
    }

    // Closing Database
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }
}
