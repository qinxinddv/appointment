package com.bank.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.bank.web.rest.TestUtil;

public class AppointmentPoolTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AppointmentPool.class);
        AppointmentPool appointmentPool1 = new AppointmentPool();
        appointmentPool1.setId(1L);
        AppointmentPool appointmentPool2 = new AppointmentPool();
        appointmentPool2.setId(appointmentPool1.getId());
        assertThat(appointmentPool1).isEqualTo(appointmentPool2);
        appointmentPool2.setId(2L);
        assertThat(appointmentPool1).isNotEqualTo(appointmentPool2);
        appointmentPool1.setId(null);
        assertThat(appointmentPool1).isNotEqualTo(appointmentPool2);
    }
}
