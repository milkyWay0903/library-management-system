package com.w.system;

import com.w.entity.Book;
import com.w.entity.User;
import com.w.entity.BorrowRecords;
import com.w.entity.Reservation;
import com.w.enums.BookType;

import java.util.*;

public class LibraryData {
    // 1. ISBN -> 多本书(同一ISBN的所有馆藏)
    private Map<String, List<Book>> booksByIsbn = new HashMap<>();
    // 2. 馆藏编号 -> 一本书
    private Map<String, Book> collectionNoToBook = new HashMap<>();
    // 3. 馆藏编号集合
    private Set<String> collectionNos = new HashSet<>();
    // 4. 类型 -> 多本书
    private Map<BookType, Set<Book>> booksByType = new EnumMap<>(BookType.class);
    // 5. 用户ID -> 一位用户
    private Map<String, User> usersById = new HashMap<>();
    // 6. 存储用户借阅记录
    private List<BorrowRecords> borrowRecords = new ArrayList<>();
    // 7. 存储用户预约记录
    private List<Reservation> reservations = new ArrayList<>();
    // 8. 存储图书评分
    private Map<Book, Double> bookRatings = new HashMap<>();
    // 9. 存储不同类型图书数量
    private Map<BookType, Integer> bookCountsByType = new EnumMap<>(BookType.class);

    // -------------------- 图书相关 --------------------
    private void addBook(Book book) {
        if (!collectionNos.add(book.getCollectionNo())) {
            throw new RuntimeException("馆藏编号重复");
        }
        collectionNos.add(book.getCollectionNo());
        collectionNoToBook.put(book.getCollectionNo(), book);
        booksByIsbn.computeIfAbsent(book.getIsbn(), k -> new ArrayList<>()).add(book);
        booksByType.computeIfAbsent(book.getType(), k -> new HashSet<>()).add(book);
    }

    public List<Book> getBooksByIsbn(String isbn) {
        return booksByIsbn.getOrDefault(isbn, Collections.emptyList());
    }

    public Book getBookByCollectionNo(String collectionNo) {
        return collectionNoToBook.get(collectionNo);
    }

    public Set<Book> getBooksByType(BookType type) {
        return booksByType.getOrDefault(type, Collections.emptySet());
    }

    // -------------------- 用户相关 --------------------
    public void addUser(User user) {
        usersById.put(user.getId(), user);
    }

    public User getUserById(String id) {
        return usersById.get(id);
    }

    // -------------------- 借阅记录相关 --------------------
    public void addBorrowRecord(BorrowRecords record) {
        
    }
}
