package com.loanapp.vo;

import java.time.Instant;

public abstract class BaseVO {

    Long version;
    Instant createdDateTime;
    String createdByUserId;
    Instant modifiedDateTime;
    String modifiedByUserId;

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Instant getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(Instant createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public String getCreatedByUserId() {
        return createdByUserId;
    }

    public void setCreatedByUserId(String createdByUserId) {
        this.createdByUserId = createdByUserId;
    }

    public Instant getModifiedDateTime() {
        return modifiedDateTime;
    }

    public void setModifiedDateTime(Instant modifiedDateTime) {
        this.modifiedDateTime = modifiedDateTime;
    }

    public String getModifiedByUserId() {
        return modifiedByUserId;
    }

    public void setModifiedByUserId(String modifiedByUserId) {
        this.modifiedByUserId = modifiedByUserId;
    }

}
