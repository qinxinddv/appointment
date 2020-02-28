package com.bank.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * 黑名单关键字
 */
@Entity
@Table(name = "black_key")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class BlackKey implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 关键字
     */
    @Column(name = "jhi_key")
    private String key;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public BlackKey key(String key) {
        this.key = key;
        return this;
    }

    public void setKey(String key) {
        this.key = key;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BlackKey)) {
            return false;
        }
        return id != null && id.equals(((BlackKey) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "BlackKey{" +
            "id=" + getId() +
            ", key='" + getKey() + "'" +
            "}";
    }
}
