package com.doulevo.userapi.util.function;

public interface Appender<T> {
    T appendTo(T target);
}