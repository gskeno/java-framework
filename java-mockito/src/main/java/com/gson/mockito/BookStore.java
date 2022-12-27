package com.gson.mockito;

import java.util.ArrayList;
import java.util.List;

public class BookStore {
    private List<Book> bookList = new ArrayList<>();

    public Book addBook(Book book){
        bookList.add(book);
        System.out.println("bookStore增加一本书");
        return book;
    }
}
