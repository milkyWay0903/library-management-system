package com.w.system;

import com.w.entity.Book;
import com.w.entity.User;
import com.w.entity.BorrowRecords;
import com.w.entity.Reservation;
import com.w.enums.BookType;

import java.util.*;

public class LibraryService {
    private final LibraryData libraryData;

    public LibraryService(LibraryData libraryData) {
        this.libraryData = libraryData;
    }

    // 1. 添加图书（馆藏编号唯一）
    public String addBook(String isbn, String collectionNo, String title, String author, BookType type, String publishDate) {
        Book book = new Book(isbn, collectionNo, title, author, type, publishDate);
        boolean success = libraryData.addBook(book);
        return success ? "✅图书添加成功！\n" : "❌添加失败：馆藏编号「" + collectionNo + "」已存在，无法重复添加";
    }

    // 2. 添加用户
    public String addUser(String userId, String userName, String contact) {
        // 校验用户ID是否重复
        if (libraryData.isUserIdExists(userId)) {
            return "❌ 错误：用户ID「" + userId + "」已存在，无法重复添加！";
        }
        // 新增用户
        User user = new User(userId, userName, contact);
        libraryData.addUser(user);
        return "✅ 用户添加成功！\n用户信息：" + user;
    }

    // 3. 搜索图书（通过ISBN搜索）
    public String searchBookByIsbn(String isbn) {
        List<Book> books = libraryData.getBooksByIsbn(isbn);
        if (books.isEmpty()) {
            return "未找到ISBN为「" + isbn + "」的图书";
        }
        StringBuilder sb = new StringBuilder("共有" + books.size() + "本书：\n");
        for (Book book : books) {
            sb.append(book.toString()).append("\n");
        }
        return sb.toString();
    }


}
