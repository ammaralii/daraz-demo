package com.venturedive.daraz.service;

import com.venturedive.daraz.domain.Customers;
import com.venturedive.daraz.repository.CustomersRepository;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Customers}.
 */
@Service
@Transactional
public class CustomersService {

    private final Logger log = LoggerFactory.getLogger(CustomersService.class);

    private final CustomersRepository customersRepository;

    public CustomersService(CustomersRepository customersRepository) {
        this.customersRepository = customersRepository;
    }

    /**
     * Save a customers.
     *
     * @param customers the entity to save.
     * @return the persisted entity.
     */
    public Customers save(Customers customers) {
        log.debug("Request to save Customers : {}", customers);
        return customersRepository.save(customers);
    }

    /**
     * Update a customers.
     *
     * @param customers the entity to save.
     * @return the persisted entity.
     */
    public Customers update(Customers customers) {
        log.debug("Request to update Customers : {}", customers);
        return customersRepository.save(customers);
    }

    /**
     * Partially update a customers.
     *
     * @param customers the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<Customers> partialUpdate(Customers customers) {
        log.debug("Request to partially update Customers : {}", customers);

        return customersRepository
            .findById(customers.getId())
            .map(existingCustomers -> {
                if (customers.getFullName() != null) {
                    existingCustomers.setFullName(customers.getFullName());
                }
                if (customers.getEmail() != null) {
                    existingCustomers.setEmail(customers.getEmail());
                }
                if (customers.getPhone() != null) {
                    existingCustomers.setPhone(customers.getPhone());
                }

                return existingCustomers;
            })
            .map(customersRepository::save);
    }

    /**
     * Get all the customers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<Customers> findAll(Pageable pageable) {
        log.debug("Request to get all Customers");
        return customersRepository.findAll(pageable);
    }

    /**
     * Get one customers by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Customers> findOne(Long id) {
        log.debug("Request to get Customers : {}", id);
        return customersRepository.findById(id);
    }

    /**
     * Delete the customers by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Customers : {}", id);
        customersRepository.deleteById(id);
    }
}
