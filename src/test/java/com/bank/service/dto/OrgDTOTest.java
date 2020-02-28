package com.bank.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.bank.web.rest.TestUtil;

public class OrgDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(OrgDTO.class);
        OrgDTO orgDTO1 = new OrgDTO();
        orgDTO1.setId(1L);
        OrgDTO orgDTO2 = new OrgDTO();
        assertThat(orgDTO1).isNotEqualTo(orgDTO2);
        orgDTO2.setId(orgDTO1.getId());
        assertThat(orgDTO1).isEqualTo(orgDTO2);
        orgDTO2.setId(2L);
        assertThat(orgDTO1).isNotEqualTo(orgDTO2);
        orgDTO1.setId(null);
        assertThat(orgDTO1).isNotEqualTo(orgDTO2);
    }
}
