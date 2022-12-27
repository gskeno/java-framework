package com.gson.mockito;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookDAOImpl implements BookDAO {

    @Override
    public List<Book> findBooks(Book book) {
        Map<String,Object> param = new HashMap<>();
        param.put("name", book.getBookName());
        param.put("author", book.getAuthor());

        return null;
    }
}
