package com.bank.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.bank.web.rest.TestUtil;

public class BlackKeyDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BlackKeyDTO.class);
        BlackKeyDTO blackKeyDTO1 = new BlackKeyDTO();
        blackKeyDTO1.setId(1L);
        BlackKeyDTO blackKeyDTO2 = new BlackKeyDTO();
        assertThat(blackKeyDTO1).isNotEqualTo(blackKeyDTO2);
        blackKeyDTO2.setId(blackKeyDTO1.getId());
        assertThat(blackKeyDTO1).isEqualTo(blackKeyDTO2);
        blackKeyDTO2.setId(2L);
        assertThat(blackKeyDTO1).isNotEqualTo(blackKeyDTO2);
        blackKeyDTO1.setId(null);
        assertThat(blackKeyDTO1).isNotEqualTo(blackKeyDTO2);
    }
}
