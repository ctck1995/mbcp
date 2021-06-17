package com.github.ctck1995.mbcp.resolver;

public class MethodCryptMetadata {

    public MethodEncryptResolver methodEncryptResolver;
    public MethodDecryptResolver methodDecryptResolver;

    public void setMethodEncryptResolver(MethodEncryptResolver methodEncryptResolver) {
        this.methodEncryptResolver = methodEncryptResolver;
    }

    public void setMethodDecryptResolver(MethodDecryptResolver methodDecryptResolver) {
        this.methodDecryptResolver = methodDecryptResolver;
    }

    public Object encrypt(Object object) {
        if (object == null) {
            return null;
        }
        return methodEncryptResolver.processEncrypt(object);
    }

    public Object decrypt(Object object) {
        if (object == null) {
            return null;
        }
        return methodDecryptResolver.processDecrypt(object);
    }
}
