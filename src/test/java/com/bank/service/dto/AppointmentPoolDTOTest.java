package com.bank.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.bank.web.rest.TestUtil;

public class AppointmentPoolDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AppointmentPoolDTO.class);
        AppointmentPoolDTO appointmentPoolDTO1 = new AppointmentPoolDTO();
        appointmentPoolDTO1.setId(1L);
        AppointmentPoolDTO appointmentPoolDTO2 = new AppointmentPoolDTO();
        assertThat(appointmentPoolDTO1).isNotEqualTo(appointmentPoolDTO2);
        appointmentPoolDTO2.setId(appointmentPoolDTO1.getId());
        assertThat(appointmentPoolDTO1).isEqualTo(appointmentPoolDTO2);
        appointmentPoolDTO2.setId(2L);
        assertThat(appointmentPoolDTO1).isNotEqualTo(appointmentPoolDTO2);
        appointmentPoolDTO1.setId(null);
        assertThat(appointmentPoolDTO1).isNotEqualTo(appointmentPoolDTO2);
    }
}
