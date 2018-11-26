package com.dbms.grammar;

import com.dbms.grammar.ArgsGuard;
import junitx.framework.FileAssert;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Special class for using in tests class only
 * See DRY & KISS
 */
public abstract class AbstractTest {

    protected void writeEntityName(FileWriter actual, ArgsGuard args) throws IOException {
        actual.write(args.getName() + "\n");
    }

    protected void writeInsertableColumns(FileWriter actual, ArgsGuard args) throws IOException {
        for (int i = 0; i < args.getInsertableColumns().size() - 1; i++)
            actual.write(args.getInsertableColumns().get(i) + ", ");

        actual.write(args.getInsertableColumns().get(args.getInsertableColumns().size() - 1) + "\n");
    }

    protected void writeWhere(FileWriter actual, ArgsGuard args) throws IOException {
        actual.write(args.getWhere() + "\n");
    }

    protected void writeInsertableValues(FileWriter actual, ArgsGuard args) throws IOException {
        for (int i = 0; i < args.getInsertableValues().size() - 1; i++)
            actual.write(args.getInsertableValue(i) + ", ");

        actual.write(args.getInsertableValue(args.getInsertableValues().size() - 1));
    }

    protected void writeUpdatableColumns(FileWriter actual, ArgsGuard args) throws IOException {
        for (int i = 0; i < args.getUpdatableColumns().size(); i++) {
            actual.write(args.getUpdatableColumns().get(i) + "\n");
            actual.write(args.getUpdatableValues().get(i) + "\n");
        }
    }

    protected void writeLimitAndOffset(FileWriter actual, ArgsGuard args) throws IOException {
        actual.write(String.valueOf(args.getLimit()) + "\n");
        actual.write(String.valueOf(args.getOffset()));
    }

    protected void assertFiles(String dir) {
        FileAssert.assertEquals(
                new File("src/test/java/resources/" + dir + "/Expected.txt"),
                new File("src/test/java/resources/" + dir + "/Actual.txt")
        );
    }
}
