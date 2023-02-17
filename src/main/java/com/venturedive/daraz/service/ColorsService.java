package com.venturedive.daraz.service;

import com.venturedive.daraz.domain.Colors;
import com.venturedive.daraz.repository.ColorsRepository;
import com.venturedive.daraz.service.dto.ColorsDTO;
import com.venturedive.daraz.service.mapper.ColorsMapper;
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

    private final ColorsMapper colorsMapper;

    public ColorsService(ColorsRepository colorsRepository, ColorsMapper colorsMapper) {
        this.colorsRepository = colorsRepository;
        this.colorsMapper = colorsMapper;
    }

    /**
     * Save a colors.
     *
     * @param colorsDTO the entity to save.
     * @return the persisted entity.
     */
    public ColorsDTO save(ColorsDTO colorsDTO) {
        log.debug("Request to save Colors : {}", colorsDTO);
        Colors colors = colorsMapper.toEntity(colorsDTO);
        colors = colorsRepository.save(colors);
        return colorsMapper.toDto(colors);
    }

    /**
     * Update a colors.
     *
     * @param colorsDTO the entity to save.
     * @return the persisted entity.
     */
    public ColorsDTO update(ColorsDTO colorsDTO) {
        log.debug("Request to update Colors : {}", colorsDTO);
        Colors colors = colorsMapper.toEntity(colorsDTO);
        colors = colorsRepository.save(colors);
        return colorsMapper.toDto(colors);
    }

    /**
     * Partially update a colors.
     *
     * @param colorsDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ColorsDTO> partialUpdate(ColorsDTO colorsDTO) {
        log.debug("Request to partially update Colors : {}", colorsDTO);

        return colorsRepository
            .findById(colorsDTO.getId())
            .map(existingColors -> {
                colorsMapper.partialUpdate(existingColors, colorsDTO);

                return existingColors;
            })
            .map(colorsRepository::save)
            .map(colorsMapper::toDto);
    }

    /**
     * Get all the colors.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ColorsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Colors");
        return colorsRepository.findAll(pageable).map(colorsMapper::toDto);
    }

    /**
     * Get all the colors with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<ColorsDTO> findAllWithEagerRelationships(Pageable pageable) {
        return colorsRepository.findAllWithEagerRelationships(pageable).map(colorsMapper::toDto);
    }

    /**
     * Get one colors by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ColorsDTO> findOne(Long id) {
        log.debug("Request to get Colors : {}", id);
        return colorsRepository.findOneWithEagerRelationships(id).map(colorsMapper::toDto);
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
