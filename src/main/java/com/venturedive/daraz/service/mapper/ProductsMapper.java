package com.venturedive.daraz.service.mapper;

import com.venturedive.daraz.domain.Categories;
import com.venturedive.daraz.domain.Products;
import com.venturedive.daraz.service.dto.CategoriesDTO;
import com.venturedive.daraz.service.dto.ProductsDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Products} and its DTO {@link ProductsDTO}.
 */
@Mapper(componentModel = "spring")
public interface ProductsMapper extends EntityMapper<ProductsDTO, Products> {
    @Mapping(target = "category", source = "category", qualifiedByName = "categoriesId")
    ProductsDTO toDto(Products s);

    @Named("categoriesId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CategoriesDTO toDtoCategoriesId(Categories categories);
}
