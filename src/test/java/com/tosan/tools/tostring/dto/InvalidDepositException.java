package com.tosan.tools.tostring.dto;

import com.tosan.tools.tostring.ToStringBuilder;
import com.tosan.tools.tostring.ToStringBuilderImpl;

/**
 * @author Mostafa Abdollahi
 * @since 6/10/2021
 */
public class InvalidDepositException extends Exception {
    private String depositNumber;

    public InvalidDepositException(String message, String depositNumber) {
        super(message);
        this.depositNumber = depositNumber;
    }

    public InvalidDepositException(String message, Throwable cause, String depositNumber) {
        super(message, cause);
        this.depositNumber = depositNumber;
    }

    @Override
    public String toString() {
        final ToStringBuilder sb = new ToStringBuilderImpl(this);
        sb.append("superClass", super.toString());
        sb.append("depositNumber", depositNumber);
        return sb.toString();
    }
}
