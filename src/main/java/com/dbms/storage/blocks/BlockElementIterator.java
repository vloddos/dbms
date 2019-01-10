package com.dbms.storage.blocks;

import java.util.Iterator;

public class BlockElementIterator<E> implements Iterator<E> {

    private BlockIterator blockIterator;
    private ElementIterator<E> elementIterator;

    public BlockElementIterator(Block block, Class<E> eClass) {
        blockIterator = new BlockIterator(block);
        elementIterator = blockIterator.getElementIterator(eClass);
    }

    public void setElement(E element) {
        elementIterator.element = element;
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

    public void write(E element) {
        elementIterator.write(element);
    }

    /*public void delete(String databaseName, String tableName, BlocksPointer blocksPointer) throws Exception {
        elementIterator.delete(databaseName, tableName, blocksPointer);
    }*/
}

/*// FIXME: 28.12.2018 убрать костыль для перезаписи последнего значения в предыдущем блоке
public class BlockElementIterator<E> implements Iterator<E> {

    private BlockIterator blockIterator;
    private ElementIterator<E> prev;
    private ElementIterator<E> elementIterator;

    public BlockElementIterator(Block block, Class<E> eClass) {
        blockIterator = new BlockIterator(block);
        elementIterator = blockIterator.getElementIterator(eClass);
    }

    public void setElement(E element) {
        if (prev != null)
            prev.element = element;
        elementIterator.element = element;
    }

    @Override
    public boolean hasNext() {
        if (!elementIterator.hasNext())
            if (!blockIterator.hasNext())
                return false;
            else {
                prev = elementIterator;
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
        if (hasNext()) {
            prev = null;
            return elementIterator.next();
        } else
            return null;
    }

    public void write(E element) {
        if (prev == null)
            elementIterator.write(element);
        else
            prev.write(element);
    }
}*/