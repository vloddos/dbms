package com.dbms.classes.lambdas_throwing;

public interface BiFunctionThrowing<T, U, R> {

    R apply(T t, U u) throws Exception;
}
