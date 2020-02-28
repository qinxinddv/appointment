package com.bank.service.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.bank.domain.BlackKey} entity.
 */
@ApiModel(description = "黑名单关键字")
public class BlackKeyDTO implements Serializable {

    private Long id;

    /**
     * 关键字
     */
    @ApiModelProperty(value = "关键字")
    private String key;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BlackKeyDTO blackKeyDTO = (BlackKeyDTO) o;
        if (blackKeyDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), blackKeyDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BlackKeyDTO{" +
            "id=" + getId() +
            ", key='" + getKey() + "'" +
            "}";
    }
}
