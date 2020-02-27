package com.bank.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class BlackKeyMapperTest {

    private BlackKeyMapper blackKeyMapper;

    @BeforeEach
    public void setUp() {
        blackKeyMapper = new BlackKeyMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(blackKeyMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(blackKeyMapper.fromId(null)).isNull();
    }
}
