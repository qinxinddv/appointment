package com.bank.service.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Objects;
import com.bank.domain.enumeration.CommunityStateEnum;

/**
 * A DTO for the {@link com.bank.domain.Community} entity.
 */
@ApiModel(description = "小区表")
public class CommunityDTO implements Serializable {

    private Long id;

    /**
     * 小区名称
     */
    @ApiModelProperty(value = "小区名称")
    private String name;

    /**
     * 小区地址
     */
    @ApiModelProperty(value = "小区地址")
    private String addr;

    /**
     * 小区状态
     */
    @ApiModelProperty(value = "小区状态")
    private String state;

    /**
     * 小区状态
     */
    @ApiModelProperty(value = "小区状态")
    private CommunityStateEnum communityStateEnum;


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
            "}";
    }
}
