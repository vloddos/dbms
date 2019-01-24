package com.dbms.storage.blocks;

import java.io.Serializable;

public class BlocksPointer implements Serializable {

    private static final long serialVersionUID = -2178449488397141379L;

    Block first;
    Block last;
    Block firstDeleted;
}