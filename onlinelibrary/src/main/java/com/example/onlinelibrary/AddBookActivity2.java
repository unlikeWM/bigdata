package com.example.onlinelibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class AddBookActivity2 extends AppCompatActivity {
    private MyDatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book2);
        dbHelper = new MyDatabaseHelper(this, "OnlineLibrary.db",null,1);
        Button addBooks = (Button) findViewById(R.id.add_book);
        addBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("author","TheDavi");
                values.put("price",68);
                values.put("pages",200);
                values.put("name","数据结构");
                values.put("category_id","2");
                db.insert("Book",null,values);
                Log.d("test","添加成功");

            }
        });
    }
}