package com.dbms.backend;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorManager {

    public static ExecutorService dataBaseWriter = Executors.newSingleThreadExecutor();//shutdown
}