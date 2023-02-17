package com.venturedive.daraz.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CarsMapperTest {

    private CarsMapper carsMapper;

    @BeforeEach
    public void setUp() {
        carsMapper = new CarsMapperImpl();
    }
}
