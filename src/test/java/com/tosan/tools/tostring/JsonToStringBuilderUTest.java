package com.tosan.tools.tostring;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tosan.tools.logger.LogMode;
import com.tosan.tools.tostring.dto.*;
import com.tosan.tools.util.ToStringJsonUtil;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonToStringBuilderUTest {
    private final ObjectMapper mapper = ToStringJsonUtil.getMapper();

    @Test
    public void testFormatForStringList() throws JsonProcessingException {
        //given
        List<String> stringList = Arrays.asList("a", "b", "c", "d");
        //when
        ToStringBuilderImpl.setLogMode(LogMode.DEBUG);
        ToStringBuilder toStringBuilder = new ToStringBuilderImpl(this);
        toStringBuilder.append("stringList", stringList);
        String toString = toStringBuilder.toString();
        //then
        String expected = "{\r\n" +
                "  \"stringList\" : {\r\n" +
                "    \"returnedItems\" : \"4/4\",\r\n" +
                "    \"stringList\" : [ \"a\", \"b\", \"c\", \"d\" ]\r\n" +
                "  }\r\n" +
                "}";
        mapper.readTree(toString);
        assertEquals(expected, toString);
    }

    @Test
    public void testFormatForIntList() throws JsonProcessingException {
        //given
        List<Integer> stringList = Arrays.asList(1, 2, 3, 4);
        //when
        ToStringBuilderImpl.setLogMode(LogMode.DEBUG);
        ToStringBuilder toStringBuilder = new ToStringBuilderImpl(this);
        toStringBuilder.append("stringList", stringList);
        String toString = toStringBuilder.toString();
        //then
        String expected = "{\r\n" +
                "  \"stringList\" : {\r\n" +
                "    \"returnedItems\" : \"4/4\",\r\n" +
                "    \"stringList\" : [ 1, 2, 3, 4 ]\r\n" +
                "  }\r\n" +
                "}";
        mapper.readTree(toString);
        assertEquals(expected, toString);
    }

    @Test
    public void testFormatForObjectList() throws JsonProcessingException {
        //given
        List<TestObj> objList = Arrays.asList(new TestObj(1), new TestObj(2), new TestObj(3));
        //when
        ToStringBuilderImpl.setLogMode(LogMode.DEBUG);
        ToStringBuilder toStringBuilder = new ToStringBuilderImpl(this);
        toStringBuilder.append("objList", objList);
        String toString = toStringBuilder.toString();
        //then
        String expected = "{\r\n" +
                "  \"objList\" : {\r\n" +
                "    \"returnedItems\" : \"3/3\",\r\n" +
                "    \"objList\" : [ {\r\n" +
                "  \"in\" : \"1\"\r\n" +
                "}, {\r\n" +
                "  \"in\" : \"2\"\r\n" +
                "}, {\r\n" +
                "  \"in\" : \"3\"\r\n" +
                "} ]\r\n" +
                "  }\r\n" +
                "}";
        mapper.readTree(toString);
        assertEquals(expected, toString);
    }

    @Test
    public void testFormatForIntArray() throws JsonProcessingException {
        //given
        int[] intArray = new int[]{1, 2, 3};
        //when
        ToStringBuilderImpl.setLogMode(LogMode.DEBUG);
        ToStringBuilder toStringBuilder = new ToStringBuilderImpl(this);
        toStringBuilder.append("intArray", intArray);
        String toString = toStringBuilder.toString();
        //then
        String expected = "{\r\n" +
                "  \"intArray\" : \"[1, 2, 3]\"\r\n" +
                "}";
        mapper.readTree(toString);
        assertEquals(expected, toString);
    }

    @Test
    public void testFormatForBranchDto() throws JsonProcessingException {
        //given
        BranchDto dto = new BranchDto();
        dto.setName("name");
        dto.setNum(1000);
        dto.setsMaskedCode("200000");
        dto.setLMaskedCityCode("45000");
        dto.setStringList(Arrays.asList("a", "b", "c"));
        //when
        ToStringBuilderImpl.setLogMode(LogMode.DEBUG);
        String toString = dto.toString();
        //then
        String expected = "{\r\n" +
                "  \"code\" : \"*SEMI_ENCRYPTED:2\",\r\n" +
                "  \"name\" : \"name\",\r\n" +
                "  \"cityCode\" : \"*SEMI_ENCRYPTED:000\",\r\n" +
                "  \"num\" : \"1000\",\r\n" +
                "  \"stringList\" : {\r\n" +
                "    \"returnedItems\" : \"3/3\",\r\n" +
                "    \"stringList\" : [ \"a\", \"b\", \"c\" ]\r\n" +
                "  }\r\n" +
                "}";
        mapper.readTree(toString);
        assertEquals(expected, toString);
    }

    @Test
    public void testFormatForBranchs() throws JsonProcessingException {
        //given
        BranchDto dto1 = new BranchDto();
        dto1.setName("name1");
        dto1.setNum(1000);
        dto1.setsMaskedCode("200000");
        dto1.setLMaskedCityCode("45000");
        dto1.setStringList(Arrays.asList("a", "b", "c"));
        BranchDto dto2 = new BranchDto();
        dto2.setName("name2");
        dto2.setNum(1000);
        dto2.setsMaskedCode("200000");
        dto2.setLMaskedCityCode("45000");
        dto2.setStringList(Arrays.asList("a", "b", "c"));
        List<BranchDto> branchDtos = Arrays.asList(dto1, dto2);
        Branches branches = new Branches();
        branches.setBranchList(branchDtos);
        branches.setTotalRecord(100);
        branches.setMaskedValue("masked");
        //when
        ToStringBuilderImpl.setLogMode(LogMode.DEBUG);
        String toString = branches.toString();
        //then
        String expected = "{\r\n" +
                "  \"totalRecord\" : \"100\",\r\n" +
                "  \"maskedValue\" : \"*SEMI_ENCRYPTED:m\",\r\n" +
                "  \"branchList\" : {\r\n" +
                "    \"returnedItems\" : \"2/2\",\r\n" +
                "    \"branchList\" : [ {\r\n" +
                "  \"code\" : \"*SEMI_ENCRYPTED:2\",\r\n" +
                "  \"name\" : \"name1\",\r\n" +
                "  \"cityCode\" : \"*SEMI_ENCRYPTED:000\",\r\n" +
                "  \"num\" : \"1000\",\r\n" +
                "  \"stringList\" : {\r\n" +
                "    \"returnedItems\" : \"3/3\",\r\n" +
                "    \"stringList\" : [ \"a\", \"b\", \"c\" ]\r\n" +
                "  }\r\n" +
                "}, {\r\n" +
                "  \"code\" : \"*SEMI_ENCRYPTED:2\",\r\n" +
                "  \"name\" : \"name2\",\r\n" +
                "  \"cityCode\" : \"*SEMI_ENCRYPTED:000\",\r\n" +
                "  \"num\" : \"1000\",\r\n" +
                "  \"stringList\" : {\r\n" +
                "    \"returnedItems\" : \"3/3\",\r\n" +
                "    \"stringList\" : [ \"a\", \"b\", \"c\" ]\r\n" +
                "  }\r\n" +
                "} ]\r\n" +
                "  }\r\n" +
                "}";
        mapper.readTree(toString);
        assertEquals(expected, toString);
    }

    @Test
    public void testFormatForStringArray() throws JsonProcessingException {
        //given
        String[] stringArray = new String[]{"a", "b", "c"};
        //when
        ToStringBuilderImpl.setLogMode(LogMode.DEBUG);
        ToStringBuilder toStringBuilder = new ToStringBuilderImpl(this);
        toStringBuilder.append("stringArray", stringArray);
        String toString = toStringBuilder.toString();
        //then
        String expected = "{\r\n" +
                "  \"stringArray\" : \"[a, b, c]\"\r\n" +
                "}";
        mapper.readTree(toString);
        assertEquals(expected, toString);
    }

    @Test
    public void testFormatForEnumSet() throws JsonProcessingException {
        //given
        Set<Type> enumSet = new LinkedHashSet<>();
        enumSet.add(Type.TYPE1);
        enumSet.add(Type.TYPE2);
        //when
        ToStringBuilderImpl.setLogMode(LogMode.DEBUG);
        ToStringBuilder toStringBuilder = new ToStringBuilderImpl(this);
        toStringBuilder.append("enumSet", enumSet);
        String toString = toStringBuilder.toString();
        //then
        String expected =
                "{\r\n" +
                        "  \"enumSet\" : {\r\n" +
                        "    \"returnedItems\" : \"2/2\",\r\n" +
                        "    \"enumSet\" : [ \"TYPE1\", \"TYPE2\" ]\r\n" +
                        "  }\r\n" +
                        "}";

        mapper.readTree(toString);
        assertEquals(expected, toString);
    }

    @Test
    public void testFormatForObjectArray() throws JsonProcessingException {
        //given
        TestObj[] objectArray = new TestObj[]{new TestObj(1, "a"), new TestObj(2, "b"), new TestObj(3, "c")};
        //when
        ToStringBuilderImpl.setLogMode(LogMode.DEBUG);
        ToStringBuilder toStringBuilder = new ToStringBuilderImpl(this);
        toStringBuilder.append("objectArray", objectArray);
        String toString = toStringBuilder.toString();
        //then
        String expected = "{\r\n" +
                "  \"objectArray\" : [{\r\n" +
                "  \"in\" : \"1\",\r\n" +
                "  \"str\" : \"a\"\r\n" +
                "}, {\r\n" +
                "  \"in\" : \"2\",\r\n" +
                "  \"str\" : \"b\"\r\n" +
                "}, {\r\n" +
                "  \"in\" : \"3\",\r\n" +
                "  \"str\" : \"c\"\r\n" +
                "}]\r\n" +
                "}";
        mapper.readTree(toString);
        assertEquals(expected, toString);
    }

    @Test
    public void testFormatForMap() throws JsonProcessingException {
        //given
        Map<String, Integer> map = new LinkedHashMap<>();
        map.put("1", 1);
        map.put("2", 2);
        //when
        ToStringBuilderImpl.setLogMode(LogMode.DEBUG);
        ToStringBuilder toStringBuilder = new ToStringBuilderImpl(this);
        toStringBuilder.append("map", map);
        String toString = toStringBuilder.toString();
        //then
        String expected = "{\r\n" +
                "  \"map\" : {\r\n" +
                "    \"1\" : 1,\r\n" +
                "    \"2\" : 2\r\n" +
                "  }\r\n" +
                "}";
        mapper.readTree(toString);
        assertEquals(expected, toString);
    }

    @Test
    public void testFormatForLocalObjectInAnotherObject() throws JsonProcessingException {
        //given
        TestObj obj = new TestObj(1, "2", new ChildObj(3, "4"));
        //when
        ToStringBuilderImpl.setLogMode(LogMode.DEBUG);
        ToStringBuilder toStringBuilder = new ToStringBuilderImpl(this);
        toStringBuilder.append("obj", obj);
        String toString = toStringBuilder.toString();
        //then
        String expected = "{\r\n" +
                "  \"obj\" : {\r\n" +
                "  \"in\" : \"1\",\r\n" +
                "  \"str\" : \"2\",\r\n" +
                "  \"child\" : {\r\n" +
                "  \"i\" : \"3\",\r\n" +
                "  \"j\" : \"4\"\r\n" +
                "}\r\n" +
                "}\r\n" +
                "}";
        mapper.readTree(toString);
        assertEquals(expected, toString);
    }

    @Test
    public void testFormatForNullObj() {
        //given
        Map<String, Integer> map = null;
        //when
        ToStringBuilderImpl.setLogMode(LogMode.DEBUG);
        ToStringBuilder toStringBuilder = new ToStringBuilderImpl(this);
        toStringBuilder.append("map", map);
        //then
        String expected = "{ }";
        assertEquals(expected, toStringBuilder.toString());
    }

    @Test
    public void testFormatForSuperClass() throws JsonProcessingException {
        //given
        ChildObj childObj = new ChildObj(1, "2");
        //when
        ToStringBuilderImpl.setLogMode(LogMode.DEBUG);
        String s = childObj.toString();
        //then
        String expected = "{\r\n" +
                "  \"i\" : \"1\",\r\n" +
                "  \"j\" : \"2\"\r\n" +
                "}";
        mapper.readTree(s);
        assertEquals(expected, s);
    }

    @Test
    public void testFormatForLogMode() throws JsonProcessingException {
        //given
        List<Integer> stringList = Arrays.asList(1, 2, 3, 4);
        //when
        ToStringBuilderImpl.setLogMode(LogMode.INFO);
        ToStringBuilderImpl.setRecordCount(2);
        ToStringBuilder toStringBuilder = new ToStringBuilderImpl(this);
        toStringBuilder.append("stringList", stringList);
        String toString = toStringBuilder.toString();
        //then
        String expected = "{\r\n" +
                "  \"stringList\" : {\r\n" +
                "    \"returnedItems\" : \"2/4\",\r\n" +
                "    \"stringList\" : [ 1, 2 ]\r\n" +
                "  }\r\n" +
                "}";
        mapper.readTree(toString);
        assertEquals(expected, toString);
    }
}