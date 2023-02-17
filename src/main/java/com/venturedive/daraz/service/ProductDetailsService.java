package com.venturedive.daraz.service;

import com.venturedive.daraz.domain.ProductDetails;
import com.venturedive.daraz.repository.ProductDetailsRepository;
import com.venturedive.daraz.service.dto.ProductDetailsDTO;
import com.venturedive.daraz.service.mapper.ProductDetailsMapper;
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

    private final ProductDetailsMapper productDetailsMapper;

    public ProductDetailsService(ProductDetailsRepository productDetailsRepository, ProductDetailsMapper productDetailsMapper) {
        this.productDetailsRepository = productDetailsRepository;
        this.productDetailsMapper = productDetailsMapper;
    }

    /**
     * Save a productDetails.
     *
     * @param productDetailsDTO the entity to save.
     * @return the persisted entity.
     */
    public ProductDetailsDTO save(ProductDetailsDTO productDetailsDTO) {
        log.debug("Request to save ProductDetails : {}", productDetailsDTO);
        ProductDetails productDetails = productDetailsMapper.toEntity(productDetailsDTO);
        productDetails = productDetailsRepository.save(productDetails);
        return productDetailsMapper.toDto(productDetails);
    }

    /**
     * Update a productDetails.
     *
     * @param productDetailsDTO the entity to save.
     * @return the persisted entity.
     */
    public ProductDetailsDTO update(ProductDetailsDTO productDetailsDTO) {
        log.debug("Request to update ProductDetails : {}", productDetailsDTO);
        ProductDetails productDetails = productDetailsMapper.toEntity(productDetailsDTO);
        productDetails = productDetailsRepository.save(productDetails);
        return productDetailsMapper.toDto(productDetails);
    }

    /**
     * Partially update a productDetails.
     *
     * @param productDetailsDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ProductDetailsDTO> partialUpdate(ProductDetailsDTO productDetailsDTO) {
        log.debug("Request to partially update ProductDetails : {}", productDetailsDTO);

        return productDetailsRepository
            .findById(productDetailsDTO.getId())
            .map(existingProductDetails -> {
                productDetailsMapper.partialUpdate(existingProductDetails, productDetailsDTO);

                return existingProductDetails;
            })
            .map(productDetailsRepository::save)
            .map(productDetailsMapper::toDto);
    }

    /**
     * Get all the productDetails.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ProductDetailsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ProductDetails");
        return productDetailsRepository.findAll(pageable).map(productDetailsMapper::toDto);
    }

    /**
     * Get one productDetails by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ProductDetailsDTO> findOne(Long id) {
        log.debug("Request to get ProductDetails : {}", id);
        return productDetailsRepository.findById(id).map(productDetailsMapper::toDto);
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
