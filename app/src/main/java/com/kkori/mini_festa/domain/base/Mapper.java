package com.kkori.mini_festa.domain.base;

public interface Mapper<T, E> {

    E mapFrom(T from);

}
