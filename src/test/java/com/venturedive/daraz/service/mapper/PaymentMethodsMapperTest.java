package com.venturedive.daraz.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PaymentMethodsMapperTest {

    private PaymentMethodsMapper paymentMethodsMapper;

    @BeforeEach
    public void setUp() {
        paymentMethodsMapper = new PaymentMethodsMapperImpl();
    }
}
