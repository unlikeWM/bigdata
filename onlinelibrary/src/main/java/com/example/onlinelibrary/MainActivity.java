package com.example.onlinelibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private MyDatabaseHelper dbHelper;
    private List<Book> BookList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new MyDatabaseHelper(this, "OnlineLibrary.db",null,1);
        Button createDatabase = (Button) findViewById(R.id.creat_database);
        Button addBooks = (Button) findViewById(R.id.add_book);
        SQLiteDatabase  db = dbHelper.getWritableDatabase();
        //查询book表中的所有数据
        Cursor cursor = db.query("Book",null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                //遍历cursor对象
                @SuppressLint("Range") String name =cursor.getString(cursor.getColumnIndex("name"));
                @SuppressLint("Range") int book_id = cursor.getInt(cursor.getColumnIndex("category_id"));
                @SuppressLint("Range") double price = cursor.getDouble(cursor.getColumnIndex("price"));
                Book book1 = new Book(name,book_id,price);
                BookList.add(book1);
            }while(cursor.moveToNext());

        }
        BookAdapter adapter = new BookAdapter(MainActivity.this,R.layout.book_item,BookList);
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);

        createDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase  db = dbHelper.getWritableDatabase();
//                ContentValues values = new ContentValues();
//                values.put("category_name","经济类");
//                values.put("category_code",1);
//                db.insert("Category",null,values);
//                values.clear();
//                values.put("category_name","计算机类");
//                values.put("category_code",2);
//                db.insert("Category",null,values);


//                db.execSQL("delete from Category");
                Log.d("test","111");
            }
        });

        addBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this,AddBookActivity2.class);
            startActivity(intent);

            }
        });



    }
}