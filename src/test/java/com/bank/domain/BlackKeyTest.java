package com.bank.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.bank.web.rest.TestUtil;

public class BlackKeyTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BlackKey.class);
        BlackKey blackKey1 = new BlackKey();
        blackKey1.setId(1L);
        BlackKey blackKey2 = new BlackKey();
        blackKey2.setId(blackKey1.getId());
        assertThat(blackKey1).isEqualTo(blackKey2);
        blackKey2.setId(2L);
        assertThat(blackKey1).isNotEqualTo(blackKey2);
        blackKey1.setId(null);
        assertThat(blackKey1).isNotEqualTo(blackKey2);
    }
}
