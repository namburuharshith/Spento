package com.example.harshith.moneyscheduler.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import com.example.harshith.moneyscheduler.Data.DataStruct;

public class DataHelper extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "Expenditure.db";

    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_EXPENDITURE_TABLE = "CREATE TABLE "+DataStruct.Spend.TABLE_NAME + " ("

                +DataStruct.Spend._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                +DataStruct.Spend.COLUMN_SPENT_ITEM + " TEXT NOT NULL,"
                +DataStruct.Spend.COLUMN_SPENT_AMOUNT+" INTEGER NOT NULL, "
                +DataStruct.Spend.COLUMN_SPENT_DATE+" TEXT NOT NULL ,"
                +DataStruct.Spend.COLUMN_SPENT_CATEGORY+" TEXT);";
    db.execSQL(SQL_CREATE_EXPENDITURE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
