package com.venturedive.daraz.web.rest;

import com.venturedive.daraz.repository.ShippingDetailsRepository;
import com.venturedive.daraz.service.ShippingDetailsQueryService;
import com.venturedive.daraz.service.ShippingDetailsService;
import com.venturedive.daraz.service.criteria.ShippingDetailsCriteria;
import com.venturedive.daraz.service.dto.ShippingDetailsDTO;
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
 * REST controller for managing {@link com.venturedive.daraz.domain.ShippingDetails}.
 */
@RestController
@RequestMapping("/api")
public class ShippingDetailsResource {

    private final Logger log = LoggerFactory.getLogger(ShippingDetailsResource.class);

    private static final String ENTITY_NAME = "shippingDetails";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ShippingDetailsService shippingDetailsService;

    private final ShippingDetailsRepository shippingDetailsRepository;

    private final ShippingDetailsQueryService shippingDetailsQueryService;

    public ShippingDetailsResource(
        ShippingDetailsService shippingDetailsService,
        ShippingDetailsRepository shippingDetailsRepository,
        ShippingDetailsQueryService shippingDetailsQueryService
    ) {
        this.shippingDetailsService = shippingDetailsService;
        this.shippingDetailsRepository = shippingDetailsRepository;
        this.shippingDetailsQueryService = shippingDetailsQueryService;
    }

    /**
     * {@code POST  /shipping-details} : Create a new shippingDetails.
     *
     * @param shippingDetailsDTO the shippingDetailsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new shippingDetailsDTO, or with status {@code 400 (Bad Request)} if the shippingDetails has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/shipping-details")
    public ResponseEntity<ShippingDetailsDTO> createShippingDetails(@Valid @RequestBody ShippingDetailsDTO shippingDetailsDTO)
        throws URISyntaxException {
        log.debug("REST request to save ShippingDetails : {}", shippingDetailsDTO);
        if (shippingDetailsDTO.getId() != null) {
            throw new BadRequestAlertException("A new shippingDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ShippingDetailsDTO result = shippingDetailsService.save(shippingDetailsDTO);
        return ResponseEntity
            .created(new URI("/api/shipping-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /shipping-details/:id} : Updates an existing shippingDetails.
     *
     * @param id the id of the shippingDetailsDTO to save.
     * @param shippingDetailsDTO the shippingDetailsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated shippingDetailsDTO,
     * or with status {@code 400 (Bad Request)} if the shippingDetailsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the shippingDetailsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/shipping-details/{id}")
    public ResponseEntity<ShippingDetailsDTO> updateShippingDetails(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody ShippingDetailsDTO shippingDetailsDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ShippingDetails : {}, {}", id, shippingDetailsDTO);
        if (shippingDetailsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, shippingDetailsDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!shippingDetailsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ShippingDetailsDTO result = shippingDetailsService.update(shippingDetailsDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, shippingDetailsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /shipping-details/:id} : Partial updates given fields of an existing shippingDetails, field will ignore if it is null
     *
     * @param id the id of the shippingDetailsDTO to save.
     * @param shippingDetailsDTO the shippingDetailsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated shippingDetailsDTO,
     * or with status {@code 400 (Bad Request)} if the shippingDetailsDTO is not valid,
     * or with status {@code 404 (Not Found)} if the shippingDetailsDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the shippingDetailsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/shipping-details/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ShippingDetailsDTO> partialUpdateShippingDetails(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody ShippingDetailsDTO shippingDetailsDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ShippingDetails partially : {}, {}", id, shippingDetailsDTO);
        if (shippingDetailsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, shippingDetailsDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!shippingDetailsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ShippingDetailsDTO> result = shippingDetailsService.partialUpdate(shippingDetailsDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, shippingDetailsDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /shipping-details} : get all the shippingDetails.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of shippingDetails in body.
     */
    @GetMapping("/shipping-details")
    public ResponseEntity<List<ShippingDetailsDTO>> getAllShippingDetails(
        ShippingDetailsCriteria criteria,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get ShippingDetails by criteria: {}", criteria);
        Page<ShippingDetailsDTO> page = shippingDetailsQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /shipping-details/count} : count all the shippingDetails.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/shipping-details/count")
    public ResponseEntity<Long> countShippingDetails(ShippingDetailsCriteria criteria) {
        log.debug("REST request to count ShippingDetails by criteria: {}", criteria);
        return ResponseEntity.ok().body(shippingDetailsQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /shipping-details/:id} : get the "id" shippingDetails.
     *
     * @param id the id of the shippingDetailsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the shippingDetailsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/shipping-details/{id}")
    public ResponseEntity<ShippingDetailsDTO> getShippingDetails(@PathVariable Long id) {
        log.debug("REST request to get ShippingDetails : {}", id);
        Optional<ShippingDetailsDTO> shippingDetailsDTO = shippingDetailsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(shippingDetailsDTO);
    }

    /**
     * {@code DELETE  /shipping-details/:id} : delete the "id" shippingDetails.
     *
     * @param id the id of the shippingDetailsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/shipping-details/{id}")
    public ResponseEntity<Void> deleteShippingDetails(@PathVariable Long id) {
        log.debug("REST request to delete ShippingDetails : {}", id);
        shippingDetailsService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
