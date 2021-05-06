package com.doulevo.userapi.util.function;

@FunctionalInterface
public interface TriPredicate<T, U, V> {
  boolean test(T t, U u, V v);
}
