package com.tosan.tools.tostring;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.RawValue;
import com.tosan.tools.logger.LogMode;
import com.tosan.tools.util.EncryptStringUtil;
import com.tosan.tools.util.ToStringJsonUtil;

import java.util.*;

import static com.tosan.tools.util.ToStringConstant.ERROR_TEXT;

/**
 * This implementation provides json format for tostring
 *
 * @author mamiri
 * @since 12/4/13
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class JsonToStringBuilderImpl extends AbstractToStringBuilder {
    private static final ObjectMapper mapper = ToStringJsonUtil.getMapper();
    private final Map<String, Object> text;

    public JsonToStringBuilderImpl(Object toStringObject) {
        super(toStringObject);
        text = new LinkedHashMap<>();
    }

    @Override
    public final ToStringBuilder append(String key, Object obj) {
        try {
            if (obj == null) {
                return this;
            }
            if (key.equals("superClass")) {
                try {
                    this.text.putAll(mapper.readValue((String) obj, HashMap.class));
                } catch (JsonParseException e) {
                    //do nothing
                }
            } else if (obj instanceof Iterable) {
                Map<String, Object> objData = new LinkedHashMap<>();
                List<Object> objItems = new ArrayList<>();
                int count = 0;
                Iterable iterable = (Iterable) obj;
                Iterator iterator = iterable.iterator();
                int size = getIterableSize(iterable);
                while (iterator.hasNext() && count < size) {
                    if (logMode.get() != null && logMode.get().equals(LogMode.INFO) && count >= recordCount) {
                        break;
                    }
                    count++;
                    Object o = iterator.next();
                    if (o != null) {
                        if (o instanceof String || o instanceof Enum)
                            objItems.add(o);
                        else
                            objItems.add(new RawValue(o.toString()));
                    }
                }
                objData.put("returnedItems", count + "/" + size);
                objData.put(key, objItems);
                this.text.put(key, objData);
            } else if (obj.getClass().isArray()) {
                if (obj instanceof int[]) {
                    this.text.put(key, Arrays.toString((int[]) obj));
                } else if (obj instanceof long[]) {
                    this.text.put(key, Arrays.toString((long[]) obj));
                } else if (obj instanceof short[]) {
                    this.text.put(key, Arrays.toString((short[]) obj));
                } else if (obj instanceof boolean[]) {
                    this.text.put(key, Arrays.toString((boolean[]) obj));
                } else if (obj instanceof char[]) {
                    this.text.put(key, Arrays.toString((char[]) obj));
                } else if (obj instanceof byte[]) {
                    this.text.put(key, Arrays.toString((byte[]) obj));
                } else if (obj instanceof float[]) {
                    this.text.put(key, Arrays.toString((float[]) obj));
                } else if (obj instanceof double[]) {
                    this.text.put(key, Arrays.toString((double[]) obj));
                } else if (obj instanceof String[] || obj instanceof Enum[]) {
                    this.text.put(key, Arrays.toString((Object[]) obj));
                } else if (obj instanceof Object[]) {
                    this.text.put(key, new RawValue(Arrays.toString((Object[]) obj)));
                }
            } else if (obj instanceof Map) {
                Map map = (Map) obj;
                this.text.put(key, map);
            } else {
                if (obj.toString().startsWith("{"))
                    this.text.put(key, new RawValue(obj.toString()));
                else
                    this.text.put(key, obj.toString());
            }
        } catch (Exception e) {
            this.text.put(key, ERROR_TEXT);
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
        this.text.put(key, EncryptStringUtil.leftEncrypt(obj));
        return this;
    }

    @Override
    public final ToStringBuilder rightEncryptedAppend(String key, Object obj) {
        this.text.put(key, EncryptStringUtil.rightEncrypt(obj));
        return this;
    }

    @Override
    public final ToStringBuilder encryptedAppend(String key, Object obj) {
        this.text.put(key, EncryptStringUtil.encrypt(obj));
        return this;
    }

    @Override
    public ToStringBuilder panEncryptedAppend(String key, Object obj) {
        this.text.put(key, EncryptStringUtil.panEncrypt(obj));
        return this;
    }

    @Override
    public ToStringBuilder semiEncryptedAppend(String key, Object obj) {
        this.text.put(key, EncryptStringUtil.semiEncrypt(obj));
        return this;
    }

    @Override
    public ToStringBuilder semiEncryptedAppend(String key, Object obj, int maskLength) {
        this.text.put(key, EncryptStringUtil.semiEncrypt(obj, maskLength));
        return this;
    }

    @Override
    public ToStringBuilder middleEncryptedAppend(String key, Object obj) {
        this.text.put(key, EncryptStringUtil.middleEncrypt(obj));
        return this;
    }

    @Override
    public final String toString() {
        return ToStringJsonUtil.toJson(this.text);
    }
}
