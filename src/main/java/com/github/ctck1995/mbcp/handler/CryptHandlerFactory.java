package com.github.ctck1995.mbcp.handler;

import com.github.ctck1995.mbcp.misc.CryptIgnore;
import com.github.ctck1995.mbcp.annotation.CryptField;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class CryptHandlerFactory {

    private static final CryptHandler EMPTY_HANDLER = new EmptyCryptHandler();
    private static final CryptHandler STRING_HANDLER = new StringCryptHandler();
    private static final CryptHandler COLLECTION_HANDLER = new CollectionCryptHandler();
    private static final CryptHandler LIST_HANDLER = new ListCryptHandler();
    private static final CryptHandler ARRAY_HANDLER = new ArrayCryptHandler();
    private static final CryptHandler BEAN_HANDLER = new BeanCryptHandler();
    private static final CryptHandler PARAMMAP_HANDLER = new ParamMapHandler();

    public static CryptHandler getCryptHandler(Object obj, CryptField cryptField) {
        // ======= 忽略掉几种情况 =======
        if (obj == null) {
            return EMPTY_HANDLER;
        }
        if (CryptIgnore.inIgnoreClass(obj.getClass())) {
            return EMPTY_HANDLER;
        }
        if (obj instanceof String && cryptField == null) {
            return EMPTY_HANDLER;
        }
        // ======= 忽略掉几种情况 =======
        if (obj instanceof Map) {
            return PARAMMAP_HANDLER;
        }
        if (obj instanceof String) {
            return STRING_HANDLER;
        }
        if (obj instanceof List) {
            return LIST_HANDLER;
        }
        if (obj instanceof Collection) {
            return COLLECTION_HANDLER;
        }
        if (obj.getClass().isArray()) {
            return ARRAY_HANDLER;
        }
        return BEAN_HANDLER;
    }

}
