package com.venturedive.daraz.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.venturedive.daraz.domain.Cars} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CarsDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer caruid;

    @NotNull
    @Size(max = 100)
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCaruid() {
        return caruid;
    }

    public void setCaruid(Integer caruid) {
        this.caruid = caruid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CarsDTO)) {
            return false;
        }

        CarsDTO carsDTO = (CarsDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, carsDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CarsDTO{" +
            "id=" + getId() +
            ", caruid=" + getCaruid() +
            ", name='" + getName() + "'" +
            "}";
    }
}
