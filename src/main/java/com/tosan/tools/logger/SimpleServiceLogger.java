package com.tosan.tools.logger;

import java.util.Collection;

import static com.tosan.tools.util.ToStringConstant.*;

/**
 * @author Mostafa Abdollahi
 * @since 6/9/2021
 */
@SuppressWarnings("rawtypes")
public class SimpleServiceLogger extends ServiceLogger {

    public String getRequestLog(String serviceName, Object[] methodArgs) {
        StringBuilder sb = new StringBuilder();
        sb.append(serviceName).append(SPACE);
        sb.append("Request:\n");
        if (methodArgs != null) {
            for (Object obj : methodArgs) {
                if (obj != null) {
                    sb.append(obj.getClass().getSimpleName());
                    sb.append(" {");
                    sb.append(obj.toString());
                    sb.append("}\n");
                } else {
                    sb.append(NULL_TEXT);
                }
                sb.append(COMMA);
            }
            sb.deleteCharAt(sb.length() - 1);
        }

        return sb.toString();
    }

    public String getResponseLog(String serviceName, Object result, Double duration) {
        StringBuilder sb = new StringBuilder();
        sb.append(serviceName).append(SPACE);
        sb.append("Response:\n");
        if (result != null) {
            if (result instanceof Collection) {
                Collection lists = (Collection) result;
                for (Object record : lists) {
                    sb.append(record.getClass().getSimpleName());
                    sb.append(" {");
                    sb.append(record);
                    sb.append("}\n");
                }
            } else {
                sb.append(result.getClass().getSimpleName());
                sb.append(" {");
                sb.append(result);
                sb.append("}\n");
            }
        }
        return sb.toString();
    }

    @Override
    public String getExceptionLog(String serviceName, Throwable ex, Double duration) {
        StringBuilder sb = new StringBuilder();
        sb.append(serviceName).append(SPACE);
        sb.append("Exception:\n");
        sb.append(ex.getClass().getSimpleName());
        sb.append(ex);
        sb.append("\n");
        return sb.toString();
    }
}
