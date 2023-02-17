package com.venturedive.daraz.service.mapper;

import com.venturedive.daraz.domain.DarazUsers;
import com.venturedive.daraz.domain.OrderDelivery;
import com.venturedive.daraz.domain.Orders;
import com.venturedive.daraz.domain.ShippingDetails;
import com.venturedive.daraz.service.dto.DarazUsersDTO;
import com.venturedive.daraz.service.dto.OrderDeliveryDTO;
import com.venturedive.daraz.service.dto.OrdersDTO;
import com.venturedive.daraz.service.dto.ShippingDetailsDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link OrderDelivery} and its DTO {@link OrderDeliveryDTO}.
 */
@Mapper(componentModel = "spring")
public interface OrderDeliveryMapper extends EntityMapper<OrderDeliveryDTO, OrderDelivery> {
    @Mapping(target = "order", source = "order", qualifiedByName = "ordersId")
    @Mapping(target = "shippingAddress", source = "shippingAddress", qualifiedByName = "shippingDetailsId")
    @Mapping(target = "deliveryManager", source = "deliveryManager", qualifiedByName = "darazUsersId")
    @Mapping(target = "deliveryBoy", source = "deliveryBoy", qualifiedByName = "darazUsersId")
    OrderDeliveryDTO toDto(OrderDelivery s);

    @Named("ordersId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    OrdersDTO toDtoOrdersId(Orders orders);

    @Named("shippingDetailsId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ShippingDetailsDTO toDtoShippingDetailsId(ShippingDetails shippingDetails);

    @Named("darazUsersId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    DarazUsersDTO toDtoDarazUsersId(DarazUsers darazUsers);
}
