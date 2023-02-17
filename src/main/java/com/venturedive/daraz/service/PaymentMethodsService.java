package com.venturedive.daraz.service;

import com.venturedive.daraz.domain.PaymentMethods;
import com.venturedive.daraz.repository.PaymentMethodsRepository;
import com.venturedive.daraz.service.dto.PaymentMethodsDTO;
import com.venturedive.daraz.service.mapper.PaymentMethodsMapper;
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

    private final PaymentMethodsMapper paymentMethodsMapper;

    public PaymentMethodsService(PaymentMethodsRepository paymentMethodsRepository, PaymentMethodsMapper paymentMethodsMapper) {
        this.paymentMethodsRepository = paymentMethodsRepository;
        this.paymentMethodsMapper = paymentMethodsMapper;
    }

    /**
     * Save a paymentMethods.
     *
     * @param paymentMethodsDTO the entity to save.
     * @return the persisted entity.
     */
    public PaymentMethodsDTO save(PaymentMethodsDTO paymentMethodsDTO) {
        log.debug("Request to save PaymentMethods : {}", paymentMethodsDTO);
        PaymentMethods paymentMethods = paymentMethodsMapper.toEntity(paymentMethodsDTO);
        paymentMethods = paymentMethodsRepository.save(paymentMethods);
        return paymentMethodsMapper.toDto(paymentMethods);
    }

    /**
     * Update a paymentMethods.
     *
     * @param paymentMethodsDTO the entity to save.
     * @return the persisted entity.
     */
    public PaymentMethodsDTO update(PaymentMethodsDTO paymentMethodsDTO) {
        log.debug("Request to update PaymentMethods : {}", paymentMethodsDTO);
        PaymentMethods paymentMethods = paymentMethodsMapper.toEntity(paymentMethodsDTO);
        paymentMethods = paymentMethodsRepository.save(paymentMethods);
        return paymentMethodsMapper.toDto(paymentMethods);
    }

    /**
     * Partially update a paymentMethods.
     *
     * @param paymentMethodsDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<PaymentMethodsDTO> partialUpdate(PaymentMethodsDTO paymentMethodsDTO) {
        log.debug("Request to partially update PaymentMethods : {}", paymentMethodsDTO);

        return paymentMethodsRepository
            .findById(paymentMethodsDTO.getId())
            .map(existingPaymentMethods -> {
                paymentMethodsMapper.partialUpdate(existingPaymentMethods, paymentMethodsDTO);

                return existingPaymentMethods;
            })
            .map(paymentMethodsRepository::save)
            .map(paymentMethodsMapper::toDto);
    }

    /**
     * Get all the paymentMethods.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<PaymentMethodsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PaymentMethods");
        return paymentMethodsRepository.findAll(pageable).map(paymentMethodsMapper::toDto);
    }

    /**
     * Get one paymentMethods by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PaymentMethodsDTO> findOne(Long id) {
        log.debug("Request to get PaymentMethods : {}", id);
        return paymentMethodsRepository.findById(id).map(paymentMethodsMapper::toDto);
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
