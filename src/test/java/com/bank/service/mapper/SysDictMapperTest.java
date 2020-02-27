package com.bank.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SysDictMapperTest {

    private SysDictMapper sysDictMapper;

    @BeforeEach
    public void setUp() {
        sysDictMapper = new SysDictMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(sysDictMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(sysDictMapper.fromId(null)).isNull();
    }
}
