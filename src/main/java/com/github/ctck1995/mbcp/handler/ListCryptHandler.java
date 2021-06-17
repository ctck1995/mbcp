package com.github.ctck1995.mbcp.handler;

import com.github.ctck1995.mbcp.annotation.CryptField;

import java.util.List;
import java.util.stream.Collectors;

public class ListCryptHandler implements CryptHandler<List> {

    @Override
    @SuppressWarnings("unchecked")
    public Object encrypt(List list, CryptField cryptField) {
        if (!needCrypt(list)) {
            return list;
        }
        return list.stream()
                .map(item -> CryptHandlerFactory.getCryptHandler(item, cryptField).encrypt(item, cryptField))
                .collect(Collectors.toList());
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object decrypt(List param, CryptField cryptField) {
        if (!needCrypt(param)) {
            return param;
        }
        return param.stream()
                .map(item -> CryptHandlerFactory.getCryptHandler(item, cryptField).decrypt(item, cryptField))
                .collect(Collectors.toList());
    }

    private boolean needCrypt(List list) {
        return list != null && list.size() != 0;
    }
}
