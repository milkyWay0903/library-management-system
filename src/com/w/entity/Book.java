package com.w.entity;

import com.w.enums.BookType;

import java.util.HashSet;
import java.util.Objects;

public class Book {
    private String isbn;
    private String collectionNo; // 馆藏编号
    private String title;
    private String author;
    private BookType type;
    private String publishDate;
    private boolean isAvailable;

    public Book(String isbn, String collectionNo, String title, String author, BookType type, String publishDate) {
        this.isbn = isbn;
        this.collectionNo = collectionNo;
        this.title = title;
        this.author = author;
        this.type = type;
        this.publishDate = publishDate;
        this.isAvailable = true;
    }

    public String getIsbn() {
        return isbn;
    }
    public String getCollectionNo() {
        return collectionNo;
    }
    public String getTitle() {
        return title;
    }
    public BookType getType() {
        return type;
    }
    public String getAuthor() {
        return author;
    }
    public boolean isAvailable() {
        return isAvailable;
    }
    public void setAvailable(boolean available) {
        isAvailable = available;
    }
    public String getPublishDate() {
        return publishDate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(isbn, book.isbn);
    }

    @Override
    public String toString() {
        return String.format(
                "ISBN: %s\n馆藏编号: %s\n书名: %s\n作者: %s\n类型: %s\n出版日期: %s\n状态: %s",
                isbn, collectionNo, title, author, type, publishDate, isAvailable ? "可借阅" : "已借出"
        );
    }
}
