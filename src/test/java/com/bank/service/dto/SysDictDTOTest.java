package com.bank.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.bank.web.rest.TestUtil;

public class SysDictDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysDictDTO.class);
        SysDictDTO sysDictDTO1 = new SysDictDTO();
        sysDictDTO1.setId(1L);
        SysDictDTO sysDictDTO2 = new SysDictDTO();
        assertThat(sysDictDTO1).isNotEqualTo(sysDictDTO2);
        sysDictDTO2.setId(sysDictDTO1.getId());
        assertThat(sysDictDTO1).isEqualTo(sysDictDTO2);
        sysDictDTO2.setId(2L);
        assertThat(sysDictDTO1).isNotEqualTo(sysDictDTO2);
        sysDictDTO1.setId(null);
        assertThat(sysDictDTO1).isNotEqualTo(sysDictDTO2);
    }
}
