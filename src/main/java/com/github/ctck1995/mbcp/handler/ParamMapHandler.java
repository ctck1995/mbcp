package com.github.ctck1995.mbcp.handler;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.apache.ibatis.binding.MapperMethod;
import com.github.ctck1995.mbcp.annotation.CryptField;

import java.util.Map;

/**
 * Created by ck on 2021/6/10.
 * <p/>
 */
public class ParamMapHandler implements CryptHandler<Map> {

    @Override
    @SuppressWarnings("unchecked")
    public Object encrypt(Map param, CryptField cryptField) {
        if (!(param instanceof MapperMethod.ParamMap) || param.size() != 2) {
            return param;
        }
        MapperMethod.ParamMap paramMap = (MapperMethod.ParamMap) param;
        if (paramMap.containsKey("et")) {
            Object body = paramMap.get("et");
            Object newbody = CryptHandlerFactory.getCryptHandler(body, cryptField).encrypt(body, cryptField);
            paramMap.put("et", newbody);
            paramMap.put("param1", newbody);
        } else if (paramMap.containsKey("ew")) {
            Object body = paramMap.get("ew");
            if (body instanceof EntityWrapper) {
                EntityWrapper wrapper = (EntityWrapper) body;
                wrapper.setEntity(CryptHandlerFactory.getCryptHandler(wrapper.getEntity(), cryptField).encrypt(wrapper.getEntity(), cryptField));
                paramMap.put("ew", wrapper);
                paramMap.put("param1", wrapper);
            } else {
                Object newbody = CryptHandlerFactory.getCryptHandler(body, cryptField).encrypt(body, cryptField);
                paramMap.put("ew", newbody);
                paramMap.put("param1", newbody);
            }
        }
        return paramMap;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object decrypt(Map param, CryptField cryptField) {
        if (!(param instanceof MapperMethod.ParamMap) || param.size() != 2) {
            return param;
        }
        MapperMethod.ParamMap paramMap = (MapperMethod.ParamMap) param;
        if (paramMap.containsKey("et")) {
            Object body = paramMap.get("et");
            Object newbody = CryptHandlerFactory.getCryptHandler(body, cryptField).decrypt(body, cryptField);
            paramMap.put("et", newbody);
            paramMap.put("param1", newbody);
        } else if (paramMap.containsKey("ew")) {
            Object body = paramMap.get("ew");
            if (body instanceof EntityWrapper) {
                EntityWrapper wrapper = (EntityWrapper) body;
                wrapper.setEntity(CryptHandlerFactory.getCryptHandler(wrapper.getEntity(), cryptField).decrypt(wrapper.getEntity(), cryptField));
                paramMap.put("ew", wrapper);
                paramMap.put("param1", wrapper);
            } else {
                Object newbody = CryptHandlerFactory.getCryptHandler(body, cryptField).decrypt(body, cryptField);
                paramMap.put("ew", newbody);
                paramMap.put("param1", newbody);
            }
        }
        return paramMap;
    }
}
