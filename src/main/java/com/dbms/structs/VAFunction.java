package com.dbms.structs;

public interface VAFunction<T, R> {//functional interface for lambdas with variable arguments

    R apply(T... t);
}