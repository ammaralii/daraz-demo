package com.venturedive.daraz.service.mapper;

import com.venturedive.daraz.domain.ProductDetails;
import com.venturedive.daraz.domain.Products;
import com.venturedive.daraz.service.dto.ProductDetailsDTO;
import com.venturedive.daraz.service.dto.ProductsDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ProductDetails} and its DTO {@link ProductDetailsDTO}.
 */
@Mapper(componentModel = "spring")
public interface ProductDetailsMapper extends EntityMapper<ProductDetailsDTO, ProductDetails> {
    @Mapping(target = "product", source = "product", qualifiedByName = "productsId")
    ProductDetailsDTO toDto(ProductDetails s);

    @Named("productsId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ProductsDTO toDtoProductsId(Products products);
}
