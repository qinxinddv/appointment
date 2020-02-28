package com.bank.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * 字典表
 */
@Entity
@Table(name = "sys_dict")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SysDict implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 字典名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 字典类型
     */
    @Column(name = "type")
    private String type;

    /**
     * 字典编码
     */
    @Column(name = "code")
    private String code;

    /**
     * 字典值
     */
    @Column(name = "value")
    private String value;

    /**
     * 父节点ID
     */
    @Column(name = "parent_id")
    private Long parentId;

    /**
     * 描述
     */
    @Column(name = "jhi_desc")
    private String desc;

    /**
     * 扩展字段1
     */
    @Column(name = "extend_1")
    private String extend1;

    /**
     * 扩展字段1
     */
    @Column(name = "extend_2")
    private String extend2;

    /**
     * 扩展字段1
     */
    @Column(name = "extend_3")
    private String extend3;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public SysDict name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public SysDict type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public SysDict code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public SysDict value(String value) {
        this.value = value;
        return this;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getParentId() {
        return parentId;
    }

    public SysDict parentId(Long parentId) {
        this.parentId = parentId;
        return this;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getDesc() {
        return desc;
    }

    public SysDict desc(String desc) {
        this.desc = desc;
        return this;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getExtend1() {
        return extend1;
    }

    public SysDict extend1(String extend1) {
        this.extend1 = extend1;
        return this;
    }

    public void setExtend1(String extend1) {
        this.extend1 = extend1;
    }

    public String getExtend2() {
        return extend2;
    }

    public SysDict extend2(String extend2) {
        this.extend2 = extend2;
        return this;
    }

    public void setExtend2(String extend2) {
        this.extend2 = extend2;
    }

    public String getExtend3() {
        return extend3;
    }

    public SysDict extend3(String extend3) {
        this.extend3 = extend3;
        return this;
    }

    public void setExtend3(String extend3) {
        this.extend3 = extend3;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SysDict)) {
            return false;
        }
        return id != null && id.equals(((SysDict) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "SysDict{" +
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
