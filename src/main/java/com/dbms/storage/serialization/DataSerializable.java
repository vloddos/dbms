package com.dbms.storage.serialization;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public interface DataSerializable {

    void write(DataOutputStream out) throws Exception;

    void read(DataInputStream in) throws Exception;
}