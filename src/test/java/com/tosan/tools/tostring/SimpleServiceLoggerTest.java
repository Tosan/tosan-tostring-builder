package com.tosan.tools.tostring;

import com.tosan.tools.logger.LogMode;
import com.tosan.tools.tostring.dto.*;
import com.tosan.tools.logger.ServiceLogger;
import com.tosan.tools.logger.ServiceLoggerImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Collections;

/**
 * @author Mostafa Abdollahi
 * @since 6/10/2021
 */
public class SimpleServiceLoggerTest {
    private static final Logger logger = LoggerFactory.getLogger(SimpleServiceLoggerTest.class);
    private static ServiceLogger serviceLogger;

    @BeforeAll
    public static void init() {
        ToStringBuilderImpl.setLogMode(LogMode.DEBUG);
        ToStringBuilderImpl.setFormat(Format.SIMPLE);
        serviceLogger = new ServiceLoggerImpl();
    }

    @Test
    void requestLog() {
        BranchFilterDto filter = new BranchFilterDto();
        filter.setName("center");
        filter.setLength(10);

        logger.info(serviceLogger.getRequestLog("getBranchList", new Object[]{filter, new BigDecimal("100")}));
    }

    @Test
    void responseLog() {
        BranchDto branch1 = new BranchDto();
        branch1.setCityName("tehran");
        branch1.setLMaskedCityCode("2100005");
        branch1.setName("center");
        Branches result = new Branches();
        result.setTotalRecord(2);
        result.setBranchList(Collections.singletonList(branch1));

        logger.info(serviceLogger.getResponseLog("getBranchList", result, null));
    }

    @Test
    void exceptionLog() {
        InvalidDepositException ex = new InvalidDepositException("deposit not found", new RuntimeException(), "1234");

        logger.info(serviceLogger.getExceptionLog("getBranchList", ex, null), ex);
    }

    @Test
    public void testObjLog() {
        TestObj obj = new TestObj(1, "2", new ChildObj(3, "4"));
        logger.info(obj.toString());
    }
}
