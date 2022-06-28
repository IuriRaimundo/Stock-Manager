package com.stockmanager.model.common;

import org.jetbrains.annotations.NotNull;

public abstract class Manager {
    public final String DATAFILE_NAME;

    public Manager(@NotNull String DATAFILE_NAME) {
        this.DATAFILE_NAME = DATAFILE_NAME;

    }
}
