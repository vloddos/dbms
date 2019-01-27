package com.dbms.classes.other;

import java.util.Arrays;

public class SingleBitBuffer {

    private byte[] buffer;
    private int bitPosition;
    private int bitLength;

    private int byteIndex() {
        return byteIndex(bitPosition);
    }

    private int byteIndex(int bitPosition) {
        return (bitPosition + 7) / 8;
    }

    public SingleBitBuffer(int bitLength) {
        this.bitLength = bitLength;
        buffer = new byte[byteIndex(bitLength)];
    }

    public SingleBitBuffer(byte[] buffer, int bitLength) {
        setBuffer(buffer, bitLength);
    }

    public void setBuffer(byte[] buffer, int bitLength) {
        if (buffer.length < byteIndex(bitLength))
            throw new IllegalArgumentException("");

        this.buffer = buffer;
        this.bitLength = bitLength;
    }

    public void reset() {
        bitPosition = 0;
    }

    public void clear() {
        Arrays.fill(buffer, (byte) 0);
    }

    public void resize(int newBitLength) {
        bitLength = newBitLength;
        buffer = Arrays.copyOf(buffer, byteIndex(newBitLength));
    }

    public byte[] getBuffer() {
        return buffer;
    }

    public int read() {
        if (bitPosition >= bitLength)
            throw new IllegalStateException("Buffer underflow");

        return (buffer[byteIndex()] >> (bitPosition++ % 8)) & 1;
    }

    public boolean readBoolean() {
        return read() == 1;
    }

    public void write(int bit) {
        if (bitPosition >= bitLength)
            throw new IllegalStateException("Buffer overflow");

        bit &= 1;//?
        buffer[byteIndex()] |= bit << (bitPosition % 8);
        ++bitPosition;
    }

    public void writeBoolean(boolean bit) {
        write(bit ? 1 : 0);
    }
}