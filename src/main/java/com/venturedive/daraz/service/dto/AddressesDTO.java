package com.venturedive.daraz.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.venturedive.daraz.domain.Addresses} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class AddressesDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 30)
    private String street;

    @NotNull
    @Size(max = 30)
    private String city;

    @NotNull
    @Size(max = 30)
    private String state;

    private DarazUsersDTO user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public DarazUsersDTO getUser() {
        return user;
    }

    public void setUser(DarazUsersDTO user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AddressesDTO)) {
            return false;
        }

        AddressesDTO addressesDTO = (AddressesDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, addressesDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AddressesDTO{" +
            "id=" + getId() +
            ", street='" + getStreet() + "'" +
            ", city='" + getCity() + "'" +
            ", state='" + getState() + "'" +
            ", user=" + getUser() +
            "}";
    }
}
