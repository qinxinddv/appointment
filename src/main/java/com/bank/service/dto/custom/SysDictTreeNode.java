package com.bank.service.dto.custom;

import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;


public class SysDictTreeNode<T> {


    @ApiModelProperty(value = "字典code", notes = "字典code")
    public String code;

    @ApiModelProperty(value = "父ID", notes = "父ID")
    private Long parentId;

    @ApiModelProperty(value = "子节点", notes = "子节点")
    private List<T> children = new ArrayList<>();

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public List<T> getChildren() {
        return children;
    }

    public void setChildren(List<T> children) {
        this.children = children;
    }
}
