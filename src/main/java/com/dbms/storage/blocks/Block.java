package com.dbms.storage.blocks;

import com.dbms.storage.Serialization;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import javafx.util.Pair;

import java.io.*;

// FIXME: 24.12.2018 отрефакторить хз как
// FIXME: 24.12.2018 общий rac/Output(1 на блок или 1 на все блоки)
public class Block implements KryoSerializable {

    public static final int SIZE_STANDARD = 65536;

    private boolean deleted;
    private int elementCount;
    private long left = -1;
    private long right = -1;

    private long blockPosition;
    private long dataBeginPosition;
    private long dataEndPosition;//чтобы знать с какого места можно аппендить данные
    private int size;

    File file;
    private ThreadLocal<Kryo> kryoThreadLocal = ThreadLocal.withInitial(Serialization.getInstance()::getKryo);

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
     * Reads an element from a given position.
     *
     * @param position the position within the file from which to read the object
     * @param eClass   class of the element
     * @return pair of element and number of bytes read
     * @throws Exception
     */
    public <E> Pair<E, Long> readElement(long position, Class<E> eClass) throws Exception {
        try (var fin = new FileInputStream(file)) {
            fin.skip(position);
            var in = new Input(fin);//input close is redundant
            var e = kryoThreadLocal.get().readObject(in, eClass);
            return new Pair<>(e, (long) in.position());
        }
    }

    /**
     * Reads an element that implements {@link KryoSerializable} from a given position.
     *
     * @param position the position within the file from which to read the object
     * @param element  an object that must be read using {@link KryoSerializable#read(Kryo, Input)}
     * @param <E>      type of the element
     * @return number of bytes read
     * @throws Exception
     */
    public <E extends KryoSerializable> long readElement(long position, E element) throws Exception {
        try (var fin = new FileInputStream(file)) {
            fin.skip(position);
            var in = new Input(fin);//input close is redundant
            element.read(kryoThreadLocal.get(), in);
            return in.position();
        }
    }

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

    public void writeBlock() {
        var out = new Output(1024, -1);
        write(kryoThreadLocal.get(), out);
        writeBytes(blockPosition, out.getBuffer(), 0, out.position());
    }

    public void appendBytes(byte[] bytes) {
        writeBytes(dataEndPosition, bytes);
        writeDataEndPosition(dataEndPosition += bytes.length);
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void writeDeleted(boolean deleted) {
        this.deleted = deleted;
        var out = new Output(1024, -1);
        out.writeBoolean(deleted);
        writeBytes(blockPosition, out.getBuffer(), 0, out.position());
    }

    public int getElementCount() {
        return elementCount;
    }

    public void writeElementCount(int elementCount) {
        this.elementCount = elementCount;
        var out = new Output(1024, -1);
        out.writeInt(elementCount);
        writeBytes(blockPosition + 1, out.getBuffer(), 0, out.position());
    }

    public void decElementCount() {
        writeElementCount(--elementCount);
    }

    public void incElementCount() {
        writeElementCount(++elementCount);
    }

    public Block readLeft() throws Exception {
        if (left == -1)
            return null;

        var b = new Block();
        try (var fin = new FileInputStream(file)) {
            fin.skip(left);
            b.read(kryoThreadLocal.get(), new Input(fin));//input close is redundant
            b.file = file;
            return b;
        }
    }

    public void writeLeft(long left) {
        this.left = left;
        var out = new Output(1024, -1);
        out.writeLong(left);
        writeBytes(blockPosition + 5, out.getBuffer(), 0, out.position());
    }

    public long getRight() {
        return right;
    }

    public Block readRight() throws Exception {
        if (right == -1)
            return null;

        var b = new Block();
        try (var fin = new FileInputStream(file)) {
            fin.skip(right);
            b.read(kryoThreadLocal.get(), new Input(fin));//input close is redundant
            b.file = file;
            return b;
        }
    }

    public void writeRight(long right) {
        this.right = right;
        var out = new Output(1024, -1);
        out.writeLong(right);
        writeBytes(blockPosition + 13, out.getBuffer(), 0, out.position());
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

    public void writeDataEndPosition(long dataEndPosition) {
        this.dataEndPosition = dataEndPosition;
        var out = new Output(1024, -1);
        out.writeLong(dataEndPosition);
        writeBytes(blockPosition + 37, out.getBuffer(), 0, out.position());
    }

    public int getSize() {
        return size;
    }

    public int getRemainingSize() {
        return size - (int) (dataEndPosition - blockPosition);
    }

    public void reuse() {
        writeDeleted(false);
        writeElementCount(0);
        writeLeft(-1);
        writeRight(-1);
        writeDataEndPosition(dataBeginPosition);
    }

    @Override
    public void write(Kryo kryo, Output output) {
        output.writeBoolean(deleted);
        output.writeInt(elementCount);
        output.writeLong(left);
        output.writeLong(right);

        output.writeLong(blockPosition);
        output.writeLong(dataBeginPosition);
        output.writeLong(dataEndPosition);
        output.writeInt(size);
    }

    @Override
    public void read(Kryo kryo, Input input) {
        deleted = input.readBoolean();
        elementCount = input.readInt();
        left = input.readLong();
        right = input.readLong();

        blockPosition = input.readLong();
        dataBeginPosition = input.readLong();
        dataEndPosition = input.readLong();
        size = input.readInt();
    }

    @Override
    public boolean equals(Object other) {
        if (!super.equals(other))
            return false;
        if (this == other)
            return true;
        if (other == null || this.getClass() != other.getClass())
            return false;

        var otherBlock = (Block) other;
        return blockPosition == otherBlock.blockPosition && file.equals(otherBlock.file);
    }
}