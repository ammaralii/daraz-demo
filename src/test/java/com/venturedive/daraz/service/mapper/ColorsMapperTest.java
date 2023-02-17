package com.venturedive.daraz.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ColorsMapperTest {

    private ColorsMapper colorsMapper;

    @BeforeEach
    public void setUp() {
        colorsMapper = new ColorsMapperImpl();
    }
}
