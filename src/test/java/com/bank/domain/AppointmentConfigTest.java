package com.bank.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.bank.web.rest.TestUtil;

public class AppointmentConfigTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AppointmentConfig.class);
        AppointmentConfig appointmentConfig1 = new AppointmentConfig();
        appointmentConfig1.setId(1L);
        AppointmentConfig appointmentConfig2 = new AppointmentConfig();
        appointmentConfig2.setId(appointmentConfig1.getId());
        assertThat(appointmentConfig1).isEqualTo(appointmentConfig2);
        appointmentConfig2.setId(2L);
        assertThat(appointmentConfig1).isNotEqualTo(appointmentConfig2);
        appointmentConfig1.setId(null);
        assertThat(appointmentConfig1).isNotEqualTo(appointmentConfig2);
    }
}
