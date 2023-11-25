package com.tosan.tools.util;

import java.math.BigDecimal;

import static com.tosan.tools.util.ToStringConstant.*;

/**
 * @author Saeed Soltani
 * @since Sep 07, 2013
 */
public class EncryptStringUtil {

    /**
     * Encode input field
     */
    public static String encodeBigDecimal(BigDecimal amount) {
        if (amount == null)
            return null;
        BigDecimal encodeValue = sumDigits(amount);
        return ENCODED + encodeValue.toPlainString();
    }

    /**
     * This method make sum of digits of input value. e.g: 534 -> 5+3+4 = 12 -> 1+2 = 3
     * The answer of '534' is '3'.
     */
    private static BigDecimal sumDigits(BigDecimal number) {
        if (number == null)
            return null;
        return (number.remainder(new BigDecimal(9)).equals(new BigDecimal(0)) && !number.equals(new BigDecimal(0)))
                ? new BigDecimal(9) : number.remainder(new BigDecimal(9));
    }

    public static String encrypt(Object obj) {
        String str = null;
        try {
            if (obj != null) {
                if (obj instanceof BigDecimal) {
                    str = EncryptStringUtil.encodeBigDecimal((BigDecimal) obj);
                } else {
                    str = ENCRYPTED;
                }
            }
        } catch (Exception e) {
            str = ERROR_TEXT;
        }
        return str;
    }

    public static String semiEncrypt(Object obj) {
        return semiEncrypt(obj, 5);
    }

    public static String semiEncrypt(Object obj, int maskLength) {
        String str = null;
        try {
            if (obj != null) {
                str = objToString(obj);
                if (str.length() > 2) {
                    str = SEMI_ENCRYPTED.concat(str.length() > maskLength ?
                            str.substring(0, str.length() - maskLength) :
                            str.substring(0, str.length() / 2));
                } else {
                    str = ENCRYPTED;
                }
            }
        } catch (Exception e) {
            str = ERROR_TEXT;
        }
        return str;
    }

    public static String leftEncrypt(Object obj) {
        String str = null;
        try {
            if (obj != null) {
                str = objToString(obj);
                if (str.length() >= 2) {
                    str = SEMI_ENCRYPTED.concat(str.substring(str.length() / 2));
                }
            }
        } catch (Exception e) {
            str = ERROR_TEXT;
        }
        return str;
    }

    public static String rightEncrypt(Object obj) {
        String str = null;
        try {
            if (obj != null) {
                str = objToString(obj);
                if (str.length() >= 2) {
                    str = SEMI_ENCRYPTED.concat(str.substring(0, str.length() / 2));
                }
            }
        } catch (Exception e) {
            str = ERROR_TEXT;
        }
        return str;
    }

    public static String panEncrypt(Object obj) {
        String str = null;
        if (obj != null) {
            str = objToString(obj);
            if (str.length() == 16) {
                str = str.substring(0, 6).concat("******").concat(str.substring(12, 16));
            } else if (str.length() == 19) {
                str = str.substring(0, 6).concat("*********").concat(str.substring(15, 19));
            } else {
                str = middleEncrypt(str);
            }
        }
        return str;
    }

    public static String middleEncrypt(Object obj) {
        String str;
        try {
            str = objToString(obj);
            if (str == null || str.isEmpty()) {
                return str;
            } else if (str.length() <= 3) {
                return ENCRYPTED;
            } else {
                int partLength = str.length() / 3;
                return str.substring(0, partLength) + generateStar(str.length() - partLength * 2) +
                        str.substring(str.length() - partLength);
            }
        } catch (Exception e) {
            str = ERROR_TEXT;
        }
        return str;
    }

    private static String objToString(Object obj) {
        if (obj != null) {
            if (obj instanceof String) {
                return (String) obj;
            } else if (obj.getClass().isPrimitive()) {
                return String.valueOf(obj);
            } else {
                return obj.toString();
            }
        }
        return null;
    }

    private static String generateStar(int length) {
        StringBuilder stars = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            stars.append("*");
        }
        return stars.toString();
    }
}
