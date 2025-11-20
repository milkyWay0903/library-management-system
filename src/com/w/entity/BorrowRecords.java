package com.w.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BorrowRecords {
    private User borrower;
    private Book book;
    private LocalDateTime borrowTime;
    private LocalDateTime returnTime;

    public BorrowRecords(User borrower, Book book, LocalDateTime borrowTime) {
        this.borrower = borrower;
        this.book = book;
        this.borrowTime = borrowTime;
    }

    public User getBorrower() {
        return borrower;
    }
    public Book getBook() {
        return book;
    }
    public LocalDateTime getBorrowTime() {
        return borrowTime;
    }
    public LocalDateTime getReturnTime() {
        return returnTime;
    }

    // 设置归还时间
    public void setReturnTime() {
        this.returnTime = LocalDateTime.now();
        this.book.setAvailable(true);
    }

    private String formatTime(LocalDateTime time) {
        return time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    @Override
    public String toString() {
        String returnInfo = returnTime == null ? "未归还" : formatTime(returnTime);
        return String.format(
                "借阅人: %s\n图书: %s\n借阅时间: %s\n归还时间: %s",
                borrower, book, formatTime(borrowTime), returnInfo
        );
    }
}
