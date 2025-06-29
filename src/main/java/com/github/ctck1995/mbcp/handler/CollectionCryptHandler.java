package com.github.ctck1995.mbcp.handler;

import com.github.ctck1995.mbcp.annotation.CryptField;

import java.util.Collection;
import java.util.stream.Collectors;

public class CollectionCryptHandler implements CryptHandler<Collection> {

    @Override
    @SuppressWarnings("unchecked")
    public Object encrypt(Collection collection, CryptField cryptField) {
        if (!needCrypt(collection)) {
            return collection;
        }
        return collection.stream()
                .map(item -> CryptHandlerFactory.getCryptHandler(item, cryptField).encrypt(item, cryptField))
                .collect(Collectors.toList());
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object decrypt(Collection param, CryptField cryptField) {
        if (!needCrypt(param)) {
            return param;
        }
        return param.stream()
                .map(item -> CryptHandlerFactory.getCryptHandler(item, cryptField).decrypt(item, cryptField))
                .collect(Collectors.toList());
    }

    private boolean needCrypt(Collection collection) {
        return collection != null && collection.size() != 0;
    }
}
