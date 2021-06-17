package com.github.ctck1995.mbcp.executor;

/**
 * Created by ck on 2021/6/17.
 * <p/>
 */
public class NoneCryptExecutor implements CryptExecutor {

    @Override
    public String encrypt(Object in) {
        return (String) in;
    }

    @Override
    public String decrypt(Object in) {
        return (String) in;
    }
}
