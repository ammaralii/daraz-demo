package com.venturedive.daraz.service;

import com.venturedive.daraz.domain.ShippingDetails;
import com.venturedive.daraz.repository.ShippingDetailsRepository;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ShippingDetails}.
 */
@Service
@Transactional
public class ShippingDetailsService {

    private final Logger log = LoggerFactory.getLogger(ShippingDetailsService.class);

    private final ShippingDetailsRepository shippingDetailsRepository;

    public ShippingDetailsService(ShippingDetailsRepository shippingDetailsRepository) {
        this.shippingDetailsRepository = shippingDetailsRepository;
    }

    /**
     * Save a shippingDetails.
     *
     * @param shippingDetails the entity to save.
     * @return the persisted entity.
     */
    public ShippingDetails save(ShippingDetails shippingDetails) {
        log.debug("Request to save ShippingDetails : {}", shippingDetails);
        return shippingDetailsRepository.save(shippingDetails);
    }

    /**
     * Update a shippingDetails.
     *
     * @param shippingDetails the entity to save.
     * @return the persisted entity.
     */
    public ShippingDetails update(ShippingDetails shippingDetails) {
        log.debug("Request to update ShippingDetails : {}", shippingDetails);
        return shippingDetailsRepository.save(shippingDetails);
    }

    /**
     * Partially update a shippingDetails.
     *
     * @param shippingDetails the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ShippingDetails> partialUpdate(ShippingDetails shippingDetails) {
        log.debug("Request to partially update ShippingDetails : {}", shippingDetails);

        return shippingDetailsRepository
            .findById(shippingDetails.getId())
            .map(existingShippingDetails -> {
                if (shippingDetails.getShippingAddress() != null) {
                    existingShippingDetails.setShippingAddress(shippingDetails.getShippingAddress());
                }
                if (shippingDetails.getShippingMethod() != null) {
                    existingShippingDetails.setShippingMethod(shippingDetails.getShippingMethod());
                }
                if (shippingDetails.getEstimatedDeliveryDate() != null) {
                    existingShippingDetails.setEstimatedDeliveryDate(shippingDetails.getEstimatedDeliveryDate());
                }

                return existingShippingDetails;
            })
            .map(shippingDetailsRepository::save);
    }

    /**
     * Get all the shippingDetails.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ShippingDetails> findAll(Pageable pageable) {
        log.debug("Request to get all ShippingDetails");
        return shippingDetailsRepository.findAll(pageable);
    }

    /**
     * Get one shippingDetails by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ShippingDetails> findOne(Long id) {
        log.debug("Request to get ShippingDetails : {}", id);
        return shippingDetailsRepository.findById(id);
    }

    /**
     * Delete the shippingDetails by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ShippingDetails : {}", id);
        shippingDetailsRepository.deleteById(id);
    }
}
