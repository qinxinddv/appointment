package com.bank.service.dto.custom;

import com.bank.domain.enumeration.BusiTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@ApiModel(description = "预约处理完成实体")
public class AppointmentOverDto {

    @ApiModelProperty(value = "ID")
    private long id;

    @ApiModelProperty(value = "处理意见")
    private String opnion;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOpnion() {
        return opnion;
    }

    public void setOpnion(String opnion) {
        this.opnion = opnion;
    }

}
