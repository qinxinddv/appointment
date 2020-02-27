package com.bank.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CommunityMapperTest {

    private CommunityMapper communityMapper;

    @BeforeEach
    public void setUp() {
        communityMapper = new CommunityMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(communityMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(communityMapper.fromId(null)).isNull();
    }
}
