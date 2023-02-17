package com.venturedive.daraz.service.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.venturedive.daraz.domain.Colors} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ColorsDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer coloruid;

    @NotNull
    @Size(max = 100)
    private String name;

    private Set<CarsDTO> cars = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getColoruid() {
        return coloruid;
    }

    public void setColoruid(Integer coloruid) {
        this.coloruid = coloruid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<CarsDTO> getCars() {
        return cars;
    }

    public void setCars(Set<CarsDTO> cars) {
        this.cars = cars;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ColorsDTO)) {
            return false;
        }

        ColorsDTO colorsDTO = (ColorsDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, colorsDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ColorsDTO{" +
            "id=" + getId() +
            ", coloruid=" + getColoruid() +
            ", name='" + getName() + "'" +
            ", cars=" + getCars() +
            "}";
    }
}
