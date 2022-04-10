package com.tosan.tools.logger;

import com.tosan.tools.tostring.AbstractToStringBuilder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Mostafa Abdollahi
 * @since 6/9/2021
 */
@SuppressWarnings("rawtypes")
public abstract class ServiceLogger {
    private String systemName;

    public Object log(ProceedingJoinPoint pjp) throws Throwable {
        return log(pjp, null);
    }

    public Object log(ProceedingJoinPoint pjp, Integer recordCount) throws Throwable {
        Class joinPointLocation = pjp.getSourceLocation().getWithinType();
        String serviceName = systemName != null ? systemName + " " + pjp.getSignature().getName() : pjp.getSignature().getName();
        Logger logger = LoggerFactory.getLogger(joinPointLocation);
        prepare(logger, recordCount);
        long startTime = System.currentTimeMillis();
        try {
            if (logger.isInfoEnabled()) {
                logger.info(getRequestLog(serviceName, pjp.getArgs()));
            }
            Object result = pjp.proceed();
            if (logger.isInfoEnabled()) {
                logger.info(getResponseLog(serviceName, result, (System.currentTimeMillis() - startTime) / 1000.0));
            }
            return result;
        } catch (Throwable ex) {
            logger.info(getExceptionLog(serviceName, ex, (System.currentTimeMillis() - startTime) / 1000.0), ex);
            throw ex;
        } finally {
            AbstractToStringBuilder.cleanLogMode();
        }
    }

    public void prepare(Logger logger, Integer recordCount) {
        AbstractToStringBuilder.setLogMode(LogMode.INFO);
        if (logger.isDebugEnabled()) {
            AbstractToStringBuilder.setLogMode(LogMode.DEBUG);
        }
        if (recordCount != null) {
            AbstractToStringBuilder.setRecordCount(recordCount);
        }
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public abstract String getRequestLog(String serviceName, Object[] methodArgs);

    public abstract String getResponseLog(String serviceName, Object result, Double duration);

    public abstract String getExceptionLog(String serviceName, Throwable ex, Double duration);
}
