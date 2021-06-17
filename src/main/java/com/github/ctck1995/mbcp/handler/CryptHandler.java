package com.github.ctck1995.mbcp.handler;

import com.github.ctck1995.mbcp.annotation.CryptField;

public interface CryptHandler<T> {

    Object encrypt(T param, CryptField cryptField);

    Object decrypt(T param, CryptField cryptField);
}
