package com.loanapp.vo;

import java.math.BigDecimal;

public class PropertyVO extends BaseVO{

    private Long        id;
    private Long        loanAppLoanId;
    private Long        propertyAddressId;
    private AddressVO   propertyAddress;
    private String      occupancyType;
    private Short       numberOfFloors;
    private Short       numberOfUnits;
    private Short       squareFootAge;
    private Short       familyUnits;
    private Short       monthBuilt;
    private Short       yearBuilt;
    private Short       lotSize;
    private BigDecimal  purchasePriceAmount;
    private Boolean     newConstructionYn;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLoanAppLoanId() {
        return loanAppLoanId;
    }

    public void setLoanAppLoanId(Long loanAppLoanId) {
        this.loanAppLoanId = loanAppLoanId;
    }

    public Long getPropertyAddressId() {
        return propertyAddressId;
    }

    public void setPropertyAddressId(Long propertyAddressId) {
        this.propertyAddressId = propertyAddressId;
    }

    public AddressVO getPropertyAddress() {
        return propertyAddress;
    }

    public void setPropertyAddress(AddressVO propertyAddress) {
        this.propertyAddress = propertyAddress;
    }

    public String getOccupancyType() {
        return occupancyType;
    }

    public void setOccupancyType(String occupancyType) {
        this.occupancyType = occupancyType;
    }

    public Short getNumberOfFloors() {
        return numberOfFloors;
    }

    public void setNumberOfFloors(Short numberOfFloors) {
        this.numberOfFloors = numberOfFloors;
    }

    public Short getNumberOfUnits() {
        return numberOfUnits;
    }

    public void setNumberOfUnits(Short numberOfUnits) {
        this.numberOfUnits = numberOfUnits;
    }

    public Short getSquareFootAge() {
        return squareFootAge;
    }

    public void setSquareFootAge(Short squareFootAge) {
        this.squareFootAge = squareFootAge;
    }

    public Short getFamilyUnits() {
        return familyUnits;
    }

    public void setFamilyUnits(Short familyUnits) {
        this.familyUnits = familyUnits;
    }

    public Short getMonthBuilt() {
        return monthBuilt;
    }

    public void setMonthBuilt(Short monthBuilt) {
        this.monthBuilt = monthBuilt;
    }

    public Short getYearBuilt() {
        return yearBuilt;
    }

    public void setYearBuilt(Short yearBuilt) {
        this.yearBuilt = yearBuilt;
    }

    public Short getLotSize() {
        return lotSize;
    }

    public void setLotSize(Short lotSize) {
        this.lotSize = lotSize;
    }

    public BigDecimal getPurchasePriceAmount() {
        return purchasePriceAmount;
    }

    public void setPurchasePriceAmount(BigDecimal purchasePriceAmount) {
        this.purchasePriceAmount = purchasePriceAmount;
    }

    public Boolean getNewConstructionYn() {
        return newConstructionYn;
    }

    public void setNewConstructionYn(Boolean newConstructionYn) {
        this.newConstructionYn = newConstructionYn;
    }
}
