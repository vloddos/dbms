package com.dbms.storage.blocks;

import java.util.Iterator;

public class BlockIterator implements Iterator<Block> {

    private Block current;
    private Block next;//!=null if current was deleted

    public BlockIterator(Block block) {
        next = block;//this.current = current;
    }

    public <E> ElementIterator<E> getElementIterator(Class<E> eClass) {//if next!=null?
        return new ElementIterator<>(current, eClass);
    }

    @Override
    public boolean hasNext() {
        return next != null || current != null && current.getRight() != -1;
    }

    @Override
    public Block next() {
        try {
            if (hasNext())
                if (next != null) {
                    current = next;
                    next = null;
                } else
                    current = current.readRight();
            else
                current = null;

            return current;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void delete(String databaseName, String tableName, BlocksPointer blocksPointer) throws Exception {
        if (current == null || current.isDeleted())
            return;

        next = current.readRight();
        BlockManager.getInstance().deleteBlock(databaseName, tableName, blocksPointer, current);
    }

    public int getElementCount() {
        return (current == null || current.isDeleted()) ? -1 : current.getElementCount();
    }
}