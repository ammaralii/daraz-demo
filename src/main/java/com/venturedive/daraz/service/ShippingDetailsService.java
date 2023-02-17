package com.venturedive.daraz.service;

import com.venturedive.daraz.domain.ShippingDetails;
import com.venturedive.daraz.repository.ShippingDetailsRepository;
import com.venturedive.daraz.service.dto.ShippingDetailsDTO;
import com.venturedive.daraz.service.mapper.ShippingDetailsMapper;
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

    private final ShippingDetailsMapper shippingDetailsMapper;

    public ShippingDetailsService(ShippingDetailsRepository shippingDetailsRepository, ShippingDetailsMapper shippingDetailsMapper) {
        this.shippingDetailsRepository = shippingDetailsRepository;
        this.shippingDetailsMapper = shippingDetailsMapper;
    }

    /**
     * Save a shippingDetails.
     *
     * @param shippingDetailsDTO the entity to save.
     * @return the persisted entity.
     */
    public ShippingDetailsDTO save(ShippingDetailsDTO shippingDetailsDTO) {
        log.debug("Request to save ShippingDetails : {}", shippingDetailsDTO);
        ShippingDetails shippingDetails = shippingDetailsMapper.toEntity(shippingDetailsDTO);
        shippingDetails = shippingDetailsRepository.save(shippingDetails);
        return shippingDetailsMapper.toDto(shippingDetails);
    }

    /**
     * Update a shippingDetails.
     *
     * @param shippingDetailsDTO the entity to save.
     * @return the persisted entity.
     */
    public ShippingDetailsDTO update(ShippingDetailsDTO shippingDetailsDTO) {
        log.debug("Request to update ShippingDetails : {}", shippingDetailsDTO);
        ShippingDetails shippingDetails = shippingDetailsMapper.toEntity(shippingDetailsDTO);
        shippingDetails = shippingDetailsRepository.save(shippingDetails);
        return shippingDetailsMapper.toDto(shippingDetails);
    }

    /**
     * Partially update a shippingDetails.
     *
     * @param shippingDetailsDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ShippingDetailsDTO> partialUpdate(ShippingDetailsDTO shippingDetailsDTO) {
        log.debug("Request to partially update ShippingDetails : {}", shippingDetailsDTO);

        return shippingDetailsRepository
            .findById(shippingDetailsDTO.getId())
            .map(existingShippingDetails -> {
                shippingDetailsMapper.partialUpdate(existingShippingDetails, shippingDetailsDTO);

                return existingShippingDetails;
            })
            .map(shippingDetailsRepository::save)
            .map(shippingDetailsMapper::toDto);
    }

    /**
     * Get all the shippingDetails.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ShippingDetailsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ShippingDetails");
        return shippingDetailsRepository.findAll(pageable).map(shippingDetailsMapper::toDto);
    }

    /**
     * Get one shippingDetails by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ShippingDetailsDTO> findOne(Long id) {
        log.debug("Request to get ShippingDetails : {}", id);
        return shippingDetailsRepository.findById(id).map(shippingDetailsMapper::toDto);
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
