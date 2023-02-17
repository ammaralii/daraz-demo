package com.venturedive.daraz.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.venturedive.daraz.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DarazUsersDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DarazUsersDTO.class);
        DarazUsersDTO darazUsersDTO1 = new DarazUsersDTO();
        darazUsersDTO1.setId(1L);
        DarazUsersDTO darazUsersDTO2 = new DarazUsersDTO();
        assertThat(darazUsersDTO1).isNotEqualTo(darazUsersDTO2);
        darazUsersDTO2.setId(darazUsersDTO1.getId());
        assertThat(darazUsersDTO1).isEqualTo(darazUsersDTO2);
        darazUsersDTO2.setId(2L);
        assertThat(darazUsersDTO1).isNotEqualTo(darazUsersDTO2);
        darazUsersDTO1.setId(null);
        assertThat(darazUsersDTO1).isNotEqualTo(darazUsersDTO2);
    }
}
