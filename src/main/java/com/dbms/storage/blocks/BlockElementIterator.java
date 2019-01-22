package com.dbms.storage.blocks;

import java.util.Iterator;

public class BlockElementIterator<E> implements Iterator<E> {

    private BlockIterator blockIterator;
    private ElementIterator<E> elementIterator;

    public BlockElementIterator(Block block, Class<E> eClass) {
        blockIterator = new BlockIterator(block);
        elementIterator = blockIterator.getElementIterator(eClass);
    }

    public void setElement(E element) throws Exception {
        elementIterator.setElement(element);
    }

    @Override
    public boolean hasNext() {
        if (!elementIterator.hasNext())
            if (!blockIterator.hasNext())
                return false;
            else {
                blockIterator.next();

                E element = elementIterator.element;
                elementIterator = blockIterator.getElementIterator(elementIterator.getElementClass());
                elementIterator.element = element;

                return elementIterator.hasNext();
            }

        return true;
    }

    @Override
    public E next() {
        return hasNext() ? elementIterator.next() : null;
    }

    public void write(E element) throws Exception {
        elementIterator.write(element);
    }

    public void delete(String databaseName, String tableName, BlocksPointer blocksPointer) throws Exception {
        elementIterator.delete(databaseName, tableName, blocksPointer);
        if (blockIterator.getElementCount() == 0)
            blockIterator.delete(databaseName, tableName, blocksPointer);
    }
}