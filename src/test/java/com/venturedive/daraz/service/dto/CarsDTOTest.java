package com.venturedive.daraz.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.venturedive.daraz.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CarsDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CarsDTO.class);
        CarsDTO carsDTO1 = new CarsDTO();
        carsDTO1.setId(1L);
        CarsDTO carsDTO2 = new CarsDTO();
        assertThat(carsDTO1).isNotEqualTo(carsDTO2);
        carsDTO2.setId(carsDTO1.getId());
        assertThat(carsDTO1).isEqualTo(carsDTO2);
        carsDTO2.setId(2L);
        assertThat(carsDTO1).isNotEqualTo(carsDTO2);
        carsDTO1.setId(null);
        assertThat(carsDTO1).isNotEqualTo(carsDTO2);
    }
}
