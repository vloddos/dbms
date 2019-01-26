package com.dbms.classes.lambdas_throwing;

public interface BiConsumerThrowing<T, U> {

    void accept(T t,U u) throws Exception;
}
