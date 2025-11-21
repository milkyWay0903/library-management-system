package com.w.system;

import com.w.entity.Book;
import com.w.entity.User;
import com.w.entity.BorrowRecords;
import com.w.entity.Reservation;
import com.w.enums.BookType;

import java.time.LocalDateTime;
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
        return success ? "✅ 图书添加成功！\n" : "❌ 添加失败：馆藏编号「" + collectionNo + "」已存在，无法重复添加";
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

    // 3. 搜索图书
    public String searchBookByIsbn(String isbn) { // 通过ISBN搜索
        List<Book> books = libraryData.getBooksByIsbn(isbn);
        if (books.isEmpty()) {
            return "未找到ISBN为「" + isbn + "」的图书";
        }
//        StringBuilder sb = new StringBuilder("共有" + books.size() + "本书：\n");
//        for (Book book : books) {
//            sb.append(book.toString()).append("\n");
//        }
//        return sb.toString();
        return books.toString();
    }

    public String searchBookByTitle(String title) { // 通过书名搜索
        List<Book> books = libraryData.searchBooksByTitle(title);
        if (books.isEmpty()) {
            return "未找到书名为「" + title + "」的图书";
        }
        return books.toString();
    }

    public String searchBookByAuthor(String author) { // 通过作者搜索
        List<Book> books = libraryData.searchBooksByAuthor(author);
        if (books.isEmpty()) {
            return "未找到作者「" + author + "」的图书";
        }
        return books.toString();
    }

    // 4. 借阅图书
    public String borrowBook(String userId, String collectionNo) {
        User user = libraryData.getUserById(userId);
        if (user == null) {
            return "❌ 错误：用户ID「" + userId + "」不存在！";
        }
        Book book = libraryData.getBookByCollectionNo(collectionNo);
        if (book == null) {
            return "❌ 错误：馆藏编号「" + collectionNo + "」不存在！";
        }
        if (!book.isAvailable()) {
            // 检查是否已预约
            if (libraryData.isReserved(collectionNo, userId)) {
                return "✅ 用户「" + user.getName() + "」已预约图书「" + book.getTitle() + "」，请耐心等待！";
            }
            // 新增预约
            libraryData.addReservation(new Reservation(user, book));
            return "❌ 馆藏编号「" + collectionNo + "」的图书已借出，预约成功！当前排队位置：" + libraryData.getAllBorrowRecords().size();
        }
        // 执行借阅
        book.setAvailable(false);
        BorrowRecords record = new BorrowRecords(user, book);
        libraryData.addBorrowRecord(record);
        return "✅ 借阅成功！\n借阅信息：" + record;
    }

    // 5. 归还图书

}
