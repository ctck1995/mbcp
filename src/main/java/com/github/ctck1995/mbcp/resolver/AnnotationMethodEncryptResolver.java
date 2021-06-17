package com.github.ctck1995.mbcp.resolver;

import com.github.ctck1995.mbcp.handler.CryptHandlerFactory;

import java.util.List;
import java.util.Map;

public class AnnotationMethodEncryptResolver implements MethodEncryptResolver {

    private List<MethodAnnotationEncryptParameter> methodAnnotationEncryptParameterList;

    public AnnotationMethodEncryptResolver(List<MethodAnnotationEncryptParameter> methodAnnotationEncryptParameterList) {
        this.methodAnnotationEncryptParameterList = methodAnnotationEncryptParameterList;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object processEncrypt(Object param) {
        Map map = (Map) param;
        methodAnnotationEncryptParameterList.forEach(item ->
            map.computeIfPresent(item.getParamName(), (key, oldValue) ->
                CryptHandlerFactory.getCryptHandler(oldValue, item.getCryptField()).encrypt(oldValue, item.getCryptField())
            )
        );
        return param;
    }

}
