package com.dbms.logic;

import com.dbms.grammar.ArgsGuard;
import com.dbms.grammar.SqlParser;
import com.dbms.structures.Databases;

import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class ExpressionExecutor {

    private ArgsGuard args;

    public void execute() throws Exception {
        File file = new File("src/test/java/resources/TestSandbox.txt");

        SqlParser parser = new SqlParser(new FileReader(file));

        Scanner scanner = new Scanner(file);

        while (true) {
            args = parser.parse();

            switch (args.command.image) {
                case "DATABASE":
                    /*rwl.writeLock().lock();
                    try {*/
                    Databases.getInstance().createDatabase(args.getName());
                    /*} finally {
                        rwl.writeLock().unlock();
                    }*/
                    break;
                case "USE":
                    /*rwl.readLock().lock();
                    try {*/
                    Databases.getInstance().useDatabase(args.getName());
                    /*} finally {
                        rwl.readLock().unlock();
                    }*/
                    break;
                case "TABLE":
                    Databases.getInstance().getCurrentDatabase().createTable(args.getName(), args.getFields());
                    break;
                case "SHOW":
                    System.out.println(Databases.getInstance().getCurrentDatabase().getTable(args.getName()));
                    break;
                case "INSERT":
                    Databases.getInstance().getCurrentDatabase().getTable(args.getName()).insert(args.convertToMap(args.getInsertableColumns(), args.getInsertableValues()));
                    break;
                case "SELECT":
                    //Databases.getInstance().select(new Vector<>(args.getInsertableColumns()), args.getLimit(), args.getOffset(), args.getName(), args.getWhere());
                    break;
                case "DELETE":
                    //Databases.getInstance().getCurrentDatabase().getTable(args.getName()).delete(args.getWhere());
                    break;
                case "UPDATE":
                    //Databases.getInstance().getCurrentDatabase().getTable(args.getName()).update(args.convertToMap(args.getInsertableColumns(), args.getInsertableValues()), args.getWhere());
                    break;
                default://exit
                    Databases.getInstance().exit();//return/break/loop break is redundant
            }
        }
    }
}
