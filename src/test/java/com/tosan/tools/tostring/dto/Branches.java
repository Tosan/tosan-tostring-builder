package com.tosan.tools.tostring.dto;

import com.tosan.tools.tostring.ToStringBuilder;
import com.tosan.tools.tostring.ToStringBuilderImpl;

import java.io.Serializable;
import java.util.List;

/**
 * @author shamsolebad
 * @since Apr 21, 2010
 */
public class Branches implements Serializable {
    private long totalRecord;
    private List<BranchDto> branchList;
    private String maskedValue;

    public long getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(long totalRecord) {
        this.totalRecord = totalRecord;
    }

    public List<BranchDto> getBranchList() {
        return branchList;
    }

    public void setBranchList(List<BranchDto> branchList) {
        this.branchList = branchList;
    }

    public String getMaskedValue() {
        return maskedValue;
    }

    public void setMaskedValue(String maskedValue) {
        this.maskedValue = maskedValue;
    }

    @Override
    public String toString() {
        final ToStringBuilder sb = new ToStringBuilderImpl(this);
        sb.append("superClass", super.toString());
        sb.append("totalRecord", totalRecord);
        sb.semiEncryptedAppend("maskedValue", maskedValue);
        sb.append("branchList", branchList);
        return sb.toString();
    }
}