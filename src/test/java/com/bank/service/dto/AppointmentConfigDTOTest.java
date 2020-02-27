package com.bank.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.bank.web.rest.TestUtil;

public class AppointmentConfigDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AppointmentConfigDTO.class);
        AppointmentConfigDTO appointmentConfigDTO1 = new AppointmentConfigDTO();
        appointmentConfigDTO1.setId(1L);
        AppointmentConfigDTO appointmentConfigDTO2 = new AppointmentConfigDTO();
        assertThat(appointmentConfigDTO1).isNotEqualTo(appointmentConfigDTO2);
        appointmentConfigDTO2.setId(appointmentConfigDTO1.getId());
        assertThat(appointmentConfigDTO1).isEqualTo(appointmentConfigDTO2);
        appointmentConfigDTO2.setId(2L);
        assertThat(appointmentConfigDTO1).isNotEqualTo(appointmentConfigDTO2);
        appointmentConfigDTO1.setId(null);
        assertThat(appointmentConfigDTO1).isNotEqualTo(appointmentConfigDTO2);
    }
}
