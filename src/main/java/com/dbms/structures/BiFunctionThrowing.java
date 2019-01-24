package com.dbms.structures;

public interface BiFunctionThrowing<T, U, R> {

    R apply(T t, U u) throws Exception;
}
