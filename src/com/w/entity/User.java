package com.w.entity;

import java.util.Objects;

public class User {
    private String id;
    private String name;
    private String contact;

    public User(String id, String name, String contact) {
        this.id = id;
        this.name = name;
        this.contact = contact;
    }

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getContact() {
        return contact;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public String toString() {
        return String.format(
                "用户ID: %s\n用户名: %s\n联系电话: %s",
                id, name, contact
        );
    }
}
