package com.venturedive.daraz.service.mapper;

import com.venturedive.daraz.domain.OrderDetails;
import com.venturedive.daraz.domain.Orders;
import com.venturedive.daraz.domain.Products;
import com.venturedive.daraz.service.dto.OrderDetailsDTO;
import com.venturedive.daraz.service.dto.OrdersDTO;
import com.venturedive.daraz.service.dto.ProductsDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link OrderDetails} and its DTO {@link OrderDetailsDTO}.
 */
@Mapper(componentModel = "spring")
public interface OrderDetailsMapper extends EntityMapper<OrderDetailsDTO, OrderDetails> {
    @Mapping(target = "order", source = "order", qualifiedByName = "ordersId")
    @Mapping(target = "product", source = "product", qualifiedByName = "productsId")
    OrderDetailsDTO toDto(OrderDetails s);

    @Named("ordersId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    OrdersDTO toDtoOrdersId(Orders orders);

    @Named("productsId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ProductsDTO toDtoProductsId(Products products);
}
