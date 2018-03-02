package com.william_zhang.williamapp.bean;

import java.io.Serializable;

/**
 * Created by william_zhang on 2018/3/2.
 */

public class Book implements Serializable {
    private String BookId;
    private String BookName;

    public Book(String bookId, String bookName) {
        BookId = bookId;
        BookName = bookName;
    }

    public String getBookId() {
        return BookId;
    }

    public void setBookId(String bookId) {
        BookId = bookId;
    }

    public String getBookName() {
        return BookName;
    }

    public void setBookName(String bookName) {
        BookName = bookName;
    }
}
