package com.bank.service.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.bank.domain.Org} entity.
 */
@ApiModel(description = "机构表")
public class OrgDTO implements Serializable {

    private Long id;

    /**
     * 机构名称
     */
    @ApiModelProperty(value = "机构名称")
    private String name;

    /**
     * 地址
     */
    @ApiModelProperty(value = "地址")
    private String addr;

    /**
     * 坐标
     */
    @ApiModelProperty(value = "坐标")
    private String coordinate;


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

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        OrgDTO orgDTO = (OrgDTO) o;
        if (orgDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), orgDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OrgDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", addr='" + getAddr() + "'" +
            ", coordinate='" + getCoordinate() + "'" +
            "}";
    }
}
