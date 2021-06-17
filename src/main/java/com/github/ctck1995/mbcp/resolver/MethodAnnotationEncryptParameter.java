package com.github.ctck1995.mbcp.resolver;

import com.github.ctck1995.mbcp.annotation.CryptField;

class MethodAnnotationEncryptParameter {

    private String paramName;
    private CryptField cryptField;
    private Class cls;

    public MethodAnnotationEncryptParameter(String paramName, CryptField cryptField, Class cls) {
        this.paramName = paramName;
        this.cryptField = cryptField;
        this.cls = cls;
    }

    public String getParamName() {
        return paramName;
    }

    public CryptField getCryptField() {
        return cryptField;
    }

    public Class getCls() {
        return cls;
    }
}
