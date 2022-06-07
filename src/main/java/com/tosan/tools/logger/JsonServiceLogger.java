package com.tosan.tools.logger;

import com.fasterxml.jackson.databind.util.RawValue;
import com.tosan.tools.util.ToStringJsonUtil;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Mostafa Abdollahi
 * @since 6/9/2021
 */
public class JsonServiceLogger extends ServiceLogger {

    @Override
    public String getRequestLog(String serviceName, Object[] methodArgs) {
        return createJson(serviceName, "request", methodArgs, null);
    }

    @Override
    public String getResponseLog(String serviceName, Object result, Double duration) {
        return createJson(serviceName, "response", new Object[]{result}, duration);
    }

    @Override
    public String getExceptionLog(String serviceName, Throwable ex, Double duration) {
        return createJson(serviceName, "exception", new Object[]{ex}, duration);
    }

    private String createJson(String serviceName, String key, Object[] objects, Double duration) {
        Map<String, Object> object = new LinkedHashMap<>(3);
        object.put("service", serviceName);
        if (duration != null) {
            object.put("duration", duration + "s");
        }
        if (objects != null) {
            Map<String, Object> objectsMap = new LinkedHashMap<>(objects.length);
            for (Object obj : objects) {
                if (obj != null) {
                    objectsMap.put(obj.getClass().getSimpleName(), obj);
                }
            }
            object.put(key, objectsMap);
        }
        return ToStringJsonUtil.toJson(object);
    }
}
