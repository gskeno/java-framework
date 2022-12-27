package com.gson.mockito;

import java.util.List;

public interface BookDAO {
    public List<Book> findBooks(Book book);
}
