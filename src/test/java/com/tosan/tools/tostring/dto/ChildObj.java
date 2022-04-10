package com.tosan.tools.tostring.dto;

import com.tosan.tools.tostring.ToStringBuilder;
import com.tosan.tools.tostring.ToStringBuilderImpl;

public class ChildObj extends ParentObj {
    private final String j;

    public ChildObj(int i, String j) {
        super(i);
        this.j = j;
    }

    public String getJ() {
        return j;
    }

    @Override
    public String toString() {
        final ToStringBuilder sb = new ToStringBuilderImpl(this);
        sb.append("superClass", super.toString());
        sb.append("j", j);
        return sb.toString();
    }
}
