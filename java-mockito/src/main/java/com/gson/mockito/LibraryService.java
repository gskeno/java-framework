package com.gson.mockito;

import javax.annotation.Resource;

public class LibraryService {
    @Resource
    private UserBookBorrowDAO userBookBorrowDAO;


    public Result borrowBook(Book book){
        return null;
    }

    public Result returnBook(Book book){
        return null;
    }

    /**
     * 查询图书
     * @return
     */
    public Result findBook(Book book){
        return null;
    }

}
