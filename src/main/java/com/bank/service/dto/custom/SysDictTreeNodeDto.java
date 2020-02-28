package com.bank.service.dto.custom;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import java.io.Serializable;

public class SysDictTreeNodeDto extends SysDictTreeNode<SysDictTreeNodeDto> implements Serializable {


    @ApiModelProperty(value = "id", notes = "id")
    public Long id;

    @ApiModelProperty(value = "字典名称", notes = "字典名称")
    public String name;

    @ApiModelProperty(value = "字典value", notes = "字典value")
    private String value;

    @ApiModelProperty(value = "描述", notes = "描述")
    private String desc;

    /**
     * 扩展字段1
     */
    @ApiModelProperty(value = "扩展字段1", notes = "扩展字段1")
    private String extend1;

    /**
     * 扩展字段2
     */
    @ApiModelProperty(value = "扩展字段2", notes = "扩展字段2")
    private String extend2;

    /**
     * 扩展字段3
     */
    @ApiModelProperty(value = "扩展字段3", notes = "扩展字段3")
    private String extend3;

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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
