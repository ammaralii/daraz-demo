package com.venturedive.daraz.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.venturedive.daraz.domain.PaymentMethods} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PaymentMethodsDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 32)
    private String cardNumber;

    @NotNull
    @Size(max = 32)
    private String cardHolderName;

    @NotNull
    @Size(max = 10)
    private String expirationDate;

    private CustomersDTO customer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public CustomersDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomersDTO customer) {
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PaymentMethodsDTO)) {
            return false;
        }

        PaymentMethodsDTO paymentMethodsDTO = (PaymentMethodsDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, paymentMethodsDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PaymentMethodsDTO{" +
            "id=" + getId() +
            ", cardNumber='" + getCardNumber() + "'" +
            ", cardHolderName='" + getCardHolderName() + "'" +
            ", expirationDate='" + getExpirationDate() + "'" +
            ", customer=" + getCustomer() +
            "}";
    }
}
