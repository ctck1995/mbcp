package com.github.ctck1995.mbcp.handler;

import com.github.ctck1995.mbcp.annotation.CryptField;

public class EmptyCryptHandler implements CryptHandler<Object> {

    @Override
    public Object encrypt(Object param, CryptField cryptField) {
        return param;
    }

    @Override
    public Object decrypt(Object param, CryptField cryptField) {
        return param;
    }
}
