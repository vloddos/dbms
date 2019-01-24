package com.dbms.storage.blocks;

import com.dbms.storage.serialization.DataSerializable;
import com.dbms.storage.serialization.Serialization;

import java.util.Iterator;

public class ElementIterator<E> implements Iterator<E> {

    private Class<E> elementClass;

    //возможно объект должен быть приведен к какому-то состоянию, прежде чем будет считан
    //поэтому лучше сделать возможность устанавливать объект, чем каждый раз при считывании создавать новый
    //(хотя можно вдобавок запилить и вариант с консумером)
    E element;//if E extends DataSerializable

    private Block block;
    private long currentPosition;
    private long deltaPosition;

    public ElementIterator(Block block, Class<E> elementClass) {
        this.elementClass = elementClass;
        this.block = block;
        if (block != null)
            currentPosition = block.getDataBeginPosition();
    }

    public Class<E> getElementClass() {
        return elementClass;
    }

    public E getElement() {
        return element;
    }

    public void setElement(E element) throws Exception {
        if (!(element instanceof DataSerializable))
            throw new Exception("The element must be instance of DataSerializable");

        this.element = element;
    }

    @Override
    public boolean hasNext() {
        return block != null && !block.isDeleted() && currentPosition + deltaPosition < block.getDataEndPosition();
    }

    @Override
    public E next() {
        try {
            if (hasNext()) {
                currentPosition += deltaPosition;//в начале итерации по блоку прибавится 0
                deltaPosition = block.readElement(currentPosition, (DataSerializable) element);
                return element;
            }

            block = null;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void write(E element) throws Exception {//перед 1 вызовом next и после 1 вызова будет перезаписывать 1 элемент
        if (block == null)
            return;

        block.writeBytes(
                currentPosition,
                Serialization.getInstance().getDataSerializableBytes(element)
        );
    }

    // TODO: 24.01.2019 как то оптимизировать или вообще поменять удаление?
    public void delete(String databaseName, String tableName, BlocksPointer blocksPointer) throws Exception {
        if (block == null || block.isDeleted())
            return;

        BlockManager.getInstance().deleteElement(databaseName, tableName, blocksPointer, block);
    }
}