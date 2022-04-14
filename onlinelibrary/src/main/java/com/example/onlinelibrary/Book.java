package com.example.onlinelibrary;

public class Book {
    private String name;
    private int book_id;
    private double price;

    public Book(String name, int book_id, double price) {
        this.name = name;
        this.book_id = book_id;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
