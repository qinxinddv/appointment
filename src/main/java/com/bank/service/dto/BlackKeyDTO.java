package com.bank.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.bank.domain.BlackKey} entity.
 */
public class BlackKeyDTO implements Serializable {

    private Long id;

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
