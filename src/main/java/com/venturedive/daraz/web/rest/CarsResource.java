package com.venturedive.daraz.web.rest;

import com.venturedive.daraz.repository.CarsRepository;
import com.venturedive.daraz.service.CarsQueryService;
import com.venturedive.daraz.service.CarsService;
import com.venturedive.daraz.service.criteria.CarsCriteria;
import com.venturedive.daraz.service.dto.CarsDTO;
import com.venturedive.daraz.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.venturedive.daraz.domain.Cars}.
 */
@RestController
@RequestMapping("/api")
public class CarsResource {

    private final Logger log = LoggerFactory.getLogger(CarsResource.class);

    private static final String ENTITY_NAME = "cars";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CarsService carsService;

    private final CarsRepository carsRepository;

    private final CarsQueryService carsQueryService;

    public CarsResource(CarsService carsService, CarsRepository carsRepository, CarsQueryService carsQueryService) {
        this.carsService = carsService;
        this.carsRepository = carsRepository;
        this.carsQueryService = carsQueryService;
    }

    /**
     * {@code POST  /cars} : Create a new cars.
     *
     * @param carsDTO the carsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new carsDTO, or with status {@code 400 (Bad Request)} if the cars has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/cars")
    public ResponseEntity<CarsDTO> createCars(@Valid @RequestBody CarsDTO carsDTO) throws URISyntaxException {
        log.debug("REST request to save Cars : {}", carsDTO);
        if (carsDTO.getId() != null) {
            throw new BadRequestAlertException("A new cars cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CarsDTO result = carsService.save(carsDTO);
        return ResponseEntity
            .created(new URI("/api/cars/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /cars/:id} : Updates an existing cars.
     *
     * @param id the id of the carsDTO to save.
     * @param carsDTO the carsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated carsDTO,
     * or with status {@code 400 (Bad Request)} if the carsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the carsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/cars/{id}")
    public ResponseEntity<CarsDTO> updateCars(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody CarsDTO carsDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Cars : {}, {}", id, carsDTO);
        if (carsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, carsDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!carsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        CarsDTO result = carsService.update(carsDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, carsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /cars/:id} : Partial updates given fields of an existing cars, field will ignore if it is null
     *
     * @param id the id of the carsDTO to save.
     * @param carsDTO the carsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated carsDTO,
     * or with status {@code 400 (Bad Request)} if the carsDTO is not valid,
     * or with status {@code 404 (Not Found)} if the carsDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the carsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/cars/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<CarsDTO> partialUpdateCars(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody CarsDTO carsDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Cars partially : {}, {}", id, carsDTO);
        if (carsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, carsDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!carsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<CarsDTO> result = carsService.partialUpdate(carsDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, carsDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /cars} : get all the cars.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cars in body.
     */
    @GetMapping("/cars")
    public ResponseEntity<List<CarsDTO>> getAllCars(
        CarsCriteria criteria,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get Cars by criteria: {}", criteria);
        Page<CarsDTO> page = carsQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /cars/count} : count all the cars.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/cars/count")
    public ResponseEntity<Long> countCars(CarsCriteria criteria) {
        log.debug("REST request to count Cars by criteria: {}", criteria);
        return ResponseEntity.ok().body(carsQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /cars/:id} : get the "id" cars.
     *
     * @param id the id of the carsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the carsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/cars/{id}")
    public ResponseEntity<CarsDTO> getCars(@PathVariable Long id) {
        log.debug("REST request to get Cars : {}", id);
        Optional<CarsDTO> carsDTO = carsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(carsDTO);
    }

    /**
     * {@code DELETE  /cars/:id} : delete the "id" cars.
     *
     * @param id the id of the carsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/cars/{id}")
    public ResponseEntity<Void> deleteCars(@PathVariable Long id) {
        log.debug("REST request to delete Cars : {}", id);
        carsService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
