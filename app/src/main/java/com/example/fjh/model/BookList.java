package com.example.fjh.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.fjh.db.DBconnection;

import java.util.ArrayList;

/**
 * Created by FJH on 2017/5/2.
 */

public class BookList extends ArrayList<Book> {

    private static final long serialVersionUID = 1L;

    private static BookList bookList = null;

    private BookList() {

    }

    public static BookList getBookList() {
        if (bookList == null) {
            bookList = new BookList();
            DBconnection dBconnection = new DBconnection();
            SQLiteDatabase db = dBconnection.getConnection();
            Cursor cursor = db.query("book", null, null, null, null, null, null);
            while (cursor.moveToNext()) {
                int idNum = cursor.getColumnIndex("ID");
                int nameNum = cursor.getColumnIndex("name");
                int priceNum = cursor.getColumnIndex("price");
                String id = cursor.getString(idNum);
                String name = cursor.getString(nameNum);
                String price = cursor.getString(priceNum);
                Book book = new Book(id, name, price);
                bookList.add(book);
                cursor.moveToNext();
            }
            dBconnection.close(db);
        }
        return bookList;
    }

    /**
     * 查找ID
     */
    private boolean checkID(String ID) {
        for (int i = 0; i < bookList.size(); i++) {
            Book book = bookList.get(i);
            if (book.getID().equals(ID)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 得到图书位置
     */
    private int getIndex(String ID) {
        int i = 0;
        for (; i < bookList.size(); i++) {
            Book book = bookList.get(i);
            if (book.getID().equals(ID)) {
                break;
            }
        }
        return i;
    }

    /**
     * 查找名称
     */
    public boolean checkNmae(String name) {
        for (int i = 0; i < bookList.size(); i++) {
            Book book = bookList.get(i);
            if (book.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public boolean insert(Book book) {
        if (checkID(book.getID())) {
            return false;
        } else {
            bookList.add(book);
            String id = book.getID();
            String name = book.getName();
            String price = book.getPrice();
            DBconnection dBconnection = new DBconnection();
            SQLiteDatabase db = dBconnection.getConnection();
            String sql = "insert into book (ID,name,price) values ('" + id + "','" + name + "','" + price + "')";
            db.execSQL(sql);
            db.close();
            return true;
        }
    }

    public boolean delete(String name) {
        if (checkNmae(name)) {
            DBconnection dBconnection = new DBconnection();
            SQLiteDatabase db = dBconnection.getConnection();
            String sql = "delete from book where name = '" + name + "'";
            db.execSQL(sql);
            return true;
        } else {
            return false;
        }
    }

    public boolean set(Book book) {
        if (checkID(book.getID())) {
            String id = book.getID();
            String name = book.getName();
            String price = book.getPrice();
            int index = getIndex(id);
            bookList.set(index, book);
            DBconnection dBconnection = new DBconnection();
            SQLiteDatabase db = dBconnection.getConnection();
            String sql = "update book set name='" + name + "',price='" + price + "' where ID = '" + id + "'";
            db.execSQL(sql);
            return true;
        } else {
            return false;
        }
    }
}
