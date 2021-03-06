package com.dbms.structures;

import com.dbms.storage.blocks.BlockManager;
import com.dbms.storage.blocks.BlocksPointer;
import com.dbms.storage.data.DataManager;
import com.dbms.storage.data.MetaDataManager;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Database implements Serializable {

    private static final long serialVersionUID = 3224626768319866348L;

    private String name;
    private transient Map<String, Table> tables = new HashMap<>();
    private transient Map<String, BlocksPointer> blocksPointers = new HashMap<>();
    //todo как то проверять загрузились ли все таблицы в ленивом случае при вызовах getTable для каждой таблицы
    private transient boolean allTablesLoaded;//transient потому что актуально только на момент работы

    public Database(String name) {
        this.name = name;
    }

    public boolean isAllTablesLoaded() {
        return allTablesLoaded;
    }

    public void fullTablesLoad() throws Exception {
        tables = MetaDataManager.getInstance().readAllTables(name);
        blocksPointers = BlockManager.getInstance().readAllBlocksPointers(name);
        allTablesLoaded = true;
    }

    public String getName() {
        return name;
    }

    /**
     * Creates a table with the specified name and fields.
     */
    public void createTable(String tableName, Map<String, Type> fields) throws Exception {
        if (MetaDataManager.getInstance().tableExists(name, tableName))
            throw new Exception(String.format("The table '%s' already exists in the database '%s'", tableName, name));

        var table = new Table(name, tableName, fields);
        var blocksPointer = new BlocksPointer();

        MetaDataManager.getInstance().writeTable(name, tableName, table);
        DataManager.getInstance().initTableData(name, tableName);
        BlockManager.getInstance().writeBlocksPointer(name, tableName, blocksPointer);

        tables.put(tableName, table);
        blocksPointers.put(tableName, blocksPointer);
    }

    public void dropTable(String tableName) throws Exception {// TODO: 16.12.2018 check
        if (!MetaDataManager.getInstance().tableExists(name, tableName))
            throw new Exception(String.format("The table '%s' does not exist in the database '%s'", tableName, name));
        tables.remove(tableName);
        blocksPointers.remove(tableName);

        MetaDataManager.getInstance().deleteTable(name, tableName);
        DataManager.getInstance().deleteTableData(name, tableName);
        BlockManager.getInstance().deleteBlocksPointer(name, tableName);
    }

    /**
     * @return the table with the specified name
     * @throws Exception if the table with the specified name does not exist
     */
    public Table getTable(String tableName) throws Exception {
        if (!MetaDataManager.getInstance().tableExists(name, tableName))
            throw new Exception(String.format("The table '%s' does not exist in the database '%s'", tableName, name));

        if (!tables.containsKey(tableName)) {
            tables.put(tableName, MetaDataManager.getInstance().readTable(name, tableName));
            blocksPointers.put(tableName, BlockManager.getInstance().readBlocksPointer(name, tableName));
        }

        return tables.get(tableName);
    }

    public BlocksPointer getBlocksPointer(String tableName) throws Exception {
        if (!blocksPointers.containsKey(tableName))
            getTable(tableName);

        return blocksPointers.get(tableName);
    }
}