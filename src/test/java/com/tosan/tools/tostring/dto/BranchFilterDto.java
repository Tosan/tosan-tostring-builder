package com.tosan.tools.tostring.dto;

import com.tosan.tools.tostring.ToStringBuilder;
import com.tosan.tools.tostring.ToStringBuilderImpl;

import java.io.Serializable;
import java.util.Currency;

/**
 * @author shamsolebad
 * @since Apr 21, 2010
 */
public class BranchFilterDto extends BranchDto implements Serializable {
    private static final long serialVersionUID = 8033497539746591355L;
    private Long depositType;
    private Currency currency;
    private boolean forInauguration;
    private boolean showVisitableBranches;
    private long offset;
    private long length;

    public Long getDepositType() {
        return depositType;
    }

    public void setDepositType(Long depositType) {
        this.depositType = depositType;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public boolean isForInauguration() {
        return forInauguration;
    }

    public void setForInauguration(boolean forInauguration) {
        this.forInauguration = forInauguration;
    }

    public boolean isShowVisitableBranches() {
        return showVisitableBranches;
    }

    public void setShowVisitableBranches(boolean showVisitableBranches) {
        this.showVisitableBranches = showVisitableBranches;
    }

    public long getOffset() {
        return offset;
    }

    public void setOffset(long offset) {
        this.offset = offset;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    @Override
    public String toString() {
        final ToStringBuilder sb = new ToStringBuilderImpl(this);
        sb.append("superClass", super.toString());
        sb.append("depositType", depositType);
        sb.append("currency", currency);
        sb.append("forInauguration", forInauguration);
        sb.append("offset", offset);
        sb.append("length", length);
        sb.append("showVisitableBranches", showVisitableBranches);
        return sb.toString();
    }
}