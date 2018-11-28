package com.dbms.logic;

import com.dbms.grammar.ArgsGuard;
import com.dbms.grammar.SqlParser;
import com.dbms.structs.DataBases;

import java.io.File;
import java.io.FileReader;
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
                    DataBases.getInstance().createDataBase(args.getName());
                    break;
                case "USE":
                    DataBases.getInstance().useDataBase(args.getName());
                    break;
                case "TABLE":
                    DataBases.getInstance().getCurrentDataBase().createTable(args.getName(), args.getFields());
                    break;
                case "SHOW":
                    System.out.println(DataBases.getInstance().getCurrentDataBase().getTable(args.getName()));
                    break;
                case "INSERT":
                    DataBases.getInstance().getCurrentDataBase().getTable(args.getName()).insert(args.convertToMap(args.getInsertableColumns(), args.getInsertableValues()));
                    break;
                case "SELECT":
                    DataBases.getInstance().select(new Vector<>(args.getInsertableColumns()), args.getLimit(), args.getOffset(), args.getName(), args.getWhere());
                    break;
                case "DELETE":
                    DataBases.getInstance().getCurrentDataBase().getTable(args.getName()).delete(args.getWhere());
                    break;
                case "UPDATE":
                    DataBases.getInstance().getCurrentDataBase().getTable(args.getName()).update(args.convertToMap(args.getInsertableColumns(), args.getInsertableValues()), args.getWhere());
                    break;
                default://exit
                    DataBases.getInstance().exit();//return/break/loop break is redundant
            }
        }
    }
}
