package com.tosan.tools.tostring;

import com.tosan.tools.logger.LogMode;

/**
 * @author Mostafa Abdollahi
 * @since 6/9/2021
 */
public abstract class AbstractToStringBuilder implements ToStringBuilder {
    protected static final ThreadLocal<LogMode> logMode = new ThreadLocal<>();
    protected static int recordCount = 10;
    protected final Object toStringObject;

    protected AbstractToStringBuilder(Object toStringObject) {
        this.toStringObject = toStringObject;
    }

    public static int getRecordCount() {
        return recordCount;
    }

    public static void setRecordCount(int recordCount) {
        AbstractToStringBuilder.recordCount = recordCount;
    }

    public static void setLogMode(LogMode logMode) {
        AbstractToStringBuilder.logMode.set(logMode);
    }

    public static void cleanLogMode() {
        logMode.remove();
    }
}
