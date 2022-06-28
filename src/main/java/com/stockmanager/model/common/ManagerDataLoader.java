package com.stockmanager.model.common;

import java.io.*;

public class ManagerDataLoader<ManagerClass extends Manager> {

    private ManagerClass instance;


    public ManagerDataLoader(ManagerClass instance) {
        this.instance = instance;
    }

    public void initialize() throws IOException {
        FileInputStream fis = null;
        ObjectInputStream in = null;
        try {
            fis = new FileInputStream(instance.DATAFILE_NAME);
            in = new ObjectInputStream(fis);
            instance = (ManagerClass) in.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            assert in != null;
            in.close();
        }
    }

    public void persist() throws IOException {
        FileOutputStream fos = null;
        ObjectOutputStream out = null;

        try {
            fos = new FileOutputStream(instance.DATAFILE_NAME);
            out = new ObjectOutputStream(fos);
            out.writeObject(instance);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            assert out != null;
            out.close();
        }
    }


}
