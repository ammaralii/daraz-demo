package com.venturedive.daraz.service.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.venturedive.daraz.domain.Roles} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class RolesDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer rolePrId;

    @NotNull
    @Size(max = 30)
    private String name;

    private Set<DarazUsersDTO> users = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRolePrId() {
        return rolePrId;
    }

    public void setRolePrId(Integer rolePrId) {
        this.rolePrId = rolePrId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<DarazUsersDTO> getUsers() {
        return users;
    }

    public void setUsers(Set<DarazUsersDTO> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RolesDTO)) {
            return false;
        }

        RolesDTO rolesDTO = (RolesDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, rolesDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RolesDTO{" +
            "id=" + getId() +
            ", rolePrId=" + getRolePrId() +
            ", name='" + getName() + "'" +
            ", users=" + getUsers() +
            "}";
    }
}
