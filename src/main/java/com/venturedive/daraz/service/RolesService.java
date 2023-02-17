package com.venturedive.daraz.service;

import com.venturedive.daraz.domain.Roles;
import com.venturedive.daraz.repository.RolesRepository;
import com.venturedive.daraz.service.dto.RolesDTO;
import com.venturedive.daraz.service.mapper.RolesMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Roles}.
 */
@Service
@Transactional
public class RolesService {

    private final Logger log = LoggerFactory.getLogger(RolesService.class);

    private final RolesRepository rolesRepository;

    private final RolesMapper rolesMapper;

    public RolesService(RolesRepository rolesRepository, RolesMapper rolesMapper) {
        this.rolesRepository = rolesRepository;
        this.rolesMapper = rolesMapper;
    }

    /**
     * Save a roles.
     *
     * @param rolesDTO the entity to save.
     * @return the persisted entity.
     */
    public RolesDTO save(RolesDTO rolesDTO) {
        log.debug("Request to save Roles : {}", rolesDTO);
        Roles roles = rolesMapper.toEntity(rolesDTO);
        roles = rolesRepository.save(roles);
        return rolesMapper.toDto(roles);
    }

    /**
     * Update a roles.
     *
     * @param rolesDTO the entity to save.
     * @return the persisted entity.
     */
    public RolesDTO update(RolesDTO rolesDTO) {
        log.debug("Request to update Roles : {}", rolesDTO);
        Roles roles = rolesMapper.toEntity(rolesDTO);
        roles = rolesRepository.save(roles);
        return rolesMapper.toDto(roles);
    }

    /**
     * Partially update a roles.
     *
     * @param rolesDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<RolesDTO> partialUpdate(RolesDTO rolesDTO) {
        log.debug("Request to partially update Roles : {}", rolesDTO);

        return rolesRepository
            .findById(rolesDTO.getId())
            .map(existingRoles -> {
                rolesMapper.partialUpdate(existingRoles, rolesDTO);

                return existingRoles;
            })
            .map(rolesRepository::save)
            .map(rolesMapper::toDto);
    }

    /**
     * Get all the roles.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<RolesDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Roles");
        return rolesRepository.findAll(pageable).map(rolesMapper::toDto);
    }

    /**
     * Get all the roles with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<RolesDTO> findAllWithEagerRelationships(Pageable pageable) {
        return rolesRepository.findAllWithEagerRelationships(pageable).map(rolesMapper::toDto);
    }

    /**
     * Get one roles by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<RolesDTO> findOne(Long id) {
        log.debug("Request to get Roles : {}", id);
        return rolesRepository.findOneWithEagerRelationships(id).map(rolesMapper::toDto);
    }

    /**
     * Delete the roles by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Roles : {}", id);
        rolesRepository.deleteById(id);
    }
}
