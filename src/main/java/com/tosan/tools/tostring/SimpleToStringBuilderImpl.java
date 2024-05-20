package com.tosan.tools.tostring;

import com.tosan.tools.logger.LogMode;
import com.tosan.tools.util.EncryptStringUtil;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import static com.tosan.tools.util.ToStringConstant.*;

/**
 * This implementation provides simple format for tostring
 *
 * @author mamiri
 * @since 12/4/13
 */
@SuppressWarnings({"rawtypes"})
public class SimpleToStringBuilderImpl extends AbstractToStringBuilder {
    private StringBuilder text;

    public SimpleToStringBuilderImpl(Object toStringObject) {
        super(toStringObject);
        text = new StringBuilder();
    }

    @Override
    public final ToStringBuilder append(String key, Object obj) {
        try {
            if (key.equals("superClass")) {
                StringBuilder result = new StringBuilder();
                String s = (String) obj;
                String[] splittedString = s.split(ENTER);
                for (int i = 0; i < splittedString.length; i++) {
                    if (i == 0) {
                        if (!splittedString[i].equals(L_BRACKET) && splittedString[i].length() > 1 && splittedString[i].startsWith(L_BRACKET)) {
                            result.append(splittedString[i].substring(1));
                            result.append(ENTER);
                        }
                    } else if (i == splittedString.length - 1) {
                        if (!splittedString[i].equals(R_BRACKET) && splittedString[i].length() > 1 && splittedString[i].endsWith(R_BRACKET)) {
                            result.append(splittedString[i], 0, splittedString[i].length() - 1);
                            result.append(ENTER);
                        }
                    } else {
                        result.append(splittedString[i]);
                        result.append(ENTER);
                    }
                }
                StringBuilder builder = new StringBuilder(result);
                builder.append(this.text);
                this.text = builder;
            } else {
                this.text.append(SPACE);
                this.text.append(key);
                this.text.append(EQUAL);
                if (obj == null) {
                    this.text.append(NULL_TEXT);
                    this.text.append(ENTER);
                } else if (obj instanceof Iterable) {
                    int level = 0;
                    Iterable iterable = (Iterable) obj;
                    Iterator iterator = iterable.iterator();
                    int size = getIterableSize(iterable);
                    if (!iterator.hasNext()) {
                        this.text.append(L_BRACKET);
                        this.text.append(R_BRACKET);
                        this.text.append(ENTER);
                    }
                    while (iterator.hasNext()) {
                        level++;
                        Object o = iterator.next();
                        this.text.append(o == null ? NULL_TEXT : o.toString());
                        if (logMode.get() != null && logMode.get().equals(LogMode.INFO) && level >= recordCount && level < (size - recordCount)) {
                            this.text.append(ENTER);
                            for (; level < (size - recordCount); ++level) iterator.next();
                        } else if (iterator.hasNext()) {
                            this.text.append(COMMA);
                        } else {
                            this.text.append(ENTER);
                        }
                        if (level == size && size - (recordCount * 2) > 0) {
                            this.text.append(size).append(SPACE).append(key).append(" records are returned.");
                            this.text.append(ENTER);
                            this.text.append(size - (recordCount * 2)).append(SPACE).append(key).append(" records are eliminated.");
                            this.text.append(ENTER);
                        }
                    }
                } else if (obj.getClass().isArray()) {
                    if (obj instanceof int[]) {
                        this.text.append(Arrays.toString((int[]) obj));
                    } else if (obj instanceof long[]) {
                        this.text.append(Arrays.toString((long[]) obj));
                    } else if (obj instanceof short[]) {
                        this.text.append(Arrays.toString((short[]) obj));
                    } else if (obj instanceof boolean[]) {
                        this.text.append(Arrays.toString((boolean[]) obj));
                    } else if (obj instanceof char[]) {
                        this.text.append(Arrays.toString((char[]) obj));
                    } else if (obj instanceof byte[]) {
                        this.text.append(Arrays.toString((byte[]) obj));
                    } else if (obj instanceof float[]) {
                        this.text.append(Arrays.toString((float[]) obj));
                    } else if (obj instanceof double[]) {
                        this.text.append(Arrays.toString((double[]) obj));
                    } else if (obj instanceof Object[]) {
                        this.text.append(Arrays.toString((Object[]) obj));
                    }
                    this.text.append(ENTER);
                } else if (obj instanceof Map) {
                    Map map = (Map) obj;
                    this.text.append(L_BRACKET);
                    for (Object o : map.keySet()) {
                        Object value = map.get(o);
                        this.text.append(ENTER);
                        this.text.append(SPACE);
                        this.text.append(o.toString());
                        this.text.append(EQUAL);
                        if (value == null) {
                            this.text.append(NULL_TEXT);
                        } else {
                            this.text.append(value.toString());
                        }
                    }
                    this.text.append(R_BRACKET);
                    this.text.append(ENTER);
                } else {
                    this.text.append(obj.toString());
                    this.text.append(ENTER);
                }
            }
        } catch (Exception e) {
            this.text.append(ERROR_TEXT);
            this.text.append(ENTER);
        }
        return this;
    }

