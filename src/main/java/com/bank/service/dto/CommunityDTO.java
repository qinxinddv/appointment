package com.bank.service.dto;

import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;
import com.bank.domain.enumeration.CommunityStateEnum;

/**
 * A DTO for the {@link com.bank.domain.Community} entity.
 */
public class CommunityDTO implements Serializable {

    private Long id;

    private String name;

    private String addr;

    private String state;

    private CommunityStateEnum communityStateEnum;

    private LocalDate createdDate;

    private LocalDate lastModifiedDate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public CommunityStateEnum getCommunityStateEnum() {
        return communityStateEnum;
    }

    public void setCommunityStateEnum(CommunityStateEnum communityStateEnum) {
        this.communityStateEnum = communityStateEnum;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDate lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CommunityDTO communityDTO = (CommunityDTO) o;
        if (communityDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), communityDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CommunityDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", addr='" + getAddr() + "'" +
            ", state='" + getState() + "'" +
            ", communityStateEnum='" + getCommunityStateEnum() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            "}";
    }
}
