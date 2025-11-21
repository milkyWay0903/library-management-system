package com.w.entity;

public class BorrowRecords {
    private User borrower;
    private Book book;

    public BorrowRecords(User borrower, Book book) {
        this.borrower = borrower;
        this.book = book;
    }

    public User getBorrower() {
        return borrower;
    }
    public Book getBook() {
        return book;
    }

    @Override
    public String toString() {
        return String.format(
                "借阅人: %s\n图书: %s",
                borrower, book
        );
    }
}
