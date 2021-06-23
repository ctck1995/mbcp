package com.github.ctck1995.mbcp;

import com.github.ctck1995.mbcp.resolver.MethodCryptMetadata;
import com.github.ctck1995.mbcp.resolver.MethodCryptMetadataBuilder;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

@Intercepts({
        @Signature(type = Executor.class, method = "update",
                args = {MappedStatement.class, Object.class}),
        @Signature(type = Executor.class, method = "query",
                args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})
})
public class CryptInterceptor implements Interceptor {

    protected static final ConcurrentHashMap<String, MethodCryptMetadata> METHOD_ENCRYPT_MAP = new ConcurrentHashMap<>();

    private boolean enable = true;

    public CryptInterceptor() {
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        if (!isEnable()) {
            return invocation.proceed();
        }
        Object[] args = invocation.getArgs();
        MethodCryptMetadata methodCryptMetadata = getCachedMethodCryptMetaData((MappedStatement) args[0]);
        Object parameter = args[1];
        // 加密
        methodCryptMetadata.encrypt(parameter);
        // 获得出参
        Object returnValue = invocation.proceed();
        // 解密
        methodCryptMetadata.decrypt(parameter);
        return methodCryptMetadata.decrypt(returnValue);
    }

    private MethodCryptMetadata getCachedMethodCryptMetaData(MappedStatement mappedStatement) {
        return METHOD_ENCRYPT_MAP.computeIfAbsent(
                mappedStatement.getId(),
                id -> new MethodCryptMetadataBuilder(id).build()
        );
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof Executor) {
            return Plugin.wrap(target, this);
        }
        return target;
    }

    @Override
    public void setProperties(Properties properties) {
    }

}
