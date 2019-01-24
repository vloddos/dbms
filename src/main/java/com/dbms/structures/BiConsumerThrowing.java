package com.dbms.structures;

public interface BiConsumerThrowing<T, U> {

    void accept(T t,U u) throws Exception;
}
