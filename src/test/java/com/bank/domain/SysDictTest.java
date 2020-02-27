package com.bank.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.bank.web.rest.TestUtil;

public class SysDictTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysDict.class);
        SysDict sysDict1 = new SysDict();
        sysDict1.setId(1L);
        SysDict sysDict2 = new SysDict();
        sysDict2.setId(sysDict1.getId());
        assertThat(sysDict1).isEqualTo(sysDict2);
        sysDict2.setId(2L);
        assertThat(sysDict1).isNotEqualTo(sysDict2);
        sysDict1.setId(null);
        assertThat(sysDict1).isNotEqualTo(sysDict2);
    }
}
