package com.dbms.backend;

import com.dbms.backend.DataBase;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

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
        return "db\\" + name;//File.separator???
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
        /*if (name.equals(currentDataBase.name))
            return;*/

        if (!dataBases.containsKey(name))
            throw new Exception(String.format("The '%s' database does not exist", name));

        /*var last = currentDataBase;
        if (last != null && last.isModified())
            ExecutorManager.dataBaseWriter.submit(
                    (Callable<Void>) () -> {
                        synchronized (last) {//фиксация по currentDataBase???
                            writeDataBase(last);
                            //last.modified=false//proxy???
                        }
                        return null;
                    }
            );*/
        currentDataBase = dataBases.get(name);
    }

    public static DataBase getCurrentDataBase() throws Exception {
        if (currentDataBase == null)
            throw new Exception("No database selected");

        return currentDataBase;
    }

    public static void exit() {
        //shutdown executors...
        //не перезаписывать часто на диск
        dataBases.forEach(//если не удалось записать 1 бд то должна быть попытка записать остальные
                (k, v) -> {
                    //if (v.isModified())
                    try {
                        writeDataBase(v);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        );
    }
    //show databases???
    //alter new thread???
}