package com.venturedive.daraz.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DarazUsersMapperTest {

    private DarazUsersMapper darazUsersMapper;

    @BeforeEach
    public void setUp() {
        darazUsersMapper = new DarazUsersMapperImpl();
    }
}
