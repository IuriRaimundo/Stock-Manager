package com.stockmanager.model.common;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Classe de geração de IDs úncios com um prefixo
 */
public class IdGenerator  {

    private final String ID_PREFIX;
    private final AtomicInteger ID_COUNTER;

    /**
     * @param ID_PREFIX Prefixo a ser utilizado na geração de IDs
     */
    public IdGenerator(String ID_PREFIX) {
        this.ID_PREFIX = ID_PREFIX;
        this.ID_COUNTER = new AtomicInteger(0);
    }

    /**
     * Função para gerar um id com o prefixo definido.
     * @return Id único
     */
    public String generateId() {
        return ID_PREFIX + ID_COUNTER.getAndIncrement();
    }
}
