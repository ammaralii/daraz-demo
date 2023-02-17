package com.venturedive.daraz.service;

import com.venturedive.daraz.domain.DarazUsers;
import com.venturedive.daraz.repository.DarazUsersRepository;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link DarazUsers}.
 */
@Service
@Transactional
public class DarazUsersService {

    private final Logger log = LoggerFactory.getLogger(DarazUsersService.class);

    private final DarazUsersRepository darazUsersRepository;

    public DarazUsersService(DarazUsersRepository darazUsersRepository) {
        this.darazUsersRepository = darazUsersRepository;
    }

    /**
     * Save a darazUsers.
     *
     * @param darazUsers the entity to save.
     * @return the persisted entity.
     */
    public DarazUsers save(DarazUsers darazUsers) {
        log.debug("Request to save DarazUsers : {}", darazUsers);
        return darazUsersRepository.save(darazUsers);
    }

    /**
     * Update a darazUsers.
     *
     * @param darazUsers the entity to save.
     * @return the persisted entity.
     */
    public DarazUsers update(DarazUsers darazUsers) {
        log.debug("Request to update DarazUsers : {}", darazUsers);
        return darazUsersRepository.save(darazUsers);
    }

    /**
     * Partially update a darazUsers.
     *
     * @param darazUsers the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<DarazUsers> partialUpdate(DarazUsers darazUsers) {
        log.debug("Request to partially update DarazUsers : {}", darazUsers);

        return darazUsersRepository
            .findById(darazUsers.getId())
            .map(existingDarazUsers -> {
                if (darazUsers.getFullName() != null) {
                    existingDarazUsers.setFullName(darazUsers.getFullName());
                }
                if (darazUsers.getEmail() != null) {
                    existingDarazUsers.setEmail(darazUsers.getEmail());
                }
                if (darazUsers.getPhone() != null) {
                    existingDarazUsers.setPhone(darazUsers.getPhone());
                }

                return existingDarazUsers;
            })
            .map(darazUsersRepository::save);
    }

    /**
     * Get all the darazUsers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<DarazUsers> findAll(Pageable pageable) {
        log.debug("Request to get all DarazUsers");
        return darazUsersRepository.findAll(pageable);
    }

    /**
     * Get one darazUsers by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DarazUsers> findOne(Long id) {
        log.debug("Request to get DarazUsers : {}", id);
        return darazUsersRepository.findById(id);
    }

    /**
     * Delete the darazUsers by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete DarazUsers : {}", id);
        darazUsersRepository.deleteById(id);
    }
}
