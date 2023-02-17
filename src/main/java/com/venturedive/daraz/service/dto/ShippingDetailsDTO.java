package com.venturedive.daraz.service.dto;

import com.venturedive.daraz.domain.enumeration.ShippingMethod;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.venturedive.daraz.domain.ShippingDetails} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ShippingDetailsDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 32)
    private String shippingAddress;

    @NotNull
    private ShippingMethod shippingMethod;

    @NotNull
    private LocalDate estimatedDeliveryDate;

    private OrdersDTO order;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public ShippingMethod getShippingMethod() {
        return shippingMethod;
    }

    public void setShippingMethod(ShippingMethod shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    public LocalDate getEstimatedDeliveryDate() {
        return estimatedDeliveryDate;
    }

    public void setEstimatedDeliveryDate(LocalDate estimatedDeliveryDate) {
        this.estimatedDeliveryDate = estimatedDeliveryDate;
    }

    public OrdersDTO getOrder() {
        return order;
    }

    public void setOrder(OrdersDTO order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ShippingDetailsDTO)) {
            return false;
        }

        ShippingDetailsDTO shippingDetailsDTO = (ShippingDetailsDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, shippingDetailsDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ShippingDetailsDTO{" +
            "id=" + getId() +
            ", shippingAddress='" + getShippingAddress() + "'" +
            ", shippingMethod='" + getShippingMethod() + "'" +
            ", estimatedDeliveryDate='" + getEstimatedDeliveryDate() + "'" +
            ", order=" + getOrder() +
            "}";
    }
}
