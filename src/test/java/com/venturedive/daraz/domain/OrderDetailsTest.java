package com.venturedive.daraz.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.venturedive.daraz.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OrderDetailsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OrderDetails.class);
        OrderDetails orderDetails1 = new OrderDetails();
        orderDetails1.setId(1L);
        OrderDetails orderDetails2 = new OrderDetails();
        orderDetails2.setId(orderDetails1.getId());
        assertThat(orderDetails1).isEqualTo(orderDetails2);
        orderDetails2.setId(2L);
        assertThat(orderDetails1).isNotEqualTo(orderDetails2);
        orderDetails1.setId(null);
        assertThat(orderDetails1).isNotEqualTo(orderDetails2);
    }
}
