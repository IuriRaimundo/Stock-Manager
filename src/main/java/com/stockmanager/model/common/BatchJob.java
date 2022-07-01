package com.stockmanager.model.common;

import org.jetbrains.annotations.NotNull;

abstract public class BatchJob {

    public final String name;

    /**
     * @param name Nome do batch job
     */
    public BatchJob(@NotNull String name) {
        this.name = name;
    }

    /**
     * Método com as operações do batch job, a ser implementadas para cada batch job.
     */
    protected abstract void run();

    /**
     * Método para executar um batch job
     */
    public final void execute() {
        System.out.println("\nExecuting batch job - " + name);

        try {
            run();
            System.out.println("Batch job executed.\n");
        } catch (Exception e) {
            System.out.println("Exception occurred in batch job.\n");
        }
    }
}
