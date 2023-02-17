package com.venturedive.daraz.service.mapper;

import com.venturedive.daraz.domain.Addresses;
import com.venturedive.daraz.domain.DarazUsers;
import com.venturedive.daraz.service.dto.AddressesDTO;
import com.venturedive.daraz.service.dto.DarazUsersDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Addresses} and its DTO {@link AddressesDTO}.
 */
@Mapper(componentModel = "spring")
public interface AddressesMapper extends EntityMapper<AddressesDTO, Addresses> {
    @Mapping(target = "user", source = "user", qualifiedByName = "darazUsersId")
    AddressesDTO toDto(Addresses s);

    @Named("darazUsersId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    DarazUsersDTO toDtoDarazUsersId(DarazUsers darazUsers);
}
