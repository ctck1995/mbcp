package com.github.ctck1995.mbcp.resolver;

public class EmptyMethodDecryptResolver implements MethodDecryptResolver {

    @Override
    public Object processDecrypt(Object param) {
        return param;
    }
}