package com.bank.service.dto.custom;

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
}
