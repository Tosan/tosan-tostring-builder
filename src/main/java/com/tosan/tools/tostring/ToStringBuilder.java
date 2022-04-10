package com.tosan.tools.tostring;

/**
 * This interface expose methods for using in toString method.
 * these methods offer facilities to mask some or whole part of value when logged.
 *
 * @author mamiri
 * @since 12/3/13
 */
public interface ToStringBuilder {

    /**
     * @param name field name
     * @param value field value
     */
    ToStringBuilder append(String name, Object value);

    /**
     * calling this method leads to showing only right part of value from middle to end.
     */
    ToStringBuilder leftEncryptedAppend(String name, Object value);

    /**
     * calling this method leads to showing only left part of value from begin to middle.
     */
    ToStringBuilder rightEncryptedAppend(String name, Object value);

    /**
     * this method will remove value
     * use this for fields such as account balance and password
     */
    ToStringBuilder encryptedAppend(String name, Object value);

    /**
     * this method will mask pan and only shows begin and end of pan value.
     */
    ToStringBuilder panEncryptedAppend(String name, Object value);

    /**
     * this method will mask part of value
     * it will mask 5 chars from right part of value.
     */
    ToStringBuilder semiEncryptedAppend(String name, Object value);

    /**
     * this method will mask part of value
     * it will mask maskLength chars from right part of value.
     */
    ToStringBuilder semiEncryptedAppend(String name, Object value, int maskLength);
}
