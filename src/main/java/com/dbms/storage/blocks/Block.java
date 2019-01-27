package com.dbms.storage.blocks;

import com.dbms.storage.serialization.DataSerializable;
import com.dbms.storage.serialization.Serialization;

import java.io.*;

// FIXME: 24.12.2018 отрефакторить хз как
// FIXME: 24.12.2018 общий rac/Output(1 на блок или 1 на все блоки)
// TODO: 24.01.2019 многопоточность на уровне блоков
public class Block implements DataSerializable, Serializable {

    private static final long serialVersionUID = 7093706013946286265L;

    public static final int SIZE_STANDARD = 65536;

    private boolean deleted;
    private int elementCount;
    private long left = -1;
    private long right = -1;

    private long blockPosition;
    private long dataBeginPosition;
    private long dataEndPosition;//чтобы знать с какого места можно аппендить данные
    private int size;

    private File file;

    private Block() {
    }

    public Block(File file, long blockPosition) {
        this(file, blockPosition, SIZE_STANDARD);
    }

    public Block(File file, long blockPosition, int size) {
        this.file = file;
        this.blockPosition = blockPosition;
        this.dataBeginPosition = this.dataEndPosition = blockPosition + 49;
        this.size = size;
    }

    /**
     * Reads an element that implements {@link DataSerializable} from a given position.
     *
     * @param position the position within the file from which to read the object
     * @param element  an object that must be read using {@link DataSerializable#read(DataInputStream)}
     * @param <E>      type of the element
     * @return number of bytes read
     * @throws Exception
     */
    public <E extends DataSerializable> long readElement(long position, E element) throws Exception {
        try (var fis = new FileInputStream(file)) {
            fis.skip(position);
            var dis = new DataInputStream(fis);
            element.read(dis);
            return fis.getChannel().position() - position;
        }
    }
    // TODO: 25.01.2019 чтение примитивов или как то так

    private void writeBytes(long position, byte[] bytes, int off, int len) {
        try (var rac = new RandomAccessFile(file, "rw")) {
            rac.seek(position);
            rac.write(bytes, off, len);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeBytes(long position, byte[] bytes) {
        writeBytes(position, bytes, 0, bytes.length);
    }

    public void writeBlock() throws Exception {
        writeBytes(
                blockPosition,
                Serialization.getInstance().getDataSerializableBytes(this)
        );
    }

    public void appendBytes(byte[] bytes) throws Exception {
        writeBytes(dataEndPosition, bytes);
        writeDataEndPosition(dataEndPosition += bytes.length);
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void writeDeleted(boolean deleted) throws Exception {
        this.deleted = deleted;
        writeBytes(
                blockPosition,
                Serialization.getInstance().getDataSerializableBytes(deleted)
        );
    }

    public int getElementCount() {
        return elementCount;
    }

    public void writeElementCount(int elementCount) throws Exception {
        this.elementCount = elementCount;
        writeBytes(
                blockPosition + 1,
                Serialization.getInstance().getDataSerializableBytes(elementCount)
        );
    }

    public void decElementCount() throws Exception {
        writeElementCount(elementCount - 1);
    }

    public void incElementCount() throws Exception {
        writeElementCount(elementCount + 1);
    }

    public Block readLeft() throws Exception {
        if (left == -1)
            return null;

        var b = new Block();
        try (var dis = new DataInputStream(new FileInputStream(file))) {
            dis.skip(left);
            b.read(dis);
            b.file = file;
            return b;
        }
    }

    public void writeLeft(long left) throws Exception {
        this.left = left;
        writeBytes(
                blockPosition + 5,
                Serialization.getInstance().getDataSerializableBytes(left)
        );
    }

    public long getRight() {
        return right;
    }

    public Block readRight() throws Exception {
        if (right == -1)
            return null;

        var b = new Block();
        try (var dis = new DataInputStream(new FileInputStream(file))) {
            dis.skip(right);
            b.read(dis);
            b.file = file;
            return b;
        }
    }

    public void writeRight(long right) throws Exception {
        this.right = right;
        writeBytes(
                blockPosition + 13,
                Serialization.getInstance().getDataSerializableBytes(right)
        );
    }

    public long getBlockPosition() {
        return blockPosition;
    }

    public long getDataBeginPosition() {
        return dataBeginPosition;
    }

    public long getDataEndPosition() {
        return dataEndPosition;
    }

    public void writeDataEndPosition(long dataEndPosition) throws Exception {
        this.dataEndPosition = dataEndPosition;
        writeBytes(
                blockPosition + 37,
                Serialization.getInstance().getDataSerializableBytes(dataEndPosition)
        );
    }

    public int getSize() {
        return size;
    }

    public int getRemainingSize() {
        return size - (int) (dataEndPosition - blockPosition);
    }

    public void reuse() throws Exception {
        writeDeleted(false);
        writeElementCount(0);
        writeLeft(-1);
        writeRight(-1);
        writeDataEndPosition(dataBeginPosition);
    }

    @Override
    public void write(DataOutputStream out) throws Exception {
        out.writeBoolean(deleted);
        out.writeInt(elementCount);
        out.writeLong(left);
        out.writeLong(right);

        out.writeLong(blockPosition);
        out.writeLong(dataBeginPosition);
        out.writeLong(dataEndPosition);
        out.writeInt(size);
    }

    @Override
    public void read(DataInputStream in) throws Exception {
        deleted = in.readBoolean();
        elementCount = in.readInt();
        left = in.readLong();
        right = in.readLong();

        blockPosition = in.readLong();
        dataBeginPosition = in.readLong();
        dataEndPosition = in.readLong();
        size = in.readInt();
    }

    @Override
    public boolean equals(Object other) {
        if (!super.equals(other))
            return false;
        if (this == other)
            return true;
        if (!this.getClass().equals(other.getClass()))
            return false;

        var otherBlock = (Block) other;
        return blockPosition == otherBlock.blockPosition && file.equals(otherBlock.file);
    }
}