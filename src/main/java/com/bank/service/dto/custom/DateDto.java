package com.bank.service.dto.custom;

import java.util.Objects;

public class DateDto {
    private String value;

    public DateDto() {
    }

    public DateDto(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DateDto dateDto = (DateDto) o;
        return Objects.equals(value, dateDto.value);
    }

    @Override
    public int hashCode() {

        return Objects.hash(value);
    }
}
