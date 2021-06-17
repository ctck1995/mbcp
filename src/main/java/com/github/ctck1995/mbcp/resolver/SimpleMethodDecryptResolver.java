package com.github.ctck1995.mbcp.resolver;

import com.github.ctck1995.mbcp.handler.CryptHandlerFactory;

public class SimpleMethodDecryptResolver implements MethodDecryptResolver {

    @Override
    @SuppressWarnings("unchecked")
    public Object processDecrypt(Object param) {
        return CryptHandlerFactory.getCryptHandler(param, null).decrypt(param, null);
    }

}