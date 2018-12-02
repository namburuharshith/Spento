package com.example.harshith.moneyscheduler;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.harshith.moneyscheduler.Data.DataHelper;
import com.example.harshith.moneyscheduler.Data.DataStruct;

import java.io.PrintStream;

public class MainActivity extends AppCompatActivity {

    private DataHelper db;
    private FloatingActionButton fab;
//    private LinearLayout fabper;
//    private boolean faboptions = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                    Intent i = new Intent(MainActivity.this,New_Entry.class);
                 startActivity(i);
            }
        });

        db = new DataHelper(this);
        displayDatabase();
    }


    @Override
    protected void onStart() {
        super.onStart();
        displayDatabase();
    }


    private void displayDatabase() {

        SQLiteDatabase sq = db.getReadableDatabase();

        String[] pro = {DataStruct.Spend._ID,DataStruct.Spend.COLUMN_SPENT_ITEM,DataStruct.Spend.COLUMN_SPENT_AMOUNT,DataStruct.Spend.COLUMN_SPENT_DATE,DataStruct.Spend.COLUMN_SPENT_CATEGORY};

        Cursor cur = sq.query(DataStruct.Spend.TABLE_NAME,pro,null,null,null,null,null);
        try{

            TextView txt = findViewById(R.id.list);
            txt.setText("My Spends");
            int item = cur.getColumnIndex(DataStruct.Spend.COLUMN_SPENT_ITEM);
            int amount = cur.getColumnIndex(DataStruct.Spend.COLUMN_SPENT_AMOUNT);
            int date = cur.getColumnIndex(DataStruct.Spend.COLUMN_SPENT_DATE);

            while (cur.moveToNext()){
                String curnm = cur.getString(item);
                String curamt = cur.getString(amount);
                String curdt = cur.getString(date);

                txt.append("\n"+curdt+" - "+curnm+" - "+curamt);
            }
        }catch (SQLException sql){
            sql.printStackTrace();
            
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
