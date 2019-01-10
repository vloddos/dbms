package com.dbms.storage.blocks;

import java.io.File;

public class BlocksPointer {

    Block first;
    Block last;
    Block firstDeleted;

    public void setFile(File file) {
        if (first != null)
            first.file = file;
        if (last != null)
            last.file = file;
        if (firstDeleted != null)
            firstDeleted.file = file;
    }
}