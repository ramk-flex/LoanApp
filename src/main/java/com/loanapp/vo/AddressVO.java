package com.loanapp.vo;



public class AddressVO extends BaseVO{

    private Long   id;
    private String addressLineOne;
    private String addressLineTwo;
    private String postalCodeId;
    private String city;
    private String stateId;
    private Short  stateFipsId;
    private String countyId;
    private Short  countyFipsId;
    private String countryId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddressLineOne() {
        return addressLineOne;
    }

    public void setAddressLineOne(String addressLineOne) {
        this.addressLineOne = addressLineOne;
    }

    public String getAddressLineTwo() {
        return addressLineTwo;
    }

    public void setAddressLineTwo(String addressLineTwo) {
        this.addressLineTwo = addressLineTwo;
    }

    public String getPostalCodeId() {
        return postalCodeId;
    }

    public void setPostalCodeId(String postalCodeId) {
        this.postalCodeId = postalCodeId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStateId() {
        return stateId;
    }

    public void setStateId(String stateId) {
        this.stateId = stateId;
    }

    public Short getStateFipsId() {
        return stateFipsId;
    }

    public void setStateFipsId(Short stateFipsId) {
        this.stateFipsId = stateFipsId;
    }

    public String getCountyId() {
        return countyId;
    }

    public void setCountyId(String countyId) {
        this.countyId = countyId;
    }

    public Short getCountyFipsId() {
        return countyFipsId;
    }

    public void setCountyFipsId(Short countyFipsId) {
        this.countyFipsId = countyFipsId;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    @Override
    public String toString() {
        return "AddressVO{" +
                "id=" + id +
                ", addressLineOne='" + addressLineOne + '\'' +
                ", addressLineTwo='" + addressLineTwo + '\'' +
                ", postalCodeId='" + postalCodeId + '\'' +
                ", city='" + city + '\'' +
                ", stateId='" + stateId + '\'' +
                ", stateFipsId=" + stateFipsId +
                ", countyId='" + countyId + '\'' +
                ", countyFipsId=" + countyFipsId +
                ", version=" + version +
                ", createdDateTime=" + createdDateTime +
                ", createdByUserId='" + createdByUserId + '\'' +
                ", modifiedDateTime=" + modifiedDateTime +
                ", modifiedByUserId='" + modifiedByUserId + '\'' +
                '}';
    }
}
