package com.github.ctck1995.mbcp.handler;

import com.github.ctck1995.mbcp.annotation.CryptField;
import com.github.ctck1995.mbcp.executor.CryptExecutorFactory;

public class StringCryptHandler implements CryptHandler<String> {

    @Override
    public Object encrypt(String param, CryptField cryptField) {
        if (needCrypt(param, cryptField) && cryptField.encrypt()) {
            return CryptExecutorFactory.getCryptExecutor(cryptField).encrypt(param);
        }
        return param;
    }

    @Override
    public Object decrypt(String param, CryptField cryptField) {
        if (needCrypt(param, cryptField) && cryptField.decrypt()) {
            return CryptExecutorFactory.getCryptExecutor(cryptField).decrypt(param);
        }
        return param;
    }

    private boolean needCrypt(String param, CryptField cryptField) {
        return cryptField != null && param != null && param.length() != 0;
    }
}

