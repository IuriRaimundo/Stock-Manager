package com.stockmanager.model.utils;

import org.jetbrains.annotations.NotNull;

import com.stockmanager.model.utils.exceptions.DuplicateKeyException;
import com.stockmanager.model.utils.exceptions.DuplicateValueException;

import java.util.HashMap;

public class HashMapUtils {

    /**
     * Método para efetuar uma inserção única a um HashMap.
     * @param map HashMap
     * @param key Key
     * @param value Value
     * @param <K> Tipo da key
     * @param <V> Tipo do valor
     * @throws DuplicateKeyException Se a chave não for única
     * @throws DuplicateValueException Se o valor não for único
     */
    public static <K, V> void hashMapUniqueKeyInsertion(@NotNull HashMap<K, V> map,
                                                        @NotNull K key,
                                                        @NotNull V value) {
        if (map.containsKey(key)) {
            throw new DuplicateKeyException();
        }

        if (map.containsValue(value)) {
            throw new DuplicateValueException();
        }

        map.put(key, value);
    }
}
