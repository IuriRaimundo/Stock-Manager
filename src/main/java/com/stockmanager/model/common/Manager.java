package com.stockmanager.model.common;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;

public interface Manager  {

    /**
     * Método para carregar um data manager dos datafiles
     */
    void load();

    /**
     * Método para guardar a instância do manager
     */
    void save();
}