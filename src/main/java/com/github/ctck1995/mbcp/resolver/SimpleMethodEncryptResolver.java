package com.github.ctck1995.mbcp.resolver;

import com.github.ctck1995.mbcp.handler.CryptHandlerFactory;

public class SimpleMethodEncryptResolver implements MethodEncryptResolver {

    @Override
    @SuppressWarnings("unchecked")
    public Object processEncrypt(Object param) {
        return CryptHandlerFactory.getCryptHandler(param, null).encrypt(param, null);
    }
}