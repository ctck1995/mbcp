package com.github.ctck1995.mbcp.resolver;

public class EmptyMethodEncryptResolver implements MethodEncryptResolver {

    @Override
    public Object processEncrypt(Object param) {
        return param;
    }
}