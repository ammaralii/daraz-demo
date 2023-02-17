package com.venturedive.daraz.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ShippingDetailsMapperTest {

    private ShippingDetailsMapper shippingDetailsMapper;

    @BeforeEach
    public void setUp() {
        shippingDetailsMapper = new ShippingDetailsMapperImpl();
    }
}
