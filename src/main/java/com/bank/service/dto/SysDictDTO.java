package com.bank.service.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.bank.domain.SysDict} entity.
 */
@ApiModel(description = "字典表")
public class SysDictDTO implements Serializable {

    private Long id;

    /**
     * 字典名称
     */
    @ApiModelProperty(value = "字典名称")
    private String name;

    /**
     * 字典类型
     */
    @ApiModelProperty(value = "字典类型")
    private String type;

    /**
     * 字典编码
     */
    @ApiModelProperty(value = "字典编码")
    private String code;

    /**
     * 字典值
     */
    @ApiModelProperty(value = "字典值")
    private String value;

    /**
     * 父节点ID
     */
    @ApiModelProperty(value = "父节点ID")
    private Long parentId;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    private String desc;

    /**
     * 扩展字段1
     */
    @ApiModelProperty(value = "扩展字段1")
    private String extend1;

    /**
     * 扩展字段1
     */
    @ApiModelProperty(value = "扩展字段1")
    private String extend2;

    /**
     * 扩展字段1
     */
    @ApiModelProperty(value = "扩展字段1")
    private String extend3;


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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getExtend1() {
        return extend1;
    }

    public void setExtend1(String extend1) {
        this.extend1 = extend1;
    }

    public String getExtend2() {
        return extend2;
    }

    public void setExtend2(String extend2) {
        this.extend2 = extend2;
    }

    public String getExtend3() {
        return extend3;
    }

    public void setExtend3(String extend3) {
        this.extend3 = extend3;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SysDictDTO sysDictDTO = (SysDictDTO) o;
        if (sysDictDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sysDictDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SysDictDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", type='" + getType() + "'" +
            ", code='" + getCode() + "'" +
            ", value='" + getValue() + "'" +
            ", parentId=" + getParentId() +
            ", desc='" + getDesc() + "'" +
            ", extend1='" + getExtend1() + "'" +
            ", extend2='" + getExtend2() + "'" +
            ", extend3='" + getExtend3() + "'" +
            "}";
    }
}
