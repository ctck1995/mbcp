package com.github.ctck1995.mbcp.handler;

import com.github.ctck1995.mbcp.annotation.CryptField;
import com.github.ctck1995.mbcp.exception.MbCryptException;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class BeanCryptHandler implements CryptHandler<Object> {

    private static final ConcurrentHashMap<Class, List<CryptFiled>> CLASS_ENCRYPT_MAP = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<Class, List<CryptFiled>> CLASS_DECRYPT_MAP = new ConcurrentHashMap<>();

    @Override
    @SuppressWarnings("unchecked")
    public Object encrypt(Object bean, CryptField cryptField) {
        if (bean == null) {
            return null;
        }
        List<CryptFiled> filedList = CLASS_ENCRYPT_MAP.computeIfAbsent(bean.getClass(), this::getEncryptFields);
        filedList.forEach(cryptFiled -> {
            try {
                cryptFiled.field.setAccessible(true);
                Object obj = cryptFiled.field.get(bean);
                if (obj != null) {
                    Object encrypted = CryptHandlerFactory.getCryptHandler(obj, cryptFiled.cryptField)
                            .encrypt(obj, cryptFiled.cryptField);
                    cryptFiled.field.set(bean, encrypted);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                throw new MbCryptException(e.getMessage());
            }
        });
        return bean;
    }

    private List<CryptFiled> getEncryptFields(Class cls) {
        List<CryptFiled> filedList = new ArrayList<>();
        if (cls == null) {
            return filedList;
        }

        Field[] objFields = cls.getDeclaredFields();
        for (Field field : objFields) {
            CryptField cryptField = field.getAnnotation(CryptField.class);
            if (cryptField != null && cryptField.encrypt()) {
                filedList.add(new CryptFiled(cryptField, field));
            }
        }
        return filedList;
    }

    private List<CryptFiled> getDecryptFields(Class cls) {
        List<CryptFiled> filedList = new ArrayList<>();
        if (cls == null) {
            return filedList;
        }

        Field[] objFields = cls.getDeclaredFields();
        for (Field field : objFields) {
            CryptField cryptField = field.getAnnotation(CryptField.class);
            if (cryptField != null && cryptField.decrypt()) {
                filedList.add(new CryptFiled(cryptField, field));
            }
        }
        return filedList;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object decrypt(Object param, CryptField cryptField) {
        if (param == null) {
            return null;
        }
        List<CryptFiled> filedList = CLASS_DECRYPT_MAP.computeIfAbsent(param.getClass(), this::getDecryptFields);
        filedList.forEach(cryptFiled -> {
            try {
                cryptFiled.field.setAccessible(true);
                Object obj = cryptFiled.field.get(param);
                if (obj != null) {
                    Object decrypted = CryptHandlerFactory.getCryptHandler(obj, cryptFiled.cryptField)
                            .decrypt(obj, cryptFiled.cryptField);
                    cryptFiled.field.set(param, decrypted);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                throw new MbCryptException(e.getMessage());
            }
        });
        return param;
    }

    private class CryptFiled {
        private CryptFiled(CryptField cryptField, Field field) {
            this.cryptField = cryptField;
            this.field = field;
        }

        private Field field;
        private CryptField cryptField;
    }
}
