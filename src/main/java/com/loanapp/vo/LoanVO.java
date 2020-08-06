package com.loanapp.vo;

import java.math.BigDecimal;
import java.sql.Statement;
import java.util.List;
import java.util.Set;

public class LoanVO extends BaseVO{

    private Long id;
    private String mortgageType;
    private String loanNumber;
    private String fhaCaseNumber;
    private BigDecimal loanAmount;
    private short loanTerm;
    private String productType;
    private Set<BorrowerVO> borrowers;
    private Set<PropertyVO> properties;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMortgageType() {
        return mortgageType;
    }

    public void setMortgageType(String mortgageType) {
        this.mortgageType = mortgageType;
    }

    public String getLoanNumber() {
        return loanNumber;
    }

    public void setLoanNumber(String loanNumber) {
        this.loanNumber = loanNumber;
    }

    public String getFhaCaseNumber() {
        return fhaCaseNumber;
    }

    public void setFhaCaseNumber(String fhaCaseNumber) {
        this.fhaCaseNumber = fhaCaseNumber;
    }

    public BigDecimal getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(BigDecimal loanAmount) {
        this.loanAmount = loanAmount;
    }

    public short getLoanTerm() {
        return loanTerm;
    }

    public void setLoanTerm(short loanTerm) {
        this.loanTerm = loanTerm;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Set<PropertyVO> getProperties() {
        return properties;
    }

    public void setProperties(Set<PropertyVO> properties) {
        this.properties = properties;
    }

    public Set<BorrowerVO> getBorrowers() {
        return borrowers;
    }

    public void setBorrowers(Set<BorrowerVO> borrowers) {
        this.borrowers = borrowers;
    }

    @Override
    public String toString() {
        return "LoanVO{" +
                "id=" + id +
                ", mortgageType='" + mortgageType + '\'' +
                ", loanNumber='" + loanNumber + '\'' +
                ", fhaCaseNumber='" + fhaCaseNumber + '\'' +
                ", loanAmount=" + loanAmount +
                ", loanTerm=" + loanTerm +
                ", productType='" + productType + '\'' +
                ", borrowers=" + borrowers +
                ", version=" + version +
                ", createdDateTime=" + createdDateTime +
                ", createdByUserId='" + createdByUserId + '\'' +
                ", modifiedDateTime=" + modifiedDateTime +
                ", modifiedByUserId='" + modifiedByUserId + '\'' +
                '}';
    }
}
