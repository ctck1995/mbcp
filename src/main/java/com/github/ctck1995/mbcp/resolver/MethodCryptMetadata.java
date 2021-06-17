package com.github.ctck1995.mbcp.resolver;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MethodCryptMetadata {

    public MethodEncryptResolver methodEncryptResolver;
    public MethodDecryptResolver methodDecryptResolver;

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
