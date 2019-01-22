package com.dbms.storage.blocks;

import java.io.File;
import java.io.Serializable;

public class BlocksPointer implements Serializable {

    private static final long serialVersionUID = -2178449488397141379L;

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