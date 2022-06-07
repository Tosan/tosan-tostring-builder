package com.tosan.tools.tostring;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tosan.tools.logger.LogMode;
import com.tosan.tools.tostring.dto.*;
import com.tosan.tools.logger.ServiceLogger;
import com.tosan.tools.logger.ServiceLoggerImpl;
import com.tosan.tools.util.ToStringJsonUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author Mostafa Abdollahi
 * @since 6/10/2021
 */
public class JsonServiceLoggerTest {
    private static final Logger logger = LoggerFactory.getLogger(JsonServiceLoggerTest.class);
    private static ServiceLogger serviceLogger;
    private final ObjectMapper mapper = ToStringJsonUtil.getMapper();

    @BeforeAll
    public static void init() {
        ToStringBuilderImpl.setLogMode(LogMode.DEBUG);
        ToStringBuilderImpl.setFormat(Format.JSON);
        serviceLogger = new ServiceLoggerImpl();
    }

    @Test
    public void requestLog() throws JsonProcessingException {
        BranchFilterDto filter = new BranchFilterDto();
        filter.setName("center");
        filter.setCurrency(Currency.getInstance("IRR"));
        filter.setLength(10);

        String log = serviceLogger.getRequestLog("getBranchList", new Object[]{filter, new BigDecimal("100"), "test"});
        logger.info(log);
        mapper.readTree(log);
    }

    @Test
    public void requestLogForMap() throws JsonProcessingException {
        BranchFilterDto filter = new BranchFilterDto();
        filter.setName("center");
        filter.setCurrency(Currency.getInstance("IRR"));
        filter.setLength(10);

        Map<String, Object> object = new LinkedHashMap<>(3);
        object.put("first", "1");
        object.put("second", "test");
        object.put("third", filter);

        String log = serviceLogger.getRequestLog("getBranchList", new Object[]{filter, new BigDecimal("100"), "test", object});
        logger.info(log);
        mapper.readTree(log);
    }

    @Test
    public void requestLogForList() throws JsonProcessingException {
        BranchFilterDto filter = new BranchFilterDto();
        filter.setName("center");
        filter.setCurrency(Currency.getInstance("IRR"));
        filter.setLength(10);

        List<Object> objList = Arrays.asList(new TestObj(1), "one", "two");

        String log = serviceLogger.getRequestLog("getBranchList", new Object[]{filter, new BigDecimal("100"), "test", objList});
        logger.info(log);
        mapper.readTree(log);
    }

    @Test
    public void responseLog() throws JsonProcessingException {
        BranchDto branch1 = new BranchDto();
        branch1.setCityName("tehran");
        branch1.setLMaskedCityCode("2100005");
        branch1.setName("center");
        Set<Type> enumSet = new HashSet<>();
        enumSet.add(Type.TYPE1);
        enumSet.add(Type.TYPE2);
        branch1.setEnumSet(enumSet);
        Branches result = new Branches();
        result.setTotalRecord(2);
        result.setBranchList(Collections.singletonList(branch1));
        double duration = 100 / 1000.0;

        String log = serviceLogger.getResponseLog("getBranchList", result, duration);
        logger.info(log);
        mapper.readTree(log);
    }

    @Test
    public void exceptionLog() throws JsonProcessingException {
        InvalidDepositException ex = new InvalidDepositException("deposit not found", new RuntimeException(), "1234");
        double duration = 222 / 1000.0;

        String log = serviceLogger.getExceptionLog("getBranchList", ex, duration) + "\n";
        logger.info(log, ex);
        mapper.readTree(log);
    }

    @Test
    public void testObjLog() throws JsonProcessingException {
        BranchDto branch1 = new BranchDto();
        branch1.setCityName("tehran");
        branch1.setLMaskedCityCode("2100005");
        branch1.setName("center");
        TestObj obj = new TestObj(1, "2", new ChildObj(3, "4"), branch1, Currency.getInstance("IRR"));

        String log = obj.toString();
        logger.info(log);
        mapper.readTree(log);
    }
}
