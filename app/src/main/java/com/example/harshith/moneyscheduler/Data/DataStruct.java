package com.example.harshith.moneyscheduler.Data;

import android.provider.BaseColumns;

public final class DataStruct {

  private DataStruct(){

    }

    public static final class Spend implements BaseColumns{

        public static final String TABLE_NAME = "Spend";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_SPENT_ITEM = "exItem";
        public static final String COLUMN_SPENT_AMOUNT = "exAmount";
        public static final String COLUMN_SPENT_DATE = "exDate";
        public static final String COLUMN_SPENT_CATEGORY = "exCat";

    }
}
