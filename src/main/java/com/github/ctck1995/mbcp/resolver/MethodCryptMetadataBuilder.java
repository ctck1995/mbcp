package com.github.ctck1995.mbcp.resolver;

import com.github.ctck1995.mbcp.exception.MbCryptException;
import org.apache.ibatis.annotations.Param;
import com.github.ctck1995.mbcp.misc.CryptIgnore;
import com.github.ctck1995.mbcp.annotation.CryptField;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

public class MethodCryptMetadataBuilder {

    private static final MethodEncryptResolver EMPTY_ENCRYPT_RESOLVER = new EmptyMethodEncryptResolver();
    private static final MethodDecryptResolver EMPTY_DECRYPT_RESOLVER = new EmptyMethodDecryptResolver();

    private String statementId;

    public MethodCryptMetadataBuilder(String statementId) {
        this.statementId = statementId;
    }

    public MethodCryptMetadata build() {
        MethodCryptMetadata methodCryptMetadata = new MethodCryptMetadata();
        Method m = getMethod();
        methodCryptMetadata.setMethodEncryptResolver(buildEncryptResolver(m));
        methodCryptMetadata.setMethodDecryptResolver(buildDecryptResolver(m));
        return methodCryptMetadata;
    }

    private MethodEncryptResolver buildEncryptResolver(Method m) {
        if (m == null) {
            return EMPTY_ENCRYPT_RESOLVER;
        }
        Parameter[] parameters = m.getParameters();
        // 如果方法没有参数
        if (parameters == null || parameters.length == 0) {
            return EMPTY_ENCRYPT_RESOLVER;
        }
        // 方法有加密注解的参数
        List<MethodAnnotationEncryptParameter> methodEncryptParamList = getMethodCryptParams(m);

        if (methodEncryptParamList.size() == 0) {
            return new SimpleMethodEncryptResolver();
        }
        if (methodEncryptParamList.size() > 0) {
            return new AnnotationMethodEncryptResolver(methodEncryptParamList);
        }
        return EMPTY_ENCRYPT_RESOLVER;
    }

    private MethodDecryptResolver buildDecryptResolver(Method m) {
        if (m == null) {
            return EMPTY_DECRYPT_RESOLVER;
        }
        // 无返回值类型
        if (m.getReturnType() == Void.class) {
            return EMPTY_DECRYPT_RESOLVER;
        }
        return new SimpleMethodDecryptResolver();
    }

    private List<MethodAnnotationEncryptParameter> getMethodCryptParams(Method m) {
        Parameter[] parameters = m.getParameters();
        if (parameters == null || parameters.length == 0) {
            return new ArrayList<>();
        }
        List<MethodAnnotationEncryptParameter> paramList = new ArrayList<>();
        final Annotation[][] paramAnnotations = m.getParameterAnnotations();
        // 方法参数中CryptField注解必须要配合Param注解一起使用才生效，因为需要参数名称

        for (int i = 0; i < parameters.length; i++) {
            Param param = null;
            CryptField crypt = null;
            if (CryptIgnore.inIgnoreClass(parameters[i].getType())) {
                continue;
            }
            for (Annotation annotation : paramAnnotations[i]) {
                if (annotation instanceof CryptField) {
                    crypt = (CryptField) annotation;
                }
                if (annotation instanceof Param) {
                    param = (Param) annotation;
                }
                if (crypt != null && param != null) {
                    paramList.add(new MethodAnnotationEncryptParameter(param.value(), crypt, parameters[i].getType()));
                    break;
                }
            }
        }
        return paramList;
    }

    private Method getMethod() {
        try {
            final Class clazz = Class.forName(statementId.substring(0, statementId.lastIndexOf(".")));
            final String methodName = statementId.substring(statementId.lastIndexOf(".") + 1);
            for (Method method : clazz.getMethods()) {
                if (method.getName().equals(methodName)) {
                    return method;
                }
            }
            return null;
        } catch (ClassNotFoundException e) {
            throw new MbCryptException("class not found: statementId=" + statementId);
        }
    }
}
