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
    // 4. 用户ID -> 一位用户
    private Map<String, User> usersById = new HashMap<>();
    // 5. 存储用户借阅记录
    private List<BorrowRecords> borrowRecords = new ArrayList<>();
    // 6. 存储用户预约记录
    private List<Reservation> reservations = new ArrayList<>();
    // 7. 存储不同类型图书数量
    private Map<BookType, Integer> bookCountsByType = new EnumMap<>(BookType.class);
    // 8. 存储图书评分
    private Map<String, Double> bookRatings = new HashMap<>();


    // -------------------- 图书相关 --------------------
    public boolean addBook(Book book) {
        if (!collectionNos.add(book.getCollectionNo())) {
            throw new RuntimeException("馆藏编号重复");
        }
        collectionNos.add(book.getCollectionNo());
        collectionNoToBook.put(book.getCollectionNo(), book);
        booksByIsbn.computeIfAbsent(book.getIsbn(), k -> new ArrayList<>()).add(book);
        bookCountsByType.put(book.getType(), bookCountsByType.getOrDefault(book.getType(), 0) + 1);
        bookRatings.put(book.getIsbn(), 0.0);
        return true;
    }

    public List<Book> getBooksByIsbn(String isbn) {
        return booksByIsbn.getOrDefault(isbn, Collections.emptyList());
    }

    public Book getBookByCollectionNo(String collectionNo) {
        return collectionNoToBook.get(collectionNo);
    }

    // -------------------- 用户相关 --------------------
    public boolean isUserIdExists(String userId) {
        return usersById.containsKey(userId);
    }

    public void addUser(User user) {
        usersById.put(user.getId(), user);
    }

    public User getUserById(String id) {
        return usersById.get(id);
    }

    // -------------------- 借阅记录相关 --------------------
    public void addBorrowRecord(BorrowRecords record) {
        borrowRecords.add(record);
    }

    public BorrowRecords getUnreturnedRecordByCollectionNo(String collectionNo) {
        for (BorrowRecords record : borrowRecords) {
            if (record.getBook().getCollectionNo().equals(collectionNo) && record.getReturnTime() == null) {
                return record;
            }
        }
        return null;
    }

    public List<BorrowRecords> getAllBorrowRecords() {
        return new ArrayList<>(borrowRecords);
    }

    // -------------------- 预约相关 --------------------
    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    public Reservation getFirstReservationByBook(Book book) {
        for (Reservation reservation : reservations) {
            if (reservation.getBook().equals(book)) {
                return reservation;
            }
        }
        return null;
    }

    public List<Reservation> getAllReservations() {
        return new ArrayList<>(reservations);
    }

    // -------------------- 统计相关 --------------------
    public Map<BookType, Integer> getBookCountByType() {
        return new HashMap<>(bookCountsByType);
    }

    // -------------------- 图书评分相关 --------------------
    public double getBookRating(Book book) {
        return bookRatings.getOrDefault(book.getIsbn(), 0.0);
    }

    public Map<String, Double> getBookRatings() {
        return new HashMap<>(bookRatings);
    }
}
