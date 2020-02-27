package com.bank.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AppointmentConfigMapperTest {

    private AppointmentConfigMapper appointmentConfigMapper;

    @BeforeEach
    public void setUp() {
        appointmentConfigMapper = new AppointmentConfigMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(appointmentConfigMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(appointmentConfigMapper.fromId(null)).isNull();
    }
}