    private int getIterableSize(Iterable iterable) {
        if (iterable instanceof Collection) {
            return ((Collection<?>) iterable).size();
        } else {
            int count = 0;
            for (Object ignored : iterable) {
                count++;
            }
            return count;
        }
    }

    @Override
    public final ToStringBuilder leftEncryptedAppend(String key, Object obj) {
        this.text.append(SPACE);
        this.text.append(key);
        this.text.append(EQUAL);
        if (obj != null) {
            this.text.append(EncryptStringUtil.leftEncrypt(obj));
        } else {
            this.text.append(NULL_TEXT);
        }
        this.text.append(ENTER);
        return this;
    }

    @Override
    public final ToStringBuilder rightEncryptedAppend(String key, Object obj) {
        this.text.append(SPACE);
        this.text.append(key);
        this.text.append(EQUAL);
        if (obj != null) {
            this.text.append(EncryptStringUtil.rightEncrypt(obj));
        } else {
            this.text.append(NULL_TEXT);
        }
        this.text.append(ENTER);
        return this;
    }

    @Override
    public final ToStringBuilder encryptedAppend(String key, Object obj) {
        this.text.append(SPACE);
        this.text.append(key);
        this.text.append(EQUAL);
        if (obj != null) {
            this.text.append(EncryptStringUtil.encrypt(obj));
        } else {
            this.text.append(NULL_TEXT);
        }
        this.text.append(ENTER);
        return this;
    }

    @Override
    public ToStringBuilder panEncryptedAppend(String key, Object obj) {
        this.text.append(SPACE);
        this.text.append(key);
        this.text.append(EQUAL);
        if (obj != null) {
            this.text.append(EncryptStringUtil.panEncrypt(obj));
            this.text.append(ENTER);
        } else {
            this.text.append(NULL_TEXT);
        }
        this.text.append(ENTER);
        return this;
    }

    @Override
    public ToStringBuilder mobileEncryptedAppend(String key, Object obj) {
        this.text.append(SPACE);
        this.text.append(key);
        this.text.append(EQUAL);
        if (obj != null) {
            this.text.append(EncryptStringUtil.mobileEncrypt(obj));
            this.text.append(ENTER);
        } else {
            this.text.append(NULL_TEXT);
        }
        this.text.append(ENTER);
        return this;
    }

    @Override
    public ToStringBuilder semiEncryptedAppend(String key, Object obj) {
        return semiEncryptedAppend(key, obj, 5);
    }

    @Override
    public ToStringBuilder semiEncryptedAppend(String key, Object obj, int maskLength) {
        this.text.append(SPACE);
        this.text.append(key);
        this.text.append(EQUAL);
        if (obj != null) {
            this.text.append(EncryptStringUtil.semiEncrypt(obj));
        } else {
            this.text.append(NULL_TEXT);
        }
        this.text.append(ENTER);
        return this;
    }

    @Override
    public ToStringBuilder middleEncryptedAppend(String key, Object obj) {
        this.text.append(SPACE);
        this.text.append(key);
        this.text.append(EQUAL);
        if (obj != null) {
            this.text.append(EncryptStringUtil.middleEncrypt(obj));
            this.text.append(ENTER);
        } else {
            this.text.append(NULL_TEXT);
        }
        this.text.append(ENTER);
        return this;
    }

    @Override
    public final String toString() {
        StringBuilder sb = new StringBuilder(L_BRACKET);
        sb.append(ENTER);
        sb.append(this.text);
        sb.append(R_BRACKET);
        this.text = sb;
        return this.text.toString();
    }
}
