package com.venturedive.daraz.service.mapper;

import com.venturedive.daraz.domain.Customers;
import com.venturedive.daraz.domain.PaymentMethods;
import com.venturedive.daraz.service.dto.CustomersDTO;
import com.venturedive.daraz.service.dto.PaymentMethodsDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link PaymentMethods} and its DTO {@link PaymentMethodsDTO}.
 */
@Mapper(componentModel = "spring")
public interface PaymentMethodsMapper extends EntityMapper<PaymentMethodsDTO, PaymentMethods> {
    @Mapping(target = "customer", source = "customer", qualifiedByName = "customersId")
    PaymentMethodsDTO toDto(PaymentMethods s);

    @Named("customersId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CustomersDTO toDtoCustomersId(Customers customers);
}
