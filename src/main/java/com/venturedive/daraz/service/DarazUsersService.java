package com.venturedive.daraz.service;

import com.venturedive.daraz.domain.DarazUsers;
import com.venturedive.daraz.repository.DarazUsersRepository;
import com.venturedive.daraz.service.dto.DarazUsersDTO;
import com.venturedive.daraz.service.mapper.DarazUsersMapper;
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

    private final DarazUsersMapper darazUsersMapper;

    public DarazUsersService(DarazUsersRepository darazUsersRepository, DarazUsersMapper darazUsersMapper) {
        this.darazUsersRepository = darazUsersRepository;
        this.darazUsersMapper = darazUsersMapper;
    }

    /**
     * Save a darazUsers.
     *
     * @param darazUsersDTO the entity to save.
     * @return the persisted entity.
     */
    public DarazUsersDTO save(DarazUsersDTO darazUsersDTO) {
        log.debug("Request to save DarazUsers : {}", darazUsersDTO);
        DarazUsers darazUsers = darazUsersMapper.toEntity(darazUsersDTO);
        darazUsers = darazUsersRepository.save(darazUsers);
        return darazUsersMapper.toDto(darazUsers);
    }

    /**
     * Update a darazUsers.
     *
     * @param darazUsersDTO the entity to save.
     * @return the persisted entity.
     */
    public DarazUsersDTO update(DarazUsersDTO darazUsersDTO) {
        log.debug("Request to update DarazUsers : {}", darazUsersDTO);
        DarazUsers darazUsers = darazUsersMapper.toEntity(darazUsersDTO);
        darazUsers = darazUsersRepository.save(darazUsers);
        return darazUsersMapper.toDto(darazUsers);
    }

    /**
     * Partially update a darazUsers.
     *
     * @param darazUsersDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<DarazUsersDTO> partialUpdate(DarazUsersDTO darazUsersDTO) {
        log.debug("Request to partially update DarazUsers : {}", darazUsersDTO);

        return darazUsersRepository
            .findById(darazUsersDTO.getId())
            .map(existingDarazUsers -> {
                darazUsersMapper.partialUpdate(existingDarazUsers, darazUsersDTO);

                return existingDarazUsers;
            })
            .map(darazUsersRepository::save)
            .map(darazUsersMapper::toDto);
    }

    /**
     * Get all the darazUsers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<DarazUsersDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DarazUsers");
        return darazUsersRepository.findAll(pageable).map(darazUsersMapper::toDto);
    }

    /**
     * Get one darazUsers by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DarazUsersDTO> findOne(Long id) {
        log.debug("Request to get DarazUsers : {}", id);
        return darazUsersRepository.findById(id).map(darazUsersMapper::toDto);
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
