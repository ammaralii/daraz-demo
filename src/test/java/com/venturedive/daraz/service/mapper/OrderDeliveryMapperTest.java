package com.venturedive.daraz.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OrderDeliveryMapperTest {

    private OrderDeliveryMapper orderDeliveryMapper;

    @BeforeEach
    public void setUp() {
        orderDeliveryMapper = new OrderDeliveryMapperImpl();
    }
}
