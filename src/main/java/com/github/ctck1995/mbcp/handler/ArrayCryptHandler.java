package com.github.ctck1995.mbcp.handler;

import com.github.ctck1995.mbcp.annotation.CryptField;

import java.util.Arrays;

public class ArrayCryptHandler implements CryptHandler<Object> {

    @Override
    @SuppressWarnings("unchecked")
    public Object encrypt(Object object, CryptField cryptField) {
        if (object == null) {
            return null;
        }
        return Arrays.stream((Object[]) object)
                .map(item -> CryptHandlerFactory.getCryptHandler(item, cryptField).encrypt(item, cryptField))
                .toArray();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object decrypt(Object param, CryptField cryptField) {
        if (param == null) {
            return null;
        }
        return Arrays.stream((Object[]) param)
                .map(item -> CryptHandlerFactory.getCryptHandler(item, cryptField).decrypt(item, cryptField))
                .toArray();
    }


}
