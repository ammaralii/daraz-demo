package com.venturedive.daraz.service.mapper;

import com.venturedive.daraz.domain.Customers;
import com.venturedive.daraz.service.dto.CustomersDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Customers} and its DTO {@link CustomersDTO}.
 */
@Mapper(componentModel = "spring")
public interface CustomersMapper extends EntityMapper<CustomersDTO, Customers> {}
