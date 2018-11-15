package com.dbms.logic;

import com.dbms.backend.DataBase;
import com.dbms.backend.DataBases;
import com.dbms.backend.Table;
import com.dbms.backend.TableHeader;
import com.dbms.grammar.ArgsGuard;
import com.dbms.grammar.SqlParser;

import javax.xml.crypto.Data;
import java.io.FileReader;

public class ExpressionExecutor {
    ArgsGuard args;

    void execute() throws Exception{
        SqlParser parser = new SqlParser(new FileReader("src/test/java/resources/TestSandbox.txt"));
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
                case "EXIT":
                    DataBases.exit();
                    break;
                case "INSERT":
                    DataBases.getCurrentDataBase().getTable(args.getName()).insert(args.convertToMap(args.getInsertableColumns(), args.getInsertableValues()));
                    break;
            }
        }
    }
}
