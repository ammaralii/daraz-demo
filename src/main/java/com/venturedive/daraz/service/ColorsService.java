package com.venturedive.daraz.service;

import com.venturedive.daraz.domain.Colors;
import com.venturedive.daraz.repository.ColorsRepository;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Colors}.
 */
@Service
@Transactional
public class ColorsService {

    private final Logger log = LoggerFactory.getLogger(ColorsService.class);

    private final ColorsRepository colorsRepository;

    public ColorsService(ColorsRepository colorsRepository) {
        this.colorsRepository = colorsRepository;
    }

    /**
     * Save a colors.
     *
     * @param colors the entity to save.
     * @return the persisted entity.
     */
    public Colors save(Colors colors) {
        log.debug("Request to save Colors : {}", colors);
        return colorsRepository.save(colors);
    }

    /**
     * Update a colors.
     *
     * @param colors the entity to save.
     * @return the persisted entity.
     */
    public Colors update(Colors colors) {
        log.debug("Request to update Colors : {}", colors);
        return colorsRepository.save(colors);
    }

    /**
     * Partially update a colors.
     *
     * @param colors the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<Colors> partialUpdate(Colors colors) {
        log.debug("Request to partially update Colors : {}", colors);

        return colorsRepository
            .findById(colors.getId())
            .map(existingColors -> {
                if (colors.getColoruid() != null) {
                    existingColors.setColoruid(colors.getColoruid());
                }
                if (colors.getName() != null) {
                    existingColors.setName(colors.getName());
                }

                return existingColors;
            })
            .map(colorsRepository::save);
    }

    /**
     * Get all the colors.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<Colors> findAll(Pageable pageable) {
        log.debug("Request to get all Colors");
        return colorsRepository.findAll(pageable);
    }

    /**
     * Get all the colors with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<Colors> findAllWithEagerRelationships(Pageable pageable) {
        return colorsRepository.findAllWithEagerRelationships(pageable);
    }

    /**
     * Get one colors by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Colors> findOne(Long id) {
        log.debug("Request to get Colors : {}", id);
        return colorsRepository.findOneWithEagerRelationships(id);
    }

    /**
     * Delete the colors by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Colors : {}", id);
        colorsRepository.deleteById(id);
    }
}
