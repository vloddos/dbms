package com.dbms.logic;

import com.dbms.structs.DataBases;
import com.dbms.grammar.ArgsGuard;
import com.dbms.grammar.SqlParser;

import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;

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
                    DataBases.createDataBase(args.getName());
                    break;
                case "USE":
                    DataBases.useDataBase(args.getName());
                    break;
                case "TABLE":
                    DataBases.getCurrentDataBase().createTable(args.getName(), args.getFields());
                    break;
                case "SHOW":
                    System.out.println(DataBases.getCurrentDataBase().getTable(args.getName()));
                    break;
                case "exit":
                    DataBases.exit();
                    break;
                case "INSERT":
                    DataBases.getCurrentDataBase().getTable(args.getName()).insert(args.convertToMap(args.getInsertableColumns(), args.getInsertableValues()));
                    break;
                case "SELECT":
                    DataBases.select(new Vector(Arrays.asList(args.getInsertableColumns())), args.getLimit(), args.getOffset(),  args.getName(), args.getWhere());
                    break;
                case "DELETE":
                    DataBases.getCurrentDataBase().getTable(args.getName()).delete(args.getWhere());
                case "UPDATE":
                    DataBases.getCurrentDataBase().getTable(args.getName()).update(args.convertToMap(args.getInsertableColumns(), args.getInsertableValues()), args.getWhere());
            }
            if (args.command.equals("exit"))
                break;
        }
    }
}
