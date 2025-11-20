package com.w.util;

import java.util.Collection;
import java.util.function.Predicate;

// 泛型工具类：实现了与具体类型无关的通用功能，提高代码复用性
public class CollectionUtils {

    /**
     * 泛型方法：查找集合中第一个满足条件的元素
     *
     * @param collection 目标集合
     * @param predicate  条件判断器
     * @param <T>        集合元素类型
     * @return 第一个满足条件的元素（无则返回null）
     */
    public static <T> T findFirst(Collection<T> collection, Predicate<T> predicate) {
        for (T t : collection) {
            if (predicate.test(t)) {
                return t;
            }
        }
        return null;
    }

    /**
     * 泛型方法：过滤集合中满足条件的元素
     *
     * @param collection 目标集合
     * @param predicate  条件判断器
     * @param <T>        集合元素类型
     * @return 满足条件的元素组成的新集合
     */
    public static <T> Collection<T> filter(Collection<T> collection, Predicate<T> predicate) {
        Collection<T> result = new java.util.ArrayList<>();
        for (T t : collection) {
            if (predicate.test(t)) {
                result.add(t);
            }
        }
        return result;
    }

    /**
     * 泛型方法：将源集合中的元素复制到目标集合中
     *
     * @param soucre 源集合
     * @param target 目标集合
     * @param <T>    集合元素类型
     */
    public static <T> void copy(Collection<T> soucre, Collection<T> target) {
        target.addAll(soucre);
    }
}
