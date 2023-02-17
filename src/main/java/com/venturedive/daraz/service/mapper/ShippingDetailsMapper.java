package com.venturedive.daraz.service.mapper;

import com.venturedive.daraz.domain.Orders;
import com.venturedive.daraz.domain.ShippingDetails;
import com.venturedive.daraz.service.dto.OrdersDTO;
import com.venturedive.daraz.service.dto.ShippingDetailsDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ShippingDetails} and its DTO {@link ShippingDetailsDTO}.
 */
@Mapper(componentModel = "spring")
public interface ShippingDetailsMapper extends EntityMapper<ShippingDetailsDTO, ShippingDetails> {
    @Mapping(target = "order", source = "order", qualifiedByName = "ordersId")
    ShippingDetailsDTO toDto(ShippingDetails s);

    @Named("ordersId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    OrdersDTO toDtoOrdersId(Orders orders);
}
