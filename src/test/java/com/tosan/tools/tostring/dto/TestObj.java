package com.tosan.tools.tostring.dto;

import com.tosan.tools.tostring.ToStringBuilder;
import com.tosan.tools.tostring.ToStringBuilderImpl;

import java.util.Currency;

public class TestObj {
    private final int in;
    private String str;
    private ChildObj child;
    private BranchDto branchDto;
    private Currency currency;

    public TestObj(int in) {
        this.in = in;
    }

    public TestObj(int in, String str) {
        this.in = in;
        this.str = str;
    }

    public TestObj(int in, String str, ChildObj child) {
        this.in = in;
        this.str = str;
        this.child = child;
    }

    public TestObj(int in, String str, ChildObj child, BranchDto branchDto) {
        this.in = in;
        this.str = str;
        this.child = child;
        this.branchDto = branchDto;
    }

    public TestObj(int in, String str, ChildObj child, BranchDto branchDto, Currency currency) {
        this.in = in;
        this.str = str;
        this.child = child;
        this.branchDto = branchDto;
        this.currency = currency;
    }

    public int getIn() {
        return in;
    }

    public String getStr() {
        return str;
    }

    public ChildObj getChild() {
        return child;
    }

    @Override
    public String toString() {
        final ToStringBuilder sb = new ToStringBuilderImpl(this);
        sb.append("superClass", super.toString());
        sb.append("in", in);
        sb.append("str", str);
        sb.append("child", child);
        sb.append("branchDto", branchDto);
        sb.append("currency", currency);
        return sb.toString();
    }
}
