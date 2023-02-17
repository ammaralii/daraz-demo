package com.venturedive.daraz.service;

import com.venturedive.daraz.domain.ProductDetails;
import com.venturedive.daraz.repository.ProductDetailsRepository;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ProductDetails}.
 */
@Service
@Transactional
public class ProductDetailsService {

    private final Logger log = LoggerFactory.getLogger(ProductDetailsService.class);

    private final ProductDetailsRepository productDetailsRepository;

    public ProductDetailsService(ProductDetailsRepository productDetailsRepository) {
        this.productDetailsRepository = productDetailsRepository;
    }

    /**
     * Save a productDetails.
     *
     * @param productDetails the entity to save.
     * @return the persisted entity.
     */
    public ProductDetails save(ProductDetails productDetails) {
        log.debug("Request to save ProductDetails : {}", productDetails);
        return productDetailsRepository.save(productDetails);
    }

    /**
     * Update a productDetails.
     *
     * @param productDetails the entity to save.
     * @return the persisted entity.
     */
    public ProductDetails update(ProductDetails productDetails) {
        log.debug("Request to update ProductDetails : {}", productDetails);
        return productDetailsRepository.save(productDetails);
    }

    /**
     * Partially update a productDetails.
     *
     * @param productDetails the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ProductDetails> partialUpdate(ProductDetails productDetails) {
        log.debug("Request to partially update ProductDetails : {}", productDetails);

        return productDetailsRepository
            .findById(productDetails.getId())
            .map(existingProductDetails -> {
                if (productDetails.getDescription() != null) {
                    existingProductDetails.setDescription(productDetails.getDescription());
                }
                if (productDetails.getImageUrl() != null) {
                    existingProductDetails.setImageUrl(productDetails.getImageUrl());
                }
                if (productDetails.getIsavailable() != null) {
                    existingProductDetails.setIsavailable(productDetails.getIsavailable());
                }

                return existingProductDetails;
            })
            .map(productDetailsRepository::save);
    }

    /**
     * Get all the productDetails.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ProductDetails> findAll(Pageable pageable) {
        log.debug("Request to get all ProductDetails");
        return productDetailsRepository.findAll(pageable);
    }

    /**
     * Get one productDetails by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ProductDetails> findOne(Long id) {
        log.debug("Request to get ProductDetails : {}", id);
        return productDetailsRepository.findById(id);
    }

    /**
     * Delete the productDetails by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ProductDetails : {}", id);
        productDetailsRepository.deleteById(id);
    }
}
