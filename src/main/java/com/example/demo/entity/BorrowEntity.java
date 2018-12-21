package com.example.demo.entity;

import java.util.Date;

//对应数据库中的borrow表

public class BorrowEntity{
    private String bookId;
    private String ISBN;
    private String account;

    public String getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    //    private Date borrowDate;
//    private Date returnDate;
    private String borrowDate;
    private String returnDate;
    private boolean renew;

    public BorrowEntity() {
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

//    public Date getBorrowDate() {
//        return borrowDate;
//    }
//
//    public void setBorrowDate(Date borrowDate) {
//        this.borrowDate = borrowDate;
//    }
//
//    public Date getReturnDate() {
//        return returnDate;
//    }
//
//    public void setReturnDate(Date returnDate) {
//        this.returnDate = returnDate;
//    }

    public boolean isRenew() {
        return renew;
    }

    public void setRenew(boolean renew) {
        this.renew = renew;
    }


}
