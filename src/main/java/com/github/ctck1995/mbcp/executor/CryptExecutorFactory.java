package com.github.ctck1995.mbcp.executor;

import com.github.ctck1995.mbcp.annotation.CryptField;
import com.github.ctck1995.mbcp.config.MbcpGlobalConfig;
import com.github.ctck1995.mbcp.exception.MbCryptException;

public class CryptExecutorFactory {

    public static CryptExecutor getCryptExecutor(CryptField cryptField) {
        CryptExecutor cryptExecutor = null;
        if (!NoneCryptExecutor.class.equals(cryptField.cryptExecutorCls())) {
            try {
                cryptExecutor = cryptField.cryptExecutorCls().newInstance();
            } catch (Exception e) {
                throw new MbCryptException(e);
            }
        } else if (MbcpGlobalConfig.getCryptExecutorCls() != null
                && !NoneCryptExecutor.class.equals(MbcpGlobalConfig.getCryptExecutorCls())) {
            try {
                cryptExecutor = cryptField.cryptExecutorCls().newInstance();
            } catch (Exception e) {
                throw new MbCryptException(e);
            }
        }
        if (cryptExecutor == null) {
            throw new MbCryptException("Unable to acquire a CryptExecutor.");
        }
        return cryptExecutor;
    }
}
