package com.example.onlinelibrary;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class BookAdapter extends ArrayAdapter<Book> {
    private int resourceId;
    public BookAdapter(Context context, int textViewResourceId, List<Book> objects){
        super(context,textViewResourceId,objects);
        resourceId = textViewResourceId;
    }
    class ViewHolder {
        TextView book_name;
        TextView book_type;
        TextView book_price;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Book book = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.book_name= (TextView) view.findViewById(R.id.text_bookname);
            viewHolder.book_type = (TextView) view.findViewById(R.id.text_booktype);
            viewHolder.book_price = (TextView) view.findViewById(R.id.text_bookprice);
            view.setTag(viewHolder);

        }
        else{
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.book_name.setText(book.getName() + "");
        viewHolder.book_type.setText(book.getBook_id() + "");
        viewHolder.book_price.setText(book.getPrice() + "");
        return view;

    }
}

