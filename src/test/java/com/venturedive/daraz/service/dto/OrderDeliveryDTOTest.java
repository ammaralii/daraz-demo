package com.venturedive.daraz.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.venturedive.daraz.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OrderDeliveryDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(OrderDeliveryDTO.class);
        OrderDeliveryDTO orderDeliveryDTO1 = new OrderDeliveryDTO();
        orderDeliveryDTO1.setId(1L);
        OrderDeliveryDTO orderDeliveryDTO2 = new OrderDeliveryDTO();
        assertThat(orderDeliveryDTO1).isNotEqualTo(orderDeliveryDTO2);
        orderDeliveryDTO2.setId(orderDeliveryDTO1.getId());
        assertThat(orderDeliveryDTO1).isEqualTo(orderDeliveryDTO2);
        orderDeliveryDTO2.setId(2L);
        assertThat(orderDeliveryDTO1).isNotEqualTo(orderDeliveryDTO2);
        orderDeliveryDTO1.setId(null);
        assertThat(orderDeliveryDTO1).isNotEqualTo(orderDeliveryDTO2);
    }
}
