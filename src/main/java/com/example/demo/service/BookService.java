package com.example.demo.service;

import com.example.demo.dao.BookDao;
import com.example.demo.exception.DescribeException;
import com.example.demo.exception.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookDao bookDao;

    //续借
    public int renewBook(String account, String bookID) {
        return bookDao.renewBook(account, bookID);
    }

    //还书
    public void returnBook(String account, String bookId) {
       if (bookDao.returnBook(account,bookId)!=0){
           throw new DescribeException(ResultCode.ERROR_IN_RETURN);
       }
    }

    //借书
    public void borrowBook(String account, String ISBN) {
        int i = bookDao.borrowBook(account, ISBN);
        if (i!=0){
            throw new DescribeException(ResultCode.ERROR_IN_BORROW);
        }
    }


    public List findBookByISBN(String ISBN) {
        List list= bookDao.findBookByISBN(ISBN);
        if (list.size()!=0){
            return list;
        }else {
            throw new DescribeException(ResultCode.BOOK_NOT_FOUNT);
        }
    }

    public List findBookByTitle(String title) {
        return bookDao.findBookByTitle(title);
    }

    public List findBookByAuthor(String author) {
        return bookDao.findBookByAuthor(author);
    }

    public List findBookByPress(String press) {
        return bookDao.findBookByPress(press);
    }

    public List borrowList(String account){
        return bookDao.borrowList(account);
    }
}
