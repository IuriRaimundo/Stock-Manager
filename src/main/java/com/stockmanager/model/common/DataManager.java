package com.stockmanager.model.common;

import java.io.*;

public class DataManager<ManagerClass extends Manager> {

    private ManagerClass instance;

    private final String USER_HOME = System.getProperty("user.home");
    private String APP_HOME = USER_HOME + "\\StockManager";
    private final String DATAFILE_PATH;


    public DataManager(ManagerClass instance) {
        DATAFILE_PATH = APP_HOME + "\\datafiles\\" + instance.DATAFILE_NAME;
        this.instance = instance;
    }

    public void initialize() throws IOException, ClassNotFoundException {
        FileInputStream fis = null;
        ObjectInputStream in = null;
        try {
            File df = new File(DATAFILE_PATH);

            if (!df.exists()) {
                System.out.println(instance.getClass().getSimpleName() + " datafile doens't exist. No data loaded.");
                return;
            } else {
                System.out.println("Loading " + instance.getClass().getSimpleName() + " datafile...");
            }

            fis = new FileInputStream(df);
            in = new ObjectInputStream(fis);
            instance = (ManagerClass) in.readObject();
            System.out.println(instance.getClass().getSimpleName() + " data loaded.");

        } finally {
            if (in != null) {
                in.close();
            }
        }
    }

    public void persist() throws IOException {
        FileOutputStream fos = null;
        ObjectOutputStream out = null;

        try {

            File appDir = new File(APP_HOME);

            if (!appDir.exists()) {
                appDir.mkdir();
                System.out.println("App directory created.");
            }

            File datafileDir = new File(APP_HOME + "\\datafiles");
            if (!datafileDir.exists()) {
                datafileDir.mkdir();
                System.out.println("Datafile directory created.");
            }

            File df = new File(DATAFILE_PATH);

            if (df.createNewFile()) {
                System.out.println("Datafile created. ");
            }

            System.out.println("Saving " + instance.getClass().getSimpleName() + "...");
            fos = new FileOutputStream(df);
            out = new ObjectOutputStream(fos);
            out.writeObject(instance);
            System.out.println(instance.getClass().getSimpleName() + " data saved.");

        } finally {
            if (out != null) {
                out.close();
            }
        }
    }


}
