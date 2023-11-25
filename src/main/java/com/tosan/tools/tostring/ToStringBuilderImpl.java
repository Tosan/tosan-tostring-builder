package com.tosan.tools.tostring;

import com.tosan.tools.logger.LogMode;

/**
 * This class acts as factory for providing implementation for ToStringBuilder.
 * based on static field format, it will use json or simple format for style. default format is Format.JSON.
 * if you want simple format, before creat new instance set it in static field through setter.
 *
 * @author mamiri
 * @since 12/4/13
 */
public class ToStringBuilderImpl implements ToStringBuilder {
    private static Format format = Format.JSON;
    private final ToStringBuilder builder;

    public ToStringBuilderImpl(Object toStringObject) {
        this.builder = (format == Format.JSON) ?
                new JsonToStringBuilderImpl(toStringObject) :
                new SimpleToStringBuilderImpl(toStringObject);
    }

    public static void setFormat(Format format) {
        ToStringBuilderImpl.format = format;
    }

    public static Format getFormat() {
        return format;
    }

    public static void setLogMode(LogMode logMode) {
        AbstractToStringBuilder.setLogMode(logMode);
    }

    public static void cleanLogMode() {
        AbstractToStringBuilder.cleanLogMode();
    }

    public static void setRecordCount(int i) {
        AbstractToStringBuilder.setRecordCount(i);
    }

    public static int getRecordCount() {
        return AbstractToStringBuilder.getRecordCount();
    }

    @Override
    public ToStringBuilder append(String key, Object obj) {
        return builder.append(key, obj);
    }

    @Override
    public ToStringBuilder leftEncryptedAppend(String key, Object obj) {
        return builder.leftEncryptedAppend(key, obj);
    }

    @Override
    public ToStringBuilder rightEncryptedAppend(String key, Object obj) {
        return builder.rightEncryptedAppend(key, obj);
    }

    @Override
    public ToStringBuilder encryptedAppend(String key, Object obj) {
        return builder.encryptedAppend(key, obj);
    }

    @Override
    public ToStringBuilder panEncryptedAppend(String key, Object obj) {
        return builder.panEncryptedAppend(key, obj);
    }

    @Override
    public ToStringBuilder semiEncryptedAppend(String key, Object obj) {
        return builder.semiEncryptedAppend(key, obj);
    }

    @Override
    public ToStringBuilder semiEncryptedAppend(String key, Object obj, int maskLength) {
        return builder.semiEncryptedAppend(key, obj, maskLength);
    }

    @Override
    public ToStringBuilder middleEncryptedAppend(String key, Object obj) {
        return builder.middleEncryptedAppend(key, obj);
    }

    @Override
    public String toString() {
        return builder.toString();
    }
}
