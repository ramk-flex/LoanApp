package com.loanapp.vo;

public class BorrowerAddressVO extends BaseVO{

    private Long id;
    private Long loanAppBorrowerId;
    private String addressType;
    private Short noOfYearsStayed;
    private Short noOfMonthsStayed;
    private AddressVO address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLoanAppBorrowerId() {
        return loanAppBorrowerId;
    }

    public void setLoanAppBorrowerId(Long loanAppBorrowerId) {
        this.loanAppBorrowerId = loanAppBorrowerId;
    }

    public AddressVO getAddress() {
        return address;
    }

    public void setAddress(AddressVO address) {
        this.address = address;
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    public Short getNoOfYearsStayed() {
        return noOfYearsStayed;
    }

    public void setNoOfYearsStayed(Short noOfYearsStayed) {
        this.noOfYearsStayed = noOfYearsStayed;
    }

    public Short getNoOfMonthsStayed() {
        return noOfMonthsStayed;
    }

    public void setNoOfMonthsStayed(Short noOfMonthsStayed) {
        this.noOfMonthsStayed = noOfMonthsStayed;
    }

    @Override
    public String toString() {
        return "BorrowerAddressVO{" +
                "id=" + id +
                ", loanAppBorrowerId=" + loanAppBorrowerId +
                ", addressType='" + addressType + '\'' +
                ", noOfYearsStayed=" + noOfYearsStayed +
                ", noOfMonthsStayed=" + noOfMonthsStayed +
                ", address=" + address +
                ", version=" + version +
                ", createdDateTime=" + createdDateTime +
                ", createdByUserId='" + createdByUserId + '\'' +
                ", modifiedDateTime=" + modifiedDateTime +
                ", modifiedByUserId='" + modifiedByUserId + '\'' +
                '}';
    }
}
