package com.w.entity;

import java.time.LocalDateTime;

public class Reservation {
    private User user;
    private Book book;
    private LocalDateTime reservationTime;

    public Reservation(User user, Book book) {
        this.user = user;
        this.book = book;
        this.reservationTime = LocalDateTime.now();
    }

    public User getUser() {
        return user;
    }
    public Book getBook() {
        return book;
    }
    public LocalDateTime getReservationTime() {
        return reservationTime;
    }

    @Override
    public String toString() {
        return String.format(
                "用户: %s\n图书: %s\n预约时间: %s",
                user, book, reservationTime
        );
    }
}
