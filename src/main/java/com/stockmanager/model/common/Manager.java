package com.stockmanager.model.common;

import org.jetbrains.annotations.NotNull;

import java.io.Serial;
import java.io.Serializable;

public abstract class Manager implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public final String DATAFILE_NAME;

    public Manager(@NotNull String DATAFILE_NAME) {
        this.DATAFILE_NAME = DATAFILE_NAME;

    }

    public static void setInstance(Manager instance) {}

}