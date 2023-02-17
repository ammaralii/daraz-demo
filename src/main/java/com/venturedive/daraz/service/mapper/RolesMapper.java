package com.venturedive.daraz.service.mapper;

import com.venturedive.daraz.domain.DarazUsers;
import com.venturedive.daraz.domain.Roles;
import com.venturedive.daraz.service.dto.DarazUsersDTO;
import com.venturedive.daraz.service.dto.RolesDTO;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Roles} and its DTO {@link RolesDTO}.
 */
@Mapper(componentModel = "spring")
public interface RolesMapper extends EntityMapper<RolesDTO, Roles> {
    @Mapping(target = "users", source = "users", qualifiedByName = "darazUsersIdSet")
    RolesDTO toDto(Roles s);

    @Mapping(target = "removeUser", ignore = true)
    Roles toEntity(RolesDTO rolesDTO);

    @Named("darazUsersId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    DarazUsersDTO toDtoDarazUsersId(DarazUsers darazUsers);

    @Named("darazUsersIdSet")
    default Set<DarazUsersDTO> toDtoDarazUsersIdSet(Set<DarazUsers> darazUsers) {
        return darazUsers.stream().map(this::toDtoDarazUsersId).collect(Collectors.toSet());
    }
}
