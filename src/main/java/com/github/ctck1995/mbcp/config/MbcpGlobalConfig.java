package com.github.ctck1995.mbcp.config;

import com.github.ctck1995.mbcp.executor.CryptExecutor;
import com.github.ctck1995.mbcp.executor.NoneCryptExecutor;

/**
 * Created by ck on 2021/6/17.
 * <p/>
 */
public class MbcpGlobalConfig {

    private static volatile Class<? extends CryptExecutor> cryptExecutorCls = NoneCryptExecutor.class;

    public static Class<? extends CryptExecutor> getCryptExecutorCls() {
        return cryptExecutorCls;
    }

    public static void setCryptExecutorCls(Class<? extends CryptExecutor> cryptExecutorCls) {
        MbcpGlobalConfig.cryptExecutorCls = cryptExecutorCls;
    }
}
