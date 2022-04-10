package com.tosan.tools.logger;

import com.tosan.tools.tostring.Format;
import com.tosan.tools.tostring.ToStringBuilderImpl;

/**
 * @author Mostafa Abdollahi
 * @since 6/9/2021
 */
public class ServiceLoggerImpl extends ServiceLogger {
    private final ServiceLogger serviceLogger;

    public ServiceLoggerImpl() {
        this.serviceLogger = (ToStringBuilderImpl.getFormat() == Format.JSON) ?
                new JsonServiceLogger() : new SimpleServiceLogger();
    }

    @Override
    public String getRequestLog(String serviceName, Object[] methodArgs) {
        return serviceLogger.getRequestLog(serviceName, methodArgs);
    }

    @Override
    public String getResponseLog(String serviceName, Object result, Double duration) {
        return serviceLogger.getResponseLog(serviceName, result, duration);
    }

    @Override
    public String getExceptionLog(String serviceName, Throwable ex, Double duration) {
        return serviceLogger.getExceptionLog(serviceName, ex, duration);
    }
}
