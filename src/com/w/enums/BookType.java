package com.w.enums;

public enum BookType {
    FICTION("小说"),
    TECHNOLOGY("技术"),
    SCIENCE("科学"),
    HISTORY("历史"),
    ART("艺术"),
    PHILOSOPHY("哲学"),
    OTHER("其他");

    private final String desc;

    BookType(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
