package com.example.fjh.control;

import com.example.fjh.model.Book;
import com.example.fjh.model.BookList;


public class Controller {
    //查找全部图书
    public BookList searchBook() {
        BookList bookList = BookList.getBookList();
        return bookList;
    }

    //删除图书
    public boolean deleteBook(String name) {
        BookList bookList = BookList.getBookList();
        if (bookList.delete(name)) {
            return true;
        }
        return false;
    }

    //添加图书
    public boolean addBook(String id, String name, String price) {
        BookList bookList = BookList.getBookList();
        Book book = new Book(id, name, price);
        if (bookList.insert(book)) {
            return true;
        } else {
            return false;
        }
    }

    //修改图书
    public boolean setBook(String id, String name, String price) {
        BookList bookList = BookList.getBookList();
        Book book = new Book(id, name, price);
        if (bookList.set(book)) {
            return true;
        } else {
            return false;
        }
    }

}
