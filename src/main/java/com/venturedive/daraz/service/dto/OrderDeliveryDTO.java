package com.venturedive.daraz.service.dto;

import com.venturedive.daraz.domain.enumeration.ShippingStatus;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.venturedive.daraz.domain.OrderDelivery} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OrderDeliveryDTO implements Serializable {

    private Long id;

    private LocalDate deliveryDate;

    private Double deliveryCharge;

    @NotNull
    private ShippingStatus shippingStatus;

    private OrdersDTO order;

    private ShippingDetailsDTO shippingAddress;

    private DarazUsersDTO deliveryManager;

    private DarazUsersDTO deliveryBoy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Double getDeliveryCharge() {
        return deliveryCharge;
    }

    public void setDeliveryCharge(Double deliveryCharge) {
        this.deliveryCharge = deliveryCharge;
    }

    public ShippingStatus getShippingStatus() {
        return shippingStatus;
    }

    public void setShippingStatus(ShippingStatus shippingStatus) {
        this.shippingStatus = shippingStatus;
    }

    public OrdersDTO getOrder() {
        return order;
    }

    public void setOrder(OrdersDTO order) {
        this.order = order;
    }

    public ShippingDetailsDTO getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(ShippingDetailsDTO shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public DarazUsersDTO getDeliveryManager() {
        return deliveryManager;
    }

    public void setDeliveryManager(DarazUsersDTO deliveryManager) {
        this.deliveryManager = deliveryManager;
    }

    public DarazUsersDTO getDeliveryBoy() {
        return deliveryBoy;
    }

    public void setDeliveryBoy(DarazUsersDTO deliveryBoy) {
        this.deliveryBoy = deliveryBoy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrderDeliveryDTO)) {
            return false;
        }

        OrderDeliveryDTO orderDeliveryDTO = (OrderDeliveryDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, orderDeliveryDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OrderDeliveryDTO{" +
            "id=" + getId() +
            ", deliveryDate='" + getDeliveryDate() + "'" +
            ", deliveryCharge=" + getDeliveryCharge() +
            ", shippingStatus='" + getShippingStatus() + "'" +
            ", order=" + getOrder() +
            ", shippingAddress=" + getShippingAddress() +
            ", deliveryManager=" + getDeliveryManager() +
            ", deliveryBoy=" + getDeliveryBoy() +
            "}";
    }
}
