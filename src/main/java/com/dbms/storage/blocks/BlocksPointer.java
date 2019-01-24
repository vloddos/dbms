package com.dbms.storage.blocks;

import java.io.Serializable;

/**
 * TODO: 24.01.2019 здесь нужен отдельный лок,
 * потому что многопоточность на уровне блоков и разные блоки могут одновременно меняться в 1 таблице,
 * следовательно может меняться и указатель на блоки
 */
public class BlocksPointer implements Serializable {

    private static final long serialVersionUID = -2178449488397141379L;

    Block first;
    Block last;
    Block firstDeleted;
}