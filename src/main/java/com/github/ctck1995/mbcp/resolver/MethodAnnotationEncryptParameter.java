package com.github.ctck1995.mbcp.resolver;

import com.github.ctck1995.mbcp.annotation.CryptField;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
class MethodAnnotationEncryptParameter {

    private String paramName;
    private CryptField cryptField;
    private Class cls;
}
