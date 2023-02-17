package com.venturedive.daraz.service.mapper;

import com.venturedive.daraz.domain.Customers;
import com.venturedive.daraz.domain.Orders;
import com.venturedive.daraz.service.dto.CustomersDTO;
import com.venturedive.daraz.service.dto.OrdersDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Orders} and its DTO {@link OrdersDTO}.
 */
@Mapper(componentModel = "spring")
public interface OrdersMapper extends EntityMapper<OrdersDTO, Orders> {
    @Mapping(target = "customer", source = "customer", qualifiedByName = "customersId")
    OrdersDTO toDto(Orders s);

    @Named("customersId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CustomersDTO toDtoCustomersId(Customers customers);
}
