package com.venturedive.daraz.service;

import com.venturedive.daraz.domain.Cars;
import com.venturedive.daraz.repository.CarsRepository;
import com.venturedive.daraz.service.dto.CarsDTO;
import com.venturedive.daraz.service.mapper.CarsMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Cars}.
 */
@Service
@Transactional
public class CarsService {

    private final Logger log = LoggerFactory.getLogger(CarsService.class);

    private final CarsRepository carsRepository;

    private final CarsMapper carsMapper;

    public CarsService(CarsRepository carsRepository, CarsMapper carsMapper) {
        this.carsRepository = carsRepository;
        this.carsMapper = carsMapper;
    }

    /**
     * Save a cars.
     *
     * @param carsDTO the entity to save.
     * @return the persisted entity.
     */
    public CarsDTO save(CarsDTO carsDTO) {
        log.debug("Request to save Cars : {}", carsDTO);
        Cars cars = carsMapper.toEntity(carsDTO);
        cars = carsRepository.save(cars);
        return carsMapper.toDto(cars);
    }

    /**
     * Update a cars.
     *
     * @param carsDTO the entity to save.
     * @return the persisted entity.
     */
    public CarsDTO update(CarsDTO carsDTO) {
        log.debug("Request to update Cars : {}", carsDTO);
        Cars cars = carsMapper.toEntity(carsDTO);
        cars = carsRepository.save(cars);
        return carsMapper.toDto(cars);
    }

    /**
     * Partially update a cars.
     *
     * @param carsDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<CarsDTO> partialUpdate(CarsDTO carsDTO) {
        log.debug("Request to partially update Cars : {}", carsDTO);

        return carsRepository
            .findById(carsDTO.getId())
            .map(existingCars -> {
                carsMapper.partialUpdate(existingCars, carsDTO);

                return existingCars;
            })
            .map(carsRepository::save)
            .map(carsMapper::toDto);
    }

    /**
     * Get all the cars.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CarsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Cars");
        return carsRepository.findAll(pageable).map(carsMapper::toDto);
    }

    /**
     * Get one cars by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CarsDTO> findOne(Long id) {
        log.debug("Request to get Cars : {}", id);
        return carsRepository.findById(id).map(carsMapper::toDto);
    }

    /**
     * Delete the cars by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Cars : {}", id);
        carsRepository.deleteById(id);
    }
}
