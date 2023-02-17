package com.venturedive.daraz.service;

import com.venturedive.daraz.domain.Cars;
import com.venturedive.daraz.repository.CarsRepository;
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

    public CarsService(CarsRepository carsRepository) {
        this.carsRepository = carsRepository;
    }

    /**
     * Save a cars.
     *
     * @param cars the entity to save.
     * @return the persisted entity.
     */
    public Cars save(Cars cars) {
        log.debug("Request to save Cars : {}", cars);
        return carsRepository.save(cars);
    }

    /**
     * Update a cars.
     *
     * @param cars the entity to save.
     * @return the persisted entity.
     */
    public Cars update(Cars cars) {
        log.debug("Request to update Cars : {}", cars);
        return carsRepository.save(cars);
    }

    /**
     * Partially update a cars.
     *
     * @param cars the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<Cars> partialUpdate(Cars cars) {
        log.debug("Request to partially update Cars : {}", cars);

        return carsRepository
            .findById(cars.getId())
            .map(existingCars -> {
                if (cars.getCaruid() != null) {
                    existingCars.setCaruid(cars.getCaruid());
                }
                if (cars.getName() != null) {
                    existingCars.setName(cars.getName());
                }

                return existingCars;
            })
            .map(carsRepository::save);
    }

    /**
     * Get all the cars.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<Cars> findAll(Pageable pageable) {
        log.debug("Request to get all Cars");
        return carsRepository.findAll(pageable);
    }

    /**
     * Get one cars by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Cars> findOne(Long id) {
        log.debug("Request to get Cars : {}", id);
        return carsRepository.findById(id);
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
