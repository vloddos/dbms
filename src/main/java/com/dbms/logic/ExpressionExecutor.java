package com.dbms.logic;

import com.dbms.backend.DataBases;
import com.dbms.backend.Table;
import com.dbms.backend.TableHeader;
import com.dbms.grammar.ArgsGuard;
import com.dbms.grammar.SqlParser;

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
                    DataBases.createDataBase(args.getName());
                    break;
                case "USE":
                    DataBases.useDataBase(args.getName());
                    break;
                case "TABLE":
                    DataBases.getCurrentDataBase().createTable(new Table(
                            new TableHeader(
                                    args.getName(),
                                    args.getFields()
                            )
                    ));
                    break;
                case "SHOW":
                    System.out.println(DataBases.getCurrentDataBase().showCreate(args.getName()));
                    break;
                case "exit":
                    DataBases.exit();
                    break;
                case "INSERT":
                    DataBases.getCurrentDataBase().getTable(args.getName()).insert(args.convertToMap(args.getInsertableColumns(), args.getInsertableValues()));
                    break;
                case "SELECT":
                    DataBases.select(args.getInsertableColumns(), args.getName(), args.getLimit(), args.getOffset());
                    break;
            }
            if (args.command.equals("exit"))
                break;
        }
    }
}
