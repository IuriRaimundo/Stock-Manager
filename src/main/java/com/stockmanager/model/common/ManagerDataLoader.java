package com.stockmanager.model.common;

import java.io.*;

public class ManagerDataLoader<ManagerClass> {

    private final String USER_HOME = System.getProperty("user.home");
    private String APP_HOME = USER_HOME + "\\StockManager";
    private final String DATAFILE_PATH;
    private final String DATAFILE_NAME;


    public ManagerDataLoader(String datafileName) {
        DATAFILE_NAME = datafileName;
        DATAFILE_PATH = APP_HOME + "\\datafiles\\" + DATAFILE_NAME;
    }

    /**
     * Método para repor a instância atual de um manager pela instância armazenada
     * no datafile associado.
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public ManagerClass initialize() throws IOException, ClassNotFoundException {
        ManagerClass instance;

        FileInputStream fis;
        ObjectInputStream in = null;
        try {
            // Abrir datafile
            File df = new File(DATAFILE_PATH);

            // Se o datafile não existir não vale a pena ler os dados, então devolver.
            if (!df.exists()) {
                System.out.println(DATAFILE_NAME + " datafile doens't exist. No data loaded.");
                return null;
            } else {
                System.out.println("Loading " + DATAFILE_NAME + " datafile...");
            }

            // Ler instância do datafile
            fis = new FileInputStream(df);
            in = new ObjectInputStream(fis);
            instance = (ManagerClass) in.readObject();
            System.out.println(instance.getClass().getSimpleName() + " data loaded.");

        } finally {
            // Fechar stream
            if (in != null) {
                in.close();
            }
        }

        return instance;
    }

    /**
     * Método para armazenar a instância do manager no datafile.
     * @throws IOException
     */
    public void persist(ManagerClass instance) throws IOException {
        FileOutputStream fos;
        ObjectOutputStream out = null;

        try {
            // Abrir diretório da aplicação e criar caso não exista
            File appDir = new File(APP_HOME);

            if (!appDir.exists()) {
                appDir.mkdir();
                System.out.println("App directory created.");
            }

            // Abrir diretório dos datafiles e criar caso não exista
            File datafileDir = new File(APP_HOME + "\\datafiles");
            if (!datafileDir.exists()) {
                datafileDir.mkdir();
                System.out.println("Datafile directory created.");
            }

            // Abrir datafile e criar caso não exista
            File df = new File(DATAFILE_PATH);

            if (df.createNewFile()) {
                System.out.println("Datafile created. ");
            }

            // Gravar instância do manager no datafile.
            System.out.println("Saving " + instance.getClass().getSimpleName() + "...");
            fos = new FileOutputStream(df);
            out = new ObjectOutputStream(fos);
            out.writeObject(instance);
            System.out.println(instance.getClass().getSimpleName() + " data saved.");

        } finally {
            // Fechar stream
            if (out != null) {
                out.close();
            }
        }
    }


}
