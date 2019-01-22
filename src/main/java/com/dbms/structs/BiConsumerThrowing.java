package com.dbms.structs;

public interface BiConsumerThrowing<T, U> {

    void accept(T t,U u) throws Exception;
}
