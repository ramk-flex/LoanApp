package com.loanapp.vo;


import java.sql.Date;
import java.util.Set;

public class BorrowerVO extends BaseVO {

    private Long            id;
    private Long            loanAppLoanId;
    private Short           borrowerNumber;
    private String          borrowerRoleId;
    private String          firstName;
    private String          middleName;
    private String          lastName;
    private String          socialSecurityNumber;
    private String          homePhoneNumber;
    private Date            dateOfBirth;
    private Short           yearsInSchool;
    private String          maritalStatus;
    private Short           numberOfDependents;
    private Set<BorrowerAddressVO>  addresses;


    public short getBorrowerNumber() {
        return borrowerNumber;
    }

    public void setBorrowerNumber(short borrowerNumber) {
        this.borrowerNumber = borrowerNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public String getHomePhoneNumber() {
        return homePhoneNumber;
    }

    public void setHomePhoneNumber(String homePhoneNumber) {
        this.homePhoneNumber = homePhoneNumber;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public short getYearsInSchool() {
        return yearsInSchool;
    }

    public void setYearsInSchool(short yearsInSchool) {
        this.yearsInSchool = yearsInSchool;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public Short getNumberOfDependents() {
        return numberOfDependents;
    }

    public void setNumberOfDependents(Short numberOfDependents) {
        this.numberOfDependents = numberOfDependents;
    }

    public void setBorrowerNumber(Short borrowerNumber) {
        this.borrowerNumber = borrowerNumber;
    }

    public void setYearsInSchool(Short yearsInSchool) {
        this.yearsInSchool = yearsInSchool;
    }

    public Set<BorrowerAddressVO> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<BorrowerAddressVO> addresses) {
        this.addresses = addresses;
    }

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

    public String getBorrowerRoleId() {
        return borrowerRoleId;
    }

    public void setBorrowerRoleId(String borrowerRoleId) {
        this.borrowerRoleId = borrowerRoleId;
    }

    @Override
    public String toString() {
        return "BorrowerVO{" +
                "id=" + id +
                ", loanAppLoanId=" + loanAppLoanId +
                ", borrowerNumber=" + borrowerNumber +
                ", borrowerRoleId='" + borrowerRoleId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", socialSecurityNumber='" + socialSecurityNumber + '\'' +
                ", homePhoneNumber='" + homePhoneNumber + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", yearsInSchool=" + yearsInSchool +
                ", maritalStatus='" + maritalStatus + '\'' +
                ", numberOfDependents=" + numberOfDependents +
                ", addresses=" + addresses +
                ", version=" + version +
                ", createdDateTime=" + createdDateTime +
                ", createdByUserId='" + createdByUserId + '\'' +
                ", modifiedDateTime=" + modifiedDateTime +
                ", modifiedByUserId='" + modifiedByUserId + '\'' +
                '}';
    }
}
