package com.venturedive.daraz.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.venturedive.daraz.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ShippingDetailsDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ShippingDetailsDTO.class);
        ShippingDetailsDTO shippingDetailsDTO1 = new ShippingDetailsDTO();
        shippingDetailsDTO1.setId(1L);
        ShippingDetailsDTO shippingDetailsDTO2 = new ShippingDetailsDTO();
        assertThat(shippingDetailsDTO1).isNotEqualTo(shippingDetailsDTO2);
        shippingDetailsDTO2.setId(shippingDetailsDTO1.getId());
        assertThat(shippingDetailsDTO1).isEqualTo(shippingDetailsDTO2);
        shippingDetailsDTO2.setId(2L);
        assertThat(shippingDetailsDTO1).isNotEqualTo(shippingDetailsDTO2);
        shippingDetailsDTO1.setId(null);
        assertThat(shippingDetailsDTO1).isNotEqualTo(shippingDetailsDTO2);
    }
}
