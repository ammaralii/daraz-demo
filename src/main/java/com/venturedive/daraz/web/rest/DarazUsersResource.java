package com.venturedive.daraz.web.rest;

import com.venturedive.daraz.repository.DarazUsersRepository;
import com.venturedive.daraz.service.DarazUsersQueryService;
import com.venturedive.daraz.service.DarazUsersService;
import com.venturedive.daraz.service.criteria.DarazUsersCriteria;
import com.venturedive.daraz.service.dto.DarazUsersDTO;
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
 * REST controller for managing {@link com.venturedive.daraz.domain.DarazUsers}.
 */
@RestController
@RequestMapping("/api")
public class DarazUsersResource {

    private final Logger log = LoggerFactory.getLogger(DarazUsersResource.class);

    private static final String ENTITY_NAME = "darazUsers";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DarazUsersService darazUsersService;

    private final DarazUsersRepository darazUsersRepository;

    private final DarazUsersQueryService darazUsersQueryService;

    public DarazUsersResource(
        DarazUsersService darazUsersService,
        DarazUsersRepository darazUsersRepository,
        DarazUsersQueryService darazUsersQueryService
    ) {
        this.darazUsersService = darazUsersService;
        this.darazUsersRepository = darazUsersRepository;
        this.darazUsersQueryService = darazUsersQueryService;
    }

    /**
     * {@code POST  /daraz-users} : Create a new darazUsers.
     *
     * @param darazUsersDTO the darazUsersDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new darazUsersDTO, or with status {@code 400 (Bad Request)} if the darazUsers has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/daraz-users")
    public ResponseEntity<DarazUsersDTO> createDarazUsers(@Valid @RequestBody DarazUsersDTO darazUsersDTO) throws URISyntaxException {
        log.debug("REST request to save DarazUsers : {}", darazUsersDTO);
        if (darazUsersDTO.getId() != null) {
            throw new BadRequestAlertException("A new darazUsers cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DarazUsersDTO result = darazUsersService.save(darazUsersDTO);
        return ResponseEntity
            .created(new URI("/api/daraz-users/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /daraz-users/:id} : Updates an existing darazUsers.
     *
     * @param id the id of the darazUsersDTO to save.
     * @param darazUsersDTO the darazUsersDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated darazUsersDTO,
     * or with status {@code 400 (Bad Request)} if the darazUsersDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the darazUsersDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/daraz-users/{id}")
    public ResponseEntity<DarazUsersDTO> updateDarazUsers(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody DarazUsersDTO darazUsersDTO
    ) throws URISyntaxException {
        log.debug("REST request to update DarazUsers : {}, {}", id, darazUsersDTO);
        if (darazUsersDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, darazUsersDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!darazUsersRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        DarazUsersDTO result = darazUsersService.update(darazUsersDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, darazUsersDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /daraz-users/:id} : Partial updates given fields of an existing darazUsers, field will ignore if it is null
     *
     * @param id the id of the darazUsersDTO to save.
     * @param darazUsersDTO the darazUsersDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated darazUsersDTO,
     * or with status {@code 400 (Bad Request)} if the darazUsersDTO is not valid,
     * or with status {@code 404 (Not Found)} if the darazUsersDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the darazUsersDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/daraz-users/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DarazUsersDTO> partialUpdateDarazUsers(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody DarazUsersDTO darazUsersDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update DarazUsers partially : {}, {}", id, darazUsersDTO);
        if (darazUsersDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, darazUsersDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!darazUsersRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DarazUsersDTO> result = darazUsersService.partialUpdate(darazUsersDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, darazUsersDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /daraz-users} : get all the darazUsers.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of darazUsers in body.
     */
    @GetMapping("/daraz-users")
    public ResponseEntity<List<DarazUsersDTO>> getAllDarazUsers(
        DarazUsersCriteria criteria,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get DarazUsers by criteria: {}", criteria);
        Page<DarazUsersDTO> page = darazUsersQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /daraz-users/count} : count all the darazUsers.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/daraz-users/count")
    public ResponseEntity<Long> countDarazUsers(DarazUsersCriteria criteria) {
        log.debug("REST request to count DarazUsers by criteria: {}", criteria);
        return ResponseEntity.ok().body(darazUsersQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /daraz-users/:id} : get the "id" darazUsers.
     *
     * @param id the id of the darazUsersDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the darazUsersDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/daraz-users/{id}")
    public ResponseEntity<DarazUsersDTO> getDarazUsers(@PathVariable Long id) {
        log.debug("REST request to get DarazUsers : {}", id);
        Optional<DarazUsersDTO> darazUsersDTO = darazUsersService.findOne(id);
        return ResponseUtil.wrapOrNotFound(darazUsersDTO);
    }

    /**
     * {@code DELETE  /daraz-users/:id} : delete the "id" darazUsers.
     *
     * @param id the id of the darazUsersDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/daraz-users/{id}")
    public ResponseEntity<Void> deleteDarazUsers(@PathVariable Long id) {
        log.debug("REST request to delete DarazUsers : {}", id);
        darazUsersService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
