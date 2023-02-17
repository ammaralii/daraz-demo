package com.venturedive.daraz.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.venturedive.daraz.domain.DarazUsers} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DarazUsersDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 100)
    private String fullName;

    @NotNull
    @Size(max = 255)
    private String email;

    @NotNull
    @Size(max = 255)
    private String phone;

    private DarazUsersDTO manager;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public DarazUsersDTO getManager() {
        return manager;
    }

    public void setManager(DarazUsersDTO manager) {
        this.manager = manager;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DarazUsersDTO)) {
            return false;
        }

        DarazUsersDTO darazUsersDTO = (DarazUsersDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, darazUsersDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DarazUsersDTO{" +
            "id=" + getId() +
            ", fullName='" + getFullName() + "'" +
            ", email='" + getEmail() + "'" +
            ", phone='" + getPhone() + "'" +
            ", manager=" + getManager() +
            "}";
    }
}
