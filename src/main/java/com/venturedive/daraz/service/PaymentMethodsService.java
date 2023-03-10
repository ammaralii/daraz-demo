package com.venturedive.daraz.service;

import com.venturedive.daraz.domain.PaymentMethods;
import com.venturedive.daraz.repository.PaymentMethodsRepository;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link PaymentMethods}.
 */
@Service
@Transactional
public class PaymentMethodsService {

    private final Logger log = LoggerFactory.getLogger(PaymentMethodsService.class);

    private final PaymentMethodsRepository paymentMethodsRepository;

    public PaymentMethodsService(PaymentMethodsRepository paymentMethodsRepository) {
        this.paymentMethodsRepository = paymentMethodsRepository;
    }

    /**
     * Save a paymentMethods.
     *
     * @param paymentMethods the entity to save.
     * @return the persisted entity.
     */
    public PaymentMethods save(PaymentMethods paymentMethods) {
        log.debug("Request to save PaymentMethods : {}", paymentMethods);
        return paymentMethodsRepository.save(paymentMethods);
    }

    /**
     * Update a paymentMethods.
     *
     * @param paymentMethods the entity to save.
     * @return the persisted entity.
     */
    public PaymentMethods update(PaymentMethods paymentMethods) {
        log.debug("Request to update PaymentMethods : {}", paymentMethods);
        return paymentMethodsRepository.save(paymentMethods);
    }

    /**
     * Partially update a paymentMethods.
     *
     * @param paymentMethods the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<PaymentMethods> partialUpdate(PaymentMethods paymentMethods) {
        log.debug("Request to partially update PaymentMethods : {}", paymentMethods);

        return paymentMethodsRepository
            .findById(paymentMethods.getId())
            .map(existingPaymentMethods -> {
                if (paymentMethods.getCardNumber() != null) {
                    existingPaymentMethods.setCardNumber(paymentMethods.getCardNumber());
                }
                if (paymentMethods.getCardHolderName() != null) {
                    existingPaymentMethods.setCardHolderName(paymentMethods.getCardHolderName());
                }
                if (paymentMethods.getExpirationDate() != null) {
                    existingPaymentMethods.setExpirationDate(paymentMethods.getExpirationDate());
                }

                return existingPaymentMethods;
            })
            .map(paymentMethodsRepository::save);
    }

    /**
     * Get all the paymentMethods.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<PaymentMethods> findAll(Pageable pageable) {
        log.debug("Request to get all PaymentMethods");
        return paymentMethodsRepository.findAll(pageable);
    }

    /**
     * Get one paymentMethods by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PaymentMethods> findOne(Long id) {
        log.debug("Request to get PaymentMethods : {}", id);
        return paymentMethodsRepository.findById(id);
    }

    /**
     * Delete the paymentMethods by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete PaymentMethods : {}", id);
        paymentMethodsRepository.deleteById(id);
    }
}
