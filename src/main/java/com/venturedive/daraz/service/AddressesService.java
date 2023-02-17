package com.venturedive.daraz.service;

import com.venturedive.daraz.domain.Addresses;
import com.venturedive.daraz.repository.AddressesRepository;
import com.venturedive.daraz.service.dto.AddressesDTO;
import com.venturedive.daraz.service.mapper.AddressesMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Addresses}.
 */
@Service
@Transactional
public class AddressesService {

    private final Logger log = LoggerFactory.getLogger(AddressesService.class);

    private final AddressesRepository addressesRepository;

    private final AddressesMapper addressesMapper;

    public AddressesService(AddressesRepository addressesRepository, AddressesMapper addressesMapper) {
        this.addressesRepository = addressesRepository;
        this.addressesMapper = addressesMapper;
    }

    /**
     * Save a addresses.
     *
     * @param addressesDTO the entity to save.
     * @return the persisted entity.
     */
    public AddressesDTO save(AddressesDTO addressesDTO) {
        log.debug("Request to save Addresses : {}", addressesDTO);
        Addresses addresses = addressesMapper.toEntity(addressesDTO);
        addresses = addressesRepository.save(addresses);
        return addressesMapper.toDto(addresses);
    }

    /**
     * Update a addresses.
     *
     * @param addressesDTO the entity to save.
     * @return the persisted entity.
     */
    public AddressesDTO update(AddressesDTO addressesDTO) {
        log.debug("Request to update Addresses : {}", addressesDTO);
        Addresses addresses = addressesMapper.toEntity(addressesDTO);
        addresses = addressesRepository.save(addresses);
        return addressesMapper.toDto(addresses);
    }

    /**
     * Partially update a addresses.
     *
     * @param addressesDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<AddressesDTO> partialUpdate(AddressesDTO addressesDTO) {
        log.debug("Request to partially update Addresses : {}", addressesDTO);

        return addressesRepository
            .findById(addressesDTO.getId())
            .map(existingAddresses -> {
                addressesMapper.partialUpdate(existingAddresses, addressesDTO);

                return existingAddresses;
            })
            .map(addressesRepository::save)
            .map(addressesMapper::toDto);
    }

    /**
     * Get all the addresses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<AddressesDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Addresses");
        return addressesRepository.findAll(pageable).map(addressesMapper::toDto);
    }

    /**
     * Get one addresses by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<AddressesDTO> findOne(Long id) {
        log.debug("Request to get Addresses : {}", id);
        return addressesRepository.findById(id).map(addressesMapper::toDto);
    }

    /**
     * Delete the addresses by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Addresses : {}", id);
        addressesRepository.deleteById(id);
    }
}
