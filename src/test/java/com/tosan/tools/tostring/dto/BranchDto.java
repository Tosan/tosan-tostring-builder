package com.tosan.tools.tostring.dto;

import com.tosan.tools.tostring.ToStringBuilder;
import com.tosan.tools.tostring.ToStringBuilderImpl;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * @author shamsolebad
 * @since Apr 21, 2010
 */
public class BranchDto implements Serializable {
    private static final long serialVersionUID = 206554581706295696L;

    private String sMaskedCode;

    private String name;
    private String address;
    private String zipCode;
    private String tel;
    private String fax;
    private String cityName;

    private String LMaskedCityCode;

    private String search;
    private List<String> stringList;
    private Set<Type> enumSet;
    private int num;

    public String getLMaskedCityCode() {
        return LMaskedCityCode;
    }

    public void setLMaskedCityCode(String LMaskedCityCode) {
        this.LMaskedCityCode = LMaskedCityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getsMaskedCode() {
        return sMaskedCode;
    }

    public void setsMaskedCode(String sMaskedCode) {
        this.sMaskedCode = sMaskedCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public Set<Type> getEnumSet() {
        return enumSet;
    }

    public void setEnumSet(Set<Type> enumSet) {
        this.enumSet = enumSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BranchDto)) return false;

        BranchDto branchDto = (BranchDto) o;
        return sMaskedCode != null ? sMaskedCode.equals(branchDto.sMaskedCode) : branchDto.sMaskedCode == null;
    }

    @Override
    public int hashCode() {
        return sMaskedCode != null ? sMaskedCode.hashCode() : 0;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public List<String> getStringList() {
        return stringList;
    }

    public void setStringList(List<String> stringList) {
        this.stringList = stringList;
    }

    @Override
    public String toString() {
        final ToStringBuilder sb = new ToStringBuilderImpl(this);
        sb.append("superClass", super.toString());
        sb.semiEncryptedAppend("code", sMaskedCode);
        sb.append("name", name);
        sb.encryptedAppend("address", address);
        sb.append("zipCode", zipCode);
        sb.append("tel", tel);
        sb.append("fax", fax);
        sb.append("cityName", cityName);
        sb.leftEncryptedAppend("cityCode", LMaskedCityCode);
        sb.append("search", search);
        sb.append("num", num);
        sb.append("stringList", stringList);
        sb.append("enumSet", enumSet);
        return sb.toString();
    }
}
