package com.tosan.tools.logger;

import com.fasterxml.jackson.databind.util.RawValue;
import com.tosan.tools.util.ToStringJsonUtil;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Collection;

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
        Map<String, Object> exception = new LinkedHashMap<>();
        exception.put("name", ex.getClass().getSimpleName());
        exception.put("message", ex.getMessage());
        exception.put("localizedMessage", ex.getLocalizedMessage());
        exception.put("stackTrace", getStackTrace(ex));
        return createJson(serviceName, "exception", new Object[]{exception}, duration);
    }

    private List<String> getStackTrace(Throwable ex) {
        List<String> stackTrace = new ArrayList<>();
        for (StackTraceElement element : ex.getStackTrace()) {
            stackTrace.add(element.toString());
            if (stackTrace.size() > 15) {
                break;
            }
        }
        return stackTrace;
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
                    if (obj instanceof String || obj instanceof Map || obj instanceof Collection)
                        objectsMap.put(obj.getClass().getSimpleName(), obj);
                    else
                        objectsMap.put(obj.getClass().getSimpleName(), new RawValue(obj.toString()));
                }
            }
            object.put(key, objectsMap);
        }
        return ToStringJsonUtil.toJson(object);
    }
}
