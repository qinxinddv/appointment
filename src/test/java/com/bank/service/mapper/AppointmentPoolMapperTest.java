package com.bank.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AppointmentPoolMapperTest {

    private AppointmentPoolMapper appointmentPoolMapper;

    @BeforeEach
    public void setUp() {
        appointmentPoolMapper = new AppointmentPoolMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(appointmentPoolMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(appointmentPoolMapper.fromId(null)).isNull();
    }
}
