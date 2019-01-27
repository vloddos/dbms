package com.dbms.storage.blocks;

import com.dbms.storage.file_structs.BlockExtendedFileStruct;
import com.dbms.storage.serialization.Serialization;
import org.apache.commons.io.FilenameUtils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

// FIXME: 24.12.2018 общий rac/Output(1 на блок или 1 на все блоки)
public class BlockManager {

    private BlockManager() {
    }

    private static BlockManager instance;

    public static BlockManager getInstance() {
        if (instance == null)
            instance = new BlockManager();

        return instance;
    }

    /** FIXME: 24.01.2019 читать {@link Block#blockPosition} вместо самих блоков?*/
    public BlocksPointer readBlocksPointer(String databaseName, String tableName) throws Exception {
        try (
                var in = new ObjectInputStream(
                        new FileInputStream(
                                BlockExtendedFileStruct.getBlocksPointerFullPath(databaseName, tableName)
                        )
                )
        ) {
            return (BlocksPointer) in.readObject();
        }
    }

    public Map<String, BlocksPointer> readAllBlocksPointers(String databaseName) throws Exception {
        var blocksPointers = new HashMap<String, BlocksPointer>();

        for (var f : new File(BlockExtendedFileStruct.getBlocksPointersFullPath(databaseName)).listFiles(File::isFile)) {
            var tableName = FilenameUtils.getBaseName(f.getName());//имя указателя на блоки=имя таблицы в которой этот указатель используется
            blocksPointers.put(tableName, readBlocksPointer(databaseName, tableName));
        }

        return blocksPointers;
    }

    /** FIXME: 24.01.2019 писать {@link Block#blockPosition} вместо самих блоков?*/
    public void writeBlocksPointer(String databaseName, String tableName, BlocksPointer blocksPointer) throws Exception {
        try (
                var out = new ObjectOutputStream(
                        new FileOutputStream(
                                BlockExtendedFileStruct.getBlocksPointerFullPath(databaseName, tableName)
                        )
                )
        ) {
            out.writeObject(blocksPointer);
        }
    }

    public void deleteBlocksPointer(String databaseName, String tableName) throws RuntimeException {
        if (!new File(BlockExtendedFileStruct.getBlocksPointerFullPath(databaseName, tableName)).delete())
            throw new RuntimeException(
                    String.format(
                            "Unsuccessful deletion of the table blocks pointer '%s' from the database '%s'",
                            tableName,
                            databaseName
                    )
            );
    }

    public Block createBlock(String databaseName, String tableName) throws Exception {
        var f = new File(BlockExtendedFileStruct.getTableDataFullPath(databaseName, tableName));

        var b = new Block(f, f.length());

        try (var rac = new RandomAccessFile(f, "rw")) {
            rac.setLength(f.length() + b.getSize());
            b.writeBlock();
        }

        return b;
    }

    public Block getBlock(String databaseName, String tableName, BlocksPointer blocksPointer) throws Exception {
        Block l;

        if (blocksPointer.firstDeleted == null) {
            l = createBlock(databaseName, tableName);
        } else {
            l = blocksPointer.firstDeleted;

            blocksPointer.firstDeleted = blocksPointer.firstDeleted.readRight();
            if (blocksPointer.firstDeleted != null)
                blocksPointer.firstDeleted.writeLeft(-1);
            //writeBlocksPointer(databaseName, tableName, blocksPointer);

            l.reuse();
        }

        return l;
    }

    //class E must be previously registered
    public <E> void appendElement(String databaseName, String tableName, BlocksPointer blocksPointer, E e) throws Exception {
        if (blocksPointer.last == null) {
            blocksPointer.first = blocksPointer.last = getBlock(databaseName, tableName, blocksPointer);
            //writeBlocksPointer(databaseName, tableName, blocksPointer);
        }

        var bytes = Serialization.getInstance().getDataSerializableBytes(e);

        if (bytes.length > blocksPointer.last.getRemainingSize()) {
            Block l = getBlock(databaseName, tableName, blocksPointer);

            blocksPointer.last.writeRight(l.getBlockPosition());
            l.writeLeft(blocksPointer.last.getBlockPosition());
            blocksPointer.last = l;
            //writeBlocksPointer(databaseName, tableName, blocksPointer);
        }

        blocksPointer.last.appendBytes(bytes);
        blocksPointer.last.incElementCount();
        writeBlocksPointer(databaseName, tableName, blocksPointer);
    }

    public BlockIterator getBlockIterator(BlocksPointer blocksPointer) {
        return new BlockIterator(blocksPointer.first);
    }

    public <E> BlockElementIterator<E> getBlockElementIterator(BlocksPointer blocksPointer, Class<E> eClass) {
        return new BlockElementIterator<>(blocksPointer.first, eClass);
    }

    //если из таблицы удаляется блок значит в ней есть как минимум 1 неудаленный блок
    //block!=null
    public void deleteBlock(String databaseName, String tableName, BlocksPointer blocksPointer, Block block) throws Exception {
        block.writeDeleted(true);

        if (block.equals(blocksPointer.first) && block.equals(blocksPointer.last))//1 block; block.left==block.right==-1
            blocksPointer.first = blocksPointer.last = null;
        else if (block.equals(blocksPointer.first)) {//block.left==-1
            var tmp = blocksPointer.first.readRight();
            if (tmp.equals(blocksPointer.last))//block<->last
                blocksPointer.first = blocksPointer.last;
            else//block<->...<->...
                blocksPointer.first = tmp;

            blocksPointer.first.writeLeft(-1);
        }
        //оно вообще должно так работать???
        else {
            if (block.equals(blocksPointer.last)) {//block.right==-1
                var tmp = blocksPointer.last.readLeft();
                if (tmp.equals(blocksPointer.first))//first<->block
                    blocksPointer.last = blocksPointer.first;
                else//...<->...<->block
                    blocksPointer.last = tmp;

                blocksPointer.last.writeRight(-1);
            } else {
                var l = block.readLeft();
                var r = block.readRight();

                if (l.equals(blocksPointer.first) && r.equals(blocksPointer.last)) {//first<->block<->last
                    l = blocksPointer.first;
                    r = blocksPointer.last;
                }//else ...<->...<->block<->...<->...
                l.writeRight(r.getBlockPosition());
                r.writeLeft(l.getBlockPosition());
            }

            block.writeLeft(-1);
        }

        if (blocksPointer.firstDeleted != null) {
            block.writeRight(blocksPointer.firstDeleted.getBlockPosition());
            blocksPointer.firstDeleted.writeLeft(block.getBlockPosition());
        } else if (block.getRight() != -1)//block.write затратны в любом случае как минимум из-за try/catch
            block.writeRight(-1);
        blocksPointer.firstDeleted = block;

        writeBlocksPointer(databaseName, tableName, blocksPointer);
    }

    //этот метод лишь помечает, что количество элементов в блоке уменьшилось, но не удаляет сам элемент
    //элементы могут быть либо удалены путем сдвига байтов, либо помечены как удаленные
    //поэтому само удаление элементов нужно производить как минимум не в этом методе
    // FIXME: 22.01.2019 rename?
    public void deleteElement(String databaseName, String tableName, BlocksPointer blocksPointer, Block block) throws Exception {
        block.decElementCount();
        if (block.equals(blocksPointer.first)) {
            blocksPointer.first = block;
            writeBlocksPointer(databaseName, tableName, blocksPointer);
        } else if (block.equals(blocksPointer.last)) {
            blocksPointer.last = block;
            writeBlocksPointer(databaseName, tableName, blocksPointer);
        }
    }
}