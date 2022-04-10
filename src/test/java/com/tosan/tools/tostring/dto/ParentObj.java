package com.tosan.tools.tostring.dto;

import com.tosan.tools.tostring.ToStringBuilder;
import com.tosan.tools.tostring.ToStringBuilderImpl;

public class ParentObj {
    private final int i;

    public ParentObj(int i) {
        this.i = i;
    }

    public int getI() {
        return i;
    }

    @Override
    public String toString() {
        final ToStringBuilder sb = new ToStringBuilderImpl(this);
        sb.append("superClass", super.toString());
        sb.append("i", i);
        return sb.toString();
    }
}
