package com.dbms.storage.blocks;

import java.util.Iterator;

public class BlockIterator implements Iterator<Block> {

    private Block current;

    public BlockIterator(Block current) {
        this.current = current;
    }

    public <E> ElementIterator<E> getElementIterator(Class<E> eClass) {
        return new ElementIterator<>(current, eClass);
    }

    @Override
    public boolean hasNext() {
        return current != null && current.getRight() != -1;
    }

    @Override
    public Block next() {
        try {
            return current = (hasNext() ? current.readRight() : null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /*public void delete(String databaseName, String tableName, BlocksPointer blocksPointer) throws Exception {
        BlockManager.getInstance().deleteBlock(databaseName, tableName, blocksPointer, current);
    }*/
}