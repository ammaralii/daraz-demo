package com.venturedive.daraz.service.mapper;

import com.venturedive.daraz.domain.DarazUsers;
import com.venturedive.daraz.service.dto.DarazUsersDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link DarazUsers} and its DTO {@link DarazUsersDTO}.
 */
@Mapper(componentModel = "spring")
public interface DarazUsersMapper extends EntityMapper<DarazUsersDTO, DarazUsers> {
    @Mapping(target = "manager", source = "manager", qualifiedByName = "darazUsersId")
    DarazUsersDTO toDto(DarazUsers s);

    @Named("darazUsersId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    DarazUsersDTO toDtoDarazUsersId(DarazUsers darazUsers);
}
