package com.example.harshith.moneyscheduler;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.harshith.moneyscheduler.Data.DataHelper;
import com.example.harshith.moneyscheduler.Data.DataStruct;

import java.util.Calendar;

public class New_Entry extends AppCompatActivity implements View.OnClickListener{

    private int mYear, mMonth, mDay;
    private EditText exItem;
    private EditText exAmount;
    private TextView exDate;
    private TextView exCat;
    private Button btn;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new__entry);

        img = findViewById(R.id.expendicon);
        btn = findViewById(R.id.Save);
        exCat = findViewById(R.id.category);
        exItem = findViewById(R.id.item);
        exAmount = findViewById(R.id.amount);
        exDate = findViewById(R.id.date);

        LinearLayout l = findViewById(R.id.scrolllayout);
        LayoutInflater inflater = LayoutInflater.from(this);


            View view = inflater.inflate(R.layout.scrolling,l,false);
            ImageView ig = view.findViewById(R.id.image);
            ig.setImageResource(R.drawable.bill);
            ig.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  exCat.setText("Bills");
                  img.setImageResource(R.drawable.bill);
                }
            });
            l.addView(view);

        View view1 = inflater.inflate(R.layout.scrolling,l,false);
              ImageView ig1 = view1.findViewById(R.id.image);
        ig1.setImageResource(R.drawable.gasstation);
        ig1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exCat.setText("fuel");
                img.setImageResource(R.drawable.gasstation);
            }
        });
        l.addView(view1);


        View view2 = inflater.inflate(R.layout.scrolling,l,false);
            ImageView ig2 = view2.findViewById(R.id.image);
            ig2.setImageResource(R.drawable.groceries);
        ig2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exCat.setText("Groceries");
                img.setImageResource(R.drawable.groceries);
            }
        });
        l.addView(view2);

        View view3 = inflater.inflate(R.layout.scrolling,l,false);
        ImageView ig3 = view3.findViewById(R.id.image);
            ig3.setImageResource(R.drawable.care);
        ig3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exCat.setText("Health");
                img.setImageResource(R.drawable.care);
            }
        });
        l.addView(view3);

        View view4 = inflater.inflate(R.layout.scrolling,l,false);
            ImageView ig4 = view4.findViewById(R.id.image);
            ig4.setImageResource(R.drawable.onlinepayment);
        ig4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exCat.setText("OnlinePay");
                img.setImageResource(R.drawable.onlinepayment);
            }
        });
        l.addView(view4);

        View view5 = inflater.inflate(R.layout.scrolling,l,false);
            ImageView ig5 = view5.findViewById(R.id.image);
            ig5.setImageResource(R.drawable.piggybank);
        ig5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exCat.setText("Savings");
                img.setImageResource(R.drawable.piggybank);
            }
        });
        l.addView(view5);

        View view6 = inflater.inflate(R.layout.scrolling,l,false);
            ImageView ig6 = view6.findViewById(R.id.image);
            ig6.setImageResource(R.drawable.restaurant);
        ig6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exCat.setText("Food");
                img.setImageResource(R.drawable.restaurant);
            }
        });
        l.addView(view6);

        View view7 = inflater.inflate(R.layout.scrolling,l,false);
            ImageView ig7 = view7.findViewById(R.id.image);
            ig7.setImageResource(R.drawable.theater);
        ig7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exCat.setText("Entertainment");
                img.setImageResource(R.drawable.theater);
            }
        });
            l.addView(view7);


        View view8 = inflater.inflate(R.layout.scrolling,l,false);
            ImageView ig8 = view8.findViewById(R.id.image);
            ig8.setImageResource(R.drawable.travelling);
        ig8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exCat.setText("Travel");
                img.setImageResource(R.drawable.travelling);
            }
        });
            l.addView(view8);


        exDate.setOnClickListener(this);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             insert();
            }
        });

    }


    public void insert(){
       String item = exItem.getText().toString().trim();
       Integer amount = Integer.parseInt(exAmount.getText().toString().trim());
       String date = exDate.getText().toString().trim();
       String category = exCat.getText().toString().trim();

        DataHelper dh = new DataHelper(this);
        SQLiteDatabase db = dh.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(DataStruct.Spend.COLUMN_SPENT_ITEM,item);
        value.put(DataStruct.Spend.COLUMN_SPENT_AMOUNT,amount);
        value.put(DataStruct.Spend.COLUMN_SPENT_DATE,date);
        value.put(DataStruct.Spend.COLUMN_SPENT_CATEGORY,category);

        long newRowId = db.insert(DataStruct.Spend.TABLE_NAME,null,value);

        if(newRowId == -1)
            Toast.makeText(this,"Error in creating a new Spend",Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this,"Spend saved with id:"+newRowId,Toast.LENGTH_LONG).show();

    }

    public void onClick(View v) {

        if (v == exDate) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            exDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
    }

}
