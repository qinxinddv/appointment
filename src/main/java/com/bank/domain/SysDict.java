package com.bank.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A SysDict.
 */
@Entity
@Table(name = "sys_dict")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SysDict implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "code")
    private String code;

    @Column(name = "value")
    private String value;

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "last_modified_date")
    private LocalDate lastModifiedDate;

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

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public SysDict createdDate(LocalDate createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getLastModifiedDate() {
        return lastModifiedDate;
    }

    public SysDict lastModifiedDate(LocalDate lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
        return this;
    }

    public void setLastModifiedDate(LocalDate lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
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
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            "}";
    }
}