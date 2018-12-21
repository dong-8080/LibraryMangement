package com.example.demo.controller;

import com.example.demo.exception.Result;
import com.example.demo.exception.ResultCode;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    //借书
    @GetMapping("/borrowbook")
    public Result borrowBook(@RequestParam(value = "account") String account,
                             @RequestParam(value = "isbn") String ISBN) {

        bookService.borrowBook(account,ISBN);
        return new Result(ResultCode.SUCCESS);
    }

    //续借
    @GetMapping("/renewbook")
    public Result renewBook(@RequestParam(value = "account") String account,
                            @RequestParam(value = "bookId") String bookId) {
        if (bookService.renewBook(account, bookId) == 1) {
            return new Result(ResultCode.SUCCESS);
        } else {
            return new Result(ResultCode.FAILURE, "续借失败");
        }
    }

    //还书
    @GetMapping("/returnbook")
    public Result returnBook(@RequestParam(value = "account") String account,
                             @RequestParam(value = "bookId") String bookId) {
        bookService.returnBook(account, bookId);
        return new Result(ResultCode.SUCCESS);
    }

    @GetMapping("/findbookbyisbn")
    public Result findBookByISBN(@RequestParam(value = "isbn") String ISBN) {
        List list = bookService.findBookByISBN(ISBN);
//        if (list != null) {
//            return new Result(ResultCode.SUCCESS, list);
//        } else {
//            return new Result(ResultCode.FAILURE, "请输入正确的ISBN");
//        }
        return new Result(ResultCode.SUCCESS, list);
    }


    @GetMapping("/findbookbytitle")
    public Result findBookByTitle(@RequestParam(value = "title") String title) {
        List list = bookService.findBookByTitle(title);
        if (list.size() > 0) {
            return new Result(ResultCode.SUCCESS, list);
        } else {
            return new Result(ResultCode.FAILURE, "请输入正确的信息");
        }
    }

    @GetMapping("/findbookbyauthor")
    public Result findBookByAuthor(@RequestParam(value = "author") String author) {
        List list = bookService.findBookByAuthor(author);
        if (list.size() > 0) {
            return new Result(ResultCode.SUCCESS, list);
        } else {
            return new Result(ResultCode.FAILURE, "请输入正确的信息");
        }
    }

    @GetMapping("/findbookbypress")
    public Result findBookByPress(@RequestParam(value = "press") String press) {
        List list = bookService.findBookByPress(press);
        if (list.size() > 0) {
            return new Result(ResultCode.SUCCESS, list);
        } else {
            return new Result(ResultCode.FAILURE, "请输入正确的信息");
        }
    }

    // TODO: 2018/11/17 借阅列表时间类型转换正确
    @GetMapping("/borrowlist")
    public Result borrowList(@RequestParam(value = "account") String account) {
        List list = bookService.borrowList(account);
        return new Result(ResultCode.SUCCESS, list);
    }

}
