package com.dbms.backend;

import javafx.util.Pair;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DataBases {

    private static DataBase currentDataBase;//auto choose???
    private static Map<String, DataBase> dataBases = new HashMap<>();

    //full loading
    //lazy loading
    static {
        var db_directory = new File(db_path(""));
        if (!db_directory.isDirectory()) {
            db_directory.delete();
            db_directory.mkdir();
        }

        try (
                var s = Files.walk(FileSystems.getDefault().getPath(db_directory.getPath()))
        ) {
            s.forEach(
                    p -> {
                        var f = p.toFile();
                        if (f.isFile()) {
                            var db = readDataBase(f);
                            if (db != null) {
                                dataBases.put(db.name, db);
                                System.out.println(db.name);//debug
                            }
                        }
                    }
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String db_path(String name) {
        return String.join(File.separator, "db", name);
    }

    private static DataBase readDataBase(File file) {
        try (
                var oin = new ObjectInputStream(new FileInputStream(file))
        ) {
            return ((DataBase) oin.readObject());
        } catch (StreamCorruptedException e1) {
            System.out.println(String.format("The '%s' database is corrupted", file.getName()));
        } catch (FileNotFoundException e2) {//???????????????
            System.out.println(String.format("The '%s' database does not exist", file.getName()));
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        return null;
    }

    private static void writeDataBase(DataBase dataBase) throws Exception {
        var oout = new ObjectOutputStream(new FileOutputStream(db_path(dataBase.name)));
        oout.writeObject(dataBase);
        oout.close();
    }

    public static void createDataBase(String name) throws Exception {
        if (dataBases.containsKey(name))
            throw new Exception(String.format("The '%s' database already exists", name));

        var db = new DataBase(name);
        writeDataBase(db);
        dataBases.put(name, db);
        useDataBase(name);
    }

    public static void useDataBase(String name) throws Exception {
        if (!dataBases.containsKey(name))
            throw new Exception(String.format("The '%s' database does not exist", name));

        currentDataBase = dataBases.get(name);
    }

    public static DataBase getCurrentDataBase() throws RuntimeException {
        if (currentDataBase == null)
            throw new RuntimeException("No database selected");

        return currentDataBase;
    }

    public static DataBase getDataBase(String name) throws RuntimeException {
        if (!dataBases.containsKey(name))
            throw new RuntimeException(String.format("The '%s' database does not exist", name));

        return dataBases.get(name);
    }

    public static void exit() {
        //shutdown executors...
        //не перезаписывать часто на диск
        dataBases.forEach(//если не удалось записать 1 бд то должна быть попытка записать остальные
                (k, v) -> {
                    try {
                        writeDataBase(v);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        );
    }

    /*
    на вход нужно подать предобработанный лимит,офсет,
    то есть, если не было указано лимита, то он должен быть равен Integer.MAX_VALUE,
    если не было указано офсета, то он должен быть равен 0
    */
    public static Map<String, ArrayList> select(ArrayList<String> fieldNames, String tableName, int limit, int offset) throws Exception {
        var table = currentDataBase.getTable(tableName);
        var selection = new LinkedHashMap<String, ArrayList>();//запилить отдельную структуру???
        //по идее дальше функионалить не стоит ибо пиздец дохуя кода становится
        //и вообще функциональность не везде походу стоит юзать
        for (var fn : fieldNames) {
            var tmp = table.data.fields.get(fn);
            if (tmp == null)
                throw new RuntimeException(String.format("No such field '%s' in table '%s'", fn, table.header.name));

            selection.put(
                    fn,
                    (ArrayList) tmp.stream()
                            .skip(offset)
                            .limit(limit)
                            .collect(Collectors.toCollection(ArrayList::new))
            );
        }

        return selection;
    }
    //show databases???
    //alter new thread???
}