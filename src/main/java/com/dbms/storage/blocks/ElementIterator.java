package com.dbms.storage.blocks;

import com.dbms.storage.Serialization;
import com.esotericsoftware.kryo.KryoSerializable;

import java.util.Iterator;

public class ElementIterator<E> implements Iterator<E> {

    private Class<E> elementClass;
    public E element;//if E extends KryoSerializable
    private Block block;
    private long currentPosition;
    private long deltaPosition;

    public ElementIterator(Block block, Class<E> elementClass) {
        this.elementClass = elementClass;
        this.block = block;
        currentPosition = block.getDataBeginPosition();
    }

    public Class<E> getElementClass() {
        return elementClass;
    }

    @Override
    public boolean hasNext() {
        return block != null && currentPosition + deltaPosition < block.getDataEndPosition();
    }

    @Override
    public E next() {
        try {
            if (hasNext()) {
                currentPosition += deltaPosition;//в начале итерации по блоку прибавится 0

                if (element instanceof KryoSerializable) {//fixme зависимость от Serialization(и от Block???)(и такое мб где то еще есть)
                    deltaPosition = block.readElement(currentPosition, (KryoSerializable) element);
                    return element;
                } else {
                    var p = block.readElement(currentPosition, elementClass);
                    deltaPosition = p.getValue();
                    return p.getKey();
                }
            }

            block = null;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void write(E element) {//перед 1 вызовом next и после 1 вызова будет перезаписывать 1 элемент
        if (block == null)
            return;

        block.writeBytes(
                currentPosition,
                Serialization.getInstance().getKryoBytes(element)
        );
    }

    /*public void delete(String databaseName, String tableName, BlocksPointer blocksPointer) throws Exception {
        if (block == null)
            return;

        BlockManager.getInstance().deleteElement(databaseName, tableName, blocksPointer, block);
    }*/
}
/*public class ElementIterator<E> implements Iterator<E> {

    private Class<E> elementClass;
    public E element;//if E extends KryoSerializable
    private Block block;
    private long previousPosition;
    private long currentPosition;

    public ElementIterator(Block block, Class<E> elementClass) {
        this.elementClass = elementClass;
        this.block = block;
        currentPosition = block.getDataBeginPosition();
    }

    public Class<E> getElementClass() {
        return elementClass;
    }

    @Override
    public boolean hasNext() {
        return !(block == null || currentPosition == block.getDataEndPosition());
    }

    @Override
    public E next() {
        try {
            if (hasNext()) {
                previousPosition = currentPosition;
                if (element instanceof KryoSerializable) {//fixme зависимость от Serialization(и от Block???)(и такое мб где то еще есть)
                    currentPosition += block.readElement(currentPosition, (KryoSerializable) element);
                    return element;
                } else {
                    var p = block.readElement(currentPosition, elementClass);
                    currentPosition += p.getValue();
                    return p.getKey();
                }
            }

            block = null;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void write(E element) {
        if (block == null)
            return;

        block.writeBytes(
                previousPosition,
                Serialization.getInstance().getKryoBytes(element)
        );
    }
}*/