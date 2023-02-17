package com.venturedive.daraz.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.venturedive.daraz.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ColorsDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ColorsDTO.class);
        ColorsDTO colorsDTO1 = new ColorsDTO();
        colorsDTO1.setId(1L);
        ColorsDTO colorsDTO2 = new ColorsDTO();
        assertThat(colorsDTO1).isNotEqualTo(colorsDTO2);
        colorsDTO2.setId(colorsDTO1.getId());
        assertThat(colorsDTO1).isEqualTo(colorsDTO2);
        colorsDTO2.setId(2L);
        assertThat(colorsDTO1).isNotEqualTo(colorsDTO2);
        colorsDTO1.setId(null);
        assertThat(colorsDTO1).isNotEqualTo(colorsDTO2);
    }
}
